#!/usr/bin/env python3
"""
Generate performance benchmark charts as PNG files comparing
CustomTreeSet V1, CustomTreeSet V2 and JDK TreeSet
with a transparent background.

Expects wide-format CSVs (one row per size, columns = operations):
  Size;add(E);addAll(Collection);...
"""

import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import matplotlib.ticker as mticker
from matplotlib.lines import Line2D
import pandas as pd
import os
import sys
import re
import numpy as np

# ──────────────────────────────────────────────────────────────────────────────
# Configuration
# ──────────────────────────────────────────────────────────────────────────────

V1_CSV   = "CustomTreeSetV1_jmh_performance.csv"
V2_CSV   = "CustomTreeSetV2_jmh_performance.csv"
JDK_CSV  = "TreeSet_jmh_performance.csv"
OUTPUT_DIR = "../charts"

COLORS = {
    'v1':   '#9B6EF3',   # purple  – CustomTreeSet V1
    'v2':   '#FF9F43',   # orange  – CustomTreeSet V2
    'jdk':  '#4DA6FF',   # blue    – JDK TreeSet
    'grid': '#252525',
}

FIGURE_SIZE = (12, 6.2)
DPI = 150


def safe_filename(label: str) -> str:
    s = re.sub(r'[<>:"/\\|?*]', '', label)
    s = re.sub(r'\s+', '_', s)
    s = s.replace('(', '_').replace(')', '').replace(',', '').replace('.', '_')
    s = re.sub(r'_+', '_', s).strip('_')
    return s or 'chart'


def load_csv(filepath):
    """
    Load wide-format JMH CSV.
    Returns: dict[size][op_label] = score
    """
    df = pd.read_csv(filepath, sep=';')
    cols = {c: c.strip() for c in df.columns}
    df = df.rename(columns=cols)

    if 'Size' not in df.columns:
        raise ValueError(f"No 'Size' column in {filepath}: {list(df.columns)}")

    df['Size'] = pd.to_numeric(df['Size'], errors='coerce')
    df = df.dropna(subset=['Size'])

    data = {}
    op_cols = [c for c in df.columns if c != 'Size']
    for _, row in df.iterrows():
        size = int(row['Size'])
        data[size] = {}
        for op in op_cols:
            val = pd.to_numeric(row[op], errors='coerce')
            if pd.notna(val):
                data[size][op] = float(val)
    return data


def format_y_axis(value, pos):
    if value == 0:
        return '0'
    if abs(value) >= 1_000_000:
        return f'{value/1_000_000:,.2f}M'
    if abs(value) >= 1_000:
        return f'{value/1_000:,.2f}k'
    return f'{value:,.2f}'


def create_chart(op_label, v1_data, v2_data, jdk_data, canonical_sizes, output_path):
    def series(data):
        return [
            data[s][op_label] if s in data and op_label in data[s] else np.nan
            for s in canonical_sizes
        ]

    v1_vals  = series(v1_data)
    v2_vals  = series(v2_data)
    jdk_vals = series(jdk_data)

    # skip if all three series are entirely missing
    if (all(np.isnan(v1_vals)) and
        all(np.isnan(v2_vals)) and
        all(np.isnan(jdk_vals))):
        return False

    fig, ax = plt.subplots(figsize=FIGURE_SIZE, dpi=DPI)
    fig.patch.set_alpha(0)
    ax.set_facecolor('none')

    x = list(range(len(canonical_sizes)))

    # lines
    ax.plot(x, v1_vals,  color=COLORS['v1'],  linewidth=1.5, zorder=2)
    ax.plot(x, v2_vals,  color=COLORS['v2'],  linewidth=1.5, zorder=2)
    ax.plot(x, jdk_vals, color=COLORS['jdk'], linewidth=1.5, zorder=2)

    # markers
    ax.scatter(x, v1_vals,  color=COLORS['v1'],  s=35, marker='o',
               edgecolors=COLORS['v1'],  linewidths=1.5, zorder=3)
    ax.scatter(x, v2_vals,  color=COLORS['v2'],  s=35, marker='o',
               edgecolors=COLORS['v2'],  linewidths=1.5, zorder=3)
    ax.scatter(x, jdk_vals, color=COLORS['jdk'], s=35, marker='o',
               edgecolors=COLORS['jdk'], linewidths=1.5, zorder=3)

    ax.grid(True, color=COLORS['grid'], linewidth=0.8, linestyle='-', zorder=0)
    ax.set_axisbelow(True)

    ax.set_xticks(x)
    ax.set_xticklabels([f'{s:,}' for s in canonical_sizes], color='white', fontsize=10)
    ax.tick_params(axis='x', colors='white', length=0, pad=8)
    ax.set_xlim(-0.4, len(canonical_sizes) - 0.6)

    ax.yaxis.set_major_formatter(mticker.FuncFormatter(format_y_axis))
    ax.tick_params(axis='y', colors='white', length=0, pad=8)
    for label in ax.get_yticklabels():
        label.set_color('white')
        label.set_fontsize(10)

    for spine in ax.spines.values():
        spine.set_visible(False)

    ax.set_xlabel('Size', color='white', fontsize=12, labelpad=12)
    ax.set_ylabel('Time (ns/op)', color='white', fontsize=11, labelpad=10)
    ax.set_title(op_label, color='white', fontsize=15, fontweight='bold', pad=14)

    legend_elements = [
        Line2D([0], [0], marker='o', color='none',
               markerfacecolor=COLORS['v1'], markeredgecolor=COLORS['v1'],
               markeredgewidth=1.5, markersize=8, label='V1', linestyle='none'),
        Line2D([0], [0], marker='o', color='none',
               markerfacecolor=COLORS['v2'], markeredgecolor=COLORS['v2'],
               markeredgewidth=1.5, markersize=8, label='V2', linestyle='none'),
        Line2D([0], [0], marker='o', color='none',
               markerfacecolor=COLORS['jdk'], markeredgecolor=COLORS['jdk'],
               markeredgewidth=1.5, markersize=8, label='JDK', linestyle='none'),
    ]

    leg = ax.legend(handles=legend_elements, loc='upper center',
                    bbox_to_anchor=(0.5, -0.26), ncol=3, frameon=False,
                    fontsize=12, handlelength=1.5, handletextpad=0.6,
                    columnspacing=2.0)
    for text in leg.get_texts():
        text.set_color('white')
        text.set_fontsize(12)

    plt.tight_layout(rect=[0, 0.18, 1, 1])
    fig.savefig(output_path, dpi=DPI, transparent=True, bbox_inches='tight',
                facecolor='none', edgecolor='none')
    plt.close(fig)
    return True


def main():
    for path in (V1_CSV, V2_CSV, JDK_CSV):
        if not os.path.exists(path):
            print(f"Error: Required file '{path}' not found.")
            sys.exit(1)

    os.makedirs(OUTPUT_DIR, exist_ok=True)

    v1_data  = load_csv(V1_CSV)
    v2_data  = load_csv(V2_CSV)
    jdk_data = load_csv(JDK_CSV)

    canonical_sizes = sorted(
        set(v1_data.keys()) | set(v2_data.keys()) | set(jdk_data.keys())
    )

    ops = set()
    for s in canonical_sizes:
        ops |= set(v1_data.get(s, {}).keys())
        ops |= set(v2_data.get(s, {}).keys())
        ops |= set(jdk_data.get(s, {}).keys())

    generated = 0
    for op in sorted(ops):
        out = os.path.join(OUTPUT_DIR, f'{safe_filename(op)}.png')
        if create_chart(op, v1_data, v2_data, jdk_data, canonical_sizes, out):
            print(f"  ✓ {op}  →  {out}")
            generated += 1

    print(f"\n✓ {generated} comparison charts saved under '{OUTPUT_DIR}/'")


if __name__ == '__main__':
    main()
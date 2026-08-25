import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import gmean

# Load wide-format CSVs (Size;op1;op2;...)
v1_df = pd.read_csv('CustomTreeSetV1_jmh_performance.csv', sep=';')
v2_df = pd.read_csv('CustomTreeSetV2_jmh_performance.csv', sep=';')

# Keep only common sizes and sort
common_sizes = set(v1_df['Size']).intersection(set(v2_df['Size']))
v1_df = (
    v1_df[v1_df['Size'].isin(common_sizes)]
    .sort_values('Size')
    .reset_index(drop=True)
)
v2_df = (
    v2_df[v2_df['Size'].isin(common_sizes)]
    .sort_values('Size')
    .reset_index(drop=True)
)

methods = [col for col in v1_df.columns if col != 'Size']

# Avoid division-by-zero / log-of-zero in geometric mean
v1_df_fixed = v1_df.copy()
v2_df_fixed = v2_df.copy()
for col in methods:
    v1_df_fixed[col] = v1_df_fixed[col].replace(0, 1)
    v2_df_fixed[col] = v2_df_fixed[col].replace(0, 1)

ratios = []
labels = []
colors = []

v1_win_color = '#4DA6FF'  # Blue → V1 faster (Left)
v2_win_color = '#FF4D4D'  # Red  → V2 faster (Right)

for m in methods:
    g_v1 = gmean(v1_df_fixed[m])
    g_v2 = gmean(v2_df_fixed[m])

    if g_v2 < g_v1:              # V2 faster → Right side (positive ratio)
        speedup = g_v1 / g_v2
        ratios.append(speedup - 1)
        colors.append(v2_win_color)
    else:                         # V1 faster → Left side (negative ratio)
        speedup = g_v2 / g_v1
        ratios.append(-(speedup - 1))
        colors.append(v1_win_color)
    labels.append(m)

# Sort so the largest advantages appear at the extremes
sorted_indices = np.argsort(ratios)
sorted_ratios = [ratios[idx] for idx in sorted_indices]
sorted_labels = [labels[idx] for idx in sorted_indices]
sorted_colors = [colors[idx] for idx in sorted_indices]

min_ratio = min(sorted_ratios)
max_ratio = max(sorted_ratios)

# Margin for labels to fit cleanly
margin = 0.5
left_limit = min_ratio - margin
right_limit = max_ratio + margin

fig_height = max(6, len(methods) * 0.45)
fig, ax = plt.subplots(figsize=(12, fig_height), facecolor='none')
ax.set_facecolor('none')

bars = ax.barh(
    range(len(sorted_labels)),
    sorted_ratios,
    color=sorted_colors,
    alpha=0.9,
    height=0.6,
)

ax.axvline(x=0, color='#ffffff', linewidth=1.2)
ax.set_xlim(left_limit, right_limit)

# Ticks
ticks = [0.0]
for t in [-5.0, -4.0, -3.0, -2.0, -1.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0]:
    if left_limit <= t <= right_limit:
        ticks.append(t)
ticks = sorted(set(ticks))

ax.set_xticks(ticks)
ax.set_xticklabels(
    [f'{abs(t)+1:.1f}x' if abs(t) > 0.05 else 'Tie' for t in ticks],
    color='#ffffff',
    fontsize=11,
)

ax.set_ylim(-0.5, len(methods) - 0.5)
ax.set_yticks(range(len(sorted_labels)))
ax.set_yticklabels(sorted_labels, color='#ffffff', fontsize=10)

# Exact numeric speedup next to each bar
for idx, r in enumerate(sorted_ratios):
    val = abs(r)
    factor = val + 1
    text_str = f'{factor:.2f}x'

    if r >= 0:
        ax.text(
            r + 0.05, idx, text_str,
            va='center', ha='left',
            color='#ffffff', fontsize=9, fontweight='bold',
        )
    else:
        ax.text(
            r - 0.05, idx, text_str,
            va='center', ha='right',
            color='#ffffff', fontsize=9, fontweight='bold',
        )

ax.set_title(
    'Overall Relative Performance Comparison (V1 vs V2)\n'
    '(Geometric Mean Across All Sizes)',
    fontsize=14, fontweight='bold', pad=15, color='#ffffff',
)

ax.set_xlabel(
    '← V1 Faster | Relative Speedup Factor | V2 Faster →',
    fontsize=12, labelpad=10, color='#ffffff',
)

ax.grid(True, axis='x', linestyle='--', alpha=0.3, color='#888888')
ax.tick_params(colors='#ffffff', which='both', length=0)

for spine in ax.spines.values():
    spine.set_edgecolor('#555555')

plt.tight_layout()
plt.savefig('geometric.png', dpi=300, transparent=True)
plt.close()
package customtreeset;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeMap;

public class CustomTreeSet<E extends Comparable<? super E>> extends AbstractSet<E> implements NavigableSet<E> {

    private final Comparator<? super E> comparator;
    private final NavigableMap<E, Object> set;

    private transient NavigableSet<E> descendingSet;

    private static final Object PRESENT = new Object();

    public CustomTreeSet() {
        this.comparator = null;
        set = new TreeMap<>();
    }

    public CustomTreeSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
        set = new TreeMap<>(comparator);
    }

    public CustomTreeSet(Collection<? extends E> c) {
        this.comparator = null;
        set = new TreeMap<>();
        addAll(c);
    }

    public CustomTreeSet(SortedSet<E> s) {
        this.comparator = s.comparator();
        set = new TreeMap<>(this.comparator);
        addAll(s);
    }

    private CustomTreeSet(NavigableMap<E, Object> set) {
        this.comparator = set.comparator();
        this.set = set;
    }

    public boolean add(E item) {
        return set.put(item, PRESENT) == null;
    }

    public E ceiling(E item) {
        Map.Entry<E, Object> entry = set.ceilingEntry(item);
        return entry == null ? null : entry.getKey();
    }

    public void clear() {
        set.clear();
    }

    public Comparator<? super E> comparator() {
        return set.comparator();
    }

    public boolean contains(Object o) {
        return set.containsKey(o);
    }

    public Iterator<E> descendingIterator() {
        return set.descendingKeySet().iterator();
    }

    public NavigableSet<E> descendingSet() {
        if (descendingSet == null) {
            CustomTreeSet<E> d = new CustomTreeSet<>(set.descendingMap());
            d.descendingSet = this;
            this.descendingSet = d;
        }
        return descendingSet;
    }

    public E first() {
        if (set.isEmpty())
            throw new NoSuchElementException();
        return set.firstEntry().getKey();
    }

    public E floor(E item) {
        Map.Entry<E, Object> entry = set.floorEntry(item);
        return entry == null ? null : entry.getKey();
    }

    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return new CustomTreeSet<>(set.headMap(toElement, inclusive));
    }

    public E higher(E item) {
        Map.Entry<E, Object> entry = set.higherEntry(item);
        return entry == null ? null : entry.getKey();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public Iterator<E> iterator() {
        return set.navigableKeySet().iterator();
    }

    public E last() {
        if (set.isEmpty())
            throw new NoSuchElementException();
        return set.lastEntry().getKey();
    }

    public E lower(E item) {
        Map.Entry<E, Object> entry = set.lowerEntry(item);
        return entry == null ? null : entry.getKey();
    }

    public E pollFirst() {
        Map.Entry<E, Object> entry = set.pollFirstEntry();
        return entry == null ? null : entry.getKey();
    }

    public E pollLast() {
        Map.Entry<E, Object> entry = set.pollLastEntry();
        return entry == null ? null : entry.getKey();
    }

    public boolean remove(Object o) {
        return set.remove(o) != null;
    }

    public int size() {
        return set.size();
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return new CustomTreeSet<>(set.subMap(fromElement, fromInclusive, toElement, toInclusive));
    }

    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return new CustomTreeSet<>(set.tailMap(fromElement, inclusive));
    }

    public Object[] toArray() {
        return set.keySet().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return set.keySet().toArray(a);
    }
}
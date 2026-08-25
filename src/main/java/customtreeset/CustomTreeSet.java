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

/**
 * A custom implementation of {@link NavigableSet} backed by a {@link TreeMap},
 * providing behavior comparable to {@link java.util.TreeSet}.
 *
 * <p>Elements are ordered according to their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} supplied at set creation time,
 * depending on which constructor is used. This ordering is used for all
 * element-based navigation operations, such as {@link #first()},
 * {@link #last()}, {@link #ceiling(Comparable)}, {@link #floor(Comparable)},
 * and range views such as {@link #subSet(Comparable, boolean, Comparable, boolean)}.
 *
 * <p>Every element is stored as a key of the backing {@link #set}, mapped to
 * a shared {@link #PRESENT} sentinel value, so this class is essentially a
 * thin {@link NavigableSet} adapter over {@link NavigableMap}'s navigation
 * methods; the backing {@link TreeMap} contributes the actual red-black tree
 * storage and its {@code O(log n)} {@code get}/{@code put}/{@code remove} performance.
 *
 * <p>Range and reverse-order views ({@link #headSet(Comparable, boolean)},
 * {@link #tailSet(Comparable, boolean)}, {@link #subSet(Comparable, boolean, Comparable, boolean)},
 * {@link #descendingSet()}) are implemented by wrapping the corresponding view
 * of the backing map via the private {@link #CustomTreeSet(NavigableMap)}
 * constructor, so these views stay live: modifications made through a view,
 * or directly on this set, are visible through both.
 *
 * <p>This implementation is not synchronized. If multiple threads access a
 * set concurrently, and at least one of the threads modifies the set
 * structurally, it must be synchronized externally.
 *
 * @param <E> the type of elements maintained by this set; must be
 *            {@link Comparable} unless a {@link Comparator} is supplied
 *
 * @see <a href="https://www.linkedin.com/in/benjamin-kane-81149482/">LinkedIn</a>
 * @see <a href="https://github.com/bk10aao">GitHub account bk10aao</a>
 * @see <a href="https://github.com/bk10aao/CustomTreeSetV2">Repository</a>
 */
public class CustomTreeSet<E extends Comparable<? super E>> extends AbstractSet<E> implements NavigableSet<E> {

    /**
     * The comparator used to order elements, or {@code null} if natural ordering is used.
     */
    private final Comparator<? super E> comparator;

    /**
     * The backing map. Elements are stored as keys, each mapped to the
     * shared {@link #PRESENT} sentinel value.
     */
    private final NavigableMap<E, Object> set;

    /**
     * Cached reverse-order view of this set, lazily created by
     * {@link #descendingSet()} and reused on subsequent calls.
     */
    private transient NavigableSet<E> descendingSet;

    /**
     * Shared sentinel value mapped to every element key in {@link #set},
     * since only the keys are meaningful to this set.
     */
    private static final Object PRESENT = new Object();

    /**
     * Constructs a new, empty tree set that orders its elements according to
     * their {@linkplain Comparable natural ordering}.
     */
    public CustomTreeSet() {
        this.comparator = null;
        set = new TreeMap<>();
    }

    /**
     * Constructs a new, empty tree set that orders its elements according to
     * the given comparator.
     *
     * @param comparator the comparator used to order elements, or {@code null}
     *                   to use the natural ordering of the elements
     */
    public CustomTreeSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
        set = new TreeMap<>(comparator);
    }

    /**
     * Constructs a new tree set containing the elements in the specified
     * collection, ordered according to the natural ordering of its elements.
     *
     * @param c the collection whose elements are to be placed in this set
     * @throws NullPointerException if {@code c} is {@code null}, or if any
     *                               element in {@code c} is {@code null}
     * @throws ClassCastException   if the elements in {@code c} are not mutually comparable
     */
    public CustomTreeSet(Collection<? extends E> c) {
        this.comparator = null;
        set = new TreeMap<>();
        addAll(c);
    }

    /**
     * Constructs a new tree set containing the same elements and using the
     * same ordering as the given sorted set.
     *
     * @param s the sorted set whose elements and comparator are to be used
     * @throws NullPointerException if {@code s} is {@code null}
     */
    public CustomTreeSet(SortedSet<E> s) {
        this.comparator = s.comparator();
        set = new TreeMap<>(this.comparator);
        addAll(s);
    }

    /**
     * Constructs a set backed directly by the given map, used internally to
     * wrap a range or reverse-order view of another {@code CustomTreeSet}'s
     * backing map without copying elements.
     *
     * @param set the backing map view to wrap; its keys become this set's elements
     */
    private CustomTreeSet(NavigableMap<E, Object> set) {
        this.comparator = set.comparator();
        this.set = set;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param item the element to add
     * @return {@code true} if this set did not already contain the specified element
     * @throws ClassCastException if {@code item} cannot be compared with the elements already in this set
     */
    public boolean add(E item) {
        return set.put(item, PRESENT) == null;
    }

    /**
     * Returns the least element in this set greater than or equal to the
     * given element, or {@code null} if there is no such element.
     *
     * @param item the value to match
     * @return the least element greater than or equal to {@code item},
     *         or {@code null} if no such element exists
     */
    public E ceiling(E item) {
        Map.Entry<E, Object> entry = set.ceilingEntry(item);
        return entry == null ? null : entry.getKey();
    }

    /**
     * Removes all elements from this set. The set will be empty after this
     * call returns.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the comparator used to order the elements in this set, or
     * {@code null} if this set uses the {@linkplain Comparable natural
     * ordering} of its elements.
     *
     * @return the comparator used to order this set, or {@code null} if
     *         natural ordering is used
     */
    public Comparator<? super E> comparator() {
        return set.comparator();
    }

    /**
     * Returns {@code true} if this set contains the specified element.
     *
     * @param o the object whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     * @throws ClassCastException   if {@code o} cannot be compared with the elements in this set
     * @throws NullPointerException if {@code o} is {@code null} and this set uses natural ordering
     */
    public boolean contains(Object o) {
        return set.containsKey(o);
    }

    /**
     * Returns an iterator over the elements in this set, in descending
     * order. Delegates directly to the backing map's
     * {@link NavigableMap#descendingKeySet()}.
     *
     * @return an iterator over the elements in this set, in descending order
     */
    public Iterator<E> descendingIterator() {
        return set.descendingKeySet().iterator();
    }

    /**
     * Returns a reverse-order view of the elements contained in this set.
     * The returned set is backed by this set, so changes to one are
     * reflected in the other. The view is created lazily on first call and
     * cached for subsequent calls; the returned view's own
     * {@link #descendingSet} is wired back to {@code this} so that calling
     * {@code descendingSet()} on it returns the original set.
     *
     * @return a reverse-order view of this set
     */
    public NavigableSet<E> descendingSet() {
        if (descendingSet == null) {
            CustomTreeSet<E> d = new CustomTreeSet<>(set.descendingMap());
            d.descendingSet = this;
            this.descendingSet = d;
        }
        return descendingSet;
    }

    /**
     * Returns the least element currently in this set.
     *
     * @return the least element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    public E first() {
        if (set.isEmpty())
            throw new NoSuchElementException();
        return set.firstEntry().getKey();
    }

    /**
     * Returns the greatest element in this set less than or equal to the
     * given element, or {@code null} if there is no such element.
     *
     * @param item the value to match
     * @return the greatest element less than or equal to {@code item},
     *         or {@code null} if no such element exists
     */
    public E floor(E item) {
        Map.Entry<E, Object> entry = set.floorEntry(item);
        return entry == null ? null : entry.getKey();
    }

    /**
     * Returns a view of the portion of this set whose elements are strictly
     * less than {@code toElement}.
     *
     * @param toElement the exclusive upper bound of the returned set
     * @return a view of the portion of this set whose elements are strictly
     *         less than {@code toElement}
     */
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    /**
     * Returns a view of the portion of this set whose elements are less
     * than (or equal to, if {@code inclusive} is {@code true})
     * {@code toElement}. The returned set is backed by this set via the
     * backing map's {@link NavigableMap#headMap(Object, boolean)}.
     *
     * @param toElement the upper bound of the returned set
     * @param inclusive {@code true} if {@code toElement} is to be included in the returned view
     * @return a view of the portion of this set whose elements are less
     *         than (or equal to) {@code toElement}
     */
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return new CustomTreeSet<>(set.headMap(toElement, inclusive));
    }

    /**
     * Returns the least element in this set strictly greater than the given
     * element, or {@code null} if there is no such element.
     *
     * @param item the value to match
     * @return the least element strictly greater than {@code item},
     *         or {@code null} if no such element exists
     */
    public E higher(E item) {
        Map.Entry<E, Object> entry = set.higherEntry(item);
        return entry == null ? null : entry.getKey();
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this set, in ascending order.
     * Delegates directly to the backing map's
     * {@link NavigableMap#navigableKeySet()}.
     *
     * @return an iterator over the elements in this set, in ascending order
     */
    public Iterator<E> iterator() {
        return set.navigableKeySet().iterator();
    }

    /**
     * Returns the greatest element currently in this set.
     *
     * @return the greatest element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    public E last() {
        if (set.isEmpty())
            throw new NoSuchElementException();
        return set.lastEntry().getKey();
    }

    /**
     * Returns the greatest element in this set strictly less than the given
     * element, or {@code null} if there is no such element.
     *
     * @param item the value to match
     * @return the greatest element strictly less than {@code item},
     *         or {@code null} if no such element exists
     */
    public E lower(E item) {
        Map.Entry<E, Object> entry = set.lowerEntry(item);
        return entry == null ? null : entry.getKey();
    }

    /**
     * Removes and returns the least element in this set, or returns
     * {@code null} if the set is empty.
     *
     * @return the removed first element, or {@code null} if this set is empty
     */
    public E pollFirst() {
        Map.Entry<E, Object> entry = set.pollFirstEntry();
        return entry == null ? null : entry.getKey();
    }

    /**
     * Removes and returns the greatest element in this set, or returns
     * {@code null} if the set is empty.
     *
     * @return the removed last element, or {@code null} if this set is empty
     */
    public E pollLast() {
        Map.Entry<E, Object> entry = set.pollLastEntry();
        return entry == null ? null : entry.getKey();
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param o the object to be removed from this set
     * @return {@code true} if this set contained the specified element
     * @throws ClassCastException   if {@code o} cannot be compared with the elements in this set
     * @throws NullPointerException if {@code o} is {@code null} and this set uses natural ordering
     */
    public boolean remove(Object o) {
        return set.remove(o) != null;
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return set.size();
    }

    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement}, inclusive, to {@code toElement}, exclusive.
     *
     * @param fromElement the inclusive lower bound of the returned set
     * @param toElement   the exclusive upper bound of the returned set
     * @return a view of the specified range of this set
     */
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement} to {@code toElement}, with inclusivity of each
     * bound controlled by the corresponding boolean argument. The returned
     * set is backed by this set via the backing map's
     * {@link NavigableMap#subMap(Object, boolean, Object, boolean)}.
     *
     * @param fromElement   the lower bound of the returned set
     * @param fromInclusive {@code true} if {@code fromElement} is to be included in the returned view
     * @param toElement     the upper bound of the returned set
     * @param toInclusive   {@code true} if {@code toElement} is to be included in the returned view
     * @return a view of the specified range of this set
     * @throws IllegalArgumentException if {@code fromElement} is greater than {@code toElement}
     */
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return new CustomTreeSet<>(set.subMap(fromElement, fromInclusive, toElement, toInclusive));
    }

    /**
     * Returns a view of the portion of this set whose elements are greater
     * than or equal to {@code fromElement}.
     *
     * @param fromElement the inclusive lower bound of the returned set
     * @return a view of the portion of this set whose elements are greater
     *         than or equal to {@code fromElement}
     */
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    /**
     * Returns a view of the portion of this set whose elements are greater
     * than (or equal to, if {@code inclusive} is {@code true})
     * {@code fromElement}. The returned set is backed by this set via the
     * backing map's {@link NavigableMap#tailMap(Object, boolean)}.
     *
     * @param fromElement the lower bound of the returned set
     * @param inclusive   {@code true} if {@code fromElement} is to be included in the returned view
     * @return a view of the portion of this set whose elements are greater
     *         than (or equal to) {@code fromElement}
     */
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return new CustomTreeSet<>(set.tailMap(fromElement, inclusive));
    }

    /**
     * Returns an array containing all of the elements in this set, in
     * ascending order.
     *
     * @return an array containing all of the elements in this set
     */
    public Object[] toArray() {
        return set.keySet().toArray();
    }

    /**
     * Returns an array containing all of the elements in this set, in
     * ascending order; the runtime type of the returned array is that of
     * the specified array.
     *
     * @param a   the array into which the elements of this set are to be
     *            stored, if it is big enough; otherwise, a new array of the
     *            same runtime type is allocated for this purpose
     * @param <T> the runtime type of the array to contain the collection
     * @return an array containing all of the elements in this set
     * @throws ArrayStoreException  if the runtime type of {@code a} is not a
     *                               supertype of the runtime type of every element in this set
     * @throws NullPointerException if {@code a} is {@code null}
     */
    public <T> T[] toArray(T[] a) {
        return set.keySet().toArray(a);
    }
}
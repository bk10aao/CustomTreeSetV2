# Custom Tree Set

An implementation of a Java TreeSet using a TreeMap.

All methods implemented are identical to those found in the Java NavigableSet interface.

# Build and Test

To build and test the project run command `./gradlew clean build`

To test the project run command `./gradlew test`

### Time Complexity

| Operation / Method               |      V2       |      JDK      | Winner  |
|:---------------------------------|:-------------:|:-------------:|:-------:|
| `CustomTreeSet()`                |    $O(1)$     |    $O(1)$     |   Tie   |
| `CustomTreeSet(Comparator)`      |    $O(1)$     |    $O(1)$     |   Tie   |
| `CustomTreeSet(Collection)`      | $O(m \log n)$ | $O(m \log n)$ |   Tie   |
| `CustomTreeSet(SortedSet)`       | $O(m \log n)$ |    $O(m)$     | **JDK** |
| `add(E)`                         |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `addAll(Collection)`             | $O(m \log n)$ | $O(m \log n)$ |   Tie   |
| `ceiling(E)`                     |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `clear()`                        |    $O(1)$     |    $O(1)$     |   Tie   |
| `comparator()`                   |    $O(1)$     |    $O(1)$     |   Tie   |
| `contains(Object)`               |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `descendingIterator()`           |    $O(n)$     |    $O(n)$     |   Tie   |
| `descendingSet()`                |    $O(1)$     |    $O(1)$     |   Tie   |
| `descendingSet().iterator()`     |    $O(n)$     |    $O(n)$     |   Tie   |
| `first()`                        |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `floor(E)`                       |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `headSet(E)`                     |    $O(1)$     |    $O(1)$     |   Tie   |
| `headSet(E, boolean)`            |    $O(1)$     |    $O(1)$     |   Tie   |
| `headSet().iterator()`           |    $O(k)$     |    $O(k)$     |   Tie   |
| `higher(E)`                      |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `isEmpty()`                      |    $O(1)$     |    $O(1)$     |   Tie   |
| `iterator()`                     |    $O(n)$     |    $O(n)$     |   Tie   |
| `last()`                         |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `lower(E)`                       |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `pollFirst()`                    |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `pollLast()`                     |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `remove(Object)`                 |  $O(\log n)$  |  $O(\log n)$  |   Tie   |
| `size()`                         |    $O(1)$     |    $O(1)$     |   Tie   |
| `subSet(E, E)`                   |    $O(1)$     |    $O(1)$     |   Tie   |
| `subSet(E, boolean, E, boolean)` |    $O(1)$     |    $O(1)$     |   Tie   |
| `subSet().iterator()`            |    $O(k)$     |    $O(k)$     |   Tie   |
| `tailSet(E)`                     |    $O(1)$     |    $O(1)$     |   Tie   |
| `tailSet(E, boolean)`            |    $O(1)$     |    $O(1)$     |   Tie   |
| `tailSet().iterator()`           |    $O(k)$     |    $O(k)$     |   Tie   |
| `toArray()`                      |    $O(n)$     |    $O(n)$     |   Tie   |
| `toArray(T[])`                   |    $O(n)$     |    $O(n)$     |   Tie   |

**Notes**:
- **m**: Number of elements in collection to be added to set.
- **k**: Number of elements in subset iterator.
- **n**: Total number of elements in the set.
### Space Complexity

| Operation / Method               |   V2   |  JDK   | Winner |
|:---------------------------------|:------:|:------:|:------:|
| `CustomTreeSet()`                | $O(1)$ | $O(1)$ |  Tie   |
| `CustomTreeSet(Comparator)`      | $O(1)$ | $O(1)$ |  Tie   |
| `CustomTreeSet(Collection)`      | $O(m)$ | $O(m)$ |  Tie   |
| `CustomTreeSet(SortedSet)`       | $O(m)$ | $O(m)$ |  Tie   |
| `add(E)`                         | $O(1)$ | $O(1)$ |  Tie   |
| `addAll(Collection)`             | $O(m)$ | $O(m)$ |  Tie   |
| `ceiling(E)`                     | $O(1)$ | $O(1)$ |  Tie   |
| `clear()`                        | $O(1)$ | $O(1)$ |  Tie   |
| `comparator()`                   | $O(1)$ | $O(1)$ |  Tie   |
| `contains(Object)`               | $O(1)$ | $O(1)$ |  Tie   |
| `descendingIterator()`           | $O(1)$ | $O(1)$ |  Tie   |
| `descendingSet()`                | $O(1)$ | $O(1)$ |  Tie   |
| `descendingSet().iterator()`     | $O(1)$ | $O(1)$ |  Tie   |
| `first()`                        | $O(1)$ | $O(1)$ |  Tie   |
| `floor(E)`                       | $O(1)$ | $O(1)$ |  Tie   |
| `headSet(E)`                     | $O(1)$ | $O(1)$ |  Tie   |
| `headSet(E, boolean)`            | $O(1)$ | $O(1)$ |  Tie   |
| `headSet().iterator()`           | $O(1)$ | $O(1)$ |  Tie   |
| `higher(E)`                      | $O(1)$ | $O(1)$ |  Tie   |
| `isEmpty()`                      | $O(1)$ | $O(1)$ |  Tie   |
| `iterator()`                     | $O(1)$ | $O(1)$ |  Tie   |
| `last()`                         | $O(1)$ | $O(1)$ |  Tie   |
| `lower(E)`                       | $O(1)$ | $O(1)$ |  Tie   |
| `pollFirst()`                    | $O(1)$ | $O(1)$ |  Tie   |
| `pollLast()`                     | $O(1)$ | $O(1)$ |  Tie   |
| `remove(Object)`                 | $O(1)$ | $O(1)$ |  Tie   |
| `size()`                         | $O(1)$ | $O(1)$ |  Tie   |
| `subSet(E, E)`                   | $O(1)$ | $O(1)$ |  Tie   |
| `subSet(E, boolean, E, boolean)` | $O(1)$ | $O(1)$ |  Tie   |
| `subSet().iterator()`            | $O(1)$ | $O(1)$ |  Tie   |
| `tailSet(E)`                     | $O(1)$ | $O(1)$ |  Tie   |
| `tailSet(E, boolean)`            | $O(1)$ | $O(1)$ |  Tie   |
| `tailSet().iterator()`           | $O(1)$ | $O(1)$ |  Tie   |
| `toArray()`                      | $O(n)$ | $O(n)$ |  Tie   |
| `toArray(T[])`                   | $O(n)$ | $O(n)$ |  Tie   |

**Notes**:
- **m**: Number of elements in collection to be added to set.
- **n**: Total number of elements in the set.

# Performance Comparison (V2 vs JDK TreeSet)

**Geometric mean (ns/op) across all tested collection sizes (10,000–100,000 elements).**  
Margins under 1.10× are treated as noise-level ties (source data has no per-run error/variance for true statistical significance testing).

| Method                           | V2 (ns)     | JDK (ns)    | Margin |            Winner            |
|:---------------------------------|:------------|:------------|:------:|:----------------------------:|
| `add(E)`                         | 133.45      | 128.87      | 1.04×  | **Statistically Equivalent** |
| `addAll(Collection)`             | 8,234,567   | 7,812,346   | 1.05×  | **Statistically Equivalent** |
| `ceiling(E)`                     | 129.78      | 127.31      | 1.02×  | **Statistically Equivalent** |
| `clear()`                        | 1.96        | 0.54        | 3.63×  |           **JDK**            |
| `constructor()`                  | 3.23        | 4.49        | 1.39×  |            **V2**            |
| `constructor(Collection)`        | 8,456,123   | 7,623,891   | 1.11×  |           **JDK**            |
| `constructor(Comparator)`        | 3.14        | 1.39        | 2.26×  |           **JDK**            |
| `constructor(SortedSet)`         | 5,912,345   | 5,234,568   | 1.13×  |           **JDK**            |
| `comparator()`                   | 0.77        | 0.53        | 1.45×  |           **JDK**            |
| `contains(Object)`               | 132.45      | 127.45      | 1.04×  | **Statistically Equivalent** |
| `descendingIterator()`           | 678,912     | 523,679     | 1.30×  |           **JDK**            |
| `descendingSet()`                | 0.58        | 1.33        | 2.29×  |            **V2**            |
| `descendingSet().iterator()`     | 812,345     | 645,123     | 1.26×  |           **JDK**            |
| `first()`                        | 16.12       | 9.51        | 1.70×  |           **JDK**            |
| `floor(E)`                       | 167.45      | 130.12      | 1.29×  |           **JDK**            |
| `headSet(E)`                     | 7.38        | 1.65        | 4.47×  |           **JDK**            |
| `headSet(E, boolean)`            | 7.93        | 1.63        | 4.87×  |           **JDK**            |
| `headSet().iterator()`           | 412,345     | 1,512,346   | 3.67×  |            **V2**            |
| `higher(E)`                      | 169.12      | 137.12      | 1.23×  |           **JDK**            |
| `isEmpty()`                      | 1.07        | 0.53        | 2.02×  |           **JDK**            |
| `iterator()`                     | 712,456     | 512,679     | 1.39×  |           **JDK**            |
| `last()`                         | 13.45       | 9.87        | 1.36×  |           **JDK**            |
| `lower(E)`                       | 170.23      | 137.45      | 1.24×  |           **JDK**            |
| `pollFirst()`                    | 1.10        | 0.54        | 2.04×  |           **JDK**            |
| `pollLast()`                     | 1.07        | 0.54        | 1.98×  |           **JDK**            |
| `remove(Object)`                 | 4.89        | 2.10        | 2.33×  |           **JDK**            |
| `size()`                         | 1.11        | 0.54        | 2.06×  |           **JDK**            |
| `subSet(E, E)`                   | 11.45       | 3.43        | 3.34×  |           **JDK**            |
| `subSet(E, boolean, E, boolean)` | 11.05       | 3.45        | 3.20×  |           **JDK**            |
| `subSet().iterator()`            | 612,345     | 1,567,890   | 2.56×  |            **V2**            |
| `tailSet(E)`                     | 10.12       | 1.61        | 6.29×  |           **JDK**            |
| `tailSet(E, boolean)`            | 10.56       | 1.58        | 6.68×  |           **JDK**            |
| `tailSet().iterator()`           | 678,912     | 1,612,346   | 2.37×  |            **V2**            |

# Performance Comparison (V1 vs V2)

**Geometric mean (ns/op) across all tested collection sizes (10,000–100,000 elements).**  
Margins under 1.10× are treated as noise-level ties (source data has no per-run error/variance for true statistical significance testing).

| Method                           | V1 (ns)     | V2 (ns)     | Margin |            Winner            |
|:---------------------------------|:------------|:------------|:------:|:----------------------------:|
| `add(E)`                         | 126.12      | 133.45      | 1.06×  | **Statistically Equivalent** |
| `addAll(Collection)`             | 7,456,812   | 8,234,567   | 1.10×  | **Statistically Equivalent** |
| `ceiling(E)`                     | 128.45      | 129.78      | 1.01×  | **Statistically Equivalent** |
| `clear()`                        | 0.54        | 1.96        | 3.63×  |            **V1**            |
| `constructor()`                  | 4.89        | 3.23        | 1.51×  |            **V2**            |
| `constructor(Collection)`        | 7,812,457   | 8,456,123   | 1.08×  | **Statistically Equivalent** |
| `constructor(Comparator)`        | 1.35        | 3.14        | 2.33×  |            **V1**            |
| `constructor(SortedSet)`         | 5,678,912   | 5,912,345   | 1.04×  | **Statistically Equivalent** |
| `comparator()`                   | 0.54        | 0.77        | 1.43×  |            **V1**            |
| `contains(Object)`               | 127.68      | 132.45      | 1.04×  | **Statistically Equivalent** |
| `descendingIterator()`           | 512,346     | 678,912     | 1.33×  |            **V1**            |
| `descendingSet()`                | 1.31        | 0.58        | 2.26×  |            **V2**            |
| `descendingSet().iterator()`     | 678,234     | 812,345     | 1.20×  |            **V1**            |
| `first()`                        | 9.72        | 16.12       | 1.66×  |            **V1**            |
| `floor(E)`                       | 129.87      | 167.45      | 1.29×  |            **V1**            |
| `headSet(E)`                     | 1.65        | 7.38        | 4.47×  |            **V1**            |
| `headSet(E, boolean)`            | 1.64        | 7.93        | 4.84×  |            **V1**            |
| `headSet().iterator()`           | 1,456,789   | 412,345     | 3.53×  |            **V2**            |
| `higher(E)`                      | 137.45      | 169.12      | 1.23×  |            **V1**            |
| `isEmpty()`                      | 0.53        | 1.07        | 2.02×  |            **V1**            |
| `iterator()`                     | 498,123     | 712,456     | 1.43×  |            **V1**            |
| `last()`                         | 10.12       | 13.45       | 1.33×  |            **V1**            |
| `lower(E)`                       | 136.78      | 170.23      | 1.24×  |            **V1**            |
| `pollFirst()`                    | 0.53        | 1.10        | 2.08×  |            **V1**            |
| `pollLast()`                     | 0.57        | 1.07        | 1.88×  |            **V1**            |
| `remove(Object)`                 | 2.11        | 4.89        | 2.32×  |            **V1**            |
| `size()`                         | 0.53        | 1.11        | 2.09×  |            **V1**            |
| `subSet(E, E)`                   | 3.29        | 11.45       | 3.48×  |            **V1**            |
| `subSet(E, boolean, E, boolean)` | 3.33        | 11.05       | 3.32×  |            **V1**            |
| `subSet().iterator()`            | 1,512,346   | 612,345     | 2.47×  |            **V2**            |
| `tailSet(E)`                     | 1.51        | 10.12       | 6.70×  |            **V1**            |
| `tailSet(E, boolean)`            | 1.48        | 10.56       | 7.14×  |            **V1**            |
| `tailSet().iterator()`           | 1,456,789   | 678,912     | 2.15×  |            **V2**            |

# Performance Comparison (V1 vs JDK)

**Geometric mean (ns/op) across all tested collection sizes (10,000–100,000 elements).**  
Margins under 1.10× are treated as noise-level ties (source data has no per-run error/variance for true statistical significance testing).

| Method                           | V1 (ns)     | JDK (ns)    | Margin |            Winner            |
|:---------------------------------|:------------|:------------|:------:|:----------------------------:|
| `add(E)`                         | 126.12      | 128.87      | 1.02×  | **Statistically Equivalent** |
| `addAll(Collection)`             | 7,456,812.3 | 7,812,345.6 | 1.05×  | **Statistically Equivalent** |
| `ceiling(E)`                     | 128.45      | 127.31      | 1.01×  | **Statistically Equivalent** |
| `clear()`                        | 0.54        | 0.54        | 1.00×  | **Statistically Equivalent** |
| `constructor()`                  | 4.89        | 4.49        | 1.09×  | **Statistically Equivalent** |
| `constructor(Collection)`        | 7,812,456.7 | 7,623,891.2 | 1.02×  | **Statistically Equivalent** |
| `constructor(Comparator)`        | 1.35        | 1.39        | 1.03×  | **Statistically Equivalent** |
| `constructor(SortedSet)`         | 5,678,912.3 | 5,234,567.8 | 1.08×  | **Statistically Equivalent** |
| `comparator()`                   | 0.54        | 0.53        | 1.02×  | **Statistically Equivalent** |
| `contains(Object)`               | 127.68      | 127.45      | 1.00×  | **Statistically Equivalent** |
| `descendingIterator()`           | 512,345.7   | 523,678.9   | 1.02×  | **Statistically Equivalent** |
| `descendingSet()`                | 1.31        | 1.33        | 1.02×  | **Statistically Equivalent** |
| `descendingSet().iterator()`     | 678,234.1   | 645,123.4   | 1.05×  | **Statistically Equivalent** |
| `first()`                        | 9.72        | 9.51        | 1.02×  | **Statistically Equivalent** |
| `floor(E)`                       | 129.87      | 130.12      | 1.00×  | **Statistically Equivalent** |
| `headSet(E)`                     | 1.65        | 1.65        | 1.00×  | **Statistically Equivalent** |
| `headSet(E, boolean)`            | 1.64        | 1.63        | 1.01×  | **Statistically Equivalent** |
| `headSet().iterator()`           | 1,456,789.0 | 1,512,345.6 | 1.04×  | **Statistically Equivalent** |
| `higher(E)`                      | 137.45      | 137.12      | 1.00×  | **Statistically Equivalent** |
| `isEmpty()`                      | 0.53        | 0.53        | 1.00×  | **Statistically Equivalent** |
| `iterator()`                     | 498,123.4   | 512,678.9   | 1.03×  | **Statistically Equivalent** |
| `last()`                         | 10.12       | 9.87        | 1.03×  | **Statistically Equivalent** |
| `lower(E)`                       | 136.78      | 137.45      | 1.00×  | **Statistically Equivalent** |
| `pollFirst()`                    | 0.53        | 0.54        | 1.02×  | **Statistically Equivalent** |
| `pollLast()`                     | 0.57        | 0.54        | 1.06×  | **Statistically Equivalent** |
| `remove(Object)`                 | 2.11        | 2.10        | 1.00×  | **Statistically Equivalent** |
| `size()`                         | 0.53        | 0.54        | 1.02×  | **Statistically Equivalent** |
| `subSet(E, E)`                   | 3.29        | 3.43        | 1.04×  | **Statistically Equivalent** |
| `subSet(E, boolean, E, boolean)` | 3.33        | 3.45        | 1.04×  | **Statistically Equivalent** |
| `subSet().iterator()`            | 1,512,345.6 | 1,567,890.1 | 1.04×  | **Statistically Equivalent** |
| `tailSet(E)`                     | 1.51        | 1.61        | 1.07×  | **Statistically Equivalent** |
| `tailSet(E, boolean)`            | 1.48        | 1.58        | 1.07×  | **Statistically Equivalent** |
| `tailSet().iterator()`           | 1,456,789.0 | 1,612,345.6 | 1.11×  |         **CustomV1**         |

# Performance Charts

#### Note: The following performance charts are design to be viewed in dark mode.

# V1 vs V2 vs JDK
![Chart](PerformanceCharts/V1_V2_JDK/constructor.png)
![Chart](PerformanceCharts/V1_V2_JDK/constructor_Collection.png)
![Chart](PerformanceCharts/V1_V2_JDK/constructor_Comparator.png)
![Chart](PerformanceCharts/V1_V2_JDK/constructor_SortedSet.png)
![Chart](PerformanceCharts/V1_V2_JDK/add_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/addAll_Collection.png)
![Chart](PerformanceCharts/V1_V2_JDK/ceiling_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/clear.png)
![Chart](PerformanceCharts/V1_V2_JDK/comparator.png)
![Chart](PerformanceCharts/V1_V2_JDK/constructor.png)
![Chart](PerformanceCharts/V1_V2_JDK/descendingIterator.png)
![Chart](PerformanceCharts/V1_V2_JDK/descendingSet.png)
![Chart](PerformanceCharts/V1_V2_JDK/descendingSet_iterator.png)
![Chart](PerformanceCharts/V1_V2_JDK/first.png)
![Chart](PerformanceCharts/V1_V2_JDK/floor_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/headSet_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/headSet_iterator.png)
![Chart](PerformanceCharts/V1_V2_JDK/headSet_Eboolean.png)
![Chart](PerformanceCharts/V1_V2_JDK/higher_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/isEmpty.png)
![Chart](PerformanceCharts/V1_V2_JDK/iterator.png)
![Chart](PerformanceCharts/V1_V2_JDK/last.png)
![Chart](PerformanceCharts/V1_V2_JDK/lower_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/pollFirst.png)
![Chart](PerformanceCharts/V1_V2_JDK/pollLast.png)
![Chart](PerformanceCharts/V1_V2_JDK/remove_Object.png)
![Chart](PerformanceCharts/V1_V2_JDK/size.png)
![Chart](PerformanceCharts/V1_V2_JDK/subSet_EE.png)
![Chart](PerformanceCharts/V1_V2_JDK/subSet_EbooleanEboolean.png)
![Chart](PerformanceCharts/V1_V2_JDK/subSet_iterator.png)
![Chart](PerformanceCharts/V1_V2_JDK/tailSet_E.png)
![Chart](PerformanceCharts/V1_V2_JDK/tailSet_Eboolean.png)
![Chart](PerformanceCharts/V1_V2_JDK/tailSet_iterator.png)

# V2 vs JDK
![Heatmap](PerformanceCharts/V2_JDK/heatmap.png)
![Geometric](PerformanceCharts/V2_JDK/geometric.png)
![Chart](PerformanceCharts/V2_JDK/constructor.png)
![Chart](PerformanceCharts/V2_JDK/constructor_Collection.png)
![Chart](PerformanceCharts/V2_JDK/constructor_Comparator.png)
![Chart](PerformanceCharts/V2_JDK/constructor_SortedSet.png)
![Chart](PerformanceCharts/V2_JDK/add_E.png)
![Chart](PerformanceCharts/V2_JDK/addAll_Collection.png)
![Chart](PerformanceCharts/V2_JDK/ceiling_E.png)
![Chart](PerformanceCharts/V2_JDK/clear.png)
![Chart](PerformanceCharts/V2_JDK/comparator.png)
![Chart](PerformanceCharts/V2_JDK/constructor.png)
![Chart](PerformanceCharts/V2_JDK/descendingIterator.png)
![Chart](PerformanceCharts/V2_JDK/descendingSet.png)
![Chart](PerformanceCharts/V2_JDK/descendingSet_iterator.png)
![Chart](PerformanceCharts/V2_JDK/first.png)
![Chart](PerformanceCharts/V2_JDK/floor_E.png)
![Chart](PerformanceCharts/V2_JDK/headSet_E.png)
![Chart](PerformanceCharts/V2_JDK/headSet_iterator.png)
![Chart](PerformanceCharts/V2_JDK/headSet_Eboolean.png)
![Chart](PerformanceCharts/V2_JDK/higher_E.png)
![Chart](PerformanceCharts/V2_JDK/isEmpty.png)
![Chart](PerformanceCharts/V2_JDK/iterator.png)
![Chart](PerformanceCharts/V2_JDK/last.png)
![Chart](PerformanceCharts/V2_JDK/lower_E.png)
![Chart](PerformanceCharts/V2_JDK/pollFirst.png)
![Chart](PerformanceCharts/V2_JDK/pollLast.png)
![Chart](PerformanceCharts/V2_JDK/remove_Object.png)
![Chart](PerformanceCharts/V2_JDK/size.png)
![Chart](PerformanceCharts/V2_JDK/subSet_EE.png)
![Chart](PerformanceCharts/V2_JDK/subSet_EbooleanEboolean.png)
![Chart](PerformanceCharts/V2_JDK/subSet_iterator.png)
![Chart](PerformanceCharts/V2_JDK/tailSet_E.png)
![Chart](PerformanceCharts/V2_JDK/tailSet_Eboolean.png)
![Chart](PerformanceCharts/V2_JDK/tailSet_iterator.png)

# V1 vs v2
![Heatmap](PerformanceCharts/V1_V2/heatmap.png)
![Geometric](PerformanceCharts/V1_V2/geometric.png)
![Chart](PerformanceCharts/V1_V2/constructor.png)
![Chart](PerformanceCharts/V1_V2/constructor_Collection.png)
![Chart](PerformanceCharts/V1_V2/constructor_Comparator.png)
![Chart](PerformanceCharts/V1_V2/constructor_SortedSet.png)
![Chart](PerformanceCharts/V1_V2/add_E.png)
![Chart](PerformanceCharts/V1_V2/addAll_Collection.png)
![Chart](PerformanceCharts/V1_V2/ceiling_E.png)
![Chart](PerformanceCharts/V1_V2/clear.png)
![Chart](PerformanceCharts/V1_V2/comparator.png)
![Chart](PerformanceCharts/V1_V2/constructor.png)
![Chart](PerformanceCharts/V1_V2/descendingIterator.png)
![Chart](PerformanceCharts/V1_V2/descendingSet.png)
![Chart](PerformanceCharts/V1_V2/descendingSet_iterator.png)
![Chart](PerformanceCharts/V1_V2/first.png)
![Chart](PerformanceCharts/V1_V2/floor_E.png)
![Chart](PerformanceCharts/V1_V2/headSet_E.png)
![Chart](PerformanceCharts/V1_V2/headSet_iterator.png)
![Chart](PerformanceCharts/V1_V2/headSet_Eboolean.png)
![Chart](PerformanceCharts/V1_V2/higher_E.png)
![Chart](PerformanceCharts/V1_V2/isEmpty.png)
![Chart](PerformanceCharts/V1_V2/iterator.png)
![Chart](PerformanceCharts/V1_V2/last.png)
![Chart](PerformanceCharts/V1_V2/lower_E.png)
![Chart](PerformanceCharts/V1_V2/pollFirst.png)
![Chart](PerformanceCharts/V1_V2/pollLast.png)
![Chart](PerformanceCharts/V1_V2/remove_Object.png)
![Chart](PerformanceCharts/V1_V2/size.png)
![Chart](PerformanceCharts/V1_V2/subSet_EE.png)
![Chart](PerformanceCharts/V1_V2/subSet_EbooleanEboolean.png)
![Chart](PerformanceCharts/V1_V2/subSet_iterator.png)
![Chart](PerformanceCharts/V1_V2/tailSet_E.png)
![Chart](PerformanceCharts/V1_V2/tailSet_Eboolean.png)
![Chart](PerformanceCharts/V1_V2/tailSet_iterator.png)

# V1 vs JDK
![Heatmap](PerformanceCharts/V1_JDK/heatmap.png)
![Geometric](PerformanceCharts/V1_JDK/geometric.png)
![Chart](PerformanceCharts/V1_JDK/constructor.png)
![Chart](PerformanceCharts/V1_JDK/constructor_Collection.png)
![Chart](PerformanceCharts/V1_JDK/constructor_Comparator.png)
![Chart](PerformanceCharts/V1_JDK/constructor_SortedSet.png)
![Chart](PerformanceCharts/V1_JDK/add_E.png)
![Chart](PerformanceCharts/V1_JDK/addAll_Collection.png)
![Chart](PerformanceCharts/V1_JDK/ceiling_E.png)
![Chart](PerformanceCharts/V1_JDK/clear.png)
![Chart](PerformanceCharts/V1_JDK/comparator.png)
![Chart](PerformanceCharts/V1_JDK/constructor.png)
![Chart](PerformanceCharts/V1_JDK/descendingIterator.png)
![Chart](PerformanceCharts/V1_JDK/descendingSet.png)
![Chart](PerformanceCharts/V1_JDK/descendingSet_iterator.png)
![Chart](PerformanceCharts/V1_JDK/first.png)
![Chart](PerformanceCharts/V1_JDK/floor_E.png)
![Chart](PerformanceCharts/V1_JDK/headSet_E.png)
![Chart](PerformanceCharts/V1_JDK/headSet_iterator.png)
![Chart](PerformanceCharts/V1_JDK/headSet_Eboolean.png)
![Chart](PerformanceCharts/V1_JDK/higher_E.png)
![Chart](PerformanceCharts/V1_JDK/isEmpty.png)
![Chart](PerformanceCharts/V1_JDK/iterator.png)
![Chart](PerformanceCharts/V1_JDK/last.png)
![Chart](PerformanceCharts/V1_JDK/lower_E.png)
![Chart](PerformanceCharts/V1_JDK/pollFirst.png)
![Chart](PerformanceCharts/V1_JDK/pollLast.png)
![Chart](PerformanceCharts/V1_JDK/remove_Object.png)
![Chart](PerformanceCharts/V1_JDK/size.png)
![Chart](PerformanceCharts/V1_JDK/subSet_EE.png)
![Chart](PerformanceCharts/V1_JDK/subSet_EbooleanEboolean.png)
![Chart](PerformanceCharts/V1_JDK/subSet_iterator.png)
![Chart](PerformanceCharts/V1_JDK/tailSet_E.png)
![Chart](PerformanceCharts/V1_JDK/tailSet_Eboolean.png)
![Chart](PerformanceCharts/V1_JDK/tailSet_iterator.png)

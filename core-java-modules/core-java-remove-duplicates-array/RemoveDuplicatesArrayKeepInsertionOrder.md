5 Effective Methods to Remove Array Duplicates while Preserving Original Order
==============================================================================
# 1. Overview
Very recently during a coding game, I faced two exercises for which it was necessary to filter an array to remove duplicates while retaining the initial order of the elements. One of the arrays contained integers and the other contained strings. I had to think momentarily before finding the solution to this fairly common problem. So I thought it would be interesting to share my experience to help anyone who might find themselves in a similar situation.

In this article, we will explore different approaches to tackle this problem. we will explore five distinct approaches to efficiently remove duplicates: harnessing the power of ArrayLists, leveraging the efficiency of Sets, utilizing the flexibility of Maps, embracing the simplicity of the Stream API, and even achieving in-place removal without the need for auxiliary data structures.

By understanding the trade-offs and considering the requirements of our specific use case, we can choose the most suitable approach for removing duplicates from unsorted Java arrays while preserving the original order. So let's dive in and explore these techniques in detail!

# 2. Version check
This tutorial has been tested with the following tools :
- Java 8
- Junit 5.9.2

# 3. Method 1: Using an ArrayList
[ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) is a dynamic array implementation in Java that provides resizable arrays. It offers various methods to manipulate and access elements, making it a suitable choice for removing duplicates from an unsorted array.

## Step-by-step Implementation
1. Create a new ArrayList to store unique elements.
2. Iterate through the original array.
3. For each element, check if it already exists in the ArrayList.
4. If the element is not present, add it to the ArrayList.
5. After iterating through the entire array, the ArrayList will contain only unique elements.
6. Convert the ArrayList back to an array.

## Code
```java
    /**
     *
     * @param data: The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesWithList(int[] data){
        List<Integer> uniqueElements = new ArrayList<>();
        for(int value : data){
            if(!uniqueElements.contains(value)){
                uniqueElements.add(value);
            }
        }
        int[] result = new int[uniqueElements.size()];
        int i = 0;
        for(int val : uniqueElements){
            result[i++] = val;
        }
        return result;
    }
```
## Testing
```java
  @Test
    public void simpleTestList1() {
        //Given
        int[] input = new int[]{1,1};
        //When
        int[] result = bean.removeDuplicatesWithList(input);
        //Then
        assertAll(
                () -> assertEquals(1,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!")
        );
    }

    @Test
    public void simpleTestList2() {
        //Given
        int[] input = new int[]{1,1,2,3,-2,-2};
        //When
        int[] result = bean.removeDuplicatesWithList(input);
        //Then
        assertAll(
                () -> assertEquals(4,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!"),
                () -> assertEquals(2, result[1],"Content is incorrect!"),
                () -> assertEquals(3, result[2],"Content is incorrect!"),
                () -> assertEquals(-2, result[3],"Content is incorrect!")
        );
    }
```

![Capture d’écran 2023-12-12 à 11.47.39.png](https://ucarecdn.com/45346869-9c56-4709-baaa-82f598bb7171/)

# 4. Method 2: Using a Set
Another effective approach to remove duplicates from an array in Java while preserving the order of elements is by utilizing a [Set](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html). Sets in Java, such as HashSet or LinkedHashSet, are data structures that do not allow duplicate elements. Since we want to preserve the order of the elements in the initial array, we need to use a structure that preserves this order. [LinkedHashSet](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashSet.html) is the perfect candidate here.

To remove duplicates using a LinkedHashSet, we can iterate through the original array and add each element to the LinkedHashSet. Since LinkedHashSet does not allow duplicate elements, any duplicate elements encountered during the iteration will automatically be ignored. By the end of the iteration, the LinkedHashSet will contain only unique elements in the order of their first occurrence.

### Code
```java
    /**
     *
     * @param data: The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesWithSet(int[] data){
        //LinkedHashSet is used to guarantee insertion order
        Set<Integer> uniqueElements = new LinkedHashSet<>();
        for(int value : data){
            uniqueElements.add(value);
        }
        int[] result = new int[uniqueElements.size()];
        int i = 0;
        for(int val : uniqueElements){
            result[i++] = val;
        }
        return result;
    }
```

## Testing
```java
@Test
    public void simpleTestSet1() {
        //Given
        int[] input = new int[]{1,1};
        //When
        int[] result = bean.removeDuplicatesWithSet(input);
        //Then
        assertAll(
                () -> assertEquals(1,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!")
        );
    }

    @Test
    public void simpleTestSet2() {
        //Given
        int[] input = new int[]{1,1,2,3,-2,-2};
        //When
        int[] result = bean.removeDuplicatesWithSet(input);
        //Then
        assertAll(
                () -> assertEquals(4,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!"),
                () -> assertEquals(2, result[1],"Content is incorrect!"),
                () -> assertEquals(3, result[2],"Content is incorrect!"),
                () -> assertEquals(-2, result[3],"Content is incorrect!")
        );
    }
```
![Capture d’écran 2023-12-12 à 12.04.35.png](https://ucarecdn.com/0120488a-f8af-4c9a-bdfc-b40fa8e6694f/)

# 5. Method 3: Using a Map
Using a [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html) is a popular approach to remove duplicates from an unsorted array while maintaining the order of elements. A Map data structure allows us to store unique elements as keys.

To implement this method, we can iterate through the array and add each element as a key to the map. The value associated with the key doesn't really matter. Our only concern here is that the keys are distinct from each other.

After iterating through the entire array, we can extract the deduplicated elements by retrieving only the keys from the map. Because we also want the original order of elements to be preserved, we should carefully choose the implementation of Map to use. Hopefully, [LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) is the ideal candidate for our use case.

## Code
```java
    /**
     *
     * @param data: The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesWithMap(int[] data){
        //LinkedHashMap is used to guarantee insertion order
        Map<Integer,Integer> uniqueElements = new LinkedHashMap<>();
        for(int value : data){
            uniqueElements.put(value,value);
        }
        int[] result = new int[uniqueElements.size()];
        int i = 0;
        for(int val : uniqueElements.keySet()){
            result[i++] = val;
        }
        return result;
    }
```

## Testing
```java
    @Test
    public void simpleTestMap1() {
        //Given
        int[] input = new int[]{1,1};
        //When
        int[] result = bean.removeDuplicatesWithMap(input);
        //Then
        assertAll(
                () -> assertEquals(1,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!")
        );
    }
    
    @Test
    public void simpleTestMap2() {
        //Given
        int[] input = new int[]{1,1,2,3,-2,-2};
        //When
        int[] result = bean.removeDuplicatesWithMap(input);
        //Then
        assertAll(
                () -> assertEquals(4,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!"),
                () -> assertEquals(2, result[1],"Content is incorrect!"),
                () -> assertEquals(3, result[2],"Content is incorrect!"),
                () -> assertEquals(-2, result[3],"Content is incorrect!")
        );
    }
```
![Capture d’écran 2023-12-12 à 14.04.50.png](https://ucarecdn.com/a6ade8aa-fe33-4ad2-bdcc-954a09445f61/)

# 6. Method 4: Using Stream API
Java 8 introduced the [Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) API, which provides an elegant way to process collections of data. We can leverage Streams to remove duplicates from an unsorted array while maintaining the order of elements.

To begin, we need to convert our array into a Stream using the `Arrays.stream()` method. This allows us to perform various operations on the elements of the array.

Next, we can use the `distinct()` method provided by Streams to eliminate duplicate elements. This method ensures that only unique elements are retained in the resulting Stream.

Finally, we can convert the Stream back into an array using the `toArray()` method. The order of elements in the resulting array will match the original order, with duplicates removed.

## Code
```java
    /**
     *
     * @param data: The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesWithStreams(int[] data){
        return Arrays.stream(data).distinct().toArray();
    }
```

## Testing
```java
@Test
    public void simpleTestStreams1() {
        //Given
        int[] input = new int[]{1,1};
        //When
        int[] result = bean.removeDuplicatesWithStreams(input);
        //Then
        assertAll(
                () -> assertEquals(1,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!")
        );
    }

    @Test
    public void simpleTestStreams2() {
        //Given
        int[] input = new int[]{1,1,2,3,-2,-2};
        //When
        int[] result = bean.removeDuplicatesWithStreams(input);
        //Then
        assertAll(
                () -> assertEquals(4,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!"),
                () -> assertEquals(2, result[1],"Content is incorrect!"),
                () -> assertEquals(3, result[2],"Content is incorrect!"),
                () -> assertEquals(-2, result[3],"Content is incorrect!")
        );
    }
```
![Capture d’écran 2023-12-12 à 14.06.02.png](https://ucarecdn.com/8ba2d707-8e20-4c47-879d-a0746583afa3/)

# 7. Method 5: In-place removal
In some scenarios, we may need to remove duplicates from an unsorted array without using any auxiliary data structure. This approach, known as in-place removal, allows us to modify the original array directly while preserving the order of elements.
To achieve in-place removal, we can utilize nested loops to compare each element with the previously discovered elements in the array. If the element was already discovered, we simply ignore it.

## Code
```java
    /**
     *
     * @param data: The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesInPlace(int[] data){
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (data[i] == data[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                data[index++] = data[i];
            }
        }

        return Arrays.copyOf(data, index);
    }
```

## Testing
```java
 @Test
    public void simpleTestInPlace1() {
        //Given
        int[] input = new int[]{1,1};
        //When
        int[] result = bean.removeDuplicatesInPlace(input);
        //Then
        assertAll(
                () -> assertEquals(1,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!")
        );
    }

    @Test
    public void simpleTestInPlace2() {
        //Given
        int[] input = new int[]{1,1,2,3,-2,-2};
        //When
        int[] result = bean.removeDuplicatesInPlace(input);
        //Then
        assertAll(
                () -> assertEquals(4,result.length,"Sizes don't match!"),
                () -> assertEquals(1, result[0],"Content is incorrect!"),
                () -> assertEquals(2, result[1],"Content is incorrect!"),
                () -> assertEquals(3, result[2],"Content is incorrect!"),
                () -> assertEquals(-2, result[3],"Content is incorrect!")
        );
    }
```
![Capture d’écran 2023-12-12 à 12.22.59.png](https://ucarecdn.com/799e41d3-2780-4cbb-948f-3324f9703b7a/)

# 8. Conclusion
To sum up, we have explored five different methods for removing duplicates from an unsorted array in Java: ArrayList, Set, Map, Stream API, and in-place removal without auxiliary data structures. Each method offers its advantages and considerations based on factors such as efficiency, simplicity, and memory usage.

ArrayList provides a straightforward approach using dynamic resizing, while Sets offer automatic duplicate elimination through their unique property. Maps provide a key-value structure to efficiently track and remove duplicates. Stream API brings concise syntax and functional programming techniques into play. Lastly, the in-place removal method allows for memory optimization by eliminating the need for auxiliary data structures.

When choosing the most suitable method for your specific scenario, consider factors such as the size of the array, time complexity requirements, memory constraints, and code readability. Experimenting with different approaches will help you find the optimal solution for your unique problem.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/core-java-remove-duplicates-array)

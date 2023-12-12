package com.kloudly.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArraysDuplicatesHelper {

    /**
     *
     * @param data : The input array. The array doesn't need to be sorted
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

    /**
     *
     * @param data : The input array. The array doesn't need to be sorted
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

    /**
     *
     * @param data : The input array. The array doesn't need to be sorted
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

    /**
     *
     * @param data : The input array. The array doesn't need to be sorted
     * @return an array where duplicate values have been removed. The insertion
     * order in the final array must be the same as in the input array
     */
    public int[] removeDuplicatesWithStreams(int[] data){
        return Arrays.stream(data).distinct().toArray();
    }

    /**
     *
     * @param data : The input array. The array doesn't need to be sorted
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
}

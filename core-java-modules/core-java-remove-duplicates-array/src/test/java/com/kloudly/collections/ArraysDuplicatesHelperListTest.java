package com.kloudly.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraysDuplicatesHelperListTest {
    private static ArraysDuplicatesHelper bean;
    @BeforeAll
    static void setUp(){
        bean = new ArraysDuplicatesHelper();
    }
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

}

package com.kloudly.stringrecipes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringRecipesTest {
    private static StringRecipes bean;

    @BeforeAll
    static void setUp(){
        bean = new StringRecipes();
    }

    @Test
    void testReplaceWhiteSpaceWithTrim(){
        //Given
        String toClear = "    Hello World   ";
        //When
        String result = toClear.trim();
        //Then
        assertEquals("Hello World", result);
    }

    @Test
    void testReplaceWhiteSpaceWithStrip(){
        //Given
        char unicodeSpaceCharacter = '\u2003';
        String toClearWithUnicode = unicodeSpaceCharacter +"Hello World" + unicodeSpaceCharacter;
        //When
        String result = toClearWithUnicode.strip();
        //Then
        assertEquals("Hello World", result);
    }

    @Test
    void testIsBlank(){
        //Given
        char asciiSpaceCharacter = ' ';
        char unicodeSpaceCharacter = '\u2003';
        String asciiBlankString = asciiSpaceCharacter + "";
        String unicodeBlankString = unicodeSpaceCharacter+"";
        //When
        //Then
        assertTrue(unicodeBlankString.isBlank());
        assertTrue(asciiBlankString.isBlank());
    }

    @Test
    void testIsEmpty(){
        //Given
        char asciiSpaceCharacter = ' ';
        char unicodeSpaceCharacter = '\u2003';
        String asciiBlankString = asciiSpaceCharacter + "";
        String unicodeBlankString = unicodeSpaceCharacter+"";
        String emptyString = "";
        //When
        //Then
        assertFalse(unicodeBlankString.isEmpty());
        assertFalse(asciiBlankString.isEmpty());
        assertTrue(emptyString.isEmpty());
        assertTrue(asciiBlankString.trim().isEmpty());
        assertTrue(unicodeBlankString.strip().isEmpty());
    }

    @Test
    void testStringCompareUsingDoubleEqualSymbol(){
        //Given
        String string1 = "String1";
        String string2 = "String1";
        String string3 = new String("String1");
        //When
        //Then
        assertTrue(string1 == string2);
        assertFalse(string1 == string3);
    }

    @Test
    void testStringCompareEqual(){
        //Given
        String string1 = "String1";
        String string2 = "String1";
        String string3 = new String("String1");
        //When
        //Then
        assertTrue(string1.equals(string2));
        assertTrue(string1.equals(string3));
    }

    @Test
    void testStringTokenizing(){
        //Given
        String myString = "Hello, World, From, Paris";
        //When
        String[] tokens = myString.split(",");
        //Then
        assertEquals(4,tokens.length);
        assertEquals("Hello",tokens[0].trim());
        assertEquals("World",tokens[1].trim());
        assertEquals("From",tokens[2].trim());
        assertEquals("Paris",tokens[3].trim());
    }

    @Test
    void testStringReplaceAndReplaceAll(){
        //Given
        String myString = "Hello World";
        //When
        String updatedWithReplace = myString.replace("l","L");
        String updatedWithReplaceAll = myString.replaceAll("[l]","L");
        //Then
        assertEquals("HeLLo WorLd",updatedWithReplace);
        assertEquals("HeLLo WorLd",updatedWithReplaceAll);
    }

    @Test
    void testStringMatches(){
        //Given
        String myString = "Hello World";
        //When
        boolean containsLetterL = myString.matches(".*[l].*");
        boolean containsDigit = myString.matches("\\d");
        //Then
        assertTrue(containsLetterL);
        assertFalse(containsDigit);
    }

    @Test
    void testIsPalindrome(){
        //Given
        String palindrome = "level";
        String notPalindrome = "levels";
        //When
        boolean result1 = bean.isPalindrome(palindrome);
        boolean result2 = bean.isPalindrome(notPalindrome);
        //Then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void testSwitchCharToUpperCase(){
        //Given
        String simple = "Hello world!";
        //When
        String result = bean.switchCharacterToUpperCase(simple,'o');
        //Then
        assertEquals("HellO wOrld!",result);

    }

    @Test
    void testContainsOnlyDigitsLoop(){
        //Given
        String validNumber = "12345";
        String invalidNumber = "1234a";
        //When
        boolean result1 = bean.containsOnlyDigitsLoop(validNumber);
        boolean result2 = bean.containsOnlyDigitsLoop(invalidNumber);
        //Then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void testContainsOnlyDigitsStream(){
        //Given
        String validNumber = "12345";
        String invalidNumber = "1234a";
        //When
        boolean result1 = bean.containsOnlyDigitsStream(validNumber);
        boolean result2 = bean.containsOnlyDigitsStream(invalidNumber);
        //Then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void testChristmasTree(){
        //Given
        int length = 11;
        //When
        String tree = bean.buildChristmasTree(length);
        //Then
        System.out.println(tree);
    }
    @Test
    void testAddLineNumbers(){
        //Given
        String input = """
                Java is an awesome programming language
                Python is a fabulous programming language
                PHP is another awesome programming language
                """;
        //When
        List<String> result = bean.addLineNumbers(input);
        //Then
        result.forEach(System.out::println);
    }

    @Test
    void testJoiningStrings(){
        //Given
        String[] input = {"John", "Doe", "30", "Software Engineer"};
        String expected = "John,Doe,30,Software Engineer";
        //When
        String result1 = bean.joinWithStringBuilder(input);
        String result2 = bean.joinWithStringJoin(input);
        String result3 = bean.joinWithStringJoiner(input);
        String result4 = bean.joinWithStream(input);
        //Then
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
    }
}

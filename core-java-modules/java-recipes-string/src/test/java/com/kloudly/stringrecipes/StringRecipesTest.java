package com.kloudly.stringrecipes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringRecipesTest {

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
}

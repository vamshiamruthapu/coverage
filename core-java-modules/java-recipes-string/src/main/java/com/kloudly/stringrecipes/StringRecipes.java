package com.kloudly.stringrecipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringRecipes {

    public boolean isPalindrome(String toCheck){
        int n = toCheck.length();
        for(int i=0; i< n; i++){
            char firstChar = toCheck.charAt(i);
            char secondChar = toCheck.charAt(n - i - 1);
            if(firstChar != secondChar)
                return false;
        }
        return true;
    }

    public String buildChristmasTree(int height){
        StringBuilder tree = new StringBuilder();
        for(int i=1; i<= height; i+=2){
            //Building the repeating symbol *
            StringBuilder star = new StringBuilder();
            for(int j=1; j<=i; j++){
                star.append("*");
            }

            //Building the repeating space
            StringBuilder space = new StringBuilder();
            for(int j=1; j<= (height - i) / 2; j++){
                space.append(" ");
            }

            tree.append(space).append(star).append(space);

            if(i < height)
                tree.append("\n");
        }

        return tree.toString();
    }

    public String switchCharacterToUpperCase(String input, char charToUpper){
        StringBuilder sb = new StringBuilder();
        for(char c : input.toCharArray()){
            if(c == charToUpper){
                sb.append(Character.toUpperCase(charToUpper));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public boolean containsOnlyDigitsLoop(String input){
        for(char c : input.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public boolean containsOnlyDigitsStream(String input){
        return input.chars().allMatch(Character::isDigit);
    }

    public List<String> addLineNumbers(String input){
        List<String> output = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(1);
        input.lines()
                .map(line -> new StringBuilder().append(counter.getAndIncrement()).append(" - ").append(line).toString())
                .forEach(output::add);
        return output;
    }

    public String joinWithStringBuilder(String[] input){
        StringBuilder sb = new StringBuilder();
        String delimiter = ",";
        int counter = 0;
        for(String str : input){
            sb.append(str);
            if(counter < input.length - 1)
                sb.append(delimiter);
            counter++;
        }
        return sb.toString();
    }

    public String joinWithStringJoin(String[] input){
        String delimiter = ",";
        return String.join(delimiter,input);
    }

    public String joinWithStringJoiner(String[] input){
        String delimiter = ",";
        StringJoiner joiner = new StringJoiner(delimiter);
        for(String str : input){
            joiner.add(str);
        }
        return joiner.toString();
    }

    public String joinWithStream(String[] input){
        String delimiter = ",";
        return Stream.of(input).collect(Collectors.joining(delimiter));
    }
}






















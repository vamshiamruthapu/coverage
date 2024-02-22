Java 17 String Manipulation: 5 Essential Recipes for Technical Interviews and Coding Challenges (Part 2)
======================================================
## Overview
**String manipulation** is an essential skill in programming, virtually unavoidable in technical interviews and coding challenges.
In this article, I will share valuable Java 17 **String Recipes** gleaned from a decade of coding experience. 
This article is one of a series dedicated to [string manipulation](https://nkamphoa.com/tag/string/) in Java.

## Version check
This tutorial has been tested with the following tools :
- Java 17
- Junit 5.9.2

## String Recipes 1: Iterating over characters
This is undoubtedly one of the most common scenarios you'll encounter when working with strings.  
### Using an index
This approach is preferable when you want to keep track of the position of the character you've traversed.  
This is the case, for example, with [palindrome](https://en.wikipedia.org/wiki/Palindrome) detection algorithms. 
#### Code
```java
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
```
#### Testing
```java
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
```

### Using `Char.toCharArray()`
Another approach is to convert the string into an array of characters and browse the array.
This approach avoids repetitive calls to "String.charAt()", which can have an impact on performance. It does, however, require additional memory space to store the array.  
This approach should therefore be avoided for very large strings.
#### Code
Let's assume we want to turn to uppercase a specific character from a String.
```java
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
}
```

#### Testing

```java
    @Test
    void testSwitchCharToUpperCase(){
        //Given
        String simple = "Hello world!";
        //When
        String result = bean.switchCharacterToUpperCase(simple,'o');
        //Then
        assertEquals("HellO wOrld!",result);

    }
```

## String Recipes 2: Building String in a loop
In Java, there are two classic ways of concatenating strings. The first is by using the "+" symbol, as in ```String str = "Hello " + "World";```. 
The second is by using the "String.concat()" method, as in ```String str = "Hello ".concat("World");```.  
However, these two approaches are not appropriate when you need to perform multiple concatenations, as in a loop for example.  
This is because, as the String class is immutable, each concatenation creates a new object, which can have a significant impact on the performance.
It is therefore advisable to use the StringBuilder class when you need to concatenate strings several times.
#### Code

```java
    public String displayChristmasTree(int height){
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
```
#### Testing
```java
    @Test
    void testChristmasTree(){
        //Given
        int length = 11;
        //When
        String tree = bean.buildChristmasTree(length);
        //Then
        System.out.println(tree);
    }
```
![String in Loop](https://nkamphoa.com/wp-content/uploads/2024/02/string_in_loop.png)

## String Recipes 3: Check if a String is a Number
Sometimes we may want to check whether a string of characters received as a parameter consists solely of numbers.  
Either because we want to convert it to an integer, or simply because we need to validate a business rule.  
There are several possible solutions. Here, we present just two: One with a classic loop and another using the Java Stream api.
### Using a classic `for` loop
#### Code
```java
    public boolean containsOnlyDigitsLoop(String input){
        for(char c : input.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
```
#### Testing
```java
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
```

### Using the Java 8 Stream API
#### Code
```java
    public boolean containsOnlyDigitsStream(String input){
        return input.chars().allMatch(Character::isDigit);
    }
```
#### Testing
```java
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
```
## String Recipes 4: Iterating over many lines
One common scenario where you might need to iterate over the lines of a String in Java is when you're parsing or processing multiline text, such as reading input from a file or processing textual data.
In the following example, we receive a multiline string as a parameter. The task is to add a line number to the beginning of each line.
### Code
```java
    public List<String> addLineNumbers(String input){
        List<String> output = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(1);
        input.lines()
                .map(line -> new StringBuilder().append(counter.getAndIncrement()).append(" - ").append(line).toString())
                .forEach(output::add);
        return output;
    }
```
### Testing
```java
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
```
![Multi lines String](https://nkamphoa.com/wp-content/uploads/2024/02/multi_lines_string.png)

## String Recipes 4: Joining Strings
We saw in a previous article how to split a string using a given delimiter.  
In some cases, we may need to do the opposite. Typical use cases are : Building CSV files, generating SQL queries, etc.
Here I present 3 possible solutions, so you're free to choose the one that suits your situation.
In this recipe, the task is to produce a comma-delimited String (CVS file entry) from an array of Strings  
Input : ```String[] values = {"John", "Doe", "30", "Software Engineer"};```  
Output : ```line = "John, Doe, 30, Software Engineer";```
### Using the classic `StringBuilder`
```java
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
```
### Using Java 8 `String.join()`
```java
    public String joinWithStringJoin(String[] input){
        String delimiter = ",";
        return String.join(delimiter,input);
    }
```
### Using Java 8 `StringJoiner`
```java
    public String joinWithStringJoiner(String[] input){
        String delimiter = ",";
        StringJoiner joiner = new StringJoiner(delimiter);
        for(String str : input){
            joiner.add(str);
        }
        return joiner.toString();
    }
```
### Using Java 8 Stream API
```java
    public String joinWithStream(String[] input){
        String delimiter = ",";
        return Stream.of(input).collect(Collectors.joining(delimiter));
    }
```
### Testing
```java
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
```

![String Join](https://nkamphoa.com/wp-content/uploads/2024/02/string_join.png)

## Conclusion
In this article, we looked at some important techniques for manipulating strings in Java.
These recipes will be very useful for your coding challenges. In our next article in the series, we'll cover other recipes for string manipulation in Java.

Did you find this blog post useful? Feel free to drop a thumbs up or comment. If you've had any difficulty completing this tutorial, leave me a comment, and I'll be happy to help.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/java-recipes-string)

*Happy Coding! And good luck with your next coding challenge!*
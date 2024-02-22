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

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip10.java

#### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip11.java

### Using `Char.toCharArray()`
Another approach is to convert the string into an array of characters and browse the array.
This approach avoids repetitive calls to "String.charAt()", which can have an impact on performance. It does, however, require additional memory space to store the array.  
This approach should therefore be avoided for very large strings.
#### Code
Let's assume we want to turn to uppercase a specific character from a String.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip12.java

#### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip13.java

## String Recipes 2: Building String in a loop
In Java, there are two classic ways of concatenating strings. The first is by using the "+" symbol, as in ```String str = "Hello " + "World";```.
The second is by using the "String.concat()" method, as in ```String str = "Hello ".concat("World");```.  
However, these two approaches are not appropriate when you need to perform multiple concatenations, as in a loop for example.  
This is because, as the String class is immutable, each concatenation creates a new object, which can have a significant impact on the performance.
It is therefore advisable to use the StringBuilder class when you need to concatenate strings several times.
#### Code

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip14.java

#### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip15.java

![String in Loop](https://nkamphoa.com/wp-content/uploads/2024/02/string_in_loop.png)

## String Recipes 3: Check if a String is a Number
Sometimes we may want to check whether a string of characters received as a parameter consists solely of numbers.  
Either because we want to convert it to an integer, or simply because we need to validate a business rule.  
There are several possible solutions. Here, we present just two: One with a classic loop and another using the Java Stream api.
### Using a classic `for` loop
#### Code

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip16.java

#### Test

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip17.java

### Using the Java 8 Stream API
#### Code

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip26.java

#### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip18.java

## String Recipes 4: Iterating over many lines
One common scenario where you might need to iterate over the lines of a String in Java is when you're parsing or processing multiline text, such as reading input from a file or processing textual data.
In the following example, we receive a multiline string as a parameter. The task is to add a line number to the beginning of each line.
### Code

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip19.java

### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip20.java

![Multi lines String](https://nkamphoa.com/wp-content/uploads/2024/02/multi_lines_string.png)

## String Recipes 4: Joining Strings
We saw in a previous article how to split a string using a given delimiter.  
In some cases, we may need to do the opposite. Typical use cases are : Building CSV files, generating SQL queries, etc.
Here I present 3 possible solutions, so you're free to choose the one that suits your situation.
In this recipe, the task is to produce a comma-delimited String (CVS file entry) from an array of Strings  
Input : ```String[] values = {"John", "Doe", "30", "Software Engineer"};```  
Output : ```line = "John, Doe, 30, Software Engineer";```
### Using the classic `StringBuilder`

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip21.java

### Using Java 8 `String.join()`

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip22.java

### Using Java 8 `StringJoiner`

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip23.java

### Using Java 8 Stream API

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip24.java

### Testing

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip25.java

![String Join](https://nkamphoa.com/wp-content/uploads/2024/02/string_join.png)

## Conclusion
In this article, we looked at some important techniques for manipulating strings in Java.
These recipes will be very useful for your coding challenges. In our next article in the series, we'll cover other recipes for string manipulation in Java.

Did you find this blog post useful? Feel free to drop a thumbs up or comment. If you've had any difficulty completing this tutorial, leave me a comment, and I'll be happy to help.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/java-recipes-string)

*Happy Coding! And good luck with your next coding challenge!*
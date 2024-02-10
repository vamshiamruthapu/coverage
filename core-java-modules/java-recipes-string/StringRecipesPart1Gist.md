Java 17 String Manipulation: 5 Essential Recipes for Technical Interviews and Coding Challenges (Part 1)
======================================================
## Overview
**String manipulation** is an essential skill in programming, virtually unavoidable in technical interviews and coding challenges.
In this article, I will share valuable Java 17 **String Recipes** gleaned from a decade of coding experience.

## Version check
This tutorial has been tested with the following tools :
- Java 17
- Junit 5.9.2

## String Recipes 1: Remove Whitespace
How would you remove trailing and leading whitespace from a string?   
Assuming that you have the following string literal : "    Hello   World  "  
How would you make it "Hello World" ?

### Use `String.trim()`
The [String.trim()](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/String.html#trim()) method deletes blank characters at the beginning and end of a string. Note that this method only considers ASCII characters.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip1.java

![Test Trim](https://nkamphoa.com/wp-content/uploads/2024/02/trim_test.png)

### Use Java 11 `String.strip()`
The [String.strip()](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.base/java/lang/String.html#strip()) method is an evolved version of String.strim() that takes UNICODE characters into account. It should be noted that this method was not introduced until Java 11.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip2.java

![Test Strip](https://nkamphoa.com/wp-content/uploads/2024/02/strip_test.png)

## String Recipes 2: Check if String is Empty or Blank
It's important to note the difference between these two terms. A string is considered "Empty" when it is of size 0. On the other hand, it's considered "Blank" when it's made up entirely of "white" characters.

### isEmpty

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip3.java

![Test isEmpty](https://nkamphoa.com/wp-content/uploads/2024/02/isEmpty_test.png)

### isBlank

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip4.java

![Test isBlank](https://nkamphoa.com/wp-content/uploads/2024/02/isBlank_test.png)

## String Recipes 3: Compare two Strings
In Java, there are two ways of comparing two strings (and objects in general). The first is to use the "==" symbol and the second is to use the `equals()` method.

### Using double equal symbol "=="
When using this approach, we are actually comparing the references of the two strings.
You should use this if and only if you are working with strings created without the new keyword.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip5.java

![Test Compare With symbols](https://nkamphoa.com/wp-content/uploads/2024/02/compareWithSymbol_test.png)

### Using `String.equals()` (Recommended)
This is the right way of comparing two strings, as it will compare the content of the strings, not their references.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip6.java.

![Test Compare With equals](https://nkamphoa.com/wp-content/uploads/2024/02/compareWithEquals_test.png)

**Best Practice* : Always code **defensively** : prefer this `"myString".equals(myOtherString)` over this `myOtherString.equals("myString")` to prevent unexpected NullPointerException.*
## String Recipes 4: Find and Replace
### How to use `String.matches()`
The [String.matches()](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#matches-java.lang.String-) method takes a regular expression as parameter and returns `true` if it is found in the string and `false` otherwise.
It can be very useful for searching for a sub-string in a string.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip7.java

![String Recipes Matches](https://nkamphoa.com/wp-content/uploads/2024/02/matches_test.png)

### Understand the difference between `String.replace()` and `String.replaceAll()`
What you need to know is that both methods do exactly the same thing. The only difference is that `String.replace()` accepts a string as parameter, whereas `String.replaceAll()` accepts a regular expression as parameter.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip8.java

![String Recipes Replace](https://nkamphoa.com/wp-content/uploads/2024/02/replace_test.png)
**Best Practice* : Avoid using `String.replaceAll()` when you are not dealing with regular expressions. Doing so will result in a huge performance impact.
## String Recipes 5: Tokenize a String
Tokenization is the process of breaking up a string into so-called tokens. Often times, these tokens are words, numbers, and/or punctuation.
There are different ways of tokenizing a string in java. We will be focusing on what is for me the easiest way : `String.split()`
`String.split()` will take the delimiter as a Regex and return the tokens into an array of strings.

https://gist.github.com/elkamphy/e8ce1107fea629f080f5ab25c61e6177#StringManip9.java
![String Recipes Split](https://nkamphoa.com/wp-content/uploads/2024/02/split_test.png)
**Tip* : Since String.split() accepts a regex, you can use `String.split("\\s")` on a sentence to obtain the list of words.*
## Conclusion
In this article, we looked at some important techniques for manipulating strings in Java.
These recipes will be very useful for your coding challenges. In our next article in the series, we'll cover other recipes for string manipulation in Java.

Did you find this blog post useful? Feel free to drop a thumbs up or comment. If you've had any difficulty completing this tutorial, leave me a comment, and I'll be happy to help.

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/java-recipes-string)

*Happy Coding! And good luck with your next coding challenge!*
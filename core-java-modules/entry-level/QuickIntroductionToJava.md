Java Introduction: A Beginner's Guide in 7 Key Points
======================================================
## Quick Introduction
Today, **Java** is one of the most popular programming languages on the market. According to the [TIOBE index of 2024](https://www.tiobe.com/tiobe-index/), it is the **4th most widely used** language. Why have so many companies adopted Java over the years? In this article, we'll delve into the **Java Essentials**, exploring the language's strengths.

## 1 - A High-level Programming Language
Java is a [high-level](https://en.wikipedia.org/wiki/High-level_programming_language) programming language. This means that when you write a program in Java, you don't have to worry about **low-level details** like memory management, register management, etc. The developer who programs in Java doesn't need to have any knowledge of the hardware (CPU, RAM) on which his program will be run. All this makes a Java program simpler to write and easier to understand. This is not the case with low-level languages such as C or Assembly.

## 2 - Write Once, Run Anywhere
Java is a programming language that is both **compiled** and **interpreted**.
Java is compiled because its source code is transformed into [byte-code](https://en.wikipedia.org/wiki/Java_bytecode) during the compilation phase.
The byte-code is then interpreted by the virtual machine (JVM) during the execution phase.
Java owes its **portability** to the use of the JVM. Indeed, any environment (Windows, Linux, macOS, cell phone, Web browser) with a Java virtual machine is capable of executing a compiled Java program.  
This portability is the origin of the slogan: [Write Once, Run Anywhere](https://en.wikipedia.org/wiki/Write_once,_run_anywhere).

## 3 - Object-Oriented Language
Java owes much of its renown to the fact that it is an [object-oriented language](https://en.wikipedia.org/wiki/Object-oriented_programming). Object-oriented programming (OOP) is a programming paradigm inspired by real life. This paradigm conceives a computer program as a set of interacting objects. An object is thus characterized by its properties and behaviors. Properties correspond to attributes (size, age, gender, etc.) and behaviors correspond to functions that can be called up on the object. The advantages of OOP over other programming paradigms include **code modularity**, ease of **maintenance** and **reusability**.

## 4 - Robustness and Security
In computer science, [robustness](https://en.wikipedia.org/wiki/Robustness_(computer_science)) is the ability of a computer system to cope with errors during execution and with erroneous input.  
The robustness of the Java language is guaranteed by the following mechanisms:
### Memory management
Memory management is undoubtedly the most important pillar of the Java language's robustness. Thanks to automatic memory management via the **Garbage Collector**, Java programs are less prone to memory leaks.
### Exception handling
Java has an advanced exception-handling mechanism. An [exception](https://en.wikipedia.org/wiki/Garbage_collection_(computer_science)) is an event that occurs during program execution and prevents the program from continuing normally.
In Java, when an exception is raised, the developer has the option of either handling it (try-catch) or propagating it (throws) to a higher level. This helps in writing more reliable and fault-tolerant code.
### Strong Type Checking
Java is a **statically-typed** language, meaning that variable types are explicitly declared during compilation. This helps catch type-related errors at compile-time, enhancing code robustness.
### Security
Java is well known for its robust [security mechanisms](https://www.oracle.com/java/technologies/security-in-java.html#:~:text=The%20bytecode%20verifier%20acts%20as,has%20passed%20the%20verifier's%20tests.), which were an integral part of its design.
Among the most important points of this security are :
- **Sandboxing and Security Manager**: Java applications run within a sandboxed environment, which restricts their access to certain system resources. The Security Manager, a component of the Java Runtime Environment (JRE), defines and enforces the access rules. This helps prevent untrusted code from performing malicious actions.
- **Bytecode Verification**: Before being executed by the JVM, the byte-code goes through a verification process to check that it conforms to the specifications.
- **Classloaders**: Java's classloaders provide a mechanism for dynamically loading classes at runtime. They contribute to security by allowing the isolation of classes and preventing unauthorized access to sensitive classes or resources.

## 5 - Multithreading
[Multi-threading](https://en.wikipedia.org/wiki/Multithreading_(computer_architecture)#:~:text=In%20computer%20architecture%2C%20multithreading%20is,This%20approach%20differs%20from%20multiprocessing.) is the ability for a program to ensure several execution streams in parallel.  
Java has a set of libraries that facilitate the implementation of multithreaded programs.

## 6 - High Performance
As mentioned above, Java applications run on a virtual machine (JVM), a layer of abstraction between byte-code and hardware architecture. One might be tempted to think that this would have a significant impact on performance.
However, thanks to concepts such as [Just In Time Compilation (JIT)](https://en.wikipedia.org/wiki/Just-in-time_compilation) and optimized memory management, including [garbage collection](https://en.wikipedia.org/wiki/Garbage_collection_(computer_science)), Java applications approach the performance of native applications.

## 7 - Strong Community and Resources
There's a reason why Java is ranked as the 4th most popular programming language in 2024 according to the TIOBE index. Java has a very strong developer community. When you encounter a problem in Java, there's a very good chance that another developer has already had it before you.  
A simple search in a forum like [StackOverflow](https://stackoverflow.com/questions) and you'll have the answer to your concern. Java's popularity also means that there are many frameworks and libraries available to address common development issues.  
The most popular Java frameworks include [Spring](https://spring.io/), [GWT](https://www.gwtproject.org/), [JSF](https://en.wikipedia.org/wiki/Jakarta_Server_Faces), [Hibernate](https://hibernate.org/), etc.


## Conclusion
To conclude this blog on Java Essentials, we've seen that Java is a high-level, object-oriented programming language, enabling you to write programs without worrying too much about so-called low-level aspects such as memory management. We have also seen that a program written in Java on one operating system can be run on any other system, thanks to the existence of the virtual machine. Finally, we've seen that the very strong community that exists around Java means that most of the problems you'll encounter, someone before you will already have had.
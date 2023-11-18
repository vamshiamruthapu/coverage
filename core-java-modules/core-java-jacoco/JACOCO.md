## 1. Overview
In this quick tutorial, we are going to see how to generate a [code coverage](https://en.wikipedia.org/wiki/Code_coverage) report for a maven project using JaCoCo.

[JaCoCo](https://github.com/jacoco/jacoco) is a free [Java code coverage library](https://en.wikipedia.org/wiki/Java_code_coverage_tools) distributed under the Eclipse Public License.

## 2. Version check
This tutorial has been tested with the following tools :
- Java 8
- Maven 3.8.6
- Junit 5.9.2
- Maven Surefire Plugin 2.22.0
- JaCoCo Plugin for Maven 0.8.7
## 3. Sample Code
In order to generate a coverage report we need some code to work with.
### 3.1. Simple calculator Class
The code we are going to use in this tutorial is a simple Java class which performs basic mathematical operations. Here is the snippet of the class :
``` java
	//Simple calculator to perform basic mathematical operations
public class Calculator {
    public double calculate(double firstOperand, double secondOperand, char operator){
        switch (operator){
            case '+':{
                return add(firstOperand,secondOperand);
            }
            case '-':{
                return subtract(firstOperand,secondOperand);
            }
            case '*':{
                return multiply(firstOperand,secondOperand);
            }
            case ':':{
                return divide(firstOperand,secondOperand);
            }
            default:
                throw new IllegalArgumentException("Unsupported operation :"+operator);
        }
    }

    private double divide(double firstOperand, double secondOperand) {
        if(secondOperand == 0)
            throw new IllegalArgumentException("Second argument must not be zero!");
        return firstOperand / secondOperand;
    }

    private double multiply(double firstOperator, double secondOperand) {
        return firstOperator * secondOperand;
    }

    private double subtract(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    private double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }
}
   
```
### 3.2. Simple calculator Test Class
Below is the test class associated to the Calculator class. We have just provided few tests cases, so we can highlight the parts of our code which are covered by tests and the parts which are not.
``` java
	//Junit test class for Simple calculator
public class CalculatorTest {
    private static Calculator calculator;
    @BeforeAll
    static void setUp(){
        calculator = new Calculator();
    }
    @Test
    void addSimple(){
        double result = calculator.calculate(1,1,'+');
        Assertions.assertEquals(2,result);
    }
    @Test
    void multiplySimple(){
        double result = calculator.calculate(1,1,'*');
        Assertions.assertEquals(1,result);
    }
    @Test
    void subtractSimple(){
        double result = calculator.calculate(1,1,'-');
        Assertions.assertEquals(0,result);
    }
    @Test
    void divideSimple(){
        double result = calculator.calculate(1,1,':');
        Assertions.assertEquals(1,result);
    }
}   
 ```
## 4. Adding JaCoCo Plugin to Maven
To be able to run the code coverage within a maven project, you need add the JaCoCo maven plugin in the build section of your pom.xml, inside the plugins tag, like shown below.
``` xml
	--Adding jacoco plugin in pom.xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.7</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <!-- attached to Maven test phase -->
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
      --other plugings
    </plugins>
```

Please, note that in order to execute your unit tests via maven, the **surefire plugin** needs to be declared in your pom.xml as well. If this is not already the case, add the following line in the build section of your pom.xml, inside the plugins tag.

```xml
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.0</version>
        </plugin>
```
## 5. Generating the coverage
### 5.1. Running the test
Run the following command from the root directory of your project to generate the code coverage of your project.
```console
foo@bar:~$ mvn clean test
```
Running the previous command will trigger the **JaCoCo agent** and generate the coverage report in a **binary format** under **target/jacoco.exec**.
![Capture d’écran 2023-11-11 à 15.54.40.png](https://ucarecdn.com/0b8581d5-ed2b-46fc-af62-edee3059256d/)

The file can be viewed in a more fashionable way with tools like [SonarQube](https://www.sonarsource.com/).
Fortunately, with the help of the **report goal** of JaCoCo Maven Plugin, we can view the coverage report directly in HTML format.
### 5.2. Viewing the result
Open the following file to see the results directly in a browser : *target/site/jacoco/index.html*
![Capture d’écran 2023-11-11 à 13.19.02.png](https://ucarecdn.com/2ef5a9f0-c32a-4a68-aeb6-641302ae8d66/)

From here, you may click on the package name to have the detailed coverage per class.

![Capture d’écran 2023-11-11 à 14.12.51.png](https://ucarecdn.com/4ecdd9f3-0870-4112-97f2-513504c09e74/)

Below is the legend to better understand this image :
- **Green** : Code is covered by a test
- **Red** : Code is not covered by any test
- **Yellow** : Code is partially covered by test

### 5.3. Adding more tests
Let's add few more tests cases to achieve **100% coverage**. Add the following tests cases in the CalculatorTest class:
```java
public class CalculatorTest {
    //.....
    @Test
    void illegalArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, 1, '/'));
    }

    @Test
    void divideByZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, 0, ':'));
    }
}
```

Now, run *mvn clean test* again and you should see this :

![Capture d’écran 2023-11-11 à 14.29.07.png](https://ucarecdn.com/57cdde9f-8f34-46cd-bcab-7099357fc247/)

![Capture d’écran 2023-11-11 à 14.29.28.png](https://ucarecdn.com/34262dae-982b-4f45-9d80-2ed464f49059/)

As you can see, every single line of code is now covered by tests.
## 6. Conclusion
In this quick tutorial, we learned how to make use of the JaCoCo maven plugin to generate code coverage reports for Java projects. For more in-depth use of JaCoCo, refer to the official documentation available [here](https://www.jacoco.org/jacoco/trunk/doc/).

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/core-java-jacoco)

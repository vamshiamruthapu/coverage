## 1. Overview
In this quick tutorial, we will see how to perform bug detection in a Java application using SpotBugs and Maven.

[SpotBugs](https://spotbugs.github.io/) is a program that uses [static analysis](https://en.wikipedia.org/wiki/Static_program_analysis) to look for bugs in Java code. It is free software, distributed under the terms of the GNU Lesser General Public License. SpotBugs is a fork of [FindBugs](https://findbugs.sourceforge.net/) which is no longer maintained.

Spotbugs can be used as a standalone application, as well as through several integrations including Maven, Gradle, Eclipse, and IntelliJ. In this tutorial, we are focusing on Maven integration.

You need at least Java 8 to run SpotBugs, but you can analyze programs written with older versions of Java.

## 2. Version check
This tutorial has been tested with the following tools :
- Java 17
- Maven 3.8.6
- SpotBugs Plugin for Maven 4.8.1.0
- Maven Site Plugin 4.0.0-M9
## 3. Sample Code
To generate a bug report we need some code to work with. For this tutorial, we are using a project with a simple Java class Flight shown below :
```java
package com.kloudly.spotbugs;

import java.util.Date;

public class Flight {
    private Date departureTime;
    private Date arrivalTime;

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
```

## 4. Adding SpotBugs Plugin to Maven
Typically, SpotBugs will generate a report in an XML format (target/spotbugsXml.xml). To have a prettier report, we need to add the SpotBugs plugin in the reporting phase of Maven.

```xml
<reporting>
        <plugins>
            <!-- Spotbugs report-->
            <plugin>
                <groupId>com.github.spotbugs</groupId>
                <artifactId>spotbugs-maven-plugin</artifactId>
                <version>${maven.spotbugs.version}</version>
                <configuration>
                    <jvmArgs>-Duser.language=en</jvmArgs>
                </configuration>
            </plugin>
        </plugins>
  </reporting>
```

We are using the *"jvmArgs"* option to force the report to be fully displayed in English.

Because the report is produced during the **maven site goal**, we also need to add the Maven Site plugin to our build.
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-site-plugin</artifactId>
                <version>${maven.site.version}</version>
            </plugin>
        </plugins>
    </build>
```

## 5. Generating the report
### 5.1. Running maven site
Run the following command from the root directory of your project to produce the bugs report :
```console
foo@bar:~$ mvn clean compile site
```
### 5.2. Viewing the result
Navigate to the following directory and open the file to see the results directly in a browser : *target/site/spotbugs.html*
![Capture d’écran 2023-11-18 à 14.48.19.png](https://ucarecdn.com/fa7aaebb-5751-4c46-89cb-ba5657ed6118/)

![Capture d’écran 2023-11-15 à 18.38.05.png](https://ucarecdn.com/aaacc098-61d5-49ca-8c19-eeba6106a09c/)

From this report, we can see that our Flight class has 4 bugs with **medium priority**.  Next, let's see how to fix those bugs.

### 5.3. Fixing some bugs
The bug [EI_EXPOSE_REP](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#malicious-code-vulnerability-malicious-code) is triggered whenever you return a **reference to a mutable** object value stored in one of the object's fields. For this specific case, the returned java.util.Date class is mutable, hence the rule is broken.

To fix this, we can do any of the following :
1. Change the setters and getters implementations to use **non-mutable** copies of the fields.
2. Change the field type from java.util.Date to **java.time.LocalDateTime** (which is not mutable)

#### Using the clone method
Update your class as shown below :
```java
package com.kloudly.spotbugs;

import java.util.Date;

/**
 * This Flight class uses clone() method to avoid
 * returning a reference to a mutable object
 */
public class FlightV2 {
    private Date departureTime;
    private Date arrivalTime;
    
    public Date getDepartureTime() {
        if(departureTime != null){
            return (Date) departureTime.clone();
        }
        return null;
    }

    public void setDepartureTime(Date departureTime) {
        if(departureTime != null){
            this.departureTime = (Date) departureTime.clone();
        }
    }

    public Date getArrivalTime() {
        if(arrivalTime != null){
            return (Date) arrivalTime.clone();
        }
        return null;
    }

    public void setArrivalTime(Date arrivalTime) {
        if(arrivalTime != null){
            this.arrivalTime = (Date) arrivalTime.clone();
        }
    }
}
```
#### Using the java.time.LocalDateTime class
Update your class as shown below :
```java
package com.kloudly.spotbugs;

import java.time.LocalDateTime;

/**
 * This Flight class uses LocalDateTime  to avoid
 * returning a reference to a mutable object.
 * LocalDateTime is by definition not mutable
 */
public class FlightV3 {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
```

After implementing any of the previous suggested changes, running **"mvn clean compile site"** again will produce a **free-bugs report** like the one below :

![Capture d’écran 2023-11-18 à 15.53.55.png](https://ucarecdn.com/50f63813-99d6-40c3-b51e-e4148b6451ca/)

## 6. Conclusion
In this quick tutorial, we learned how to make use of the **SpotBugs maven plugin** to list the bugs in a Java application. For more in-depth use of SpotBugs, refer to the official documentation available [here](https://spotbugs.readthedocs.io/en/latest/).

The complete code used in this article can be found [here in GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/core-java-spotbugs)

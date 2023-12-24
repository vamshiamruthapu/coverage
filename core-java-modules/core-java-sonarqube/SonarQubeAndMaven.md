5 Steps to Integrate Maven with SonarQube for Effective Quality Assurance
=========================================================================
# Overview
It has been statistically proven that, as software developers, we spend 80% of our time reading code and only 20% of our time writing it.  So it's important that during that 20% of our time, we write code that will make our lives easier during the 80% of the time we have to maintain it. So you'll be hearing more and more about clean code.

The term clean code can have several definitions depending on the author, but the one that comes up most often is the following: [Clean Code](https://www.sonarsource.com/solutions/clean-code/#:~:text=Clean%20Code%20is%20code%20that's,value%20out%20of%20your%20software.) is code that's easy to read, maintain, understand and change through structure and consistency yet remains robust and secure to withstand performance demands.

So you guessed it, in this article, we will be talking about Clean Code. We'll be looking at one of the most popular tools on the market on the subject, SonarQube. We'll look at integrating SonarQube into a Maven project to generate code quality metrics. Ready to explore further? Let's get started!

# Version Check
This tutorial has been tested with the following tools :
- Java 8
- Maven 3.8.6
- SonarQube Community 10.1.0.73491

# Step 1: Create a Maven Project
We're not going to create a whole new project. Instead, we'll use the project from a [previous article](https://kloudly.hashnode.dev/how-to-generate-code-coverage-report-using-jacoco-in-a-java-application) on Jacoco. You can find the full code for that article on [GitHub](https://github.com/elkamphy/kloudly-tutorials/tree/master/core-java-modules/core-java-jacoco).
# Step 2: Set up SonarQube server locally
This tutorial assumes that you already have a local installation of SonarQube. If this is not the case, please follow [this guide](https://docs.sonarsource.com/sonarqube/latest/try-out-sonarqube/). 

# Step 3: Configure your project in SonarQube
Once you have a SonarQube instance up and running, connect to it to configure your project.
The SonarQube dashboard will look like this: 
![Capture d’écran 2023-12-22 à 13.55.51.png](https://ucarecdn.com/1e491d54-0cf0-4b6a-b705-335d1bae2114/)

Click on "Create a project" in the top right-hand corner, then select "Manually" in the following window as follows: 
![Capture d’écran 2023-12-22 à 14.03.39(1).jpg](https://ucarecdn.com/f6fd1fed-ecda-4ec6-9b32-5148677f42d9/)

Fill in the next screen as follows and click "next": 
![Capture d’écran 2023-12-22 à 14.23.14.jpg](https://ucarecdn.com/f94dd0d9-062b-4c86-b46c-2307620e3f71/).

In the next screen, select "Use the global setting" and then "Create project" : 
![Capture d’écran 2023-12-22 à 19.34.45.png](https://ucarecdn.com/435c0244-6da1-49aa-bd13-320d114ac17b/).

Next, you'll have to specify how you want to analyze your repository. Different options are possible: Local, Jenkins, GitHub Actions, etc. For this demo project, we will analyze a local repository. So select "Locally".
![Capture d’écran 2023-12-22 à 19.44.15.png](https://ucarecdn.com/d9b3cce7-f433-470a-b8db-601a63bb2f51/)

In the next screen, you'll generate a token to analyze your project. You can adjust the validity of your token as you wish. Click "generate" once you're done.
![Capture d’écran 2023-12-22 à 19.48.01.png](https://ucarecdn.com/aaf0ab3c-f3cb-46fd-9b08-48eb6a181e80/).

Once the token is generated, you can save it in a safe location and click "Continue".
![Capture d’écran 2023-12-22 à 19.51.39.png](https://ucarecdn.com/abaec6c4-4a5c-46dd-a2da-fa050d1c30c7/).

The final step is to select your build tool, i.e. Maven. Then copy the scanner command to be executed in your project directory.
![Capture d’écran 2023-12-22 à 19.57.16.png](https://ucarecdn.com/a7cf32d2-cb68-4e79-b8dd-af02b3724225/)

# Step 4: Run the Sonar Scanner in your project
Open a terminal and navigate to the root directory of your project. Then execute the scanner command as shown below :
![Capture d’écran 2023-12-22 à 20.04.16.png](https://ucarecdn.com/04749c38-1493-440f-9d80-4d885c4327dc/)
After a few seconds, depending on your project size, you'll have an output like this : 
![Capture d’écran 2023-12-22 à 20.05.52.png](https://ucarecdn.com/25ddc2cf-3125-463a-9881-401e8092e497/)
# Step 5: View your project report in SonarQube
Moving back to your Sonar Dashboard, you'll see the analysis report.
![Capture d’écran 2023-12-22 à 20.07.28.png](https://ucarecdn.com/295e3f0d-cede-483d-b77c-1f5e1315f578/).

This screen gives you an overview of the quality of your code. The metrics are grouped into several categories: Reliability, Security, Security review, Coverage, Maintainability, and Duplications. In the next article, we'll take a look at each of these categories and see how to correct some of the vulnerabilities.

# Conclusion
In this article, we look at integrating SonarQube into a Maven project to perform static code analysis. If you would like to use the SonarQube tool in more depth, please consult the [official documentation](https://docs.sonarsource.com/sonarqube/latest/).

In a future article, we'll look at how to automatically launch a Sonar analysis from a continuous integration pipeline, using a tool like Jenkins. 

Did you find this blog post useful? Feel free to drop a thumbs up or comment.

*Happy Coding! And more importantly, Clean as You Code!*

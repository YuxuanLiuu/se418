# WordLadder
Homework 1 for SJTU se418
## Environment
Java se 11
## IDE
visual studio
## Frame
Maven. 
```
mvn archetype:generate -DarchetypeArtifactId="archetype-quickstart-jdk8" -DarchetypeGroupId="com.github.ngeor"
```
Lamda is used in this homework, which needs maven.compiler.source to be equal or higher than 1.8. However, most of the tutorial I found use maven-archetype-quickstart, which uses 1.7 maven.compiler.source. The code above will generate frame support 1.8.
## Unit Test
Junit
## Guide
1. prerequisite

be sure you have java and maven properly installed.

2. build and test

```
mvn install
```
This may take some time.

3. run

```
mvn exec:java
```
It will ask you for the file name of the dictionary. You can input EnglishWords.txt or use your own dictionary. Then you are asked to input 2 words respectively. The example is as follow:

```
Please input the file name of the dictionary.

EnglishWords.txt
Please input word 1 or input Enter to quit: 

cat
Please input word 2 or input Enter to quit: 

dog
[cat, cot, dot, dog]

```


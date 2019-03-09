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
Lamda is used in this homework, which needs maven.compiler.source to be equal or higher than 1.8. However, most of the tutorial I found use maven-archetype-quickstart, which uses 1.7 maven.compiler.source. Besides I have yet to find out how to change its version when the frame has already been generated. The code above will generate frame support 1.8.
## Unit Test
Junit

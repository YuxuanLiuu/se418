FROM openjdk:10-jre-slim
VOLUME /tmp
EXPOSE 8080
ADD ./target/se418-0.0.1-SNAPSHOT.jar se418-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","se418-0.0.1-SNAPSHOT.jar"]
FROM openjdk:18-alpine
COPY . /src/main/java
WORKDIR /src/java
ENTRYPOINT ["javac","MoviereviewApiApplication.java"]


#FROM openjdk:18-alpine
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
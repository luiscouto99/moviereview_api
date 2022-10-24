FROM openjdk:18-alpine
COPY . /src/main/java
WORKDIR /src/main/java
RUN ["javac","MoviereviewApiApplication.java"]
ENTRYPOINT ["java","MoviereviewApiApplication"]


#FROM openjdk:18-alpine
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
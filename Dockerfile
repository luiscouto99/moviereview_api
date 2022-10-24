FROM openjdk:18-alpine
COPY target/*.jar app.jar
CMD ["java","-jar","/app.jar"]
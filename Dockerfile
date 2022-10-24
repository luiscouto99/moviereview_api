FROM openjdk:18-alpine
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
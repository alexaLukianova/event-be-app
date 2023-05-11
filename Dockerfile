FROM arm64v8/openjdk:17-ea-16-jdk

EXPOSE 8080

COPY target/events-1.0.jar events-1.0.jar

ENTRYPOINT ["java","-jar","events-1.0.jar"]

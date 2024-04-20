FROM openjdk:8-jdk-alpine

COPY ./scrapper/target/scrapper.jar ./app/scrapper.jar

EXPOSE 8080 8081

ENTRYPOINT ["java", "-jar", "./app/scrapper.jar"]

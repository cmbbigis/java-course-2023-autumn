FROM openjdk:8-jdk-alpine

COPY ./bot/target/bot.jar ./app/bot.jar

ENV TOKEN=${TOKEN}

EXPOSE 8090 8091

ENTRYPOINT ["java", "-jar", "./app/bot.jar"]

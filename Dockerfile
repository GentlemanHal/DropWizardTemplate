FROM openjdk:8

EXPOSE 8080

ADD build/libs/greeting-service-all.jar /
ADD src/config/app_config.yml /

CMD ["java", "-jar", "greeting-service-all.jar", "server", "app_config.yml"]

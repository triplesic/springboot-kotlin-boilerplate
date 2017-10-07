FROM triplesic/minimal-openjdk8:latest
ADD build/libs/springboot-kotlin-boilerplate-0.0.1-SNAPSHOT.jar kotlin_sample_service.jar

RUN mkdir -p /config

ENTRYPOINT ["java", "-jar", "kotlin_sample_service.jar" , "--spring.config.location=/config/application.properties"]
EXPOSE 443 8080 80
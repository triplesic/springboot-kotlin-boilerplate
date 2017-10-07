FROM triplesic/minimal-openjdk8:latest
ADD out/artifacts/springboot_kotlin_boilerplate_jar/springboot-kotlin-boilerplate.jar kotlin_sample_service.jar

RUN mkdir -p /config

ENTRYPOINT ["java", "-jar", "kotlin_sample_service.jar" , "--spring.config.location=/config/application.properties"]
EXPOSE 443 8080 80
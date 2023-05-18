FROM amazoncorretto:17
COPY target/movie_provider-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
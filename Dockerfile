FROM amazoncorretto:17-alpine-jdk 
MAINTAINER NF
COPY target/back-0.0.1-SNAPSHOT.jar back-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/back-0.0.1-SNAPSHOT.jar"]
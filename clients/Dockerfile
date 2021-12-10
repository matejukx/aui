FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/ClientsApp-0.0.1.jar /usr/local/lib/ClientsApp.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/ClientsApp.jar"]
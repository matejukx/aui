FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/OrdersApp-0.0.1.jar /usr/local/lib/OrdersApp.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/local/lib/OrdersApp.jar"]
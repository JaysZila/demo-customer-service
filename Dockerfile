FROM openjdk:8-jdk-alpine
ADD target/customer.war application.war

ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017","-jar","/application.war"]
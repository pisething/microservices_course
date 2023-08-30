#Start with base image containing java runtime
FROM openjdk:17 as build

# Information of owner or maintainer of image
MAINTAINER pisethbank

# Add the application's jar to the container
COPY target/loan-0.0.1-SNAPSHOT.jar loan-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar","/loan-0.0.1-SNAPSHOT.jar"]




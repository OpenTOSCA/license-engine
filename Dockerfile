FROM maven:3-jdk-8 as builder

WORKDIR /tmp/resus/licenseengine
COPY . /tmp/resus/licenseengine

RUN mvn clean package    
    
From openjdk:8

copy --from=builder /tmp/resus/licenseengine/target/licenseengine-0.0.1-SNAPSHOT.jar licenseengine-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","licenseengine-0.0.1-SNAPSHOT.jar"]

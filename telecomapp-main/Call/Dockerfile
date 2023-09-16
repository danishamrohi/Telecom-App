FROM openjdk:11

WORKDIR usr/src

ADD ./target/Call-0.0.1-SNAPSHOT.jar /usr/src/Call-0.0.1-SNAPSHOT.jar

#ENV MONGO_URL=mongodb://localhost:27017/dockmongodb

ENTRYPOINT ["java","-jar","Call-0.0.1-SNAPSHOT.jar"]
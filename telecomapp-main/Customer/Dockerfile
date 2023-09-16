FROM openjdk:11

WORKDIR usr/src

ADD ./target/Customer-0.0.1-SNAPSHOT.jar /usr/src/Customer-0.0.1-SNAPSHOT.jar

#ENV MYSQL_ROOT_PASSWORD=root
#ENV MYSQL_URL_CUSTOMERSERVICE_DB=jdbc:mysql://localhost:3306/cgisamplecustomer?createDatabaseIfNotExist=true&user=root&password=root

ENTRYPOINT ["java","-jar","Customer-0.0.1-SNAPSHOT.jar"]
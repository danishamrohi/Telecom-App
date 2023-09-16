FROM openjdk:11

WORKDIR usr/src

ADD ./target/Admin-0.0.1-SNAPSHOT.jar /usr/src/Admin-0.0.1-SNAPSHOT.jar

#ENV MYSQL_ROOT_PASSWORD=root
#ENV MYSQL_URL_ADMINSERVICE_DB=jdbc:mysql://localhost:3306/cgisampleadmin?createDatabaseIfNotExist=true&user=root&password=root
#ENV MYSQL_URL_ADMINSERVICE_DB=jdbc:mysql://localhost:3306/cgisampleadmin?createDatabaseIfNotExist=true&user=root&password=root


ENTRYPOINT ["java","-jar","Admin-0.0.1-SNAPSHOT.jar"]
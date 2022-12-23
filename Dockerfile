FROM openjdk:11
COPY target/bankmanager-1.0-SNAPSHOT.war /home/bankmanager-1.0-SNAPSHOT.war
CMD ["java","-jar","/home/bankmanager-1.0-SNAPSHOT.war"]

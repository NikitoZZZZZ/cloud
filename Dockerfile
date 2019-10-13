# -- FIRST IMAGE WITH SRC -- 
FROM jimador/docker-jdk-8-maven-node
MAINTAINER Igor Konovalov "rogee.nok@gmail.com"
COPY client ./home/nc-app/src/client
COPY server ./home/nc-app/src/server
COPY pom.xml ./home/nc-app/src/
# MAKE BUILD SRC
# --------------
RUN mvn -f ./home/nc-app/src/pom.xml clean package

# -- SECOND IMAGE WITH JAR ONLY
FROM anapsix/alpine-java
COPY --from=0 /home/nc-app/src/server/target/server-1.0.jar /home/nc-app.jar
ENTRYPOINT ["java","-jar","/home/nc-app.jar"]
EXPOSE 8090
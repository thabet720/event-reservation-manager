FROM maven:3.6.1-jdk-8-alpine

COPY pom.xml /api/

COPY Dockerfile /api/

ADD src /api/src

RUN cd /api && ls src/ && mvn package -Dmaven.test.skip=true

RUN cp /api/target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080
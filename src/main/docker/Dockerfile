FROM java:8
MAINTAINER mehdi.mahmoud@gmail.com
EXPOSE 8090
EXPOSE 8091
RUN mkdir -p /microservices
RUN groupadd -r gUser -g 433 && useradd -u 431 -r -g gUser -d /microservices -s /sbin/nologin -c "Borrowers microservices" swuser && chown -R swuser:gUser /microservices
ADD borrowers-services-0.0.1.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
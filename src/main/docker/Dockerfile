FROM amazoncorretto:17
MAINTAINER mehdi.mahmoud@gmail.com
ARG JAR_FILE
EXPOSE 8090
EXPOSE 8091
RUN mkdir -p /app
ADD ${JAR_FILE} /app/app.jar
# Make sure the JAR file is accessible and set permissions
RUN chmod 755 /app/app.jar
# Copy the entry point script. Remove "src/main/docker/" if image is created from gradle docker task
COPY src/main/docker/entrypoint.sh /app/entrypoint.sh
# Ensure the entry point script is executable
RUN chmod +x /app/entrypoint.sh
# Set the working directory
WORKDIR /app
# Set the entry point to the script
ENTRYPOINT ["./entrypoint.sh"]
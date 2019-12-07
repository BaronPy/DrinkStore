FROM tomcat:8

MAINTAINER Papay Baron "ferdinand.anton@ogya.co.id"

# Create app directory
WORKDIR /opt/tomcat/webapps/

# Copy war file into docker tomcat directory
COPY target/*.war /opt/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]

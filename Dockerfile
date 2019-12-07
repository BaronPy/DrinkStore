FROM tomcat:8

MAINTAINER Papay Baron "ferdinand.anton@ogya.co.id"

WORKDIR /usr/local/tomcat

# Copy war file into docker tomcat directory
ADD target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]

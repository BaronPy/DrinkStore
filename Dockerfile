FROM tomcat:8

MAINTAINER Papay Baron "ferdinand.anton@ogya.co.id"

ENV CATALINA_HOME /opt/tomcat/
ENV PATH $CATALINA_HOME/bin:$PATH

RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# Copy war file into docker tomcat directory
COPY target/*.war $CATALINA_HOME/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]

FROM oracle/glassfish:nightly
ENV AUTODEPLOY /glassfish5/glassfish/domains/domain1/autodeploy
ENV WAR javaee8-jaxrs-sse-client.war
COPY target/$WAR $AUTODEPLOY
#CMD ["asadmin", "start-domain", "-v"]
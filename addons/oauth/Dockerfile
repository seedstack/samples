FROM jboss/keycloak
ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV KEYCLOAK_IMPORT=/tmp/SeedStackRealm.json
EXPOSE 8080
COPY SeedStackRealm.json /tmp

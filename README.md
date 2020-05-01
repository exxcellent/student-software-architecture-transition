# Eine hybride Software-Architektur für plattformübergreifende Anwendungen

> Vorbedingungen:
>
> Der Docker Deamon muss laufen

## Datenbank

Führe das `startup.sh` Shell-Script aus, um die PostgeSQL Datenbank zu starten.

```
jdbc:postgresql://localhost:5432/thesis

User: thesis
Password: thesis
Database: thesis
Schema: thesis

Host: localhost (thesis-db)
Port: 5432
```
Die Datenbank wird über `docker` bzw. `docker-compose` bereitgestellt. 
Docker Compose startet den Datenbank Service bei einem Neustart automatisch.
Bei jeder Ausführung von `startup.sh` wird die Datenbank zurückgesetzt.

Siehe:
- https://www.docker.com/get-started
- https://docs.docker.com/compose/
- https://www.postgresql.org/docs/12/index.html

# Server

Führe `sh ./startServer.sh` aus, um den Spring Boot Server zu starten.
Der Server läuft unter `http://localhost:9000/`

Siehe:
- https://maven.apache.org/guides/index.html
- https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/


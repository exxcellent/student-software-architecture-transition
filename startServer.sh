#!/bin/bash

echo Starting server... Open http://localhost:9000/api-docs
cd ./02-server || exit
#sh ./mvnw.sh clean install -f ./pom.xml
sh ./mvnw.sh spring-boot:run -f ./application/pom.xml

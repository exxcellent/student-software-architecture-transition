#!/bin/bash

#
# Attention: Danger of loosing data! Only uncomment the 
# following three lines if you know what you're doing
#
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker volume rm thesis_data

docker volume create thesis_data

docker-compose down

docker-compose up --build # -d

#echo Starting server... Open http://localhost:9000/api-docs
#cd ./02-server || exit
#sh ./mvnw.sh spring-boot:run -f ./application/pom.xml



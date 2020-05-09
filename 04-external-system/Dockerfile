# Use the official Python 3 image.
# https://hub.docker.com/_/python
#
# python:3 builds a 954 MB image - 342.3 MB in Google Container Registry
# FROM python:3
#
# python:3-slim builds a 162 MB image - 51.6 MB in Google Container Registry
# FROM python:3-slim
#
# python:3-alpine builds a 97 MB image - 33.2 MB in Google Container Registry
FROM python:3-alpine
 
# build tools are needed for numpy
RUN apk add make
RUN apk add g++
RUN apk add gfortran
# RUN apk add curl
 
COPY ./requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . /app
 
# Create and change to the app directory.
WORKDIR /app
 
RUN chmod 444 main.py
RUN chmod 444 requirements.txt

# Run the web service on container startup.
CMD [ "python", "main.py" ]

# Check if application is running...
# docker container ls
# docker exec -i -t <CONTAINER ID> /bin/sh
# echo -e "GET /\n\n" | nc 127.0.0.1 5000
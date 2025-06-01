FROM bellsoft/liberica-openjdk-alpine:17.0.8

#workspace
WORKDIR /home/selenium-docker

ADD target/docker-resources /home/selenium-docker

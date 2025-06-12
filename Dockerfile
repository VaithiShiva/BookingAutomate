FROM bellsoft/liberica-openjdk-alpine:17.0.8

RUN apk add curl jq


#workspace
WORKDIR /home/selenium-docker

ADD target/docker-resources /home/selenium-docker

ADD runner.sh /home/selenium-docker/runner.sh

ENTRYPOINT sh runner.sh



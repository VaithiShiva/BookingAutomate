FROM bellsoft/liberica-openjdk-alpine

RUN apk add curl jq


#workspace
WORKDIR /home/selenium-docker

ADD target/docker-resources .

ADD runner.sh /home/selenium-docker/runner.sh

ENTRYPOINT sh runner.sh



FROM bellsoft/liberica-openjdk-alpine:17.0.10
RUN apk add curl jq
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
ENTRYPOINT sh runner.sh
           
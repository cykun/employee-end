FROM ubuntu

MAINTAINER cyquen

EXPOSE 8080

VOLUME /tmp

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update &&        \
    apt-get -y install       \
        openjdk-17-jdk       \
        curl                 \
        less                 \
        vim-tiny             \
        iproute2             \
        net-tools
ADD target/employee-end-0.0.1.jar employee-end-0.0.1.jar

ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","employee-end-0.0.1.jar"]
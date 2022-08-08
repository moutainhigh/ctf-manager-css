FROM openjdk:8-jre
MAINTAINER H.m hackmgod@foxmail.com

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone

VOLUME /tmp

ADD target/ums-service.jar ctf-ums.jar

ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom","-jar","/ctf-ums.jar"]

EXPOSE 8601

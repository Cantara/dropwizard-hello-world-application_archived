FROM azul/zulu-openjdk-centos:8
MAINTAINER Kim Christian Gaarder <kim.christian.gaarder@gmail.com>
RUN yum -y install yum-cron
RUN yum -y update
RUN yum -y install curl

# Install Application
RUN adduser hellouser
ADD target/dropwizard-hello-world-application-*.jar /home/hellouser/dropwizard-hello-world-application.jar"
ADD docker/hello-world.yml /home/hellouser/hello-world.yml
RUN chown hellouser:hellouser /home/hellouser/hello-world.yml
ADD docker/hello-world_override.properties /home/hellouser/hello-world-override.properties
RUN chown hellouser:hellouser /home/hellouser/hello-world-override.properties

EXPOSE 8080 8081 62911 1898

WORKDIR "/home/hellouser"
CMD [ \
    "java", \
    "-Xdebug", \
    "-Xrunjdwp:transport=dt_socket,address=62911,server=y,suspend=n", \
    "-Dcom.sun.management.jmxremote.port=1898", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-Djava.rmi.server.hostname=localhost", \
    "-jar", \
    "dropwizard-hello-world-application.jar" \
]

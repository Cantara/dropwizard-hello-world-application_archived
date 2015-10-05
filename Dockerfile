FROM azul/zulu-openjdk-centos:8
MAINTAINER Kim Christian Gaarder <kim.christian.gaarder@gmail.com>
RUN yum -y install yum-cron
RUN yum -y update
RUN yum -y install curl

# Install Application
RUN adduser hellouser
ADD target/dropwizard-hello-world-application-1.1-SNAPSHOT.jar /home/hellouser/dropwizard-hello-world-application-1.1-SNAPSHOT.jar"
ADD docker/hello-world.yml /home/hellouser/hello-world.yml
RUN chown hellouser:hellouser /home/hellouser/hello-world.yml
ADD docker/hello-world_override.properties /home/hellouser/hello-world-override.properties
RUN chown hellouser:hellouser /home/hellouser/hello-world-override.properties

EXPOSE 8080 8081

WORKDIR "/home/hellouser"
CMD ["java", "-jar", "dropwizard-hello-world-application-1.1-SNAPSHOT.jar"]

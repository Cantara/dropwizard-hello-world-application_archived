#!/usr/bin/env bash
docker run -d --name=dropwizard-hello-world -p 9443:443 -p 9022:22 -p 9080:80 cantara/dropwizard-hello-world

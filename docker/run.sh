#!/usr/bin/env bash
docker run -d --name=dropwizard-hello-world -p 8080:8080 -p 8081:8081 cantara/dropwizard-hello-world

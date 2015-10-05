#!/usr/bin/env bash
docker run -d --name=dropwizard-hello-world -p 1898:1898 -p 62911:62911 -p 8080:8080 -p 8081:8081 cantara/dropwizard-hello-world

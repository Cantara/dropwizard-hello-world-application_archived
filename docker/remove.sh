#!/usr/bin/env bash
echo stopping dropwizard-hello-world
docker stop dropwizard-hello-world
echo removing dropwizard-hello-world
docker rm dropwizard-hello-world
echo list active docker containers
docker ps

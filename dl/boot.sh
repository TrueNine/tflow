#!/bin/sh

podman-compose \
-f ./docker-compose.yaml \
down

podman-compose \
-f ./docker-compose.yaml \
pull

podman-compose \
-f ./docker-compose.yaml \
up -d
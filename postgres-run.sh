#!/bin/bash

docker run -d \
    -p 5432:5432 \
	--name spring-postgres \
	-e POSTGRES_PASSWORD=admin \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v /custom/mount:/var/lib/postgresql/data \
	postgres

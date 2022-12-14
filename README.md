# Panda server
Panda server is a Spring Boot REST API backend server application. 
It is designed to power any frontend.

## Database setup
The application persists its data to a database. It currently uses Postgres with PostGIS extensions installed as database server.
For Flyway database migrations to kick in you must provide connection credentials of an existing user in application.yml

Postgres with PostGIS extensions installed can be run in a Docker container on port 5432 with this example command:

`docker run -p 5432:5432 --name panda-postgis -e POSTGRES_PASSWORD=password -d postgis/postgis:14-3.2`

Note: Running the above command requires Docker installed. It uses Postgres version 14 with PostGIS extentions version 3.2.3
(including postgis, postgis_topology, postgis_tiger_geocoder).

An example SQL script for creating a database user in given is ```/src/resources/db/postgresql/create-user.sql```.
Attention: the user created in the example is a superuser. Take great care if you use it.

When it comes to GIS support, WGS 84 spatial reference system is used (SRID 4326).

For testing purposes it is possible to insert sample data using ```/src/resources/db/postgresql/insert-sample-data.sql```.
A better way to populate Database with sample data is to run Application with "init" profile and put the following property to "true":
```yml
vars:
  startup:
    init-sample-data: true
```

### Swagger UI (OpenAPI 3.0 spec)

This application uses SpringDoc tool to integrate with Swagger UI. It is OpenAPI 3.0 compliant API documentation.
Interactive Swagger UI is available at URL:  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

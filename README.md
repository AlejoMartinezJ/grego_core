# Getting Started
# Sigom - Hexagonal
> written in *Hexagonal (Ports & Adapters) Architecture*

This is a small application that provides basic REST endpoints for managing library (add new book, reserve, borrow it, etc.). 

The technology behind it: 
* Java 11
* Postgres
* Spring Boot 

## Installing / Getting started

#### Using `docker-compose`

In the terminal run the following command:
```console
$ docker-compose up
``` 

#### Using Maven (with H2 or local Postgres database)

First compile an application:

```console
$ mvn clean package
```

Then, you have two options either run it with H2 database or with local Postgres database. For first approach just run:

```console
$ mvn spring-boot:run 
```

For a second option, check in the configuration file - `src/main/resources/application.yml` for profile *local-postgres* if connection details are correct and if so, run the command:
```console
$ mvn spring-boot:run -P local-postgres
```
# sigom

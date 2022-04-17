#Micro Service Tutorial


###Profiles:

####local
 - Run all projects in IntelliJ IDEA
 - Database will be h2 in memory
 - Each service should run without spring cloud config

####dev
 - Run infrastructure projects on docker 
 - Other projects will run in IntelliJ IDEA  
 - Ports are accessible from host
 - Services could call directly
 - Database will be PostgreSQL
 - Configuration load from spring cloud config
 - ddl-update: auto

####stage 
 - All projects will run on docker
 - Ports are not accessible from host 
 - Only api-gateway will accessible from host

####prod
 - ddl-update: none
 - use flyway for running the scripts

##Docker 

```
docker images "digilab/*"

docker rmi $(docker images "digilab/*" -q)
```

##Open API / Swagger

```
cd swagger-editor
http-server -p 18000
```
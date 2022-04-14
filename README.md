#Micro Service Tutorial


###Profiles:

####local
 - Run all projects in IntelliJ IDEA
 - Database will be h2 in memory

####dev
 - Run infrastructure projects on docker 
 - Other projects will run in IntelliJ IDEA  
 - Ports are accessible from host
 - Services could call directly
 - Database will be PostgreSQL

####stage 
 - All projects will run on docker
 - Ports are not accessible from host 
 - Only api-gateway will accessible from host

####prod


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
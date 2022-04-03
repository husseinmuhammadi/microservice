#Micro Service Tutorial


###Profiles:

####local
Run in IntelliJ IDEA
Database will be h2 in memory

####dev
Run infrastructure projects in docker and the other side will start in IntelliJ IDEA  
Ports are accessible from host
Database will be PostgreSQL

####stage 
All projects will run on docker and only api-gateway will accessible from host

##Docker 

```
docker images "digilab/*"

docker rmi $(docker images "digilab/*" -q)
```


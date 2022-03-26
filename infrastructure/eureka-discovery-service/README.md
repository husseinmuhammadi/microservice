Build an image 
```
cd infrastructure/eureka-discovery-service
docker build -t eureka-discovery-service -f Dockerfile --build-arg PROFILE=stage=stage ../..
```

Run a container
```
docker run --name eureka-discovery-service -p 8761:80 eureka-discovery-service
```
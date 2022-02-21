# Spring Boot Redis

This project demonstrates a simple microservice that uses Spring Boot and Redis.

### Run project

``` docker-compose build ```

``` docker-compose up ``` 

#####Swagger:

Access Swagger and create same books: ``` http://localhost:8080/swagger-ui.html ```

![Alt text](docs/swagger.png?raw=true "Swagger Request")

![Alt text](docs/books.png?raw=true "Books")

#####Redis Insight (GUI for Redis):
 
Go to ``` http://localhost:8001 ```

Create new access, enter Redis ip (run ```docker inspect spring-boot-redis_redis_1``` and get ip of this container) and Port (6379)

![Alt text](docs/redis.png?raw=true "Redis")
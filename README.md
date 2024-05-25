## Spring Boot Second Level Cache with Redis
This project is a simple example of how to use Redis as a second level cache in a Spring Boot application.

### Requirements
- PostgreSQL database
- Redis database

### How to run
1. `export SPRING_DATASOURCE_URL=...`
2. `export REDIS_ADDRESS=...`
3. `export REDIS_PORT=...`
4. `./mvnw spring-boot:run`
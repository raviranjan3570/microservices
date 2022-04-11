# Microservices

## Running microservices locally
Microservice contains a set of [Spring Boot](https://spring.io/guides/gs/spring-boot) applications and is built using [Maven](https://spring.io/guides/gs/maven/).

```
1. git clone https://github.com/raviranjan3570/currency-conversion-microservices.git
2. Make sure you have docker client installed in your system.
3. cd microservices
4. docker-compose up
```
## Architecture
<img width="1042" alt="todo-screenshot" src="https://github.com/raviranjan3570/microservices/blob/main/Microserives%20architecture.png">

## Database configuration

In its default configuration, todo uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is automatically exposed at `http://localhost:8080/h2-console`
and it is possible to inspect the content of the database using the url from terminal.

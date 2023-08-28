User Microservices
===

Microservice application that stores users as well as their notifications/reminders. Copy of user table in notifications service is updated using message broker.

## How to Run

* Clone the repository to your computer
* Open project in your IDE and configure JDK 17 for the project
* Run docker-compose files for user-service and notifications-service
* Start microservice applications

## Microservices

* Config Server
* Discovery Server
* User Service
* Notifications Service

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
* [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) + [Apache Kafka Binder](https://github.com/spring-cloud/spring-cloud-stream#apache-kafka-binder)
* [PostgreSQL](https://www.postgresql.org/)
* [MySQL](https://www.mysql.com/)
* [Flyway](https://flywaydb.org/) - Database-migration tool
* [Gradle](https://gradle.org/) - Dependency management
* [Spring Cloud Contract](https://spring.io/projects/spring-cloud-contract) & [Testcontainers](https://www.testcontainers.com/) - For integration tests
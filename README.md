# Employee Management System

A Spring Boot-based Employee Management System with PostgreSQL, JWT Authentication, and REST APIs.

## Features

* Employee CRUD Operations
* Department Management
* JWT Authentication & Authorization
* Spring Security
* PostgreSQL Database Integration
* JPA/Hibernate ORM
* RESTful APIs

## Technologies Used

* Java 24
* Spring Boot 3
* Spring Security
* Spring Data JPA
* PostgreSQL
* Maven
* JWT

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/peace4m/employee-management-system.git
cd employee-management-system
```

### 2. Create PostgreSQL Database

```sql
CREATE DATABASE ems_db;
```

### 3. Configure Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_DB_PASSWORD
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8081
```

## Authentication

### Login

**POST**

```text
/api/auth/login
```

Request Body:

```json
{
  "email": "test@test.com",
  "password": "password"
}
```

Successful login returns a JWT token.

## Author

Sthiti

# Management System

## Overview
The ** Management System** is a Spring Boot and Angular-based application designed to manage key aspects of a business, including categories, products, suppliers, transactions, and users. This project provides a robust backend and supports essential business functionalities with a secure and well-organized architecture.

---

## Features
- **Authentication and Authorization**:
  - User registration and login.
  - Role-based access control using JWT (JSON Web Tokens).
- **Category Management**:
  - Add, update, delete, and retrieve categories.
- **Product Management**:
  - Manage product inventory with CRUD operations.
- **Supplier Management**:
  - Track and manage suppliers.
- **Transaction Management**:
  - Handle transactions with detailed status and types.
- **User Management**:
  - Manage user roles and permissions.
- **Exception Handling**:
  - Global exception handling for consistent error messages.
- **Security**:
  - CORS configuration and secure API endpoints.

---

## Directory Structure
### Root
- **`mvnw` & `mvnw.cmd`**: Maven wrapper scripts for platform independence.
- **`pom.xml`**: Maven project configuration file.
- **`lib/`**: External libraries (if any).

### Source Code: `src/main/java/com/ouss/mangmentsystem`
#### Main Application:
- **`MangmentSystemApplication.java`**: Entry point of the Spring Boot application.

#### Packages:
1. **`DTO`**:
   - Contains data transfer objects for communication between layers.
   - Examples: `CategoryDTO`, `ProductDTO`, `UserDTO`, etc.

2. **`config`**:
   - Configuration files, such as `ModelMapperConfig.java`.

3. **`controller`**:
   - REST controllers for handling HTTP requests.
   - Examples: `AuthController`, `ProductController`, etc.

4. **`entity`**:
   - JPA entity classes representing the database tables.
   - Examples: `Category`, `Product`, `Transaction`, etc.

5. **`enums`**:
   - Enumerations for specific field values.
   - Examples: `TransactionStatus`, `UserRole`.

6. **`exceptions`**:
   - Custom exception classes and global handlers.
   - Examples: `NotFoundException`, `GlobalExceptionHandler`.

7. **`repository`**:
   - Interfaces for database interactions using Spring Data JPA.
   - Examples: `CategoryRepository`, `UserRepository`.

8. **`security`**:
   - Security-related classes, such as `AuthFilter` and `JwtUtils`.

9. **`service`**:
   - Business logic implementations.
   - **Sub-packages**:
     - `implementation/`: Implements service interfaces.
     - `interfaces/`: Service interface definitions.

### Resources: `src/main/resources`
- **`application.properties`**: Configuration file for the Spring Boot application.

### Tests: `src/test/java/com/ouss/mangmentsystem`
- **`MangmentSystemApplicationTests.java`**: Unit and integration tests.

### Maven Wrapper: `.mvn/wrapper`
- **`maven-wrapper.properties`**: Configuration for the Maven wrapper.

---

## Getting Started

# Project Prerequisites

To successfully build and run the `Management System` project, make sure to meet the following prerequisites:

## 1. Java 21
Ensure that you have Java 21 installed. The project is configured to use Java 21.

- [Download Java 21](https://adoptopenjdk.net/)
  
## 2. Maven
The project uses Apache Maven for building and dependency management.

- [Install Maven](https://maven.apache.org/install.html)

## 3. MySQL
The project requires MySQL as the database. Ensure that MySQL is installed and running.

- [Download MySQL](https://dev.mysql.com/downloads/)

## 4. Dependencies
The project includes the following dependencies:
- **Spring Boot**: Framework for building the application.
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-security`
  - `spring-boot-starter-validation`
  - `spring-boot-starter-web`
- **Lombok**: For automatic generation of boilerplate code (getter/setter, toString, etc.).
- **ModelMapper**: For object-to-object mapping.
- **JWT**: For managing JSON Web Tokens (JWT).
- **JUnit & Spring Security Test**: For testing the application.

Make sure to have these dependencies in your local repository or configure Maven to download them.

## 5. MySQL Configuration
Ensure that the MySQL connection is properly configured in the application (e.g., in `application.properties` or `application.yml`).

Example configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password


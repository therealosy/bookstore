# Bookstore API

This is a Spring Boot REST application for a bookstore.

## Configuration and Installation

1. **Prerequisites**: Ensure you have Jdk 17 or newer, Maven and SQL Server installed on your system.

2. **Clone the repository**: Use the command `git clone https://github.com/therealosy/bookstore.git` to clone the repository to your local machine.

3. **Configure the application parameters** Edit the `application.properties` file in `src/main/resources` to configure the required parameters.

4. **Build the project**: Navigate to the project directory and use the command `mvn clean install` to build the project.

5. **Run the application**: Use the command `mvn spring-boot:run` to start the application.

## API Documentation

The API Documentation is available via swagger-ui at `[host:port]/[context-path]/swagger-ui.html` and the Open API documentation is available at `[host:port]/[context-path]/v3/api-docs`.

## Application Management

A default admin user is available for the API with email `admin@bookstore.com` and password `bookstore_admin`. All management endpoints are only available to users with the admin role.

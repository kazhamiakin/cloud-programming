# EchoService

A simple Spring Boot application with a REST API ``/message`` that returns a pre-configured string. The string is read from the Spring Cloud Config Server.

Connection with the Spring Cloud Config Server is defined in ``src/main/resources/bootstrap.properties``. 

To run on a port different from default (e.g., 8888), use ``-Dserver.port=8888`` VM argument. 

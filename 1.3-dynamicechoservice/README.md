# EchoService

A simple Spring Boot application with a REST API ``/message`` that returns a pre-configured string. The string is read from the Spring Cloud Config Server. 

The project supports dynamic configuration update using Spring Boot Actuator Refresh API:

- dependency ``spring-boot-starter-actuator`` enabled;
- ``management.endpoints.web.exposure.include=*`` property defined in ``src/main/resources/application.properties``;
- ``@RefreshScope`` annotation is enabled on the Rest Controller.

To force configuration update use the following API call:

- ``POST http://localhost:8888/actuator/refresh``
 

Connection with the Spring Cloud Config Server is defined in ``src/main/resources/bootstrap.properties``. 

To run on a port different from default (e.g., 8888), use ``-Dserver.port=8888`` VM argument. 

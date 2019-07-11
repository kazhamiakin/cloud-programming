# Configuration Server (Git) with Value encryption

The project is based on Spring Cloud Config Server project. It uses
``git`` configuration mode for reading the service configurations from Git repository folder and encryption / decryption for secure keys.

To enable encryption / decryption, ``src/main/resources/bootstrap.properties`` file defines a ``encryption.key`` property (symmetric key). 



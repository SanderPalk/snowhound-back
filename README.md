# Project setup
It is important to set up the database before you try to launch the project.
## Database
### Prerequisites
* Installed [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* Docker daemon running.
### Setup
First you need to download [this docker file](https://github.com/SanderPalk/snowhound-db), it should be downloaded in a standalone folder.
* Navigate to that folder in command line
* Run `dockr compose up`
* Wait for the container to build

## Spring application
### Prerequisites
* Installed Maven
### Setup
* Run `mvn package`
* Run ` java -jar target/snowhound-back-0.0.1-SNAPSHOT.jar`


## Documentation
Project full documentation can be found [here](https://docs.google.com/document/d/1XiCYjNyLiuy5Poq-KuyHmEZ3938igFe4qp9eVVc0Ryc/edit?usp=sharing)

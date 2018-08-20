# Kalah Game

## Requirements
The requirement is given by backbase as a initial coding challenge to program a *​Java RESTful Web Service* ​that runs a game of 6-stone Kalah. To get an idea of kalah, the research paper [Solve Kalah](https://naml.us/paper/irving2000_kalah.pdf) was read and analysis the core engine of game. 
For more understanding, existing [Game](http://kalaha.krus.dk/play/) was played and got more understanding about Kalah.
## Overview of Project
### Architecture
This project is based on multilayer architecture with in-memory cache and the communication is done via REST api. 
### Structure
The project consists on two module: 
* *Common* - It deals with the core functionality of the kalah. e.g. POJOs which are being used in entire project and core engine of Kalah. 
* *Service* - It deals with the rest api and cache which is used for persisting and retrieving the game. 

### Technology Stack
* Java 8
* Spring Boot 2 with Embedded tomcat server. 
* Spring Boot Test
* JUnit
* Mockito
* JaCoCo 
* Lombok
* UmlGraph for JavaDocs

### Running Unit Tests locally
The Unit Tests of Service module uses Mocking and common module use the reference of classes to test actual result.    

Unit Tests can be executed by
> <b>mvn clean test</b>

### Running Integration Tests locally
There is only single Integration test for *com.backbase.assignment.kalah.api.GameControllerIntegrationTest*.
It will execute with unit test as there is no separate folder for single IT.

### Build Project
> mvn clean install
### Run Application
> mvn clean spring-boot:run
###
or
> java -jar service/target/service-0.0.1-SNAPSHOT.jar 
### Generate JavaDocs
Before generating JavaDocs, graphviz is required to install on OS.
#### Install graphviz.
* Ubuntu for example:
> $ apt-get install graphviz
###
* OSX:
> $ brew install graphviz
###
* Windows:
> [Download Link](http://www.graphviz.org/pub/graphviz/stable/windows/graphviz-2.28.0.msi)
###
After installing graphviz, the following command is required to execute:
> mvn javadoc:javadoc
###
JavaDocs will be generated on parent folder : e.g. kalah/javadocs/

### Code coverage
JaCoCo is used for code coverage. 

The result can be seen at site folder of module's target.
###
e.g. {module}/target/site/jacoco/index.html
 
### Kalah Web-Service description

### Creation of the game
##### Example of creation of game request
```
 curl --header "Content-Type: application/json" \
 --request POST \
 http://<host>:<port>/games
```
##### Request Body
```
HTTP code: 201
 Response Body: { "id": "1", "uri": "http://<host>:<port>/games/1" }
```
 where 
 * id: unique identifier of a game
 * url: link to the game created
 
### Make a move
##### Example request of make a move in game.
```
curl --header "Content-Type: application/json" \
 --request PUT \
 http://<host>:<port>/games/{gameId}/pits/{pitId}
```
##### Response Body
```
HTTP code: 200
 Response Body:
{"id":"1234","url":"http://<host>:<port>/games/1234","status":{"1":"4","2":"4","3":"4","4":"4","5":"4","6":"4","7":"0","8":"4","
9":"4","10":"4","11":"4","12":"4","13":"4","14":"0"}}
```
where 
 * status: json object key-value, where key is the pitId and value is the number of stones in the pit
of each player
##### Response
| Status Code | Description |
|-------------|-------------|
| 200         | Request has been successfully received. |
| 201         | Request has been successfully created. |
| 400         | Server cannot or will not process the request due to something that is perceived to be a client error |
| 500         | A server error occurred, such as deserialization error. |
  

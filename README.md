# Medisreen application 

Mediscreen est une application qui permet aux médecins de gérer ses fiches patients, d'entrer des notes pour chaque patient. L'application détecte les risques de diabète pour chaque patient en fonction de différent critères comme   l'âge du patient, son sexe, un décompte de la présence de certain mot clé spécifique dans les notes établit à chaque rendez vous patient. Cela offrant ainsi une aide au diagnostique du médecin.

## Configuration Application en Microservices

#### Front Mediscreen (application principal) :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- Spring starter web
- Thymeleaf
- Bootstrap
- Spring Openfeign
- Swagger

#### Patient Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- Mysql
- Spring Data JPA
- Swagger

#### Note Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- MongoDB
- Spring Data Mongodb
- Swagger

#### Assessments Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- Spring Openfeign
- Swagger

## Documentation Technique (Swagger)

#### Front Mediscreen :

http://localhost:8000/swagger-ui.html#/

#### Patient Microservice :

http://localhost:8001/swagger-ui.html#/

#### Note Microservice :

http://localhost:8002/swagger-ui.html#/

#### Assessments Microservice : 

http://localhost:8003/swagger-ui.html#/

## Diagramme d'architecture

![Diagramme Architecture](https://user-images.githubusercontent.com/24941159/219952386-a7b4f39a-0d16-4849-b642-2574ef629dd2.png)
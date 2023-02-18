# Medisreen application 

Mediscreen est une application qui permet aux médecins de gérer ses fiches patients, d'entrer des notes pour chaque patient. L'application détecte les risques de diabète pour chaque patient en fonction de différent critères comme   l'âge du patient, son sexe, un décompte de la présence de certain mot clé spécifique dans les notes établit à chaque rendez vous patient. Cela offrant ainsi une aide au diagnostique du médecin.

## Configuration Application en Microservices

#### Front Mediscreen (application principal) :

- Java 11
- Spring Boot 2.7.8
- Gradle 8

#### Patient Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- Mysql

#### Note Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8
- MongoDB

#### Assessments Microservice :

- Java 11
- Spring Boot 2.7.8
- Gradle 8

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

![DiagrammeArchitectureProjet9](https://user-images.githubusercontent.com/24941159/219900137-4d528386-c190-4f07-ad42-7c723869d0de.png)
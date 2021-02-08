# Franchise Application Services



## Content

- [Setup](#setup)
- [Docker](#docker)
- [ApiDetails](#configuration)
- [EncryptPassword](#encryptpassword)
- [Usage](#usage)


## Setup

Using mvn package

```
  git clone https://github.com/abhaymeshram/franchise-services.git
  cd franchise-services
  mvn clean install
  java -jar target/franchise-services.jar
```


## Docker

Docker build image using docker command

```
 git clone https://github.com/abhaymeshram/franchise-services.git
 cd franchise-services
 docker build -t ooredoo/franchise-services .
 docker run -p8080:8080 ooredoo/franchise-services
```

Docker Build image using jib-maven-plugin

```
  git clone https://github.com/abhaymeshram/franchise-services.git
  cd franchise-services
  mvn compile com.google.cloud.tools:jib-maven-plugin:2.7.1:dockerBuild
  docker run -p8080:8080 ooredoo/franchise-services:1.0.0-SNAPSHOT
```



## Configuration

**application.properties**

```
server.port = 8080

#Change it to dev for Mysql DB changes
spring.profiles.active = local
spring.application.name = franchise-services

server.servlet.context-path = /franchise-services

#Swagger API details
#Change it to false to disable swagger in prod
springdoc.swagger-ui.enabled = true

#Jsypt password encryption key
jasypt.encryptor.password = ooredoo-franchise
```

**application-dev.properties**

```spring.jpa.hibernate.ddl-auto = update
spring.datasource.url = jdbc:mysql://localhost:3306/franchise
spring.datasource.username = root
spring.datasource.password = ENC(Qe6wxD2BcdaL/610K3CVnw==)
```

**application-local.properties**

```spring.jpa.hibernate.ddl-auto = update
spring.datasource.url = jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username = sa
spring.datasource.password = ENC(iFQcDx4828bsUuQA5lBp2A==)
```

## EncryptPassword
```
Jasypt Online Encryption
https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
Enter password in plain text format
Select Two way encryption
Enter Secret key ooredoo-franchise (Choose any key. Please change it accordingly in application properties)
```

## Usage
http://localhost:8080/franchise-services/swagger-ui.html

1) Save Franchise 

```
URL: http://localhost:8080/franchise-services/v1/save
Method: POST
Request:

    {
      "applicantsName": "Test",
      "idNumber": "Z28043",
      "birthDate": "2021-02-08",
      "contactAddress": "Qatar",
      "contactPhone": "+974333256",
      "contactEmail": "abc@gmail.com",
      "interestAboutBrand": "Ooreado Mobile",
      "investmentAmount": 250000,
      "preferredOperateLocation": "Qatar",
      "notes": "City center mall"
    }
    
    applicantsName, idNumber: Is mandatory field
    contactEmail: is annotated as email
    idNumber: defined as String to handle alphbates in id
    contactPhone: defined as String to have country code
    
    EntityListeners: AuditListener is added to update created and updated date before persit and update entity.
    
```

2) Update

```
URL: http://localhost:8080/franchise-services/v1/update
Method: PUT
Request:

    {
      "applicantsName": "Test",
      "idNumber": "Z28043",
      "birthDate": "2021-02-08",
      "contactAddress": "Qatar",
      "contactPhone": "+974333256",
      "contactEmail": "abc@gmail.com",
      "interestAboutBrand": "Ooreado Mobile",
      "investmentAmount": 250000,
      "preferredOperateLocation": "Qatar",
      "notes": "Changed",
      "createdDate": "2021-02-08T18:34:58.298Z",
      "id": 1,
    }    
```

3) GetAll

```
URL: http://localhost:8080/franchise-services/v1/getAll
Method: GET
Response:

    [
      {
        "createdDate": "2021-02-08T17:29:39.992+00:00",
        "updateDate": null,
        "id": 2,
        "applicantsName": "Abhay",
        "idNumber": "string",
        "birthDate": "2021-02-08",
        "contactAddress": "string",
        "contactPhone": "string",
        "contactEmail": "string@gmail.com",
        "interestAboutBrand": "string",
        "investmentAmount": 0,
        "preferredOperateLocation": "string",
        "notes": "string"
      }
  ]  
```

4) Delete

```
URL: http://localhost:8080/franchise-services/v1/delete/1
Method: DELETE
```

5) Filter Franchise based on applicantsName, idNumber, contactEmail & contactPhone

```
URL: http://localhost:8080/franchise-services/v1/franchise/filter
Method: POST
*** case 1: Fetch using applicantsName ***
Request:
 
    {
      "applicantsName": "Test"
    }
   
case 2: Fetch using contactEmail ***
Request:
 
    {
      "contactEmail": "Test"
    }
 
case 3: Fetch using contactEmail & applicantsName***
Request:
 
    {
      "contactEmail": "Test",
      "applicantsName": "Test"
    }
    
case 4: Fetch all the record. We can remove GetAll API as both are serving same result/
Request:
 
    {
    
    }
    
Specification has been added to handle search criteria using like fields

List<Franchise> franchises = franchiseRepository.findAll(searchSpec(franchise));

private Specification<Franchise> searchSpec(Franchise franchise) {
        return new Specification<Franchise>() {
            @Override
            public Predicate toPredicate(Root<Franchise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (franchise.getApplicantsName() != null) {
                    predicates.add(cb.like(root.get("applicantsName"), "%" + franchise.getApplicantsName() + "%"));
                }

                if (franchise.getIdNumber() != null) {
                    predicates.add(cb.like(root.get("idNumber"), "%" + franchise.getIdNumber() + "%"));
                }

                if (franchise.getContactEmail() != null) {
                    predicates.add(cb.like(root.get("contactEmail"), "%" + franchise.getContactEmail() + "%"));
                }

                if (franchise.getContactPhone() != null) {
                    predicates.add(cb.like(root.get("contactPhone"), "%" + franchise.getContactPhone() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
    
```



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

#Default password is sa. Encrypt using https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
# secret keys ooredoo-franchise
```

**application-local.properties**

```spring.jpa.hibernate.ddl-auto = update
spring.datasource.url = jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username = sa
spring.datasource.password = ENC(iFQcDx4828bsUuQA5lBp2A==)

#Default password is sa. Can be encrypted using https://www.devglan.com/online-tools/jasypt-online-encryption-decryption
# secret keys ooredoo-franchise
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

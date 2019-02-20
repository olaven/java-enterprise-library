# Enterprise-Library

JEE-applciation for a library. Written as a hobby project to complement 
[this course](https://github.com/arcuri82/testing_security_development_enterprise_systems) at _Høyskolen Kristiania_.
   

Plan: 
Go through every topic thoroughly in Spring.
- [X] Lesson 1
- [x] Lesson 2
- [ ] Lesson 3
- [ ] Lesson 4
- [ ] Lesson 5
- [ ] Lesson 6
- [ ] Lesson 7
- [ ] Lesson 8
- [ ] Lesson 9
- [ ] Lesson 10
- [ ] Lesson 11
- [ ] Lesson 12

## Setup 
* configure PostgreSQL locally
* create a database called "spring-library" 
* in this project, create `backend/src/main/resources/application.properties `, and add the following lines:
    * replace username and password with your own PostgreSQL user   
```

# datasource config
spring.datasource.url=jdbc:postgresql://localhost:5432/spring-library
spring.datasource.username=__YOUR_USERNAME__
spring.datasource.password=__YOUR_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

# jpa config
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
```

## Notes: 
Using posgresql: 
use command `psql` and \h and \? for help

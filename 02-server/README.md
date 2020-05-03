# Software Architecture Transition Example

> Hauptklasse: `de.exxcellent.student.softwarearchitecture.transition.Application`

Führe `sh ./mvnw.sh spring-boot:run -f ./application/pom.xml` aus, um Spring Boot über Maven zu starten.

Alternativ kann auch die `main()`-Methode der Hauptklasse in einer IDE gestartet werden.

## API Dokumentation

Swagger/ Open API Dokumentation: 
`http://localhost:9000/api-docs`

Open Swagger UI:
`http://localhost:9000/swagger-ui/index.html`
und füge `http://localhost:9000/api-docs` in das Eingabefeld ein.

Alternativ kann der Inhalt von `http://localhost:9000/api-docs`
auch unter http://editor.swagger.io/ eingefügt werden.

----
## Schichtenarchitectur

### Komponenten

```
Resource Component:

<resourcePath> 
└ <resourcePackage>
  └ .<componentName>s
    ├ .types
    │ └ <componentName>TO.java
    │ └ <componentName>sCTO.java
    ├ .mapper
    │ └ <componentName>Mapper.java
    └ <componentName>sResourceV1.java



Business Logic Component:

<componentPath> 
└ <componentPackage>
  └ .<componentName>
    ├ .api
    │ ├ .types
    │ │ └ <componentName>DO.java
    │ └ <componentName>Component.java
    └ .impl
      ├ .businesslogic
      │ ├ .logic
      │ │ └ <componentName>Logic.java
      │ ├ .mapper
      │ │ └ <componentName>Mapper.java
      │ └ <componentName>Facade.java
      └ .data
        ├ .entities
        │ └ <componentName>Entity.java
        └ <componentName>Repository.java
```

Die Komponenten wurden per Template generiert:

https://github.com/lehnert-andre/project-code-generator/tree/master/project-code-generator

Beispiel:

`plop spring-component-with-resource -- --componentPath /Users/alehnert/Projekte/BA_ALEXA/student-software-architecture-transition/02-server/businesslogic/src/main/java --componentPackage de.exxcellent.student.softwarearchitecture.transition.businesslogic.components --resourcePath /Users/alehnert/Projekte/BA_ALEXA/student-software-architecture-transition/02-server/application/src/main/java --resourcePackage de.exxcellent.student.softwarearchitecture.transition.application.resources --hasRepository true --componentName Appointment`


A simple JAX-RS (Jersey) project that exposes REST API.

### Unit Tests
UT use Retrofit as REST Client library
```maven
mvn test
```

### Functional Tests
Functional tests use Cucumber (example in *activity.feature*)
```cucumber
Feature: When an activity is created, it appears in the Activity Streams

  Scenario: the activity appears in the Activity Stream of the owner of the activity
    Given I publish an activity with title "Hello World!"
    When I consult my Activity Stream
    Then this activity is present in the stream
```

Cucumber call the REST API through Retrofit REST Client library
```maven
mvn test
```

### API documentation - Runtime
The documentation of the REST API is generated at run time by swagger.io.

```maven
mvn jetty:run
```

Then open in browser:
[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/#!/activity/createActivity)

The JSON swagger file can be found here:
[http://localhost:8080/swagger.json](http://localhost:8080/swagger.json)

### API documentation - Build time
The generation of the file swagger.json is also included in the compilation phase thanks to the [swagger-maven-plugin] (https://github.com/kongchen/swagger-maven-plugin)

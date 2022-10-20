# Demo

Validate password by Regular Expression

[[__TOC__]]

## develop environment
JDK 8, Maven, Spring Boot, Junit, Lombok, Freemarker

## How to
### Maven
Run junit tests
```
mvn test
```

Build jar file
```
mvn clean install
```

Service startup
```
java -jar ./target/passwordvalidator-22.10.1.jar
```

### Test
When Spring Boot is startup, you can test password by requst API or from webpage:

1. Using Postman clinet tools, replace password below request body
```shell
curl --location --request POST 'http://localhost:8080/api/validatePassword' \
--header 'Content-Type: application/json' \
--data-raw '{
    "requestEntity": {
        "password": "123456abc"
    }
}'
```

2. You can open browser and go to [http://localhost:8080/validatePassword](http://localhost:8080/validatePassword), input your password then validate it

### Configuration

#### PasswordValidateService:
Currently regular expression are config in [application.yml](./src/main/resources/application.yml)
```yaml
validate-service:
  password-validate:
    positive-regex-pattern-list:
      - "[0-9a-z]{5,12}"
      - ".*[0-9].*"
      - ".*[a-z].*"
    negative-regex-pattern-list:
      - ".*(//w+)//1.*"
```
- positive-regex-pattern-list: if password matches pattern, result will be pass
- negative-regex-pattern-list: if password matches pattern, result will be unpass

### Enhancement
#### Custom ValidateService

1. create new java class, implement **ValidateService** interface
2. implement and override **validate** method

[sample](./src/main/java/com/innova/passwordvalidator/service/impl/SampleValidateService.java)

#### Custom Rule

1. create new java class, extends **AbstractRule** class
2. implement and override **check** method

[sample](./src/main/java/com/innova/passwordvalidator/rule/AbstractRule.java)

#### Custom Test case

you can add custom test case at
- Rule unit test: [RuleTests](./src/test/java/com/innova/passwordvalidator/rule/RuleTests.java)
- Service Integration test: [ValidateServiceTests](./src/test/java/com/innova/passwordvalidator/service/ValidateServiceTests.java)
- Controller Integration test: [APIControllerTests](./src/test/java/com/innova/passwordvalidator/controller/APIControllerTests.java)

### API Spec
[document ref.](./API-SPEC.md)

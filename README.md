# Config Process

To run this API it is necessary:

- Java 21 (graalvm-ce-21)
- Maven
- Quarkus

First of all, it's necessary run the command **mvn clean install** ( _./mvnw clean install_ on windows)


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_SWAGGER:_** You can access the swagger page at <http://localhost:8080/q/swagger-ui/>.

There is one endpoint there to test the application. I made an API.

To stop the application, please use **CTRL + C** in the terminal.
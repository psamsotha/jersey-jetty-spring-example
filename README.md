# Jersey, Jetty, with Spring Example

This project is a small example to show how to use Jersey and Spring in an embedded Jetty container.
There are only a couple things required:

1. Make sure you have the `jersey-spring4` dependency. The spring4 dependency is new in Jersey 2.26.
Prior versions use `jersey-spring3` which uses Spring 3, instead of Spring 4. You can check out
the pom.xml from this [Stack Overflow post][pom3] if you're using `jersey-spring3` and you want
to use Spring 4.

3. You just need to register a `ContextLoaderListener` equipped with a `WebApplicationContext` that
has your Spring context configuration (see `Main` class in project).

### Installation

    mvn clean install

### Test

    mvn clean test

### Run

    mvn exec:java

### Endpoints

    curl http://localhost:9090/greeting/en/{name}
    curl http://localhost:9090/greeting/es/{name}


[pom3]: https://stackoverflow.com/a/32357991/2587435

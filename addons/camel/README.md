# SeedStack Camel addon sample

This sample demonstrates how to use Apache Camel addon for Seedstack

## Build

```bash
mvn clean package
```

## Run

Execute tests located in `src/test/java` in your IDE, or with Maven:

```bash
mvn clean verify
```

The integration test gisves an example of how to set a basic Camel route.
This route copies files from one folder to another using the addon.

## Sample

This sample is a simple web-application exposing a "/person" resource as a Web service.
Sending a POST request to /person/queue will send a Person object both
- Through a standard JMS route
- Through a Camel Managed JMS route

The JSM component, creating the route with a JMS queue is initialized using an implementation of CamelContextInitializer

## Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 

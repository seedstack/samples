# SeedStack W20-bridge sample

This sample demonstrates how to automatically integrate the W20 Web framework in a SeedStack application.

## Build

```bash
mvn clean package
```

## Run

```bash
mvn seedstack:run
```
    
An alternative is to execute the [Capsule](http://www.capsule.io/) automatically created by the [SeedStack Maven plugin](http://seedstack.org/docs/maven-plugin/package/) on build:

```bash
mvn clean package
java -jar target/w20-bridge-sample-capsule.jar
```

Then point your browser to the following URL:

    http://localhost:8080
        

## Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 

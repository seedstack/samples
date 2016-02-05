# Batch sample

An "Hello World"-style batch job, demonstrating Spring Batch integration and CLI usage and testing.

# Running it

## Locally

If you have [Maven 3](http://maven.apache.org/) installed, you can clone the whole samples repository, go into the batch sub-directory 
and run it locally:

    mvn seedstack:run -Dargs="run-job --job helloWorldJob"
    
An alternative is to execute the [Capsule](http://www.capsule.io/) automatically created by the [SeedStack Maven plugin](http://seedstack.org/docs/seed/maven-plugin/) on build:

    mvn clean install
    java -jar target/batch-sample-capsule.jar run-job --job helloWorldJob
    
# Usage

This batch does nothing outside printing "Hello World!" on the console.

# Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 

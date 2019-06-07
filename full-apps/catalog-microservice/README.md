# SeedStack hypermedia micro-service sample

A micro-service project demonstrating REST features of SeedStack: JAX-RS integration, hypermedia and JSON-home.

**NOTE:** This micro-service uses the `business-migrate` module of the business framework 4.x to allow 
compatibility with business framework 3.x code. For up-to-date business code, prefer the following examples instead:

* [DDD sample](../ddd)
* [Store WebApp](../store-webapp)  

## Run

    mvn seedstack:run

## Usage

Discover all application entry points as a JSON-HOME resource on the following URL:

    http://localhost:8080/

Then follow the links to HAL resources.

## Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 

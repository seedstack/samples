# Web-Services sample

A project demonstrating features of the [SeedStack Web-Services add-on](http://seedstack.org/addons/web-services).

## Running it

If you have [Maven 3](http://maven.apache.org/) installed, in the `web-services` directory, run:

    mvn seedstack:run

 
## Usage

Add the following URL in your favorite WS client:

    http://localhost:8080/product-info?wsdl

Generate a sample request for the productInfoByID operation and use any product identifier. Mock data is generated on-the-fly.

# Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 

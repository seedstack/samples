# Catalog

A REST microservice exposing the API of a product catalog.

Download the sample:

    git clone https://github.com/seedstack/samples
    cd catalog

Build the project

    mvn clean install

Start the sample:

    mvn jetty:run

Show all the application entry points as a JSON-HOME resource:

    curl 'http://localhost:8080/rest/'

Then follow links to HAL resources.
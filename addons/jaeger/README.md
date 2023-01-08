# Sample Apps for Jaeger-Addon

web-service1 is the root service which calls web-service2 and web-service3.

web-service2 and web-service3 can also run independently.



Steps to Run:

1. run maven build for each web-service
2. mvn seedstack:run is the command to start each web-service
3. web-service1, web-service2, web-service3 will start on port 8080, 9000, 8090 respectively
4. you need a collector to collect the traces send by above web services and that can be downloaded             from https://www.jaegertracing.io/download/ ( see binary for windows, simpler one ).
5. start the jaeger-all-in-one.exe as back-end service (Collector)


URLs of Web Services-

web-service1-> http://localhost:8080/api/v1/names/random

web-service2-> http://localhost:9000/api/v1/animals/random

web-service3-> http://localhost:8090/api/v1/scientists/random

now access- http://localhost:16686/search to see the jaeger traces 




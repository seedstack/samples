# jaeger-sample
jaeger-sample apps for Jaeger-Addon

Web-Service1 is the root service which calls Web-Service2 and Web-Service3.

Web-Service2 and Web-Service3 can also run independently.



Steps to Run:

1. run maven build for each Web-Service
2. mvn seedstack:run is the command to start each Web-Service
3. Web-Service1, Web-Service2, Web-Service3 will start on port 8080, 9000, 8090 respectively
4. you need a collector to collect the traces send by above web services and that can be downloaded             from https://www.jaegertracing.io/download/ ( see binary for windows, simpler one ).
5. start the jaeger-all-in-one.exe as back-end service (Collector)


URLs of Web Services-

Web-Service1-> http://localhost:8080/api/v1/names/random

Web-Service2-> http://localhost:9000/api/v1/animals/random

Web-Service3-> http://localhost:8090/api/v1/scientists/random

now access- http://localhost:16686/search to check the traces 




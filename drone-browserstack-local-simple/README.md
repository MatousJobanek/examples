## Simple example how to use local testing with Arquillian Drone BrowserStack extension

###How to use it:
1) put your username and access.key into arquillian.xml file
https://github.com/MatousJobanek/examples/blob/master/drone-browserstack-local-simple/src/test/resources/arquillian.xml#L9-L10
 
2) run `mvn clean verify`

The extension downloads and runs the binary BrowserStackLocal for you. 


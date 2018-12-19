# Registration
Simple application that allows creating user accounts.

## Application start
To start the application run:  
`gradle bootRun` from `project root` directory  
Application will be available under http://localhost:8080/registration

## Generate WAR file
Application has the ability to generate WAR file that can be deployed on the web server as Tomcat.

To generate WAR file run: 
`gradle war` from `project root` directory
WAR file will be will be generated in `build/libs` directory.

___
Tested with Java 9.0.1 and Apache Tomcat 8.5.35 

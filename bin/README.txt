socialnetworking;
Student Name: Milson Munakami
Email: milsonmunakami@u.boisestate.edu
Sudent ID : 114012811
Course Number: CS 597 - Cloud Computing
Assignment Number - 1
Instructor - Dr Vijay Dialani

Assumtions:
----------------
I have added the both JAR and WAR file with POM.XML.

I have changed the WEbservice endpoints so please make sure the endpoints are correct, if you are testing it with browser url.

I already did complete test from my application so I would suggest to test from web application rather than from browser url typing of webservice path.

The github repo for this is: 

https://github.com/Milstein/MS-2014-Boise-State-University/tree/master/Cloud%20Computing/Assignments/socialnetworking

The database configuration is in : socialnetworking\src\dao\Database.java 

The project database name is: db_socialnetwork

The login detail I have used for DB is: user: root and password:''

I have done the Database design in MySQL, I have included the SQL dump file in db_socialnetwork.sql
After running the db_socialnetwork.sql we can create the needed user running following script

CREATE USER 'root'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;


The Base URL for the webservice is : var BASE_URL = "http://localhost:8080/socialnetworking/";

so if any change in this url then the page javascript value need to be updated so I suggest to create same hosting service in same name as socialnetworking.

All services are in this folder: \socialnetworking\src\webservices

The Client part is exported as socialnetworking.war

The whole project is exported as socialnetworking.jar

I have completed all the necessary back-end methods in JAX-RS web service but due to time constraint I am able to develop only some functionality in front/client side even thought that demonstrate the implementation of POST, GET methods using web-service and also Session to login and logout user is done. To update message/status I design the client web application with css and js.


I have written the sql queries but their validity and verification is not fully tested at this moment. Which is left for later time!

I have tested the site using Tomcat 8.0 and all needed jar files are included on lib folder of the application. Hopefully the dependency is included in WAR file.

It was a great challenging assignment and I learnt so much about uses of JAX-RS and Servelet in JAVA application.

Thanks,
Milson

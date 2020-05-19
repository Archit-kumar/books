# bookassignment
#steps to run the application

Step 1 : Go to the root directory and package the build using following command.

mvn clean package

Step 2 : build the docker image by running following command on terminal.

docker build -t books .

Step 3 : deploy the image on docker container using following command

docker run -p 8081:8081 books

Now you have successfully deplyed you applicaion.

API Documentation :

you can find the all api's those are expose on the following url of swagger

http://localhost:8081/swagger-ui.html

Database :

In memory databse (H2) has used in this application.




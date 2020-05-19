# bookassignment
Restful Solution is desgined the based on MVC architecure.

    - There is Book controller to handle all the request.

    - Domain layer : contains all the db entities.
    
    - Repository : contain the logic to interact with the database
    
    - Request/Response : contains all the data models(json) used in request and response
    
    - Service Layer : where all the business logic resides.
    
    - Config - contains all configuration like swagger , rest template to call third party api.
            
 

#steps to run the application in docker container

Step 1 : Go to the root directory and package the build using following command.

    mvn clean package

Step 2 : build the docker image by running following command on terminal.

    docker build -t books .

Step 3 : deploy the image on docker container with postgres database using following command

    docker-compose build && docker-compose up

Now you have successfully deplyed you applicaion on dokcer containers.

API Documentation :

    you can find all exposed api's documentation on the following url of swagger

    http://localhost:8081/swagger-ui.html

Database -

    Postgres db is used in this application

Ports : 

    application - 8081
    database - 5434





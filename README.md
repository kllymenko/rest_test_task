# rest_test_task
This project is a Spring Boot application that provides a REST endpoint for retrieving personal information.
# Endpoints
The endpoint for this application is /person/{id}, where id is the unique identifier for the person. The endpoint returns a JSON object containing the following information:
+ first name
+ last name
+ age

In addition to the /person/{id} endpoint, this application also provides a second endpoint /person/all for retrieving all the personal information from the database. This endpoint returns a JSON array containing all the personal information stored in the database.

# Database
This application uses an H2 embedded database for storing and retrieving personal information.

# Exception Handling
It will throw a NotFoundException if no data is found with the id passed in the endpoint

# Java API Assessment: Vehicle Rental API Documentation

## Introduction

Vehicle Rentals is built as a RESTful API using Java and Spring Boot. It utilises CRUD (`create`, `read`, `update`, and `delete`) operations and allows the user manage vehicle rentals and reservations. It uses standard HTTP methods and returns responses in JSON format. 

## Dependencies
 To allow proper installation of necessary dependencies, java version in pom file was switched from 21 to 17 to match the version installed on VScode. To install the dependencies, run the below command on the terminal ensuring you first navigate to the root of the directory where you have your pom file.

    ./mvnw clean dependency:resolve

## Database and Configuration
A database is needed for storage and MySql is used in this instance for storing the `rental` objects. The application properties is configured as seen below to ensure flexibility and easier maintenance of the api. 

    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/rentals
    spring.datasource.username=*****
    spring.datasource.password=*****
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=create
    spring.jpa.open-in-view=true
    spring.config.import=optional:./local.properties

## Running the Application
Ensure you are still at the root of the directory in your terminal and run the below command. The application has started successfully when there are no errors in the terminal and no error displayed as "Could not send request" on Postman when any CRUD operation is attempted.

    ./mvnw spring-boot:run

## Testing API Endpoints
To test the API endpoints, connect to the API using Postman on port 8080. The API returns request responses in JSON format. When an API request returns an error, it is sent in the JSON response as an error key. The base URI used for the API is below.

    https://localhost:8080/api 


    POST Method 
    Description > Use this to create or add new VehicleRentals
    Endpoint > https://localhost:8080/api/vehicleRentals
   

    Request Body:
    {
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 305.87,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2023-11-02T12:00:00",
        "reservationEndDateTime": "2023-11-03T12:00:00"
    }

    Response: Status 201 Created

    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d",
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 305.87,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2023-11-02T12:00:00",
        "reservationEndDateTime": "2023-11-03T12:00:00"
    }

    PUT	Method 
    Description > Use this to update or replace a VehicleRental 
    Full Endpoint > https://localhost:8080/api/vehicleRentals/8ecf75b8-47b6-44c8-8226-d86c64e3520d
    
     Request Body:
    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d"
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 305.87,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2023-11-02T12:00:00",
        "reservationEndDateTime": "2023-11-03T12:00:00"
    }    

    Response: Status 200 OK 
    {
        "reservationId": "91b027ea-766b-4386-8981-03736695c600",
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 135.45,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-06-16T12:00:00",
        "reservationEndDateTime": "2024-06-18T12:00:00"
    }

    GET Method 
    Description > A GET call is used to retrieve all the vehicleRental data.
    Full Endpoint > https://localhost:8080/api/vehicleRentals

    Http Response: 
    * 200 OK

    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d"
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 305.87,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2023-11-02T12:00:00",
        "reservationEndDateTime": "2023-11-03T12:00:00"
    },
    {
        "reservationId": "91b027ea-766b-4386-8981-03736695c600",
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 135.45,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-06-16T12:00:00",
        "reservationEndDateTime": "2024-06-18T12:00:00"
    }

    	
    GET	by Id Method 
    Description > Get a VehicleRental by reservationId
    Full Endpoint > https://localhost:8080/api/vehicleRentals/8ecf75b8-47b6-44c8-8226-d86c64e3520d

    Http Response: 
    * 200 OK 

    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d"
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 305.87,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2023-11-02T12:00:00",
        "reservationEndDateTime": "2023-11-03T12:00:00"
    }

    Http Response:
    * 404 Not Found 

    DELETE Method 
    Description > Deletes a VehicleRental by reservationId. 
    Full Endpoint > https://localhost:8080/api/vehicleRentals/91b027ea-766b-4386-8981-03736695c600
    
    Http Response: 
    * 204 No Content 
    * 404 Not Found 

## Error Handling

The following error codes may be displayed when testing the endpoints. It shows if a request has been successful or is invalid.

    200 OK: This means request was successful.
    201 Created: The resource was successfully created.
    204 No Content: The resource was successfully deleted.
    400 Bad Request: The request was invalid or cannot be served.
    404 Not Found: The requested resource could not be found.
    500 Internal Server Error: An error occurred on the server.

Functionalities

Issue and how to resolve
# Java API Assessment: Vehicle Rental API Documentation

## Description

Vehicle Rentals is built as a RESTful API using Java and Spring Boot. It utilises CRUD (`create`, `read`, `update`, and `delete`) operations and allows the client/user manage vehicle rentals and reservations. It uses standard HTTP methods and returns responses in JSON format on Postman. 

## Database Set-up 
To setup the database to store the `rental` objects. Install and then open MySQL Workbench or use the command line. You will need to remember the username and password used during installation. Ensure the MySql server is running and create a new database:

    CREATE DATABASE rentals;

## Configuration
The application properties file is configured as seen below to ensure flexibility and easier maintenance of the api. 

    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/rentals
    spring.datasource.username=*****
    spring.datasource.password=*****
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=create
    spring.jpa.open-in-view=true
    spring.config.import=optional:./local.properties

## Dependencies
 To allow proper installation of necessary dependencies, java version in pom file was switched from 21 to 17 to match the version installed on VScode.

    <properties>
		<java.version>17</java.version>
    </properties>

Check that your pom file has all the required dependencies including the below; 

Mockito-core

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
	</dependency>

Springboot starter

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

MySQL connector dependency 

    <dependency>
       <groupId>com.mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <scope>runtime</scope>
    </dependency>

To install all the dependencies, run the below command on the terminal ensuring you first navigate to the root of the directory where you have your pom file.

    ./mvnw clean dependency:resolve    

## Running the Application
Make sure you are still at the root of the directory in your terminal and run the below command. The application has started successfully when there are no errors in the terminal and no error displayed as "Could not send request" on Postman when any CRUD operation is attempted.

    ./mvnw spring-boot:run

## Testing API Endpoints
To test the API endpoints, connect to the API using Postman on port 8080. The API returns request responses in JSON format on Postman and when the request returns an error, it is sent as an error key. The base URI used for the API is below.

    http://localhost:8080/api 


## POST Method 
Description > Use this to create or add new VehicleRentals

Full Endpoint > http://localhost:8080/api/vehicleRentals
   
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
    },
    {
        "renter": "Mary Jane",
        "plateNumber": "BS21 CGA",
        "vehicleType": "Car",
        "rentalPrice": 120.99,
        "vehicleStatus": "Unavailable",
        "driverRequested": false,
        "reservationStartDateTime": "2024-04-19T12:00:00",
        "reservationEndDateTime": "2024-04-21T12:00:00"
    }

Response: Returns a 201 (Created response) if the vehicleRental was created successfully.

Response Body: 

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
    },
    {
        "reservationId": "78189a57-cdf0-4751-be1f-edc1a08531b4",
        "renter": "Mary Jane",
        "plateNumber": "BS21 CGA",
        "vehicleType": "Car",
        "rentalPrice": 120.99,
        "vehicleStatus": "Unavailable",
        "driverRequested": false,
        "reservationStartDateTime": "2024-04-19T12:00:00",
        "reservationEndDateTime": "2024-04-21T12:00:00"
    }

Error handling: Returns a 400 (Bad Request error) if the reservation start date is greater than the end date.

## GET Method 

Description > A GET call is used to retrieve all the vehicleRental data.

Full Endpoint > http://localhost:8080/api/vehicleRentals

Response: Returns a 200 (OK response) if successful

Response Body:

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
        "reservationId": "78189a57-cdf0-4751-be1f-edc1a08531b4",
        "renter": "Mary Jane",
        "plateNumber": "BS21 CGA",
        "vehicleType": "Car",
        "rentalPrice": 120.99,
        "vehicleStatus": "Unavailable",
        "driverRequested": false,
        "reservationStartDateTime": "2024-04-19T12:00:00",
        "reservationEndDateTime": "2024-04-21T12:00:00"
    }    

## PUT Method 
Description > Use this to update or replace data in a VehicleRental 

Full Endpoint > http://localhost:8080/api/vehicleRentals/8ecf75b8-47b6-44c8-8226-d86c64e3520d
    
Request Body:

    {
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 135.45,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-06-16T12:00:00",
        "reservationEndDateTime": "2024-06-18T12:00:00"
    }    

Response: Returns a 200 (OK response) if the vehicleRental was successfully updated. 

Response Body:

    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d",
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 135.45,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-06-16T12:00:00",
        "reservationEndDateTime": "2024-06-18T12:00:00"
    }

Exception handling: Throws a NoSuchElementException if the reservationId doesn't exist.

Error handling: Returns a 404 (Not Found error) if the reservation Id is invalid or does not exist.    
    	
## GET by Id Method

Description > Get a VehicleRental by reservationId

Full Endpoint > http://localhost:8080/api/vehicleRentals/8ecf75b8-47b6-44c8-8226-d86c64e3520d

Response: Returns a 200 (OK response) if successful.

Response Body: 

    {
        "reservationId": "8ecf75b8-47b6-44c8-8226-d86c64e3520d",
        "renter": "John Doe",
        "plateNumber": "WA23 BRT",
        "vehicleType": "Van",
        "rentalPrice": 135.45,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-06-16T12:00:00",
        "reservationEndDateTime": "2024-06-18T12:00:00"
    }     

Exception handling: Throws a NoSuchElementException if the reservation doesn't exist

Error handling: Returns a 404 (Not Found error) if the reservation Id is invalid or doesn"t exist.

## DELETE Method 

Description > Deletes a VehicleRental by reservationId. 

Full Endpoint > http://localhost:8080/api/vehicleRentals/91b027ea-766b-4386-8981-03736695c600
    
Response: Returns a 204 (No Content response) if the vehical rental was successfully deleted.

Exception handling: Throws an IllegalArgumentException and NoSuchElementException if the reservation invalid or doesn't exist

Error handling: Returns a 404 (Not Found error) if the reservation Id is invalid or doesn"t exist.


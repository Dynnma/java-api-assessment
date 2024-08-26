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
Description > This endpoint is used to create new vehicle rental records in the system. The request should include details about the renter, vehicle, and reservation period. Multiple rentals can be added in a single request.

Full Endpoint > http://localhost:8080/api/vehiclerentals
   
Request Body: The request body must be a JSON array of rental objects. Each rental object should include the following fields:

`renter` (string, required): The name of the person renting the vehicle.
`plateNumber` (string, required): The license plate number of the vehicle.
`vehicleType` (string, required): The type of vehicle (e.g. Car, Van, Suv).
`rentalPrice` (number, required): The price of the rental.
`vehicleStatus` (string, required): The status of the vehicle (Available, Unavailable).
`driverRequested` (boolean, required): Whether the renter has requested a driver.
`reservationStartDateTime` (string, required): The start date and time of the reservation in ISO 8601 format (YYYY-MM-DDTHH:MM:SS).
`reservationEndDateTime` (string, required): The end date and time of the reservation in ISO 8601 format (YYYY-MM-DDTHH:MM:SS).

Sample Body

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

Error handling: Returns a `400 Bad Request` error if the request is invalid. One common reason is if the reservationStartDateTime is later than the reservationEndDateTime.

Expected Response: Returns the status code `201 Created` if the vehicle rental(s) were created successfully. The response body will include the full details of the newly created rental(s), including a unique reservationId for each as illustrated below.

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

## GET Method 

Description > This endpoint is used to retrieve all the vehicle rental data stored in the system. It provides details about each rental, including the renter's information, vehicle type, vehicle status, rental price, and reservation details.

Full Endpoint > http://localhost:8080/api/vehiclerentals

Expected Response: Returns the status code `200 OK` with a response body if the request is successful.

Response Body: The expected response body is a JSON array containing the details of all the vehicle rentals as illustrated below.

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
Description > This endpoint is used to update or replace the details of an existing vehicle rental. You need to provide the reservationId in the URL path to specify which rental record to update. Ensure to replace the placeholder `{reservationId}` with the actual ID of the vehicle rental you wish to update.

Full Endpoint > http://localhost:8080/api/vehiclerentals/{reservationId}

Exception Handling: Throws a NoSuchElementException if the provided reservationId does not exist in the system. This is an internal exception and should be translated to an appropriate HTTP error response.

Error Handling: Returns the status code `404 Not Found` if the provided reservationId is invalid or does not exist.

Expected Response: Returns the status code `200 OK` if the vehicle rental was successfully updated. The response body will include the updated details of the vehicle rental. The sample body below represents an update on the vehicle rental with reservationId "8ecf75b8-47b6-44c8-8226-d86c64e3520d":

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

Expected response body after update:

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
    	
## GET by Id Method

Description > This endpoint retrieves the details of a specific vehicle rental based on its unique reservationId. It is useful for fetching the details of a particular rental record. Replace the placeholder `{reservationId}` with the actual ID of the vehicle rental you want to retrieve.

Full Endpoint > http://localhost:8080/api/vehiclerentals/{reservationId}

Exception Handling: Throws a NoSuchElementException if the provided reservationId does not exist in the system. This is an internal exception and should be translated to an appropriate HTTP error response.

Error Handling: Returns the status code `404 Not Found` if the provided reservationId is invalid or does not exist.

Expected Response: Returns the status code `200 OK` if the request was successful, and the response body contains the details of the vehicle rental associated with the provided reservationId.

Sample Response Body using the endpoint http://localhost:8080/api/vehiclerentals/8ecf75b8-47b6-44c8-8226-d86c64e3520d

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

## DELETE Method 

Description > This endpoint is used to delete a specific vehicle rental record identified by its unique reservationId. Once deleted, the vehicle rental record will no longer be available in the system. Replace the placeholder `{reservationId}` with the actual ID of the vehicle rental you want to delete.

Full Endpoint > http://localhost:8080/api/vehiclerentals/{reservationId}

Exception Handling: Throws an `IllegalArgumentException` if the reservationId provided is invalid, such as being incorrectly formatted and throws a `NoSuchElementException` if the reservationId does not exist in the system. This is typically used to handle cases where the ID is valid in format but does not correspond to any existing rental record.

Error Handling: Returns the status code `404 Not Found` if the provided reservationId is invalid or does not exist in the system. 
    
Expected Response: Returns the status code `204 No Content` if the vehicle rental was successfully deleted. This response indicates that the operation was successful, but no content is returned in the response body.

## GET Method (Testing filter by vehicleType and Order by rentalPrice algorithm on Postman)

Description > This endpoint allows you to retrieve a list of vehicle rentals filtered by a specific vehicleType, such as "Car", and orders the results by rentalPrice in ascending order. This is useful for finding the most affordable options within a specific vehicle category. Replace the placeholder `{vehicleType}` with the desired vehicle type (e.g., Car, Van, SUV).

Full Endpoint > http://localhost:8080/api/vehiclerentals/filterByVehicleType?vehicleType={vehicleType}

Response: Returns the status code `200 OK` if the request was successful. The response body contains a JSON array of vehicle rental records that match the filter criteria. The records are sorted by rentalPrice in ascending order.

## Sample Testing Process in Postman 
> Select the GET method in Postman,
> Enter the Full Endpoint http://localhost:8080/api/vehiclerentals/filterByVehicleType?vehicleType=Car.
> Send the request and verify that the results are filtered by the specified vehicleType `Car`and sorted by rentalPrice in ascending order.

Response Body

    {
        "reservationId": "5a3ca809-036f-4516-8942-b3e0258c96ea",
        "renter": "May June",
        "plateNumber": "RT98 MCK",
        "vehicleType": "Car",
        "rentalPrice": 200.99,
        "vehicleStatus": "Available",
        "driverRequested": true,
        "reservationStartDateTime": "2024-03-21T12:00:00",
        "reservationEndDateTime": "2024-03-28T12:00:00"
    },
    {
        "reservationId": "c60cc04c-41d2-4829-9a66-d6fdf5426fb5",
        "renter": "Chap Fenty",
        "plateNumber": "FT23 TDD",
        "vehicleType": "Car",
        "rentalPrice": 450.99,
        "vehicleStatus": "Reserved",
        "driverRequested": false,
        "reservationStartDateTime": "2024-07-01T12:00:00",
        "reservationEndDateTime": "2024-07-22T12:00:00"
    }



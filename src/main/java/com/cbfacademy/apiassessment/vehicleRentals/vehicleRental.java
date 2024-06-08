package com.cbfacademy.apiassessment.vehicleRentals;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicleRental")

public class vehicleRental {
    
    private static Object vehicleType;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String renter;
    private String plateNumber;
    private String VehicleType;
    private int rentalPrice;
    private Instant dateTime;

    public vehicleRental() {
        this(null, null, VehicleType, rentalPrice, Instant.now());
    }

    public vehicleRental(String renter, String plateNumber, String vehicleType, int rentalPrice) {
        this(renter, plateNumber, vehicleType, rentalPrice, Instant.now());
    }

    public vehicleRental(String renter, String plateNumber, String vehicleType, int rentalPrice, Instant dateTime) {
        this.renter = renter;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.rentalPrice = rentalPrice;
        this.dateTime = dateTime;
    }
    
    public String getPlateNumber() {
        return plateNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType() {
        this.vehicleType = vehicleType;
    }
    public String getRenter() {
        return renter;
    }
    public void setRenter(String renter) {
        this.renter = renter;
    }
    public int getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public Instant getDateTime() {
        return dateTime;
    }
    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
 } 

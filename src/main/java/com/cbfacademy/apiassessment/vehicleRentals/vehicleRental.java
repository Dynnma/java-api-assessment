package com.cbfacademy.apiassessment.vehicleRentals;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicleRental")

public class vehicleRental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String renter;
    private String vehicleSupplier;
    private String plateNumber;
    private String vehicleType;
    private int rentalPrice;
    private LocalDateTime reservationStartDateTime;
    private LocalDateTime reservationEndDateTime;


    public vehicleRental() {
        this(null, null, null, null, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public vehicleRental(String renter, String vehicleSupplier, String plateNumber, String vehicleType, int rentalPrice) {
        this(renter, vehicleSupplier, plateNumber, vehicleType, rentalPrice, LocalDateTime.now(), LocalDateTime.now());
    }

    public vehicleRental(String renter, String vehicleSupplier, String plateNumber, String vehicleType, int rentalPrice, LocalDateTime reservationStartDateTime, LocalDateTime reservationEndDateTime) {
        this.renter = renter;
        this.vehicleSupplier = vehicleSupplier;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.rentalPrice = rentalPrice;
        this.reservationStartDateTime = reservationStartDateTime;
        this.reservationEndDateTime = reservationEndDateTime;
    }
    public UUID getId() {
        return id;
    }
    public String getRenter() {
        return renter;
    }
    public void setRenter(String renter) {
        this.renter = renter;
    }
    public String getVehicleSupplier() {
        return vehicleSupplier;
    }
    public void setVehicleSupplier(String vehicleSupplier) {
        this.vehicleSupplier = vehicleSupplier;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public int getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public LocalDateTime getReservationStartDateTime() {
        return reservationStartDateTime;
    }
    public void setReservationStartDateTime(LocalDateTime reservationStartDateTime) {
        this.reservationStartDateTime = reservationStartDateTime;
    }
    public LocalDateTime getReservationEndDateTime() {
        return reservationEndDateTime;
    }
    public void setReservationEndDateTime(LocalDateTime reservationEndDateTime) {
        this.reservationEndDateTime = reservationEndDateTime;
    }
 } 

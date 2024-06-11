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
@Table(name = "VehicleRental")

public class VehicleRental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reservationId;
    private String renter;
    private String plateNumber;
    private String vehicleType;
    private double rentalPrice;
    private LocalDateTime reservationStartDateTime;
    private LocalDateTime reservationEndDateTime;


    public VehicleRental() {
        this(null, null, null, 0.0, LocalDateTime.now(), LocalDateTime.now());
    }

    public VehicleRental(String renter, String plateNumber, String vehicleType, double rentalPrice) {
        this(renter, plateNumber, vehicleType, rentalPrice, LocalDateTime.now(), LocalDateTime.now());
    }

    public VehicleRental(String renter, String plateNumber, String vehicleType, double rentalPrice2, LocalDateTime reservationStartDateTime, LocalDateTime reservationEndDateTime) {
        this.renter = renter;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.rentalPrice = rentalPrice2;
        this.reservationStartDateTime = reservationStartDateTime;
        this.reservationEndDateTime = reservationEndDateTime;
    }
    public UUID getReservationId() {
        return reservationId;
    }
    public String getRenter() {
        return renter;
    }
    public void setRenter(String renter) {
        this.renter = renter;
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
    public double getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(double rentalPrice) {
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

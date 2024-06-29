package com.cbfacademy.apiassessment.vehicleRentals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicleRentals")
public class VehicleRentalController {
    private VehicleRentalService vehiclerentalService;

    @Autowired
    public VehicleRentalController(VehicleRentalService vehiclerentalService) {
        this.vehiclerentalService = vehiclerentalService;
    }

    @GetMapping()
    public List<VehicleRental> getAllVehicleRentals() {
        return vehiclerentalService.getAllVehicleRentals();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<VehicleRental> getVehicleRentals(@PathVariable UUID reservationId) {
        try {
            VehicleRental getvehiclerental = vehiclerentalService.getVehicleRental(reservationId);
            return ResponseEntity.ok(getvehiclerental);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<VehicleRental> createVehiclerental(@RequestBody VehicleRental vehiclerental) {
        if (vehiclerental.getReservationStartDateTime().isAfter(vehiclerental.getReservationEndDateTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } 
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(vehiclerentalService.createVehicleRental(vehiclerental));
        }
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<VehicleRental> updateVehicleRental(@PathVariable UUID reservationId, @RequestBody VehicleRental updatedVehicleRental) {
        try {
            VehicleRental updatedVehiclerental = vehiclerentalService.updateVehicleRental(reservationId, updatedVehicleRental);
            return ResponseEntity.ok(updatedVehiclerental);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteVehicleRental(@PathVariable UUID reservationId) {
        try {
            vehiclerentalService.getVehicleRental(reservationId);
            vehiclerentalService.deleteVehicleRental(reservationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException | NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

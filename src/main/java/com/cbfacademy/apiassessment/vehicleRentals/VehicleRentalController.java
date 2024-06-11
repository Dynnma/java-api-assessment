package com.cbfacademy.apiassessment.vehicleRentals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("{id}")
    public ResponseEntity<VehicleRental> getVehicleRentals(@PathVariable UUID id) {
        try {
            VehicleRental getvehiclerental = vehiclerentalService.getVehicleRental(id);
            return ResponseEntity.ok(getvehiclerental);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public VehicleRental createVehiclerental(@RequestBody VehicleRental vehiclerental) {
        return vehiclerentalService.createVehicleRental(vehiclerental);
    }

    @PutMapping("{id}")
    public ResponseEntity<VehicleRental> updateVehicleRental(@PathVariable UUID id, @RequestBody VehicleRental updatedVehicleRental) {
        try {
            VehicleRental updatedVehiclerental = vehiclerentalService.updateVehicleRental(id, updatedVehicleRental);
            return ResponseEntity.ok(updatedVehiclerental);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVehicleRental(@PathVariable UUID id){
        try {
            vehiclerentalService.getVehicleRental(id);
            vehiclerentalService.deleteVehicleRental(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException | NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

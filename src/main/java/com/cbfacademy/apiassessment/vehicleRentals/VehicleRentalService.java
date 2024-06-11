package com.cbfacademy.apiassessment.vehicleRentals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class VehicleRentalService {
    public VehicleRentalRepository vehiclerentalRepository;

    public VehicleRentalService(VehicleRentalRepository vehiclerentalRepository) {
        this.vehiclerentalRepository = vehiclerentalRepository;
    }
    public List<VehicleRental> getAllVehicleRentals() {
        return vehiclerentalRepository.findAll();
    }
    public VehicleRental getVehicleRental(UUID id) {
        return vehiclerentalRepository.findById(id).orElseThrow();
    }
    public VehicleRental createVehicleRental(VehicleRental vehiclerental) throws IllegalArgumentException, OptimisticLockingFailureException {
        return vehiclerentalRepository.save(vehiclerental);
    } 
    public VehicleRental updateVehicleRental(UUID id, VehicleRental updatedVehicleRental) throws NoSuchElementException {
        VehicleRental vehiclerental = vehiclerentalRepository.findById(id).orElseThrow();
        vehiclerental.setRenter(updatedVehicleRental.getRenter());
        vehiclerental.setPlateNumber(updatedVehicleRental.getPlateNumber());
        vehiclerental.setVehicleType(updatedVehicleRental.getVehicleType());
        vehiclerental.setRentalPrice(updatedVehicleRental.getRentalPrice());
        vehiclerental.setReservationStartDateTime(updatedVehicleRental.getReservationStartDateTime());
        vehiclerental.setReservationEndDateTime(updatedVehicleRental.getReservationEndDateTime());
        return vehiclerentalRepository.save(vehiclerental);
    }
    public void deleteVehicleRental(UUID id) {
        vehiclerentalRepository.findById(id).orElseThrow();
        vehiclerentalRepository.deleteById(id);
    }
}

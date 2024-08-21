package com.cbfacademy.apiassessment.vehiclerentals;

import java.util.Arrays;
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

    public VehicleRental getVehicleRental(UUID reservationId) {
        return vehiclerentalRepository.findById(reservationId).orElseThrow();
    }

    public VehicleRental createVehicleRental(VehicleRental vehiclerental)
            throws IllegalArgumentException, OptimisticLockingFailureException {
        return vehiclerentalRepository.save(vehiclerental);
    }

    public VehicleRental updateVehicleRental(UUID reservationId, VehicleRental updatedVehicleRental)
            throws NoSuchElementException {
        VehicleRental vehiclerental = vehiclerentalRepository.findById(reservationId).orElseThrow();
        vehiclerental.setRenter(updatedVehicleRental.getRenter());
        vehiclerental.setPlateNumber(updatedVehicleRental.getPlateNumber());
        vehiclerental.setVehicleType(updatedVehicleRental.getVehicleType());
        vehiclerental.setRentalPrice(updatedVehicleRental.getRentalPrice());
        vehiclerental.setVehicleStatus(updatedVehicleRental.getVehicleStatus());
        vehiclerental.setDriverRequested(updatedVehicleRental.isDriverRequested());
        vehiclerental.setReservationStartDateTime(updatedVehicleRental.getReservationStartDateTime());
        vehiclerental.setReservationEndDateTime(updatedVehicleRental.getReservationEndDateTime());
        return vehiclerentalRepository.save(vehiclerental);
    }

    public void deleteVehicleRental(UUID reservationId) {
        vehiclerentalRepository.findById(reservationId).orElseThrow();
        vehiclerentalRepository.deleteById(reservationId);
    }

    public List<VehicleRental> filterByVehicleType(String vehicleType) {

        List<VehicleRental> rentals = vehiclerentalRepository.findByVehicleType(vehicleType);
        VehicleRental[] rentalsArray = rentals.toArray(new VehicleRental[rentals.size()]);

        for (int i = 0; i < rentalsArray.length; i++) {
            for (int v = 0; v < i; v++) {

                if (rentalsArray[i].getRentalPrice() < rentalsArray[v].getRentalPrice()) {
                    VehicleRental rental = rentalsArray[i];
                    rentalsArray[i] = rentalsArray[v];
                    rentalsArray[v] = rental;
                }
            }
        }
        return Arrays.asList(rentalsArray);
    }
}

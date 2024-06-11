package com.cbfacademy.apiassessment.vehicleRentals;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface VehicleRentalRepository extends ListCrudRepository<VehicleRental, UUID> {

    List<VehicleRental> searchByrenter(String renter); 

    List<VehicleRental> searchByplateNumber(String plateNumber); 

    List<VehicleRental> sortByvehicleType(String vehicleType); 


}
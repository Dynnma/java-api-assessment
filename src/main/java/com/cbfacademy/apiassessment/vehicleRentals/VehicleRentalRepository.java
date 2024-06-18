package com.cbfacademy.apiassessment.vehicleRentals;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRentalRepository extends ListCrudRepository<VehicleRental, UUID> {

    List<VehicleRental> filterByRenter(String renter); 

    List<VehicleRental> filterByVehicleType(String vehicleType); 



}
package com.cbfacademy.apiassessment.vehiclerentals;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRentalRepository extends ListCrudRepository<VehicleRental, UUID> {

    List<VehicleRental> findByRenter(String renter); 

    List<VehicleRental> findByVehicleType(String vehicleType); 



}
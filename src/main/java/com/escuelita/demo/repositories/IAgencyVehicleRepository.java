package com.escuelita.demo.repositories;


import com.escuelita.demo.entities.pivots.AgencyVehicle;
import com.escuelita.demo.entities.projections.AgencyProjection;
import com.escuelita.demo.entities.projections.VehicleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAgencyVehicleRepository extends JpaRepository<AgencyVehicle,Long> {

    @Query(value = "select * from agencies_vehicles "+
            "inner join agencies on agencies_vehicles.agency_id =agency_id " +
            "where agencies_vehicles.vehicle_id = :vehicleId", nativeQuery = true)
    List<AgencyProjection> listAllAgenciesByVehicleId(Long vehicleId);

    @Query(value = "select vehicles.* from agencies_vehicles "+
            "inner join agencies on agencies_vehicles.agency_id = agencies.id " +
            "inner join vehicles on agencies_vehicles.vehicles_id = vehicles.id " +
            "where agencies_vehicles.vehicle_id = :agencyId", nativeQuery = true)
    List<VehicleProjection> listAllVehicleByAgenciesId(Long agencyId);



}

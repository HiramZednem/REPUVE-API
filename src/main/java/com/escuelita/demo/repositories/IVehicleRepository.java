package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Vehicle;
import com.escuelita.demo.entities.projections.VehicleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select vehicles.*, owner.first_name + owner.last_name as ownerName from vehicles " +
            "inner join owners owner on owner.id = vehicles.owner_id " +
            "where owner.id = vehicles.owner_id", nativeQuery = true)
    List<VehicleProjection> listAllVehiclesByOwnerId(Long ownerId);



}

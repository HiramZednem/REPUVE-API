package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Vehicle;

import com.escuelita.demo.entities.projections.VehicleEngineProjection;
import com.escuelita.demo.entities.projections.VehicleOwnerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.escuelita.demo.entities.projections.VehicleProjection;


import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "select v.id,v.color,v.mileage, v.model ,v.year, v.price,v.owner_id as ownerId ,o.first_name, o.last_name from vehicles v " +
            "inner join owners o on v.owner_id = o.id " +
            "where v.owner_id = :ownerId", nativeQuery = true)
    List<VehicleOwnerProjection> listAllVehiclesByOwnerId(Long ownerId);


    @Query(value = "select v.id,v.color,v.mileage, v.model ,v.year, v.price, v.engine_id as engineId ,e.engine_type as engineType from vehicles v " +
            "inner join  engines e on v.engine_id = e.id " +
            "where v.engine_id = :engineId;", nativeQuery = true)
    List<VehicleEngineProjection> ListAllVehiclesByEngineId(Long engineId);



    @Query(value = "select vehicles.* from vehicles" +
            "    inner join brands on vehicles.brand_id = brands.id" +
            "    where brands.id =:brandId",nativeQuery = true)
    List<VehicleProjection> listAllVehiclesByBrandId (Long brandId);

}

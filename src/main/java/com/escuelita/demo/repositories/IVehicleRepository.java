package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Vehicle;
import com.escuelita.demo.entities.projections.VehicleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select vehicles.* from vehicles" +
            "    inner join brands on vehicles.brand_id = brands.id" +
            "    where brands.id =:brandId",nativeQuery = true)
    List<VehicleProjection> listAllVehiclesByBrandId (Long brandId);
}

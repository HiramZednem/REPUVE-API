package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Vehicle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

}
package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car, Long> {
}

package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInsuranceRepository extends JpaRepository<Insurance, Long> {
}

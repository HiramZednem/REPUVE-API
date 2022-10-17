package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgencyRepository extends JpaRepository <Agency,Long> {
}
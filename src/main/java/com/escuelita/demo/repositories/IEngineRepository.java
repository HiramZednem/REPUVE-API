package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEngineRepository extends JpaRepository<Engine, Long> {

}
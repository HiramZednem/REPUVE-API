package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.entities.projections.OwnerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Long> {

    @Query(value = "select owners.*, insurance.name as insuranceName from owners"+
    " inner join insurances insurance on owners.insurance_id = insurance.id"+
    " where insurance.id = :id", nativeQuery = true)
    List<OwnerProjection> listAllOwnersByInsurance(Long id);
}

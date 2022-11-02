package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.entities.projections.OwnerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Long> {

    @Query(value = "select owners.*, insurance.name from owners"+
    " inner join insurances insurance on inusrance.id = owner.id"+
    " where insurance.id = owners.insurance_id", nativeQuery = true)
    List<OwnerProjection> listAllOwnersByInsurance(Long id);
}

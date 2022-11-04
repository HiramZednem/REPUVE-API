package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.ProsecutorsOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProsecutorsOfficeRepository extends JpaRepository<ProsecutorsOffice, Long> {
}

package com.escuelita.demo.entities.pivots;


import com.escuelita.demo.entities.Agency;
import com.escuelita.demo.entities.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="agencies_vehicles")
public class AgencyVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Agency agency;

    @ManyToOne
    private Vehicle vehicle;
}

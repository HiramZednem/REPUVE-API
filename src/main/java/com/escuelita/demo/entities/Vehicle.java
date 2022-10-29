package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.AgencyVehicle;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "Vehicles")
public class Vehicle {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double mileage;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String model;

    @OneToMany(mappedBy="vehicle")
    private List<AgencyVehicle> agencyVehicles;
}

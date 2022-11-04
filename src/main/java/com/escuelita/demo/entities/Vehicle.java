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
    private Integer year;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double mileage;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String plate;


    @OneToMany(mappedBy="vehicle")
    private List<AgencyVehicle> agencyVehicles;


}

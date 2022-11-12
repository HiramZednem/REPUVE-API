package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.AgencyVehicle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    private Double price;

    @Column(nullable = false)
    private Double mileage;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String model;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    private Engine engine;

    @OneToMany(mappedBy="vehicle")
    private List<AgencyVehicle> agencyVehicles;


}

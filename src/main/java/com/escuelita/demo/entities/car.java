package com.escuelita.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
@Setter @Getter
public class car {

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

}

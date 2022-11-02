package com.escuelita.demo.entities.projections;

import lombok.Getter;

@Getter
public class VehicleOwnerProjection {

    private Long Id;
    private String color;
    private Double mileage;
    private String model;
    private Double price;
    private Integer year;
    private Long ownerId;

    private String firstName;
    private String lastName;

}

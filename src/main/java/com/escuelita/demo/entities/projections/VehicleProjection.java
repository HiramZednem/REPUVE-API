package com.escuelita.demo.entities.projections;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VehicleProjection {

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

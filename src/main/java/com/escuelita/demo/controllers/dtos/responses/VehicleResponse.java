package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VehicleResponse {
    private Long Id;
    private String color;
    private Double mileage;
    private Integer year;
    private String model;
    private String ownerName;
}

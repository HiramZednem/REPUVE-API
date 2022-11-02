package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VehicleEngineResponse {
    private Long Id;
    private String color;
    private Double mileage;
    private String model;
    private Double price;
    private Integer year;
    private Long engineId;
    private String engineType;
}

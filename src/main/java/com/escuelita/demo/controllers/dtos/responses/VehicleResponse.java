package com.escuelita.demo.controllers.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponse {
    private Long Id;
    private String model;
    private Integer year;
    private String color;
}

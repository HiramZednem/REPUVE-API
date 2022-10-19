package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateVehicleRequest {

    private int year;

    private double price;

    private double mileage;

    private String color;

    private String model;
}

package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class CreateVehicleResponse {

    private Long id;

    private int year;

    private double price;

    private double mileage;

    private String color;

    private String model;

     private OwnerResponse ownerName;
}

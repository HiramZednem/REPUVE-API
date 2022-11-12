package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateVehicleRequest {

    private Integer year;

    private Double price;

    private Double mileage;

    private String color;

    private String model;

    // not use this variable when creating a new vehicle
    private Long ownerId; //ESTA PARTE ES DE HUERTA, ESTOY HACIENDO EL MERGE Y CREO QUE NO LA HA TERMINADO 

    private Long brandId;

    private Long engineId;

}

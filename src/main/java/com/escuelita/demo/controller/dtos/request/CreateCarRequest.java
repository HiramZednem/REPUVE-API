package com.escuelita.demo.controller.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCarRequest {

    private int year;

    private double price;

    private double mileage;

    private String color;

    private String model;
}

package com.escuelita.demo.controller.dtos.response;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUpdateCarResponse {

    private int year;

    private double price;

    private double mileage;

    private String color;

    private String model;
}

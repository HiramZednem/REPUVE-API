package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProsecutorsOfficeRequest {

    private String name;
    private String country;
    private String state;
    private String city;
    private String address;
    private String postalCode;
}

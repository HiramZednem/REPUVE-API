package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter

public class GetProsecutorsOfficeResponse {

    private Long id;
    private String name;
    private String country;
    private String state;
    private String city;
    private String address;
    private String postalCode;
}

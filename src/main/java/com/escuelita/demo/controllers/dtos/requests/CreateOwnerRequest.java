package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOwnerRequest {
    private String rfc;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;

}


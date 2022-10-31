package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerResponse {
    private Long id;
    private String rfc;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String city;


}

package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetOwnerResponse {
    private Long id;
    private String rfc;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
}

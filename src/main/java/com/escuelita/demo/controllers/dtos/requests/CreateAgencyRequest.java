package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAgencyRequest {
    private String name;
    private String address;
    private Long cellphone_number;
}

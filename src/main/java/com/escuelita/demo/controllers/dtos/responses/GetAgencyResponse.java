package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAgencyResponse {
    private Long id;
    private String name;
    private String address;
    private Long cellphone_number;
}

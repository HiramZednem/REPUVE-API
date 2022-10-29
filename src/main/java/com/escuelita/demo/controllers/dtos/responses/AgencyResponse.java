package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgencyResponse {
    private Long Id;
    private String Address;
    private String Name;
    private Long Cellphone_number;
}

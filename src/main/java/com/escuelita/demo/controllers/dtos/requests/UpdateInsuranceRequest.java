package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateInsuranceRequest {
    private Long id;

    private String name;

    private String headquarter;

    private String website;
}

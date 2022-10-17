package com.escuelita.demo.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateInsuranceRequest {
    private Long id;

    private String name;

    private String headquarter;

    private String website;
}

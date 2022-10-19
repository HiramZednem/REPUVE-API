package com.escuelita.demo.controllers.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateInsuranceResponse {
    private Long id;

    private String name;

    private String headquarter;

    private String website;
}

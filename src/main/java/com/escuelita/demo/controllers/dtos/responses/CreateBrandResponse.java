package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateBrandResponse {
    Long id;
    String name;
    String headquarter;
    String website;
}

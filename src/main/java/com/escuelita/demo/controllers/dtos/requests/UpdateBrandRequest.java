package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateBrandRequest {
    String name;
    String headquarter;
    String website;
}

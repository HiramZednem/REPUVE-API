package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetEngineResponse {
    private Long id;
    private String cylinder;
    private String engineType;
}

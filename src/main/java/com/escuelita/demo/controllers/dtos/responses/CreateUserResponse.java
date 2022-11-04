package com.escuelita.demo.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long workerCode;
}

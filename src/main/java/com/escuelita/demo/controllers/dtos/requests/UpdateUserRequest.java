package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long workerCode;
}

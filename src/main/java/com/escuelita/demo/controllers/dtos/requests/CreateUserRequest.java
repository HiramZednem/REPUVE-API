package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long workerCode;

    private Long officeId;
}

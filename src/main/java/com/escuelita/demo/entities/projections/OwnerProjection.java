package com.escuelita.demo.entities.projections;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerProjection {
    private Long id;

    private String rfc;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String address;

    private String phoneNumber;
}

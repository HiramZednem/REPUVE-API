package com.escuelita.demo.entities.projections;

import lombok.Getter;
import lombok.Setter;


public interface OwnerProjection {
   Long getId();

    String getRfc();

    String getFirstName();

    String getLastName();

    String getCountry();

    String getCity();

    String getAddress();

    String getPhoneNumber();

    String getInsuranceName();
}

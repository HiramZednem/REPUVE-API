package com.escuelita.demo.entities.projections;



public interface VehicleOwnerProjection {

    Long getId();
    String getColor();
    Double getMileage();
    String getModel();
    Double getPrice();
    Integer getYear();
    Long getOwnerId();

    String getFirstName();
    String getLastName();

}

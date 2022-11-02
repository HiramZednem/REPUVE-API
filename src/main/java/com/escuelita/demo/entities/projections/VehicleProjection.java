package com.escuelita.demo.entities.projections;

public interface VehicleProjection {
    Long getId();

    int getYear();

    double getPrice();

    double getMileage();

    String getColor();

    String getModel();

    Long getBrand_Id();

}

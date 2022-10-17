package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateCarRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateCarResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateCarResponse;

import java.util.List;

public interface ICarService {
    //CREATE
    CreateCarResponse createCar(CreateCarRequest car);
    //READ
    CreateCarResponse seeCar(Long id);
    List<CreateCarResponse> seeCars();
    //UPDATE
    CreateUpdateCarResponse updateCar(Long id, CreateCarRequest carRequest);
    //DELETE
    void deleteCar(Long id);
}

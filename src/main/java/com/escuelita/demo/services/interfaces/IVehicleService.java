package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateVehicleResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateVehicleResponse;

import java.util.List;

public interface IVehicleService {
    //CREATE
    CreateVehicleResponse createCar(CreateVehicleRequest car);
    //READ
    CreateVehicleResponse seeCar(Long id);
    List<CreateVehicleResponse> seeCars();
    //UPDATE
    CreateUpdateVehicleResponse updateCar(Long id, CreateVehicleRequest carRequest);
    //DELETE
    void deleteCar(Long id);


}

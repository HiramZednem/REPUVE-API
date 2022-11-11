package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateVehicleResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateVehicleResponse;
import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.entities.Vehicle;

import java.util.List;

public interface IVehicleService {
    BaseResponse createCar(CreateVehicleRequest car);
    //READ
    BaseResponse seeCar(Long id);
    BaseResponse seeCars();
    //UPDATE
    BaseResponse updateCar(Long id, CreateVehicleRequest carRequest);
    //DELETE
    void deleteCar(Long id);

    BaseResponse listAllVehiclesByOwnerId(Long ownerId);

    BaseResponse listAllVehiclesByEngineId(Long engineId);

    List<CreateVehicleResponse>listAllVehiclesByBrandId (Long id);
}

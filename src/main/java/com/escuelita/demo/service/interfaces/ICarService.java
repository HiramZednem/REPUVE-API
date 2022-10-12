package com.escuelita.demo.service.interfaces;

import com.escuelita.demo.controller.dtos.request.CreateCarRequest;
import com.escuelita.demo.controller.dtos.response.CreateCarResponse;
import com.escuelita.demo.controller.dtos.response.CreateUpdateCarResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

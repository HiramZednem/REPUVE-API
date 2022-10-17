package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateCarRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateCarResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateCarResponse;
import com.escuelita.demo.services.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    ICarService service;

    //CREATE
    @PostMapping
    CreateCarResponse createCar(@RequestBody CreateCarRequest carRequest){
        return service.createCar(carRequest);
    }

    //READ
    @GetMapping("{id}")
    CreateCarResponse seeCar(@PathVariable Long id){
         return service.seeCar(id);
    }
    @GetMapping
    List<CreateCarResponse> seeCars(){
        return service.seeCars();
    }

    //UPDATE
    @PutMapping("{id}")
    CreateUpdateCarResponse updateCar(@PathVariable Long id, @RequestBody CreateCarRequest carRequest){
         return service.updateCar(id, carRequest);
    }

    //DELETE
    @DeleteMapping("{id}")
    void deleteCar(@PathVariable Long id){
        service.deleteCar(id);
    }
}

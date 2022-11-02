package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateVehicleResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateVehicleResponse;
import com.escuelita.demo.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Vehicles")
public class VehicleController {

    @Autowired
    IVehicleService service;

    //CREATE
    @PostMapping
    CreateVehicleResponse createCar(@RequestBody CreateVehicleRequest carRequest){
        return service.createCar(carRequest);
    }

    //READ
    @GetMapping("{id}")
    CreateVehicleResponse seeCar(@PathVariable Long id){
         return service.seeCar(id);
    }
    @GetMapping
    List<CreateVehicleResponse> seeCars(){
        return service.seeCars();
    }

    @GetMapping("brand/{brandId}")
    List<CreateVehicleResponse> listAllVehiclesByBrandId(@PathVariable Long brandId) {
        return service.listAllVehiclesByBrandId(brandId);
    }

    //UPDATE
    @PutMapping("{id}")
    CreateUpdateVehicleResponse updateCar(@PathVariable Long id, @RequestBody CreateVehicleRequest carRequest){
         return service.updateCar(id, carRequest);
    }

    //DELETE
    @DeleteMapping("{id}")
    void deleteCar(@PathVariable Long id){
        service.deleteCar(id);
    }


}

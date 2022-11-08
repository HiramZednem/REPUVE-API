package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateVehicleResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateVehicleResponse;
import com.escuelita.demo.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    IVehicleService service;

    @PostMapping
    public void createCar(@RequestBody CreateVehicleRequest carRequest){
        BaseResponse baseResponse= service.createCar(carRequest);
//        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    //READ
    @GetMapping
    public ResponseEntity<BaseResponse> seeCars(){
        BaseResponse baseResponse = service.seeCars();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> seeCar(@PathVariable Long id){
        BaseResponse baseResponse = service.seeCar(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("own/{ownerId}")
    public ResponseEntity< BaseResponse> listAllVehiclesByOwnerId(@PathVariable Long ownerId){
       BaseResponse baseResponse= service.listAllVehiclesByOwnerId(ownerId);
       return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }



    @GetMapping("engine/{engineId}")
    public ResponseEntity< BaseResponse> listAllVehiclesByEngineId(@PathVariable Long engineId) {
        BaseResponse baseResponse = service.listAllVehiclesByEngineId(engineId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }



    @GetMapping("brand/{brandId}")
    List<CreateVehicleResponse> listAllVehiclesByBrandId(@PathVariable Long brandId) {
        return service.listAllVehiclesByBrandId(brandId);
    }


    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> updateCar(@PathVariable Long id, @RequestBody CreateVehicleRequest carRequest){
        BaseResponse baseResponse = service.updateCar(id, carRequest);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    //DELETE
    @DeleteMapping("{id}")
    void deleteCar(@PathVariable Long id){
        service.deleteCar(id);
    }


}

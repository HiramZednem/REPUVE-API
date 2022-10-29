package com.escuelita.demo.controllers;


import com.escuelita.demo.controllers.dtos.responses.AgencyResponse;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IAgencyVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("agency-vehicle")
public class AgencyVehicleController {

    @Autowired
    private IAgencyVehicleService service;
    @GetMapping ("agency/vehicle/{vehicleId}")
    public ResponseEntity<BaseResponse> listAllAgenciesByVehicleId(@PathVariable Long vehicleId) {
        BaseResponse baseResponse =  service.listAllAgenciesByVehicleId(vehicleId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}

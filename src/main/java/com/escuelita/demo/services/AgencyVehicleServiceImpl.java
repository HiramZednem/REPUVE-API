package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.responses.AgencyResponse;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.projections.AgencyProjection;
import com.escuelita.demo.repositories.IAgencyVehicleRepository;
import com.escuelita.demo.services.interfaces.IAgencyVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgencyVehicleServiceImpl implements IAgencyVehicleService {

    @Autowired
    private IAgencyVehicleRepository repository;


    @Override
    public BaseResponse listAllAgenciesByVehicleId(Long vehicleId) {
        List<AgencyProjection> agencies =repository.listAllAgenciesByVehicleId(vehicleId);
        List<AgencyResponse> response = agencies.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder().data(response).message("Agency list by vehicle id")
                .success(Boolean.TRUE).httpStatus(HttpStatus.OK).build();
    }

    private AgencyResponse from(AgencyProjection agency){
        AgencyResponse response = new AgencyResponse();
        response.setId(agency.getId());
        response.setAddress(agency.getAddress());
        response.setCellphone_number(agency.getCellphone_number());
        response.setName(agency.getName());
        return response;
    }



}

package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetInsuranceResponse;
import com.escuelita.demo.controllers.responses.CreateInsuranceResponse;
import com.escuelita.demo.controllers.responses.UpdateInsuranceResponse;
import com.escuelita.demo.entities.Insurance;
import com.escuelita.demo.repositories.IInsuranceRepository;
import com.escuelita.demo.services.interfaces.IInsuranceService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements IInsuranceService {
    @Autowired
    private IInsuranceRepository repository;
    @Override
    public BaseResponse create(CreateInsuranceRequest request) {
        CreateInsuranceResponse response = from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("Insurance created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        Insurance response = findAndEnsureExist(id);
        return BaseResponse.builder()
                .data(response)
                .message("Get Insurance by Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetInsuranceResponse> response=repository.findAll()
                .stream()
                .map(this::toGetInsuranceResponse)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Insurances List by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateInsuranceRequest request) {
        Insurance insurance = findAndEnsureExist(id);
        insurance = update(insurance, request);
        UpdateInsuranceResponse  response = toUpdateInsuranceResponse(insurance);

        return BaseResponse.builder()
                .data(response)
                .message("Insurances Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public Insurance findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
    }
    private Insurance update(Insurance insurance, UpdateInsuranceRequest request){
        insurance.setName(request.getName());
        insurance.setHeadquarter(request.getHeadquarter());
        insurance.setWebsite(request.getWebsite());
        return repository.save(insurance);
    }
    private UpdateInsuranceResponse toUpdateInsuranceResponse(Insurance insurance){
        UpdateInsuranceResponse response = new UpdateInsuranceResponse();
        response.setId(insurance.getId());
        response.setName(insurance.getName());
        response.setHeadquarter(insurance.getHeadquarter());
        response.setWebsite(insurance.getWebsite());
        return response;
    }
    private GetInsuranceResponse toGetInsuranceResponse(Insurance insurance){
        GetInsuranceResponse response = new GetInsuranceResponse();
        response.setId(insurance.getId());
        response.setName(insurance.getName());
        response.setHeadquarter(insurance.getHeadquarter());
        response.setWebsite(insurance.getWebsite());
        return response;
    }
    private CreateInsuranceResponse from(Insurance insurance){
        CreateInsuranceResponse response= new CreateInsuranceResponse();
        response.setId(insurance.getId());
        response.setName(insurance.getName());
        response.setHeadquarter(insurance.getHeadquarter());
        response.setWebsite(insurance.getWebsite());
        return response;
    }
    private Insurance from(CreateInsuranceRequest request){
        Insurance insurance= new Insurance();
        insurance.setName(request.getName());
        insurance.setHeadquarter(request.getHeadquarter());
        insurance.setWebsite(request.getWebsite());
        return insurance;
    }
    private Insurance findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
    }

}

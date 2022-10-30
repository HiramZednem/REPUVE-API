package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateAgencyRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAgencyRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetAgencyResponse;
import com.escuelita.demo.entities.Agency;
import com.escuelita.demo.repositories.IAgencyRepository;
import com.escuelita.demo.services.interfaces.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgencyServiceImpl implements IAgencyService {

    @Autowired
    private IAgencyRepository repository;
    @Override
    public BaseResponse get(Long id) {
        GetAgencyResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Agency by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @Override
    public BaseResponse list() {
        List <GetAgencyResponse> response = repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Agency list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateAgencyRequest request) {
        Agency agency=from (request);
        GetAgencyResponse response =from(repository.save(agency));
        return BaseResponse.builder()
                .data(response)
                .message("create agency ")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateAgencyRequest request) {
        Agency agency = repository.findById(id).orElseThrow(()-> new RuntimeException("The agency does not exits"));
        agency=update(agency,request);

        GetAgencyResponse response=from(agency);
        return BaseResponse.builder()
                .data(response)
                .message("Agency update")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id){
    repository.deleteById(id);
    }



    private Agency update(Agency agency,UpdateAgencyRequest request){
        agency.setName(request.getName());
        agency.setAddress(request.getAddress());
        agency.setCellphone_number(request.getCellphone_number());
        return repository.save(agency);
    }
    private Agency from (CreateAgencyRequest request){
        Agency agency = new Agency();
        agency.setName(request.getName());
        agency.setAddress(request.getAddress());
        agency.setCellphone_number(request.getCellphone_number());
        return agency;
    }
    private GetAgencyResponse from (Agency agency){
        GetAgencyResponse response = new GetAgencyResponse();
        response.setId(agency.getId());
        response.setName(agency.getName());
        response.setAddress(agency.getAddress());
        response.setCellphone_number(agency.getCellphone_number());
        return response;
    }
    private GetAgencyResponse from (Long idAgency){
        return repository.findById(idAgency)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException ("The Agency does not exist"));
    }

}
package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateInsuranceRequest;
import com.escuelita.demo.controllers.responses.CreateInsuranceResponse;
import com.escuelita.demo.controllers.responses.GetInsuranceResponse;
import com.escuelita.demo.controllers.responses.UpdateInsuranceResponse;
import com.escuelita.demo.entities.Insurance;
import com.escuelita.demo.repositories.IInsuranceRepository;
import com.escuelita.demo.services.interfaces.IInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements IInsuranceService {
    @Autowired
    private IInsuranceRepository repository;
    @Override
    public CreateInsuranceResponse create(CreateInsuranceRequest request) {
        Insurance save = repository.save(from(request));
        return from(save);
    }

    @Override
    public GetInsuranceResponse get(Long id) {
        Insurance insurance = findAndEnsureExist(id);
        return toGetInsuranceResponse(insurance);
    }

    @Override
    public List<GetInsuranceResponse> list() {
        return repository.findAll().stream().map(this::toGetInsuranceResponse).collect(Collectors.toList());
    }

    @Override
    public UpdateInsuranceResponse update(Long id, UpdateInsuranceRequest request) {
        Insurance insurance = findAndEnsureExist(id);
        insurance.setName(request.getName());
        insurance.setHeadquarter(request.getHeadquarter());
        insurance.setWebsite(request.getWebsite());
        return toUpdateInsuranceResponse(repository.save(insurance));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
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

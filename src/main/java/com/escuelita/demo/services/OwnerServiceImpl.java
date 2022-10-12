package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateOwnerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOwnerRequest;
import com.escuelita.demo.controllers.dtos.responses.GetOwnerResponse;
import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.repositories.IOwnerRepository;
import com.escuelita.demo.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements IOwnerService {

    @Autowired
    private IOwnerRepository repository;


    @Override
    public List<GetOwnerResponse> list() {
        return repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetOwnerResponse get(Long id) {
       return from(id);
    }

    @Override
    public GetOwnerResponse create(CreateOwnerRequest request) {
        return from(repository.save(from(request)));
    }

    @Override
    public GetOwnerResponse update(Long id, UpdateOwnerRequest request) {
        Owner owner=repository.findById(id).orElseThrow(()->new RuntimeException("Owner do not exist"));
        owner= update(owner, request);
        return from(owner);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    private Owner update(Owner owner, UpdateOwnerRequest request){
        owner.setFirstName(request.getFirstName());
        owner.setLastName(request.getLastName());
        owner.setRfc(request.getRfc());
        owner.setCountry(request.getCountry());
        owner.setCity(request.getCity());
        owner.setAddress(request.getAddress());
        owner.setPhoneNumber(request.getPhoneNumber());
        return repository.save(owner);
    }

    private Owner from(CreateOwnerRequest request){
        Owner owner=new Owner();
        owner.setFirstName(request.getFirstName());
        owner.setLastName(request.getLastName());
        owner.setRfc(request.getRfc());
        owner.setCountry(request.getCountry());
        owner.setCity(request.getCity());
        owner.setAddress(request.getAddress());
        owner.setPhoneNumber(request.getPhoneNumber());
          return owner;
    }
    private GetOwnerResponse from(Owner owner){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setRfc(owner.getRfc());
        response.setFirstName(owner.getFirstName());
        response.setLastName(owner.getLastName());
        response.setCountry(owner.getCountry());
        response.setCity(owner.getCity());
           return response;
    }

    private GetOwnerResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()->new RuntimeException("Owner do not exist"));
    }

}

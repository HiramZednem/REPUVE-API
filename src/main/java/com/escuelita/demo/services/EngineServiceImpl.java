package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateEngineRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateEngineRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetEngineResponse;
import com.escuelita.demo.controllers.dtos.responses.GetOwnerResponse;
import com.escuelita.demo.entities.Engine;
import com.escuelita.demo.repositories.IEngineRepository;
import com.escuelita.demo.services.interfaces.IEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngineServiceImpl implements IEngineService{

    @Autowired
    private IEngineRepository repository;

    @Override
    public BaseResponse list() {
        List<GetEngineResponse>  response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("engines list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }


    @Override
    public BaseResponse get(Long id) {
        GetEngineResponse response =from(id);
        return BaseResponse.builder()
                .data(response)
                .message("engine by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateEngineRequest request) {
        GetEngineResponse response= from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("engine creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public GetEngineResponse update(Long id, UpdateEngineRequest request) {
        Engine engine=repository.findById(id).orElseThrow(()->new RuntimeException("Engine does not exist"));
        engine= update(engine, request);
        return from(engine);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Engine update(Engine engine, UpdateEngineRequest request){
        engine.setCylinder(request.getCylinder());
        engine.setEngineType(request.getEngineType());
        return repository.save(engine);
    }

    private Engine from(CreateEngineRequest request){
        Engine engine=new Engine();
        engine.setCylinder(request.getCylinder());
        engine.setEngineType(request.getEngineType());
        return engine;
    }

    @Override
    public Engine findById(Long engineId) {
        return repository.findById(engineId).orElseThrow(()-> new RuntimeException("engine do not exist"));
    }

    private GetEngineResponse from(Engine engine){
        GetEngineResponse response = new GetEngineResponse();
        response.setId(engine.getId());
        response.setCylinder(engine.getCylinder());
        response.setEngineType(engine.getEngineType());

        return response;
    }

    private GetEngineResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()->new RuntimeException("Engine do not exist"));
    }
}

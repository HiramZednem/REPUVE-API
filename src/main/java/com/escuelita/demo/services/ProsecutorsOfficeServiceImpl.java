package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetProsecutorsOfficeResponse;
import com.escuelita.demo.entities.ProsecutorsOffice;
import com.escuelita.demo.repositories.IProsecutorsOfficeRepository;
import com.escuelita.demo.services.interfaces.IProsecutorsOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProsecutorsOfficeServiceImpl implements IProsecutorsOfficeService {
    @Autowired
    private IProsecutorsOfficeRepository repository;

    @Override
    public BaseResponse get(Long id) {
        GetProsecutorsOfficeResponse response= from(id);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetProsecutorsOfficeResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateProsecutorsOfficeRequest request) {
        GetProsecutorsOfficeResponse response = from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateProsecutorsOfficeRequest request) {
        ProsecutorsOffice office=repository.findById(id).orElseThrow(()->new RuntimeException("Office do not exist"));
        office= update(office, request);
        GetProsecutorsOfficeResponse response = from(office);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by office id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public ProsecutorsOffice getById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("Office do not exist"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProsecutorsOffice update(ProsecutorsOffice office, UpdateProsecutorsOfficeRequest request){
        office.setName(request.getName());
        office.setCountry(request.getCountry());
        office.setState(request.getState());
        office.setCity(request.getCity());
        office.setAddress(request.getAddress());
        office.setPostalCode(request.getPostalCode());
        return repository.save(office);
    }



    private ProsecutorsOffice from(CreateProsecutorsOfficeRequest request){
        ProsecutorsOffice office=new ProsecutorsOffice();
        office.setName(request.getName());
        office.setCountry(request.getCountry());
        office.setState(request.getState());
        office.setCity(request.getCity());
        office.setAddress(request.getAddress());
        office.setPostalCode(request.getPostalCode());
        return office;
    }
    private GetProsecutorsOfficeResponse from(ProsecutorsOffice office){
        GetProsecutorsOfficeResponse response = new GetProsecutorsOfficeResponse();
        response.setId(office.getId());
        response.setName(office.getName());
        response.setCountry(office.getCountry());
        response.setState(office.getState());
        response.setCity(office.getCity());
        response.setAddress(office.getAddress());
        response.setPostalCode(office.getPostalCode());
        return response;
    }

    private GetProsecutorsOfficeResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()->new RuntimeException("office do not exist"));
    }
}

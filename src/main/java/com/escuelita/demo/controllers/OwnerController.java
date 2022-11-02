package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateOwnerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOwnerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetOwnerResponse;
import com.escuelita.demo.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private IOwnerService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/owners/insurance/{id}")
    public ResponseEntity<BaseResponse> listAllOwnersByInsuranceId(@PathVariable Long id){
        BaseResponse baseResponse = service.listAllOwnersByInsurance(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse>  get(@PathVariable Long id){
        BaseResponse baseResponse =  service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse>  create(@RequestBody CreateOwnerRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@PathVariable Long id,@RequestBody UpdateOwnerRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

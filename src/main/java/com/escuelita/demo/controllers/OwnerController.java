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
    public List<GetOwnerResponse> list(){
        return service.list();
    }

    @GetMapping("/owners/insurance/{id}")
    public ResponseEntity<BaseResponse> listAllOwnersByInsuranceId(@PathVariable Long id){
        BaseResponse baseResponse = service.listAllOwnersByInsurance(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public GetOwnerResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public GetOwnerResponse create(@RequestBody CreateOwnerRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetOwnerResponse update(@PathVariable Long id,@RequestBody UpdateOwnerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateEngineRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateEngineRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetEngineResponse;
import com.escuelita.demo.services.interfaces.IEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engine")
public class EngineController {
    @Autowired
    private IEngineService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new  ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse baseResponse= service.get(id);
        return new  ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateEngineRequest request){
        BaseResponse baseResponse= service.create(request);
        return new  ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public GetEngineResponse update(@PathVariable Long id,@RequestBody UpdateEngineRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

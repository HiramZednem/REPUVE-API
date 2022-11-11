package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateBrandRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBrandRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateBrandResponse;
import com.escuelita.demo.services.interfaces.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private IBrandService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create (@RequestBody CreateBrandRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get (@PathVariable Long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll () {
        BaseResponse baseResponse= service.getAll();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateBrandRequest request){
        BaseResponse baseResponse= service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id){
        service.delete(id);
    }


}

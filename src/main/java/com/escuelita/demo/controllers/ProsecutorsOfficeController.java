package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IProsecutorsOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prosecutorsOffice")
public class ProsecutorsOfficeController {

    @Autowired
    private IProsecutorsOfficeService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse>  get(@PathVariable Long id){
        BaseResponse baseResponse =  service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse>  create(@RequestBody CreateProsecutorsOfficeRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@PathVariable Long id,@RequestBody UpdateProsecutorsOfficeRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

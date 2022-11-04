package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

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
    public ResponseEntity<BaseResponse>  create(@RequestBody CreateUserRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@PathVariable Long id,@RequestBody UpdateUserRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

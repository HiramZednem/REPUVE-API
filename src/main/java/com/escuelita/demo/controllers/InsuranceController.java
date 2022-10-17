package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.requests.CreateInsuranceRequest;
import com.escuelita.demo.controllers.requests.UpdateInsuranceRequest;
import com.escuelita.demo.controllers.responses.CreateInsuranceResponse;
import com.escuelita.demo.controllers.responses.GetInsuranceResponse;
import com.escuelita.demo.controllers.responses.UpdateInsuranceResponse;
import com.escuelita.demo.services.interfaces.IInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("insurance")
public class InsuranceController {
    @Autowired
    private IInsuranceService service;

    @PostMapping
    public CreateInsuranceResponse create(@RequestBody CreateInsuranceRequest request){
        return service.create(request);
    }

    @GetMapping("{id}")
    public GetInsuranceResponse get (@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping
    public List<GetInsuranceResponse> list(){
        return service.list();
    }

    @PutMapping("{id}")
    public UpdateInsuranceResponse update(@RequestBody UpdateInsuranceRequest request, @PathVariable Long id){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

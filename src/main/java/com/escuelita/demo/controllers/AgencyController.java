package com.escuelita.demo.controllers;
import com.escuelita.demo.controllers.dtos.requests.CreateAgencyRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAgencyRequest;
import com.escuelita.demo.controllers.dtos.responses.GetAgencyResponse;
import com.escuelita.demo.services.interfaces.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("agency")
public class AgencyController {
@Autowired
private IAgencyService service;

    @GetMapping
    public List<GetAgencyResponse> list(){
        return service.list();
    }
    @GetMapping("{id}")
    public GetAgencyResponse get(@PathVariable Long id){
        return service.get(id);
    }
    @PostMapping
    public GetAgencyResponse create(@RequestBody CreateAgencyRequest request){
        return service.create(request);
    }
   @PutMapping("{id}")
    public GetAgencyResponse update(@PathVariable Long id, @RequestBody UpdateAgencyRequest request){
        return service.update(id,request);
    }
    @DeleteMapping ("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
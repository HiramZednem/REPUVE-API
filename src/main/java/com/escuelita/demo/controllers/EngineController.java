package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateEngineRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateEngineRequest;
import com.escuelita.demo.controllers.dtos.responses.GetEngineResponse;
import com.escuelita.demo.services.interfaces.IEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engine")
public class EngineController {
    @Autowired
    private IEngineService service;

    @GetMapping
    public List<GetEngineResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetEngineResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public GetEngineResponse create(@RequestBody CreateEngineRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetEngineResponse update(@PathVariable Long id,@RequestBody UpdateEngineRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(Long id){
        service.delete(id);
    }
}

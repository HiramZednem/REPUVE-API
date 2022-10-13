package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateBrandRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBrandRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateBrandResponse;
import com.escuelita.demo.services.interfaces.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private IBrandService service;

    @PostMapping
    CreateBrandResponse create (@RequestBody CreateBrandRequest request){
        return service.create(request);
    }

    @GetMapping("{id}")
    CreateBrandResponse get (@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping
    List<CreateBrandResponse> getAll () {
        return service.getAll();
    }

    @PutMapping("{id}")
    CreateBrandResponse update(@PathVariable Long id, @RequestBody UpdateBrandRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id){
        service.delete(id);
    }


}

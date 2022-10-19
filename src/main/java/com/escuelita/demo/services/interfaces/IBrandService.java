package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateBrandRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBrandRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateBrandResponse;

import java.util.List;

public interface IBrandService {
    //Create
    CreateBrandResponse create (CreateBrandRequest request);

    //Read
    CreateBrandResponse get (Long id);
    List<CreateBrandResponse> getAll ();

    //Update
    CreateBrandResponse update (Long id, UpdateBrandRequest request);

    //Delete
    void delete (Long id);

}

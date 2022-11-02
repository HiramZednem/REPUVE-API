package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateOwnerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOwnerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetOwnerResponse;
import com.escuelita.demo.entities.Owner;

import java.util.List;

public interface IOwnerService {


    BaseResponse list();


    BaseResponse get(Long id);


    BaseResponse create(CreateOwnerRequest request);


     BaseResponse update(Long id, UpdateOwnerRequest request);

    void delete(Long id);

    Owner findById(Long ownerId);

    BaseResponse listAllOwnersByInsurance(long id);


}

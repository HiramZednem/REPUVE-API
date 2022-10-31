package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateOwnerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateOwnerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetOwnerResponse;

import java.util.List;

public interface IOwnerService {

    List<GetOwnerResponse> list();

    GetOwnerResponse get(Long id);

    GetOwnerResponse create(CreateOwnerRequest request);

    GetOwnerResponse update(Long id, UpdateOwnerRequest request);

    void delete(Long id);

    BaseResponse listAllOwnersByInsurance(long id);

}

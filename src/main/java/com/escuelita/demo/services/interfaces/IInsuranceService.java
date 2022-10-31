package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.Insurance;


public interface IInsuranceService {
    BaseResponse create(CreateInsuranceRequest request);

    BaseResponse get(Long id);

    BaseResponse list();

    BaseResponse update(Long id, UpdateInsuranceRequest request);

    void delete(Long id);

    Insurance findById(Long id);
}

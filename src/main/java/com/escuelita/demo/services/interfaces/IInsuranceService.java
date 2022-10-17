package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateInsuranceRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateInsuranceRequest;
import com.escuelita.demo.controllers.responses.CreateInsuranceResponse;
import com.escuelita.demo.controllers.responses.GetInsuranceResponse;
import com.escuelita.demo.controllers.responses.UpdateInsuranceResponse;

import java.util.List;

public interface IInsuranceService {
    CreateInsuranceResponse create(CreateInsuranceRequest request);

    GetInsuranceResponse get(Long id);

    List<GetInsuranceResponse> list();

    UpdateInsuranceResponse update(Long id, UpdateInsuranceRequest request);

    void delete(Long id);
}

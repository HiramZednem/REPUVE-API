package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateAgencyRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAgencyRequest;
import com.escuelita.demo.controllers.dtos.responses.GetAgencyResponse;

import java.util.List;

public interface IAgencyService {
    GetAgencyResponse get (Long id);
    List<GetAgencyResponse> list ();
    GetAgencyResponse create(CreateAgencyRequest request);
    GetAgencyResponse update(Long id, UpdateAgencyRequest request);
    void delete (Long id);
}

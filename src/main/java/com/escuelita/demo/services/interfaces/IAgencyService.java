package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateAgencyRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateAgencyRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetAgencyResponse;
import com.escuelita.demo.entities.Agency;

import java.util.List;

public interface IAgencyService {
    BaseResponse get (Long id);
    BaseResponse list ();
    BaseResponse create(CreateAgencyRequest request);
    BaseResponse update(Long id, UpdateAgencyRequest request);
    void delete (Long id);


}

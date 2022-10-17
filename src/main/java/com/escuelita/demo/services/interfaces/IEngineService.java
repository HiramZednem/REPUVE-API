package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateEngineRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateEngineRequest;
import com.escuelita.demo.controllers.dtos.responses.GetEngineResponse;

import java.util.List;

public interface IEngineService {

    List<GetEngineResponse> list();

    GetEngineResponse get(Long id);

    GetEngineResponse create(CreateEngineRequest request);

    GetEngineResponse update(Long id, UpdateEngineRequest request);

    void delete(Long id);

}
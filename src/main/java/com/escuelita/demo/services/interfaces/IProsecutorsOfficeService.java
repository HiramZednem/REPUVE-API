package com.escuelita.demo.services.interfaces;


import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.ProsecutorsOffice;

public interface IProsecutorsOfficeService {
    BaseResponse get(Long id);
    BaseResponse list();
    BaseResponse create(CreateProsecutorsOfficeRequest request);
    BaseResponse update(Long id, UpdateProsecutorsOfficeRequest request);
    void delete (Long id);

    ProsecutorsOffice getById(Long  id);
}

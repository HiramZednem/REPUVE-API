package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserService{
    BaseResponse get(Long id);
    BaseResponse list();
    BaseResponse create(CreateUserRequest request);
    BaseResponse update(Long id, UpdateUserRequest request);
    void delete (Long id);
}

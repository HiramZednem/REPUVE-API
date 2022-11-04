package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateProsecutorsOfficeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.GetUserResponse;
import com.escuelita.demo.entities.User;
import com.escuelita.demo.repositories.IUserRepository;
import com.escuelita.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;


    @Override
    public BaseResponse get(Long id) {
        GetUserResponse response= from(id);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetUserResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {

        GetUserResponse response = from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id).orElseThrow(()->new RuntimeException("User do not exist"));
        user = update(user, request);
        GetUserResponse response = from(user);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by User id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    private User update(User user, UpdateUserRequest request){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setWorkerCode(request.getWorkerCode());
        return repository.save(user);
    }

    private User from(CreateUserRequest request){
        User user=new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setWorkerCode(request.getWorkerCode());
        return user;
    }
    private GetUserResponse from(User user){
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
       response.setFirstName(user.getFirstName());
       response.setLastName(user.getLastName());
       response.setEmail(user.getEmail());
       response.setPassword(user.getPassword());
       response.setWorkerCode(user.getWorkerCode());
        return response;
    }

    private GetUserResponse from(Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()->new RuntimeException("Owner do not exist"));
    }

}

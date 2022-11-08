package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateBrandRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBrandRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateBrandResponse;
import com.escuelita.demo.entities.Brand;
import com.escuelita.demo.repositories.IBrandRepository;
import com.escuelita.demo.services.interfaces.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandRepository repository;

    @Override
    public BaseResponse create(CreateBrandRequest request) {
        Brand brand = requestToBrand(request);
        CreateBrandResponse response= BrandToResponse(repository.save(brand));
        return BaseResponse.builder()
                .data(response)
                .message("Brand creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        Optional<Brand> repositoryBrand = repository.findById(id);

        if (repositoryBrand.isPresent()){
            CreateBrandResponse response= BrandToResponse(repositoryBrand.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("brand by owner id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        throw new RuntimeException("The Brand with the id: " + id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateBrandResponse> response = new ArrayList<>();
        List<Brand> all = repository.findAll();

        for (Brand brand : all) {
            response.add(BrandToResponse(brand));
        }
        return BaseResponse.builder()
                .data(response)
                .message("Brand list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    public Brand findBrandById ( Long id ) {
        Optional<Brand> brandFromDB = repository.findById(id);
        if (brandFromDB.isPresent()) {
            return brandFromDB.get();
        }
        throw new RuntimeException("The Brand with the id: " + id + " doesn't exist");

    }

    @Override
    public BaseResponse update(Long id, UpdateBrandRequest request) {
        Optional<Brand> brandOptional = repository.findById(id);

        if (brandOptional.isPresent()){
            Brand brand = brandOptional.get();
            brand.setName(request.getName());
            brand.setWebsite(request.getWebsite());
            brand.setHeadquarter(request.getHeadquarter());

            CreateBrandResponse response= BrandToResponse(repository.save(brand));
            return BaseResponse.builder()
                    .data(response)
                    .message("Brand ]update")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        throw new RuntimeException("The Brand with the id: " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }



    private Brand requestToBrand(CreateBrandRequest request) {
        Brand response = new Brand();
        response.setName(request.getName());
        response.setHeadquarter(request.getHeadquarter());
        response.setWebsite(request.getWebsite());
        return response;
        
    }
    
    private CreateBrandResponse BrandToResponse (Brand request) {
        CreateBrandResponse response = new CreateBrandResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setHeadquarter(request.getHeadquarter());
        response.setWebsite(request.getWebsite());
        return response;
    }
}

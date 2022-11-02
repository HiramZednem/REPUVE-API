package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateBrandRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateBrandRequest;
import com.escuelita.demo.controllers.dtos.responses.CreateBrandResponse;
import com.escuelita.demo.entities.Brand;
import com.escuelita.demo.repositories.IBrandRepository;
import com.escuelita.demo.services.interfaces.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandRepository repository;

    @Override
    public CreateBrandResponse create(CreateBrandRequest request) {
        Brand brand = requestToBrand(request);
        return BrandToResponse(repository.save(brand));
    }

    @Override
    public CreateBrandResponse get(Long id) {
        Optional<Brand> repositoryBrand = repository.findById(id);

        if (repositoryBrand.isPresent()){
            return BrandToResponse(repositoryBrand.get());
        }
        throw new RuntimeException("The Brand with the id: " + id + " doesn't exist");
    }

    @Override
    public List<CreateBrandResponse> getAll() {
        List<CreateBrandResponse> responses = new ArrayList<>();
        List<Brand> all = repository.findAll();

        for (Brand brand : all) {
            responses.add(BrandToResponse(brand));
        }
        return responses;
    }

    @Override
    public CreateBrandResponse update(Long id, UpdateBrandRequest request) {
        Optional<Brand> brandOptional = repository.findById(id);

        if (brandOptional.isPresent()){
            Brand brand = brandOptional.get();
            brand.setName(request.getName());
            brand.setWebsite(request.getWebsite());
            brand.setHeadquarter(request.getHeadquarter());

            return BrandToResponse(repository.save(brand));
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

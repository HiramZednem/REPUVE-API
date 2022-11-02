package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;

import com.escuelita.demo.controllers.dtos.responses.*;
import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.entities.Vehicle;
import com.escuelita.demo.entities.projections.VehicleEngineProjection;
import com.escuelita.demo.entities.projections.VehicleOwnerProjection;
import com.escuelita.demo.entities.Brand;
import com.escuelita.demo.entities.projections.VehicleProjection;
import com.escuelita.demo.repositories.IVehicleRepository;
import com.escuelita.demo.services.interfaces.IEngineService;
import com.escuelita.demo.services.interfaces.IOwnerService;
import com.escuelita.demo.services.interfaces.IBrandService;
import com.escuelita.demo.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleRepository repository;

    @Autowired
    IOwnerService ownerService;
    
    @Autowired
    IBrandService brandService;
    
    @Autowired
    IEngineService engineService;


    //CREATE
    @Override
    public CreateVehicleResponse createCar(CreateVehicleRequest carRequest){
        Vehicle vehicleToBD = repository.save(createNewCar(carRequest));
        return carToCarReponse(vehicleToBD);
    }

    //READ
    //ONE
    @Override
    public CreateVehicleResponse seeCar(Long id){
        Optional<Vehicle> carToBDOptional = repository.findById(id);
        if(carToBDOptional.isPresent()){
            Vehicle vehicleToBD = carToBDOptional.get();
            return carToCarReponse(vehicleToBD);
        }
        throw  new RuntimeException("NAH NAH Bye bye");
    }
    //ALL COMENTS
    @Override
    public List<CreateVehicleResponse> seeCars(){
        List<CreateVehicleResponse> listCarResponse = new ArrayList<>();
        List<Vehicle> listAllVehicle = repository.findAll();

        for (int i = 0; i< listAllVehicle.size(); i++){
            listCarResponse.add(carToCarReponse(listAllVehicle.get(i)));
        }
        return listCarResponse;

    }

    //UPDATE
    @Override
    public CreateUpdateVehicleResponse updateCar(Long id, CreateVehicleRequest carRequest){
        Optional<Vehicle> carOptional = repository.findById(id);

        if(carOptional.isPresent()){
            Vehicle newVehicle = createNewCar(carRequest);
            newVehicle.setId(carOptional.get().getId());
            Owner owner = ownerService.findById(carRequest.getOwnerId());
            newVehicle.setOwner(owner);
            repository.save(newVehicle);
            return carToUpdateCar(newVehicle);
        }
        throw new RuntimeException("Vehicle do not exist");

    }

    //DELETE
    @Override
    public void deleteCar(Long id){repository.deleteById(id);
    }

    @Override
    public BaseResponse listAllVehiclesByOwnerId(Long ownerId) {
        List<VehicleOwnerProjection> vehicles = repository.listAllVehiclesByOwnerId(ownerId);
       List<VehicleOwnerResponse> response = vehicles.stream()
               .map(this::from)
               .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
       }


    @Override
    public List<CreateVehicleResponse> listAllVehiclesByBrandId(Long brandId) {
        List<VehicleProjection> vehiclesRepository = repository.listAllVehiclesByBrandId(brandId);
        return vehiclesRepository.stream().map(this::from).collect(Collectors.toList());

    

    @Override
    public BaseResponse listAllVehiclesByEngineId(Long engineId) {
        List<VehicleEngineProjection> vehicles = repository.ListAllVehiclesByEngineId(engineId);
        List<VehicleEngineResponse> response = vehicles.stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by engine id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //FUNTIONS
    //CREATEEEE
    private Vehicle createNewCar (CreateVehicleRequest carRequest){
        Vehicle newVehicle = new Vehicle();
        newVehicle.setYear(carRequest.getYear());
        newVehicle.setPrice(carRequest.getPrice());
        newVehicle.setMileage(carRequest.getMileage());
        newVehicle.setColor(carRequest.getColor());
        newVehicle.setModel(carRequest.getModel());

        Brand brand = brandService.findBrandById(carRequest.getBrandId());
        newVehicle.setBrand(brand);
        return newVehicle;
    }
    private CreateVehicleResponse carToCarReponse (Vehicle vehicleToBS){
        CreateVehicleResponse carResponse = new CreateVehicleResponse();
        carResponse.setId(vehicleToBS.getId());
        carResponse.setYear(vehicleToBS.getYear());
        carResponse.setPrice(vehicleToBS.getPrice());
        carResponse.setMileage(vehicleToBS.getMileage());
        carResponse.setColor(vehicleToBS.getColor());
        carResponse.setModel(vehicleToBS.getModel());
        carResponse.setBrand(from(vehicleToBS.getBrand()));
        //        carResponse.setOwnerName(from(vehicleToBS.getOwner()));
        return carResponse;
    }

    private OwnerResponse from(Owner owner){
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(owner.getFirstName()+" "+ owner.getLastName());
        return ownerResponse;
    }

    private BrandResponse from ( Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setName(brand.getName());
        return response;
    }


    private CreateUpdateVehicleResponse carToUpdateCar (Vehicle newVehicle){
        CreateUpdateVehicleResponse updateCar = new CreateUpdateVehicleResponse();
        updateCar.setYear(newVehicle.getYear());
        updateCar.setPrice(newVehicle.getPrice());
        updateCar.setMileage(newVehicle.getMileage());
        updateCar.setColor(newVehicle.getColor());
        updateCar.setModel(newVehicle.getModel());
        updateCar.setOwner(from(newVehicle.getOwner()));
        return updateCar;
    }
    private VehicleOwnerResponse from(VehicleOwnerProjection projection){
        VehicleOwnerResponse response = new VehicleOwnerResponse();
        response.setId(projection.getId());
        response.setColor(projection.getColor());
        response.setMileage(projection.getMileage());
        response.setYear(projection.getYear());
        response.setModel(projection.getModel());
        response.setOwnerName(projection.getFirstName() +" "+ projection.getLastName());

        return response;
    }


    private VehicleEngineResponse from(VehicleEngineProjection projection){
        VehicleEngineResponse response = new VehicleEngineResponse();
        response.setId(projection.getId());
        response.setColor(projection.getColor());
        response.setMileage(projection.getMileage());
        response.setYear(projection.getYear());
        response.setModel(projection.getModel());
        response.setEngineId(projection.getEngineId());
        response.setEngineType(projection.getEngineType());
         return response;
    }

    private CreateVehicleResponse from (VehicleProjection request) {
        CreateVehicleResponse response = new CreateVehicleResponse();
        response.setId(request.getId());
        response.setYear(request.getYear());
        response.setPrice(request.getPrice());
        response.setMileage(request.getMileage());
        response.setColor(request.getColor());
        response.setModel(request.getModel());

        Brand brand = brandService.findBrandById(request.getBrand_Id());

        response.setBrand(from(brand));
         return response;
    }     
}

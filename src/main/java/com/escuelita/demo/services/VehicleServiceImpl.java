package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;

import com.escuelita.demo.controllers.dtos.responses.*;
import com.escuelita.demo.entities.Engine;
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


    @Override
    public BaseResponse createCar(CreateVehicleRequest carRequest){
        Vehicle vehicleToBD = repository.save(createNewCar(carRequest));
        CreateVehicleResponse response= carToCarReponse(vehicleToBD);
        return BaseResponse.builder()
                .data(response)
                .message("vehicle creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //READ
    //ONE
    @Override
    public BaseResponse seeCar(Long id){
        Optional<Vehicle> carToBDOptional = repository.findById(id);
        if(carToBDOptional.isPresent()){
            Vehicle vehicleToBD = carToBDOptional.get();
            CreateVehicleResponse response= carToCarReponse(vehicleToBD);
            return BaseResponse.builder()
                    .data(response)
                    .message("vehicle by Id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        throw  new RuntimeException("NAH NAH Bye bye");


    }
    //ALL COMENTS
    @Override
    public BaseResponse seeCars(){
        List<CreateVehicleResponse>  response= repository.findAll()
                .stream()
                .map(this::carToCarReponse)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("vehicle list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();

    }

    @Override
    public BaseResponse updateCar(Long id, CreateVehicleRequest carRequest){
        Optional<Vehicle> carOptional = repository.findById(id);

        if(carOptional.isPresent()){
            Vehicle newVehicle = createNewCar(carRequest);
            newVehicle.setId(carOptional.get().getId());
            repository.save(newVehicle);
            CreateUpdateVehicleResponse response= carToUpdateCar(newVehicle);
            return BaseResponse.builder()
                    .data(response)
                    .message("vehicle updated data")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
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
    }

    

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
        Engine engine = engineService.findById(carRequest.getEngineId());
        newVehicle.setEngine(engine);
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
        carResponse.setOwnerName(from(vehicleToBS.getOwner()));
        carResponse.setBrand(from(vehicleToBS.getBrand()));
        carResponse.setEngine(from(vehicleToBS.getEngine()));
        //        carResponse.setOwnerName(from(vehicleToBS.getOwner()));
        return carResponse;
    }

    private OwnerResponse from(Owner owner){
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(owner.getFirstName()+" "+ owner.getLastName());
        ownerResponse.setFirstName(owner.getFirstName());
        ownerResponse.setLastName(owner.getLastName());
        ownerResponse.setCountry(owner.getCountry());
        ownerResponse.setCity(owner.getCity());
        ownerResponse.setAddress(owner.getAddress());
        ownerResponse.setRfc(owner.getRfc());
        ownerResponse.setId(owner.getId());
        return ownerResponse;
    }

    private BrandResponse from ( Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setName(brand.getName());
        return response;
    }

    private EngineResponse from(Engine engine){
    EngineResponse response=new EngineResponse();
    response.setCylinder(engine.getCylinder());
    response.setEngineType(engine.getEngineType());
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

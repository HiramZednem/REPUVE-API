package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.*;
import com.escuelita.demo.entities.Owner;
import com.escuelita.demo.entities.Vehicle;
import com.escuelita.demo.entities.projections.VehicleProjection;
import com.escuelita.demo.repositories.IVehicleRepository;
import com.escuelita.demo.services.interfaces.IOwnerService;
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
        List<VehicleProjection> vehicles = repository.listAllVehiclesByOwnerId(ownerId);
       List<VehicleResponse> response = vehicles.stream()
               .map(this::from)
               .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("vehicle list by owner id")
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
//        carResponse.setOwnerName(from(vehicleToBS.getOwner()));
        return carResponse;
    }

    private OwnerResponse from(Owner owner){
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(owner.getFirstName()+" "+ owner.getLastName());
        return ownerResponse;
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
    private VehicleResponse from(VehicleProjection projection){
        VehicleResponse response = new VehicleResponse();
        response.setId(projection.getId());
        response.setColor(projection.getColor());
        response.setMileage(projection.getMileage());
        response.setYear(projection.getYear());
        response.setModel(projection.getModel());
        response.setOwnerName(projection.getFirstName() +" "+ projection.getLastName());

        return response;
    }

}

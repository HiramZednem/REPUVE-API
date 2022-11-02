package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateVehicleRequest;
import com.escuelita.demo.controllers.dtos.responses.BrandResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateVehicleResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateVehicleResponse;
import com.escuelita.demo.entities.Brand;
import com.escuelita.demo.entities.Vehicle;
import com.escuelita.demo.repositories.IVehicleRepository;
import com.escuelita.demo.services.interfaces.IBrandService;
import com.escuelita.demo.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleRepository repository;

    @Autowired
    IBrandService brandService;

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
            repository.save(newVehicle);

            return carToUpdateCar(newVehicle);
        }
        throw new RuntimeException("ERROR XXXXXXX");

    }

    //DELETE
    @Override
    public void deleteCar(Long id){repository.deleteById(id);}




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
        return carResponse;
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
        return updateCar;
    }

}

package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateCarRequest;
import com.escuelita.demo.controller.dtos.response.CreateCarResponse;
import com.escuelita.demo.controller.dtos.response.CreateUpdateCarResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateCarResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateUpdateCarResponse;
import com.escuelita.demo.entities.Car;
import com.escuelita.demo.repositories.ICarRepository;
import com.escuelita.demo.services.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    ICarRepository repository;

    //CREATE
    @Override
    public CreateCarResponse createCar(CreateCarRequest carRequest){
        Car carToBD = repository.save(createNewCar(carRequest));
        return carToCarReponse(carToBD);
    }

    //READ
    //ONE
    @Override
    public CreateCarResponse seeCar(Long id){
        Optional<Car> carToBDOptional = repository.findById(id);
        if(carToBDOptional.isPresent()){
            Car carToBD = carToBDOptional.get();
            return carToCarReponse(carToBD);
        }
        throw  new RuntimeException("NAH NAH Bye bye");
    }
    //ALL COMENTS
    @Override
    public List<CreateCarResponse> seeCars(){
        List<CreateCarResponse> listCarResponse = new ArrayList<>();
        List<Car> listAllCar = repository.findAll();

        for ( int i=0; i<listAllCar.size();i++){
            listCarResponse.add(carToCarReponse(listAllCar.get(i)));
        }
        return listCarResponse;

    }

    //UPDATE
    @Override
    public CreateUpdateCarResponse updateCar(Long id, CreateCarRequest carRequest){
        Optional<Car> carOptional = repository.findById(id);

        if(carOptional.isPresent()){
            Car newCar = createNewCar(carRequest);

            newCar.setId(carOptional.get().getId());
            repository.save(newCar);

            return carToUpdateCar(newCar);
        }
        throw new RuntimeException("ERROR XXXXXXX");

    }

    //DELETE
    @Override
    public void deleteCar(Long id){repository.deleteById(id);}




    //FUNTIONS
    //CREATEEEE
    private Car createNewCar (CreateCarRequest carRequest){
        Car newCar= new Car();
        newCar.setYear(carRequest.getYear());
        newCar.setPrice(carRequest.getPrice());
        newCar.setMileage(carRequest.getMileage());
        newCar.setColor(carRequest.getColor());
        newCar.setModel(carRequest.getModel());
        return newCar;
    }
    private CreateCarResponse carToCarReponse (Car carToBS){
        CreateCarResponse carResponse = new CreateCarResponse();
        carResponse.setId(carToBS.getId());
        carResponse.setYear(carToBS.getYear());
        carResponse.setPrice(carToBS.getPrice());
        carResponse.setMileage(carToBS.getMileage());
        carResponse.setColor(carToBS.getColor());
        carResponse.setModel(carToBS.getModel());
        return carResponse;
    }
    private CreateUpdateCarResponse carToUpdateCar (Car newCar){
        CreateUpdateCarResponse updateCar = new CreateUpdateCarResponse();
        updateCar.setYear(newCar.getYear());
        updateCar.setPrice(newCar.getPrice());
        updateCar.setMileage(newCar.getMileage());
        updateCar.setColor(newCar.getColor());
        updateCar.setModel(newCar.getModel());
        return updateCar;
    }

}
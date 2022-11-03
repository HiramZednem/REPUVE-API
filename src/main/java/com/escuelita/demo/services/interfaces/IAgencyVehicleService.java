package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.responses.AgencyResponse;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

import java.util.List;

public interface IAgencyVehicleService {
    BaseResponse listAllAgenciesByVehicleId(Long vehicleId);
    BaseResponse listAllVehicleByAgenciesId(Long agencyId);
}

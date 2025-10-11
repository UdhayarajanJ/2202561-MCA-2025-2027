package com.fptp.api.services;

import com.fptp.api.models.CommonResult;
import com.fptp.api.models.VehicleTypes;
import com.fptp.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleServices{
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleTypes> GetVehcileTypes(String ownerId) {
        return vehicleRepository.GetVehcileTypes(ownerId);
    }

    public CommonResult SaveUpdateVehicleTypes(VehicleTypes vehicleTypes) {
        return vehicleRepository.SaveUpdateVehicleTypes(vehicleTypes);
    }

    public CommonResult DeleteVehicleTypes(VehicleTypes vehicleTypes) {
        return vehicleRepository.DeleteVehicleTypes(vehicleTypes);
    }
}

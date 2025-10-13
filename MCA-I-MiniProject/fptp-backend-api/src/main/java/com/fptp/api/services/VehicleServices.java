package com.fptp.api.services;

import com.fptp.api.models.CommonResult;
import com.fptp.api.models.PaginationResult;
import com.fptp.api.models.VehicleTypes;
import com.fptp.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

    public PaginationResult<VehicleTypes> GetVehicleTableRecords (int pageNo, int pageSize, LocalDateTime filterDate, String filterName, String ownerId){
        return vehicleRepository.GetVehicleTableRecords(pageNo,pageSize,filterDate,filterName,ownerId);
    }

}

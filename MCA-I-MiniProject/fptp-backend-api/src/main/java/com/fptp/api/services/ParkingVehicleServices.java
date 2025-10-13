package com.fptp.api.services;

import com.fptp.api.models.*;
import com.fptp.api.repository.ParkingVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingVehicleServices {

    @Autowired
    private ParkingVehicleRepository parkingVehicleRepository;

    public CommonResult RegisterVehicle(ParkingVehicle parkingVehicle) {
        return parkingVehicleRepository.RegisterVehicle(parkingVehicle);
    }

    public AvailableSlots GetAvailableSlots(int vehicleId, String ownerId) {
        return parkingVehicleRepository.GetAvailableSlots(vehicleId, ownerId);
    }

    public VehicleStatus GetVehicleStatus(String vehicleNo, String ownerId) {
        return parkingVehicleRepository.GetVehicleStatus(vehicleNo, ownerId);
    }

    public PaginationResult<ParkingVehicle> GetParkingVehicleTableRecords(
            int pageNo,
            int pageSize,
            String ownerId,
            String filterVehicleNo,
            Boolean filterIsPaid,
            LocalDateTime filterDate,
            String filterMobileNo,
            String filterName,
            Integer filterVehicleId,
            Boolean filterIsSubmittedKey,
            String filterPaidThrow) {
        return parkingVehicleRepository.GetParkingVehicleTableRecords(
                pageNo,
                pageSize,
                ownerId,
                filterVehicleNo,
                filterIsPaid,
                filterDate,
                filterMobileNo,
                filterName,
                filterVehicleId,
                filterIsSubmittedKey,
                filterPaidThrow);
    }
}

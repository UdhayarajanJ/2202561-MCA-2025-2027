package com.fptp.api.contracts;

import com.fptp.api.models.*;

import java.time.LocalDateTime;

public interface IParkingVehicleContracts {
    CommonResult RegisterVehicle(ParkingVehicle parkingVehicle);

    AvailableSlots GetAvailableSlots(int vehicleId, String ownerId);

    VehicleStatus GetVehicleStatus(String vehicleNo, String ownerId);

    PaginationResult<ParkingVehicle> GetParkingVehicleTableRecords(
            int pageNo,
            int pageSize,
            String ownerId,
            String filterVehicleNo,
            Boolean filterIsPaid,
            LocalDateTime filterCheckInDate,
            LocalDateTime filterCheckOutDate,
            String filterMobileNo,
            String filterName,
            Integer filterVehicleId,
            Boolean filterIsSubmittedKey,
            String filterPaidThrow);

    CommonResult DeleteRegisterationVehicle(int parkingId, String ownerId);

    ParkingVehicle GetTotalChargeOfVehicle(int parkingId, String ownerId);

    CommonResult PaidVehicleCharge(ParkingVehicle parkingVehicle);

}

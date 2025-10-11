package com.fptp.api.contracts;

import com.fptp.api.models.CommonResult;
import com.fptp.api.models.VehicleTypes;

import java.util.List;

public interface IVehicleContracts {
    List<VehicleTypes> GetVehcileTypes();
    CommonResult SaveUpdateVehicleTypes(VehicleTypes vehicleTypes);
    CommonResult DeleteVehicleTypes(VehicleTypes vehicleTypes);
}

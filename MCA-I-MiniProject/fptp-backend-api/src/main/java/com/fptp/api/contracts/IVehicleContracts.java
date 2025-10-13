package com.fptp.api.contracts;

import com.fptp.api.models.CommonResult;
import com.fptp.api.models.PaginationResult;
import com.fptp.api.models.VehicleTypes;

import java.time.LocalDateTime;
import java.util.List;

public interface IVehicleContracts {
    List<VehicleTypes> GetVehcileTypes(String ownerId);

    CommonResult SaveUpdateVehicleTypes(VehicleTypes vehicleTypes);

    PaginationResult<VehicleTypes> GetVehicleTableRecords(int pageNo, int pageSize, LocalDateTime filterDate, String filterName, String ownerId);
}

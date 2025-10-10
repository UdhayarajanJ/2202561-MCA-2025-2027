package com.fptp.api.controller;

import com.fptp.api.models.ApiResponse;
import com.fptp.api.models.CommonResult;
import com.fptp.api.models.VehicleTypes;
import com.fptp.api.services.VehicleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Vehicle/")
@Tag(name = "Vehicle", description = "APIs for vehicle management")
public class VehicleController {
    @Autowired
    private VehicleServices vehicleServices;

    @GetMapping("/GetVehicleTypes")
    @Operation(summary = "Get all vehicle types")
    public ResponseEntity<ApiResponse<List<VehicleTypes>>>  GetVehicleTypes() {
        List<VehicleTypes> vehicleTypes = vehicleServices.GetVehcileTypes();

        if (vehicleTypes == null || vehicleTypes.isEmpty()) {
            // Return 404 Not Found
            ApiResponse<List<VehicleTypes>> response = new ApiResponse<List<VehicleTypes>>(false, "No vehicle types found", null);
            return ResponseEntity.status(404).body(response);
        }

        // Return 200 OK with data
        ApiResponse<List<VehicleTypes>> response = new ApiResponse<>(true, "Vehicle types fetched successfully", vehicleTypes);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/SaveUpdateVehicleTypes")
    @Operation(summary = "Save or update a vehicle type")
    public ResponseEntity<ApiResponse<CommonResult>> SaveUpdateVehicleTypes(
            @Valid @RequestBody VehicleTypes vehicleType) {
        CommonResult result = vehicleServices.SaveUpdateVehicleTypes(vehicleType);
        return ResponseEntity.ok(new ApiResponse<>(true, result.getMessage(), result));
    }


    @DeleteMapping("/DeleteVehicleTypes")
    @Operation(summary = "Delete a vehicle type")
    public ResponseEntity<ApiResponse<CommonResult>> DeleteVehicleTypes(
            @Valid @RequestBody VehicleTypes vehicleType) {
        CommonResult result = vehicleServices.DeleteVehicleTypes(vehicleType);
        return ResponseEntity.ok(new ApiResponse<>(true, result.getMessage(), result));
    }

}

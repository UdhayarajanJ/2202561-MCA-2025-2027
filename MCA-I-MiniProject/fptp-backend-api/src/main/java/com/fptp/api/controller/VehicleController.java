package com.fptp.api.controller;

import com.fptp.api.models.ApiResponse;
import com.fptp.api.models.CommonResult;
import com.fptp.api.models.PaginationResult;
import com.fptp.api.models.VehicleTypes;
import com.fptp.api.services.VehicleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/Vehicle/")
@Tag(name = "Vehicle", description = "APIs for vehicle management")
public class VehicleController {
    @Autowired
    private VehicleServices vehicleServices;

    @GetMapping("/GetVehicleTypes/{ownerId}")
    @Operation(summary = "Get all vehicle types")
    public ResponseEntity<ApiResponse<List<VehicleTypes>>>  GetVehicleTypes(@PathVariable String ownerId) {
        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<List<VehicleTypes>> response = new ApiResponse<List<VehicleTypes>>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        List<VehicleTypes> vehicleTypes = vehicleServices.GetVehcileTypes(ownerId);

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

    @GetMapping("/GetVehicleTableRecords")
    @Operation(summary = "Get a vehicle type table records")
    public ResponseEntity<ApiResponse<PaginationResult<VehicleTypes>>> GetVehicleTableRecords(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime filterDate,
            @RequestParam(required = false) String filterName,
            @RequestParam String ownerId) {

        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<PaginationResult<VehicleTypes>> response = new ApiResponse<PaginationResult<VehicleTypes>>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        PaginationResult<VehicleTypes> paginationResult = vehicleServices.GetVehicleTableRecords(pageNo,pageSize,filterDate,filterName,ownerId);

        if (paginationResult == null || paginationResult.getData().isEmpty()) {
            // Return 404 Not Found
            ApiResponse<PaginationResult<VehicleTypes>> response = new ApiResponse<PaginationResult<VehicleTypes>>(false, "No vehicle types found", null);
            return ResponseEntity.status(404).body(response);
        }

        // Return 200 OK with data
        ApiResponse<PaginationResult<VehicleTypes>> response = new ApiResponse<PaginationResult<VehicleTypes>>(true, "Vehicle types fetched successfully", paginationResult);
        return ResponseEntity.ok(response);
    }

}

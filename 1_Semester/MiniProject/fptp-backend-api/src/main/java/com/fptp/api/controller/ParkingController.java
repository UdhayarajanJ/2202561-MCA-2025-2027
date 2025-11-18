package com.fptp.api.controller;

import com.fptp.api.models.*;
import com.fptp.api.services.ParkingVehicleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/parking/")
@Tag(name = "Parking Vehicle", description = "APIs for parking management")
public class ParkingController {
    @Autowired
    private ParkingVehicleServices parkingVehicleServices;

    @PostMapping("/RegisterVehicle")
    @Operation(summary = "Register vehicle details")
    public ResponseEntity<ApiResponse<CommonResult>> RegisterVehicle(
            @Valid @RequestBody ParkingVehicle parkingVehicle) {
        CommonResult result = parkingVehicleServices.RegisterVehicle(parkingVehicle);
        return ResponseEntity.ok(new ApiResponse<>(true, result.getMessage(), result));
    }

    @GetMapping("/GetAvailableSlots")
    @Operation(summary = "Get a available slots")
    public ResponseEntity<ApiResponse<AvailableSlots>> GetAvailableSlots(
            @RequestParam int vehicleId,
            @RequestParam String ownerId) {

        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<AvailableSlots> response = new ApiResponse<AvailableSlots>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        AvailableSlots availableSlots = parkingVehicleServices.GetAvailableSlots(vehicleId, ownerId);

        if (availableSlots == null) {
            ApiResponse<AvailableSlots> response = new ApiResponse<AvailableSlots>(true, "No slots are available", availableSlots);
            return ResponseEntity.ok(response);
        }

        // Return 200 OK with data
        ApiResponse<AvailableSlots> response = new ApiResponse<AvailableSlots>(true, "Slots are available", availableSlots);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/GetVehicleStatus")
    @Operation(summary = "Get a vehicle status")
    public ResponseEntity<ApiResponse<VehicleStatus>> GetVehicleStatus(
            @RequestParam String vehicleNo,
            @RequestParam String ownerId) {

        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<VehicleStatus> response = new ApiResponse<VehicleStatus>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        VehicleStatus vehicleStatus = parkingVehicleServices.GetVehicleStatus(vehicleNo, ownerId);

        if (vehicleStatus == null) {
            ApiResponse<VehicleStatus> response = new ApiResponse<VehicleStatus>(false, "Vehicle status are not available", null);
            return ResponseEntity.status(404).body(response);
        }

        // Return 200 OK with data
        ApiResponse<VehicleStatus> response = new ApiResponse<VehicleStatus>(true, "Successfully fetched vehicle status", vehicleStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/GetParkingVehicleTableRecords")
    @Operation(summary = "Get a parking vehicle type table records")
    public ResponseEntity<ApiResponse<PaginationResult<ParkingVehicle>>> GetParkingVehicleTableRecords(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam String ownerId,
            @RequestParam(required = false) String filterVehicleNo,
            @RequestParam(required = false) Boolean filterIsPaid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime filterCheckInDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime filterCheckOutDate,
            @RequestParam(required = false) String filterMobileNo,
            @RequestParam(required = false) String filterName,
            @RequestParam(required = false) Integer filterVehicleId,
            @RequestParam(required = false) Boolean filterIsSubmittedKey,
            @RequestParam(required = false) String filterPaidThrow
    ) {

        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<PaginationResult<ParkingVehicle>> response = new ApiResponse<PaginationResult<ParkingVehicle>>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        PaginationResult<ParkingVehicle> paginationResult = parkingVehicleServices.GetParkingVehicleTableRecords(
                pageNo,
                pageSize,
                ownerId,
                filterVehicleNo,
                filterIsPaid,
                filterCheckInDate,
                filterCheckOutDate,
                filterMobileNo,
                filterName,
                filterVehicleId,
                filterIsSubmittedKey,
                filterPaidThrow);

        if (paginationResult == null || paginationResult.getData().isEmpty()) {
            // Return 404 Not Found
            ApiResponse<PaginationResult<ParkingVehicle>> response = new ApiResponse<PaginationResult<ParkingVehicle>>(false, "No parking vehicle types found", null);
            return ResponseEntity.status(404).body(response);
        }

        // Return 200 OK with data
        ApiResponse<PaginationResult<ParkingVehicle>> response = new ApiResponse<PaginationResult<ParkingVehicle>>(true, "Vehicle types fetched successfully", paginationResult);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/DeleteRegisterationVehicle")
    @Operation(summary = "Delete registered vehicle details")
    public ResponseEntity<ApiResponse<CommonResult>> DeleteRegisterationVehicle(
            @RequestParam int parkingId,
            @RequestParam String ownerId) {
        CommonResult result = parkingVehicleServices.DeleteRegisterationVehicle(parkingId,ownerId);
        return ResponseEntity.ok(new ApiResponse<>(true, result.getMessage(), result));
    }
    
    @GetMapping("/GetTotalChargeOfVehicle")
    @Operation(summary = "Get total charges of vehicle")
    public ResponseEntity<ApiResponse<ParkingVehicle>> GetTotalChargeOfVehicle(
            @RequestParam int parkingId,
            @RequestParam String ownerId) {
        ParkingVehicle result = parkingVehicleServices.GetTotalChargeOfVehicle(parkingId,ownerId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Parking charges are prepared.", result));
    }

    @PutMapping("/PaidVehicleCharge")
    @Operation(summary = "Paid vehicle charges")
    public ResponseEntity<ApiResponse<CommonResult>> PaidVehicleCharge(
            @Valid @RequestBody ParkingVehicle parkingVehicle) {
        CommonResult result = parkingVehicleServices.PaidVehicleCharge(parkingVehicle);
        return ResponseEntity.ok(new ApiResponse<>(true, result.getMessage(), result));
    }
}

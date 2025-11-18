package com.fptp.api.controller;

import com.fptp.api.models.ApiResponse;
import com.fptp.api.models.Summary;
import com.fptp.api.models.VehicleTypes;
import com.fptp.api.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Dashboard/")
@Tag(name = "Dashboard", description = "APIs for general report")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/GetDashboardSummary/{ownerId}")
    @Operation(summary = "Get general dashboard summary")
    public ResponseEntity<ApiResponse<Summary>> GetVehicleTypes(@PathVariable String ownerId) {
        if (ownerId == null || ownerId.isBlank()) {
            ApiResponse<Summary> response = new ApiResponse<Summary>(false, "Owner id is mandatory", null);
            return ResponseEntity.status(400).body(response);
        }

        Summary summary = dashboardService.GetDashboardSummary(ownerId);

        if (summary == null) {
            // Return 404 Not Found
            ApiResponse<Summary> response = new ApiResponse<Summary>(false, "No dashboard summary found", null);
            return ResponseEntity.status(404).body(response);
        }

        // Return 200 OK with data
        ApiResponse<Summary> response = new ApiResponse<Summary>(true, "Dashboard summaries are fetched successfully", summary);
        return ResponseEntity.ok(response);
    }
}

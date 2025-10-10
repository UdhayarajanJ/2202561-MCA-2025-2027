package com.fptp.api.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypes {
    private int VehicleId;

    @NotBlank(message = "Vehicle name is required")
    @Size(min = 2, max = 100, message = "Vehicle name must be between 2 and 100 characters")
    private String Name;
    private Date CreatedDate;
    private Date ModifiedDate;
    private boolean IsDeleted;

    @DecimalMin(value = "1.00", message = "Per hour charge must be greater than or equal to 1.00")
    private double PerHourCharge;
}

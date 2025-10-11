package com.fptp.api.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypes {
    private int VehicleId;

    @NotBlank(message = "Vehicle name is required")
    @Size(min = 2, max = 100, message = "Vehicle name must be between 2 and 100 characters")
    private String Name;

    @DecimalMin(value = "1.00", message = "Per hour charge must be greater than or equal to 1.00")
    private double PerHourCharge;

    @NotBlank(message = "Block name is required")
    private String BlockName;

    private  int TotalSlots;

    private boolean IsEnabled;

    private Date CreatedDate;

    private Date ModifiedDate;

    private boolean IsDeleted;

    @NotBlank(message = "Owner Id is required")
    private String OwnerId;


}

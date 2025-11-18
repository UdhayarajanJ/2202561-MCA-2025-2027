package com.fptp.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingVehicle {
    private int ParkingId;
    private String Name;
    private String MobileNo;

    @Pattern(
            regexp = "^[A-Za-z]{2}[0-9]{1,2}[A-Za-z]{0,3}[0-9]{4}$",
            message = "Invalid vehicle number format. Example: TN01AB1234"
    )
    private String VehicleNo;

    private LocalDateTime CheckIn;
    private LocalDateTime CheckOut;
    private String TotalHours;
    private int VehicleTypeId;
    private double PerHourCharge;
    private String TransactionId;
    private String PaidThrow;
    private Boolean IsPaid;
    private LocalDateTime CreatedDate;

    private LocalDateTime ModifiedDate;
    @NotBlank(message = "Owner Id is required")
    private String OwnerId;
    private boolean IsDeleted;


    private double TotalCharge;
    @NotBlank(message = "Block name is required")
    private String BlockName;

    private  int SlotNo;

    private Boolean IsSubmittedKey;
    private String VehicleTypeName;
    private String Comments;
}

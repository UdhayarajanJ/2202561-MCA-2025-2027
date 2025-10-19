package com.fptp.api.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatus {
    private boolean ParkingStatus;
    private String VehicleNo;
    private String Message;
    private String MobileNo;
    private String Name;
    private Integer VehicleTypeId;
}

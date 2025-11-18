package com.fptp.api.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeSummary {
    private String Name;
    private Integer TotalSlots;
    private Integer AvailableSlots;
    private Integer ReservedSlots;
    private Boolean IsEnabled;

}

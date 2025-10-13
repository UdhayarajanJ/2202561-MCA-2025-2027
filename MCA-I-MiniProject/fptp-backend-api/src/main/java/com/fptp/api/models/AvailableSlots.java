package com.fptp.api.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSlots {
    private int slotNumber;
    private String blockName;
    private LocalDateTime checkInTime;
}

package com.fptp.api.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCharts {
    private Integer TotalCount;
    private String Text;
    private Integer InParking;
    private Integer OutParking;
    private  double TotalAmount;
}
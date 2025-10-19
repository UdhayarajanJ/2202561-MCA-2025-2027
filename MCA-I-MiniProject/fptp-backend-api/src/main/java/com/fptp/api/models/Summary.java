package com.fptp.api.models;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private List<DashboardCharts> DonutChart;
    private List<DashboardCharts> PieChart;
    private DashboardCharts ParkingSummary;
}

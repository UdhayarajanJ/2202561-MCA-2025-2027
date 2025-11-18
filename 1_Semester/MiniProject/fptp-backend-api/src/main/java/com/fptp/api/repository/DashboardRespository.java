package com.fptp.api.repository;

import com.fptp.api.contracts.IDashboardContracts;
import com.fptp.api.models.DashboardCharts;
import com.fptp.api.models.Summary;
import com.fptp.api.models.VehicleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardRespository implements IDashboardContracts {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Summary GetDashboardSummary(String ownerId) {
        Summary summary = new Summary();

        List<DashboardCharts> donutChartList = jdbcTemplate.query("CALL usp_GetDonutPie(?)", new Object[]{ownerId},
                (rs, rowNum) -> new DashboardCharts(
                        rs.getInt("TotalCount"),
                        rs.getString("Text"),
                        0,
                        0,
                        0
                )
        );

        List<DashboardCharts> barChartList = jdbcTemplate.query("CALL usp_GetBarAmountReceive(?)", new Object[]{ownerId},
                (rs, rowNum) -> new DashboardCharts(
                        0,
                        rs.getString("Text"),
                        0,
                        0,
                        rs.getDouble("TotalAmount")
                )
        );

        DashboardCharts parkingSummary = jdbcTemplate.queryForObject("CALL usp_GetParkingSummary(?)", new Object[]{ownerId},
                (rs, rowNum) -> new DashboardCharts(
                        0,
                        "",
                        rs.getInt("In_Parking"),
                        rs.getInt("Out_Parking"),
                        0
                )
        );

        return new Summary(donutChartList,barChartList,parkingSummary);
    }
}

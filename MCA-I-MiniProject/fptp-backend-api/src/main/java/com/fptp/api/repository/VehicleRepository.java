package com.fptp.api.repository;

import com.fptp.api.contracts.IVehicleContracts;
import com.fptp.api.models.CommonResult;
import com.fptp.api.models.VehicleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehicleRepository implements IVehicleContracts {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VehicleTypes> GetVehcileTypes() {
        return jdbcTemplate.query("CALL usp_GetVehicleTypes()", new RowMapper<VehicleTypes>() {
            @Override
            public VehicleTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new VehicleTypes(
                        rs.getInt("VehicleId"),
                        rs.getString("Name"),
                        rs.getTimestamp("CreatedDate"),
                        rs.getTimestamp("ModifiedDate"),
                        rs.getBoolean("IsDeleted"),
                        rs.getDouble("PerHourCharge")
                );
            }
        });
    }

    @Override
    public CommonResult SaveUpdateVehicleTypes(VehicleTypes vehicleTypes) {
        String sql = "CALL usp_SaveVehicleTypes(?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{
                        vehicleTypes.getVehicleId(),         // p_VehicleId
                        vehicleTypes.getName(),              // p_Name
                        vehicleTypes.getPerHourCharge(),     // p_PerHourCharge
                        false             // p_IsDeleted
                },
                (rs, rowNum) -> new CommonResult(
                        rs.getInt("Result"),
                        rs.getString("Message")
                )
        );
    }

    @Override
    public CommonResult DeleteVehicleTypes(VehicleTypes vehicleTypes) {
        String sql = "CALL usp_SaveVehicleTypes(?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{
                        vehicleTypes.getVehicleId(),         // p_VehicleId
                        vehicleTypes.getName(),              // p_Name
                        vehicleTypes.getPerHourCharge(),     // p_PerHourCharge
                        true
                },
                (rs, rowNum) -> new CommonResult(
                        rs.getInt("Result"),
                        rs.getString("Message")
                )
        );
    }
}

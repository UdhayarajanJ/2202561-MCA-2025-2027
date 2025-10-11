package com.fptp.api.repository;

import com.fptp.api.contracts.IVehicleContracts;
import com.fptp.api.models.CommonResult;
import com.fptp.api.models.VehicleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehicleRepository implements IVehicleContracts {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VehicleTypes> GetVehcileTypes(String ownerId) {
        String sql = "CALL usp_GetVehicleTypes(?)";

        return jdbcTemplate.query(
                sql,
                new Object[]{ ownerId },
                (rs, rowNum) -> new VehicleTypes(
                        rs.getInt("VehicleId"),
                        rs.getString("Name"),
                        rs.getDouble("PerHourCharge"),
                        rs.getString("BlockName"),
                        rs.getInt("TotalSlots"),
                        rs.getBoolean("IsEnabled"),
                        rs.getTimestamp("CreatedDate"),
                        rs.getTimestamp("ModifiedDate"),
                        rs.getBoolean("IsDeleted"),
                        rs.getString("OwnerId")
                )
        );
    }

    @Override
    public CommonResult SaveUpdateVehicleTypes(VehicleTypes vehicleTypes) {
        String sql = "CALL usp_SaveVehicleTypes(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{
                        vehicleTypes.getVehicleId(),        // p_VehicleId
                        vehicleTypes.getName(),              // p_Name
                        vehicleTypes.getPerHourCharge(),     // p_PerHourCharg
                        vehicleTypes.isIsDeleted(),
                        vehicleTypes.isIsEnabled(),// p_IsDeleted
                        vehicleTypes.getBlockName(),
                        vehicleTypes.getOwnerId(),
                        vehicleTypes.getTotalSlots()
                },
                (rs, rowNum) -> new CommonResult(
                        rs.getInt("Result"),
                        rs.getString("Message")
                )
        );
    }

    @Override
    public CommonResult DeleteVehicleTypes(VehicleTypes vehicleTypes) {
        String sql = "CALL usp_SaveVehicleTypes(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{
                        vehicleTypes.getVehicleId(),         // p_VehicleId
                        vehicleTypes.getName(),              // p_Name
                        vehicleTypes.getPerHourCharge(),     // p_PerHourCharge
                        vehicleTypes.isIsDeleted(),
                        vehicleTypes.isIsEnabled(),
                        vehicleTypes.getBlockName(),
                        vehicleTypes.getOwnerId(),
                        vehicleTypes.getTotalSlots()
                },
                (rs, rowNum) -> new CommonResult(
                        rs.getInt("Result"),
                        rs.getString("Message")
                )
        );
    }
}

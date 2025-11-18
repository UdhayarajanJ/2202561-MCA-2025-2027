package com.fptp.api.repository;

import com.fptp.api.contracts.IVehicleContracts;
import com.fptp.api.models.CommonResult;
import com.fptp.api.models.PaginationResult;
import com.fptp.api.models.VehicleTypeSummary;
import com.fptp.api.models.VehicleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VehicleRepository implements IVehicleContracts {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall jdbcCall;

    @Override
    public List<VehicleTypes> GetVehcileTypes(String ownerId) {
        String sql = "CALL usp_GetVehicleTypes(?)";

        return jdbcTemplate.query(
                sql,
                new Object[]{ownerId},
                (rs, rowNum) -> new VehicleTypes(
                        rs.getInt("VehicleId"),
                        rs.getString("Name"),
                        rs.getDouble("PerHourCharge"),
                        rs.getString("BlockName"),
                        rs.getInt("TotalSlots"),
                        rs.getBoolean("IsEnabled"),
                        rs.getTimestamp("CreatedDate").toLocalDateTime(),
                        rs.getTimestamp("ModifiedDate").toLocalDateTime(),
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
    public PaginationResult<VehicleTypes> GetVehicleTableRecords(int pageNo, int pageSize, LocalDateTime filterDate, String filterName, String ownerId) {
        jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("usp_GetTableVehicleTypes")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_PageNo", Types.INTEGER),
                        new SqlParameter("p_PageSize", Types.INTEGER),
                        new SqlParameter("p_OwnerId", Types.VARCHAR),
                        new SqlParameter("p_FilterDate", Types.TIMESTAMP),
                        new SqlParameter("p_FilterName", Types.VARCHAR)
                )
                .returningResultSet("totalRecords", (rs, rowNum) -> rs.getInt("totalRecords"))
                .returningResultSet("vehicles", (rs, rowNum) -> {
                    VehicleTypes v = new VehicleTypes();
                    v.setVehicleId(rs.getInt("VehicleId"));
                    v.setName(rs.getString("Name"));
                    v.setPerHourCharge(rs.getDouble("PerHourCharge"));
                    v.setBlockName(rs.getString("BlockName"));
                    v.setTotalSlots(rs.getInt("TotalSlots"));
                    v.setIsEnabled(rs.getBoolean("IsEnabled"));
                    v.setCreatedDate(rs.getTimestamp("CreatedDate").toLocalDateTime());
                    v.setModifiedDate(rs.getTimestamp("ModifiedDate").toLocalDateTime());
                    v.setIsDeleted(rs.getBoolean("IsDeleted"));
                    v.setOwnerId(rs.getString("OwnerId"));
                    return v;
                });
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_PageNo", pageNo);
        inParams.put("p_PageSize", pageSize);
        inParams.put("p_OwnerId", ownerId);
        inParams.put("p_FilterDate", filterDate);
        inParams.put("p_FilterName", filterName);

        Map<String, Object> out = jdbcCall.execute(inParams);
        int totalRecords = 0;
        List<Integer> totalList = (List<Integer>) out.get("totalRecords");
        if (totalList != null && !totalList.isEmpty()) {
            totalRecords = totalList.get(0);
        }
        List<VehicleTypes> items = (List<VehicleTypes>) out.get("vehicles");
        return new PaginationResult<>(totalRecords, items);
    }

    @Override
    public PaginationResult<VehicleTypeSummary> GetVehicleTypeSummary(int pageNo, int pageSize, String filterName, String ownerId) {
        jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("usp_GetVehicleTypeSummary")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_PageNo", Types.INTEGER),
                        new SqlParameter("p_PageSize", Types.INTEGER),
                        new SqlParameter("p_OwnerId", Types.VARCHAR),
                        new SqlParameter("p_FilterName", Types.VARCHAR)
                )
                .returningResultSet("totalRecords", (rs, rowNum) -> rs.getInt("totalRecords"))
                .returningResultSet("vehiclesTypeSummary", (rs, rowNum) -> {
                    VehicleTypeSummary v = new VehicleTypeSummary();
                    v.setName(rs.getString("Name"));
                    v.setTotalSlots(rs.getInt("TotalSlots"));
                    v.setAvailableSlots(rs.getInt("AvailableSlots"));
                    v.setReservedSlots(rs.getInt("ReservedSlots"));
                    v.setIsEnabled(rs.getBoolean("IsEnabled"));
                    return v;
                });
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_PageNo", pageNo);
        inParams.put("p_PageSize", pageSize);
        inParams.put("p_OwnerId", ownerId);
        inParams.put("p_FilterName", filterName);

        Map<String, Object> out = jdbcCall.execute(inParams);
        int totalRecords = 0;
        List<Integer> totalList = (List<Integer>) out.get("totalRecords");
        if (totalList != null && !totalList.isEmpty()) {
            totalRecords = totalList.get(0);
        }
        List<VehicleTypeSummary> items = (List<VehicleTypeSummary>) out.get("vehiclesTypeSummary");
        return new PaginationResult<>(totalRecords, items);
    }
}

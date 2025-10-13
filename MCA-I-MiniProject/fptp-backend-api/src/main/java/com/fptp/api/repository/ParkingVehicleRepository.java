package com.fptp.api.repository;

import com.fptp.api.contracts.IParkingVehicleContracts;
import com.fptp.api.models.*;
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
public class ParkingVehicleRepository implements IParkingVehicleContracts {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall jdbcCall;

    @Override
    public CommonResult RegisterVehicle(ParkingVehicle parkingVehicle) {
        String sql = "CALL usp_SaveParkingVehicle(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{
                        parkingVehicle.getName(),        // p_VehicleId
                        parkingVehicle.getMobileNo(),              // p_Name
                        parkingVehicle.getVehicleNo().toUpperCase(),     // p_PerHourCharg
                        parkingVehicle.getVehicleTypeId(),
                        parkingVehicle.getVehicleTypeName(),// p_IsDeleted
                        parkingVehicle.getIsSubmittedKey(),
                        parkingVehicle.getPerHourCharge(),
                        parkingVehicle.getOwnerId(),
                        parkingVehicle.getBlockName(),
                        parkingVehicle.getSlotNo()
                },
                (rs, rowNum) -> new CommonResult(
                        rs.getInt("Result"),
                        rs.getString("Message")
                )
        );
    }

    @Override
    public AvailableSlots GetAvailableSlots(int vehicleId, String ownerId) {
        String sql = "CALL usp_GetAvailableSlots(?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{vehicleId, ownerId},
                (rs, rowNum) -> new AvailableSlots(
                        rs.getInt("slotNumber"),
                        rs.getString("blockName"),
                        rs.getTimestamp("checkInTime").toLocalDateTime()
                )
        );
    }

    @Override
    public VehicleStatus GetVehicleStatus(String vehicleNo, String ownerId) {
        String sql = "CALL usp_GetVehicleStatus(?, ?)";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{vehicleNo.toUpperCase(), ownerId},
                (rs, rowNum) -> new VehicleStatus(
                        rs.getBoolean("ParkingStatus"),
                        rs.getString("VehicleNo"),
                        rs.getString("Message"),
                        rs.getString("MobileNo"),
                        rs.getString("Name")
                )
        );
    }

    @Override
    public PaginationResult<ParkingVehicle> GetParkingVehicleTableRecords(
            int pageNo,
            int pageSize,
            String ownerId,
            String filterVehicleNo,
            Boolean filterIsPaid,
            LocalDateTime filterDate,
            String filterMobileNo,
            String filterName,
            Integer filterVehicleId,
            Boolean filterIsSubmittedKey,
            String filterPaidThrow) {
        jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("usp_GetParkingVehicleTable")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_PageNo", Types.INTEGER),
                        new SqlParameter("p_PageSize", Types.INTEGER),
                        new SqlParameter("p_OwnerId", Types.VARCHAR),
                        new SqlParameter("p_FilterVehicleNo", Types.VARCHAR),
                        new SqlParameter("p_FilterIsPaid", Types.BIT),
                        new SqlParameter("p_FilterCheckInDate", Types.TIMESTAMP),
                        new SqlParameter("p_FilterMobileNumber", Types.VARCHAR),
                        new SqlParameter("p_FilterName", Types.VARCHAR),
                        new SqlParameter("p_FilterVehicleId", Types.INTEGER),
                        new SqlParameter("p_FilterSubmittedKey", Types.BIT),
                        new SqlParameter("p_FilterPaidThrow", Types.VARCHAR)
                )
                .returningResultSet("totalRecords", (rs, rowNum) -> rs.getInt("totalRecords"))
                .returningResultSet("parkingVehicle", (rs, rowNum) -> {
                    ParkingVehicle v = new ParkingVehicle();
                    v.setParkingId(rs.getInt("ParkingId"));
                    v.setName(rs.getString("Name"));
                    v.setMobileNo(rs.getString("MobileNo"));
                    v.setVehicleNo(rs.getString("VehicleNo"));
                    v.setCheckIn(rs.getTimestamp("CheckIn").toLocalDateTime());
                    v.setCheckOut(rs.getTimestamp("CheckOut") != null ? rs.getTimestamp("CheckOut").toLocalDateTime() : null);
                    v.setTotalHours(rs.getTimestamp("TotalHours") != null ? rs.getTimestamp("TotalHours").toLocalDateTime().toLocalTime() : null);
                    v.setVehicleTypeId(rs.getInt("VehicleTypeId"));
                    v.setPerHourCharge(rs.getDouble("PerHourCharge"));
                    v.setTransactionId(rs.getString("TransactionId"));
                    v.setPaidThrow(rs.getString("PaidThrow"));
                    v.setIsPaid(rs.getBoolean("IsPaid"));
                    v.setCreatedDate(rs.getTimestamp("CreatedDate").toLocalDateTime());
                    v.setModifiedDate(rs.getTimestamp("ModifiedDate").toLocalDateTime());
                    v.setOwnerId(rs.getString("OwnerId"));
                    v.setIsDeleted(rs.getBoolean("IsDeleted"));
                    v.setTotalCharge(rs.getDouble("TotalCharges"));
                    v.setBlockName(rs.getString("BlockName"));
                    v.setSlotNo(rs.getInt("SlotNo"));
                    v.setIsSubmittedKey(rs.getBoolean("IsSubmittedKey"));
                    v.setVehicleTypeName(rs.getString("VehicleTypeName"));
                    v.setComments(rs.getString("Comments"));
                    return v;
                });
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_PageNo", pageNo);
        inParams.put("p_PageSize", pageSize);
        inParams.put("p_OwnerId", ownerId);
        inParams.put("p_FilterVehicleNo", filterVehicleNo);
        inParams.put("p_FilterIsPaid", filterIsPaid);
        inParams.put("p_FilterCheckInDate", filterDate);
        inParams.put("p_FilterMobileNumber", filterMobileNo);
        inParams.put("p_FilterName", filterName);
        inParams.put("p_FilterVehicleId", filterVehicleId);
        inParams.put("p_FilterSubmittedKey", filterIsSubmittedKey);
        inParams.put("p_FilterPaidThrow", filterPaidThrow);

        Map<String, Object> out = jdbcCall.execute(inParams);
        int totalRecords = 0;
        List<Integer> totalList = (List<Integer>) out.get("totalRecords");
        if (totalList != null && !totalList.isEmpty()) {
            totalRecords = totalList.get(0);
        }
        List<ParkingVehicle> items = (List<ParkingVehicle>) out.get("parkingVehicle");
        return new PaginationResult<>(totalRecords, items);
    }
}

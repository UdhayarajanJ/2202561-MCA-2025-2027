CREATE DATABASE  IF NOT EXISTS `fptp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fptp`;
-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: fptp
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping events for database 'fptp'
--

--
-- Dumping routines for database 'fptp'
--
/*!50003 DROP PROCEDURE IF EXISTS `usp_DeleteParkingVehicle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_DeleteParkingVehicle`(
	in p_ParkingId int,
    in p_OwnerId varchar(45)
)
BEGIN
	declare result int;
	declare messge varchar(200);
	UPDATE tbl_vehicleparking
	SET
		ModifiedDate = now(),
		IsDeleted = 1,
		OwnerId = p_OwnerId
	WHERE 
		ParkingId = p_ParkingId
		and IsDeleted = 0
		and OwnerId = p_OwnerId;
	set messge = 'Vehicle registration details are deleted successfully';
    set result = p_ParkingId;
    select
		result as 'Result',
        messge as 'Message';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetAvailableSlots` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetAvailableSlots`(
	in p_VehicleTypeId int,
    in p_OwnerId varchar(45)
)
BEGIN
	select
		vb.SlotNumber as 'slotNumber',
		vb.Name as 'blockName',
		now() as 'checkInTime'
	from
		tbl_vehicleblocks vb
		left join tbl_vehicleparking parking on parking.VehicleTypeId = vb.VehicleId and parking.SlotNo = vb.SlotNumber and parking.IsPaid = 0 and parking.OwnerId = p_OwnerId and parking.IsDeleted = 0
	where
		vb.VehicleId = p_VehicleTypeId
        and vb.OwnerId = p_OwnerId
		and parking.ParkingId is null
	limit 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetBarAmountReceive` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetBarAmountReceive`(
	in p_OwnerId varchar(45)
)
BEGIN
	select
		sum(tv.TotalCharges) as 'TotalAmount',
		tv.PaidThrow as 'Text'
	from
		tbl_vehicleparking tv
	where
		tv.IsDeleted = 0
		and tv.PaidThrow is not null and tv.PaidThrow <> 'Abandant'
		and date(tv.CreatedDate) = date(now())
		and tv.IsPaid = 1
        and tv.OwnerId = p_OwnerId
	group by tv.PaidThrow;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetDonutPie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetDonutPie`(
	in p_OwnerId varchar(45)
)
BEGIN
	select
		count(tv.ParkingId) as 'TotalCount',
		tv.PaidThrow as 'Text'
	from
		tbl_vehicleparking tv
	where
		tv.IsDeleted = 0
		and tv.PaidThrow is not null
		and date(tv.CreatedDate) = date(now())
        and tv.OwnerId = p_OwnerId
	group by tv.PaidThrow;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetParkingSummary` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetParkingSummary`(
	in p_OwnerId varchar(45)
)
BEGIN
	select
	(
		select
			count(tv.ParkingId)
		from
			tbl_vehicleparking tv
		where
			tv.IsDeleted = 0
			and tv.IsPaid = 0
            and date(tv.CreatedDate) = date(now())
            and tv.OwnerId = p_OwnerId
    ) as 'In_Parking',
    (
		select
			count(tv.ParkingId)
		from
			tbl_vehicleparking tv
		where
			tv.IsDeleted = 0
			and tv.IsPaid = 1
            and date(tv.CreatedDate) = date(now())
            and tv.OwnerId = p_OwnerId
            and tv.PaidThrow is not null and tv.PaidThrow <> 'Abandant'
    ) as 'Out_Parking';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetParkingVehicleTable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetParkingVehicleTable`(
	in p_PageNo int,
    in p_PageSize int,
    in p_OwnerId varchar(45),
    in p_FilterVehicleNo varchar(45),
    in p_FilterIsPaid bit(1),
    in p_FilterCheckInDate datetime,
    in p_FilterCheckOutDate datetime,
    in p_FilterMobileNumber varchar(15),
    in p_FilterName varchar(100),
    in p_FilterVehicleId int,
    in p_FilterSubmittedKey bit(1),
    in p_FilterPaidThrow varchar(100)
)
BEGIN
	declare offsetRows int;
    set offsetRows = (p_PageNo * p_PageSize);
    SELECT 
		COUNT(vp.ParkingId) AS totalRecords
	FROM
		tbl_vehicleparking vp
	WHERE
		vp.IsDeleted = 0
		AND vp.OwnerId = p_OwnerId
		AND (p_FilterCheckInDate IS NULL OR DATE(vp.CheckIn) = DATE(p_FilterCheckInDate))
		AND (p_FilterCheckOutDate IS NULL OR DATE(vp.CheckOut) = DATE(p_FilterCheckOutDate))
		AND (p_FilterVehicleId IS NULL OR p_FilterVehicleId = 0 OR vp.VehicleTypeId = p_FilterVehicleId)
		AND (p_FilterMobileNumber IS NULL OR p_FilterMobileNumber = '' OR vp.MobileNo LIKE CONCAT(p_FilterMobileNumber, '%'))
		AND (p_FilterVehicleNo IS NULL OR p_FilterVehicleNo = '' OR vp.VehicleNo LIKE CONCAT(p_FilterVehicleNo, '%'))
		AND (p_FilterPaidThrow IS NULL OR p_FilterPaidThrow = '' OR vp.PaidThrow = p_FilterPaidThrow)
		AND (p_FilterIsPaid IS NULL OR vp.IsPaid = p_FilterIsPaid)
		AND (p_FilterSubmittedKey IS NULL OR vp.IsSubmittedKey = p_FilterSubmittedKey)
		AND (p_FilterName IS NULL OR p_FilterName = '' OR vp.Name LIKE CONCAT('%', p_FilterName, '%'));
	SELECT 
		vp.ParkingId,
		vp.Name,
		vp.MobileNo,
		vp.VehicleNo,
		vp.CheckIn,
		vp.CheckOut,
		vp.TotalHours,
		vp.VehicleTypeId,
		vp.PerHourCharge,
		vp.TransactionId,
		vp.PaidThrow,
		vp.IsPaid,
		vp.CreatedDate,
		vp.ModifiedDate,
		vp.OwnerId,
		vp.IsDeleted,
		vp.TotalCharges,
		vp.BlockName,
		vp.SlotNo,
		vp.IsSubmittedKey,
		vp.VehicleTypeName,
		vp.Comments
	FROM 
		tbl_vehicleparking vp
	WHERE
		vp.IsDeleted = 0
		AND vp.OwnerId = p_OwnerId
		AND (p_FilterCheckInDate IS NULL OR DATE(vp.CheckIn) = DATE(p_FilterCheckInDate))
		AND (p_FilterCheckOutDate IS NULL OR DATE(vp.CheckOut) = DATE(p_FilterCheckOutDate))
		AND (p_FilterVehicleId IS NULL OR p_FilterVehicleId = 0 OR vp.VehicleTypeId = p_FilterVehicleId)
		AND (p_FilterMobileNumber IS NULL OR p_FilterMobileNumber = '' OR vp.MobileNo LIKE CONCAT(p_FilterMobileNumber, '%'))
		AND (p_FilterVehicleNo IS NULL OR p_FilterVehicleNo = '' OR vp.VehicleNo LIKE CONCAT(p_FilterVehicleNo, '%'))
		AND (p_FilterPaidThrow IS NULL OR p_FilterPaidThrow = '' OR vp.PaidThrow = p_FilterPaidThrow)
		AND (p_FilterIsPaid IS NULL OR vp.IsPaid = p_FilterIsPaid)
		AND (p_FilterSubmittedKey IS NULL OR vp.IsSubmittedKey = p_FilterSubmittedKey)
		AND (p_FilterName IS NULL OR p_FilterName = '' OR vp.Name LIKE CONCAT('%', p_FilterName, '%'))
	ORDER BY vp.CheckIn DESC,vp.IsPaid ASC
    limit p_PageSize
    offset offsetRows;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetTableVehicleTypes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetTableVehicleTypes`(
	in p_PageNo int,
    in p_PageSize int,
    in p_OwnerId varchar(45),
    in p_FilterDate datetime,
    in p_FilterName varchar(150)
)
BEGIN
	declare offsetRows int;
    set offsetRows = (p_PageNo * p_PageSize);
	SELECT 
		COUNT(vt.VehicleId) AS totalRecords
	FROM
		tbl_vehicletypes vt
	WHERE
		vt.IsDeleted = 0
		AND vt.OwnerId = p_OwnerId
		AND (p_FilterDate IS NULL OR DATE(vt.ModifiedDate) = DATE(p_FilterDate))
		AND (p_FilterName IS NULL OR p_FilterName = '' OR vt.Name LIKE CONCAT('%', p_FilterName, '%'));

	SELECT 
		vt.VehicleId,
		vt.Name,
		vt.PerHourCharge,
		vt.BlockName,
		vt.TotalSlots,
		vt.IsEnabled,
		vt.CreatedDate,
		vt.ModifiedDate,
		vt.IsDeleted,
		vt.OwnerId
	FROM
		tbl_vehicletypes vt
	WHERE
		vt.IsDeleted = 0
		AND vt.OwnerId = p_OwnerId
		AND (p_FilterDate IS NULL OR DATE(vt.ModifiedDate) = DATE(p_FilterDate))
		AND (p_FilterName IS NULL OR p_FilterName = '' OR vt.Name LIKE CONCAT('%', p_FilterName, '%'))
	ORDER BY vt.ModifiedDate DESC
    limit p_PageSize
    offset offsetRows;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetTotalChargeOfParking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetTotalChargeOfParking`(
	in p_ParkingId int,
    in p_OwnerId varchar(45)
)
BEGIN
	SELECT 
		ParkingId,
		TIMEDIFF(NOW(), CheckIn) AS TotalHours,
		ROUND((TIME_TO_SEC(TIMEDIFF(NOW(), CheckIn)) / 3600) * PerHourCharge, 2) AS TotalCharge,
		PerHourCharge,
		VehicleNo
	FROM fptp.tbl_vehicleparking
	WHERE ParkingId = p_ParkingId AND IsDeleted = 0 AND OwnerId = p_OwnerId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetVehicleOwner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetVehicleOwner`(
	in p_VehicleNo varchar(45),
	in p_OwnerId varchar(45)
)
BEGIN
	SELECT 
		tbl_vehicleparking.ParkingId,
		tbl_vehicleparking.Name,
		tbl_vehicleparking.MobileNo,
		tbl_vehicleparking.VehicleNo,
		tbl_vehicleparking.CheckIn,
		tbl_vehicleparking.CheckOut,
		tbl_vehicleparking.TotalHours,
		tbl_vehicleparking.VehicleTypeId,
		tbl_vehicleparking.PerHourCharge,
		tbl_vehicleparking.TransactionId,
		tbl_vehicleparking.PaidThrow,
		tbl_vehicleparking.IsPaid,
		tbl_vehicleparking.CreatedDate,
		tbl_vehicleparking.ModifiedDate,
		tbl_vehicleparking.OwnerId,
		tbl_vehicleparking.IsDeleted,
		tbl_vehicleparking.TotalCharges,
		tbl_vehicleparking.BlockName,
		tbl_vehicleparking.SlotNo,
		tbl_vehicleparking.IsSubmittedKey,
		tbl_vehicleparking.VehicleTypeName,
		tbl_vehicleparking.Comments
	FROM 
		tbl_vehicleparking
	WHERE
		tbl_vehicleparking.VehicleNo = p_VehicleNo
		and tbl_vehicleparking.OwnerId = p_OwnerId
	order by tbl_vehicleparking.ModifiedDate desc
	limit 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetVehicleStatus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetVehicleStatus`(
	in p_VehicleNo varchar(45),
    in p_OwnerId varchar(45)
)
BEGIN
	if exists (select ParkingId from  tbl_vehicleparking where IsPaid = 0 and IsDeleted = 0 and VehicleNo = p_VehicleNo and OwnerId = p_OwnerId limit 1)
    then
		select
			1 as 'ParkingStatus',
            p_VehicleNo as 'VehicleNo',
            concat('(',p_VehicleNo,')', ' Already vehicle were parked') as 'Message',
            '' as 'MobileNo',
            '' as 'Name',
            0 as 'VehicleTypeId';
	else
		if exists (select ParkingId from  tbl_vehicleparking where IsPaid = 1 and IsDeleted = 0 and VehicleNo = p_VehicleNo and OwnerId = p_OwnerId limit 1)
        then
			select
				0 as 'ParkingStatus',
				VehicleNo as 'VehicleNo',
				concat('Vehicle can park (',VehicleNo,')') as 'Message',
				MobileNo as 'MobileNo',
				Name as 'Name',
                VehicleTypeId as 'VehicleTypeId'
			from  
				tbl_vehicleparking
			where
				IsDeleted = 0
                and IsPaid = 1
				and VehicleNo = p_VehicleNo
                and OwnerId = p_OwnerId
			order by ModifiedDate desc limit 1;
		else
			select
				0 as 'ParkingStatus',
				p_VehicleNo as 'VehicleNo',
				concat('Vehicle can park (',p_VehicleNo,')') as 'Message',
				'' as 'MobileNo',
				'' as 'Name',
                0 as 'VehicleTypeId';
        end if;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetVehicleTypes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetVehicleTypes`(
	in p_OwnerId varchar(45)
)
BEGIN
	SELECT 
		tbl_vehicletypes.VehicleId,
		tbl_vehicletypes.Name,
		tbl_vehicletypes.PerHourCharge,
		tbl_vehicletypes.BlockName,
		tbl_vehicletypes.TotalSlots,
		tbl_vehicletypes.IsEnabled,
		tbl_vehicletypes.CreatedDate,
		tbl_vehicletypes.ModifiedDate,
		tbl_vehicletypes.IsDeleted,
		tbl_vehicletypes.OwnerId
	FROM
		tbl_vehicletypes
	WHERE
		tbl_vehicletypes.IsEnabled = 1
        and tbl_vehicletypes.IsDeleted = 0
        and tbl_vehicletypes.OwnerId = p_OwnerId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_GetVehicleTypeSummary` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_GetVehicleTypeSummary`(
	in p_PageNo int,
    in p_PageSize int,
    in p_OwnerId varchar(45),
    in p_FilterName varchar(150)
)
BEGIN
	declare offsetRows int;
    set offsetRows = (p_PageNo * p_PageSize);
	SELECT 
		COUNT(vt.VehicleId) AS totalRecords
	FROM
		tbl_vehicletypes vt
	WHERE
		vt.IsDeleted = 0
		AND vt.OwnerId = p_OwnerId
		AND (p_FilterName IS NULL OR p_FilterName = '' OR vt.Name LIKE CONCAT('%', p_FilterName, '%'));
        
	SELECT
		tvt.Name,
		tvt.TotalSlots,
		(
			select
				count(vb.BlockId)
			from
				tbl_vehicleblocks vb
				left join tbl_vehicleparking parking on parking.VehicleTypeId = vb.VehicleId and parking.SlotNo = vb.SlotNumber and parking.IsPaid = 0 and parking.OwnerId = p_OwnerId and parking.IsDeleted = 0
			where
				vb.VehicleId = tvt.VehicleId
				and vb.OwnerId = p_OwnerId
				and parking.ParkingId is null
		) as 'AvailableSlots',
		(
			select
				count(vb.BlockId)
			from
				tbl_vehicleblocks vb
				left join tbl_vehicleparking parking on parking.VehicleTypeId = vb.VehicleId and parking.SlotNo = vb.SlotNumber and parking.IsPaid = 0 and parking.OwnerId = p_OwnerId and parking.IsDeleted = 0
			where
				vb.VehicleId = tvt.VehicleId
				and vb.OwnerId = p_OwnerId
				and parking.ParkingId is not null
		) as 'ReservedSlots',
		tvt.IsEnabled
	FROM
		tbl_vehicletypes tvt
	WHERE
		tvt.IsDeleted = 0
		AND tvt.OwnerId = p_OwnerId
		AND (p_FilterName IS NULL OR p_FilterName = '' OR tvt.Name LIKE CONCAT('%', p_FilterName, '%'))
	ORDER BY tvt.ModifiedDate DESC
    limit p_PageSize
    offset offsetRows;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_SaveParkingVehicle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_SaveParkingVehicle`(
    in p_Name varchar(100),
    in p_MobileNo varchar(15),
    in p_VehicleNo varchar(45),
    in p_VehicleTypeId int,
    in p_VehicleTypeName varchar(150),
    in p_IsSubmittedKey bit(1),
    in p_PerHourCharge decimal(10,2),
    in p_OwnerId varchar(45),
    in p_BlockName varchar(45),
    in p_SlotNo int
)
BEGIN
	declare result int;
	declare messge varchar(200);
	INSERT INTO tbl_vehicleparking
	(
		Name,
		MobileNo,
		VehicleNo,
		CheckIn,
		VehicleTypeId,
        VehicleTypeName,
		PerHourCharge,
        IsSubmittedKey,
		CreatedDate,
		ModifiedDate,
		OwnerId,
		BlockName,
		SlotNo
    )
	VALUES
    (
		p_Name,
        p_MobileNo,
        p_VehicleNo,
        now(),
        p_VehicleTypeId,
        p_VehicleTypeName,
        p_PerHourCharge,
        p_IsSubmittedKey,
        now(),
        now(),
        p_OwnerId,
        p_BlockName,
        p_SlotNo
    );
    
    set result = last_insert_id();
	set messge = 'Vehicles details are registered successfully';
    select
		result as 'Result',
        messge as 'Message';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_SaveVehicleTypes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_SaveVehicleTypes`(
	in p_VehicleId int,
    in p_Name varchar(150),
    in p_PerHourCharge decimal(10,2),
    in p_IsDeleted bit(1),
    in p_IsEnabled bit(1),
    in p_BlockName Varchar(45),
    in p_OwnerId Varchar(45),
    in p_TotalSlots int
)
BEGIN
	declare result int;
    declare slotInitialize int default 1;
	declare messge varchar(200);
	if not exists(select VehicleId from tbl_vehicletypes where IsDeleted = 0 and VehicleId = p_VehicleId limit 1)
    then
		INSERT INTO tbl_vehicletypes
		(
			Name,
			CreatedDate,
			ModifiedDate,
			IsDeleted,
			PerHourCharge,
            IsEnabled,
            BlockName,
            OwnerId,
            TotalSlots
        )
		VALUES
		(
			p_Name,
            now(),
            now(),
			0,
            p_PerHourCharge,
            p_IsEnabled,
            p_BlockName,
            p_OwnerId,
            p_TotalSlots
        );
        set result = last_insert_id();
        set messge = 'Vehicle details are added successfully';
	else
		UPDATE tbl_vehicletypes
		SET
			Name = p_Name,
			ModifiedDate = now(),
			IsDeleted = p_IsDeleted,
			PerHourCharge = p_PerHourCharge,
			IsEnabled = p_IsEnabled,
            OwnerId = p_OwnerId,
            BlockName = p_BlockName,
            TotalSlots = p_TotalSlots
		WHERE 
			VehicleId = p_VehicleId
            and IsDeleted = 0
            and OwnerId = p_OwnerId;
		
        set result = p_VehicleId;
        if p_IsDeleted = 0 then
			set messge = 'Vehicle details are updated successfully';
		else
			set messge = 'Vehicle details are deleted successfully';
		end if;
    end if;
    
    delete from tbl_vehicleblocks where OwnerId = p_OwnerId and VehicleId = result;
    if p_TotalSlots > 0 and p_IsDeleted = 0 then
		WHILE slotInitialize <= p_TotalSlots DO
			INSERT INTO tbl_vehicleblocks
			(
				Name,
				SlotNumber,
				OwnerId,
				CreatedDate,
				ModifiedDate,
				IsDeleted,
				VehicleId
			)
			VALUES
			(
				p_BlockName,
				slotInitialize,
				p_OwnerId,
				now(),
				now(),
				0,
				result
			);
			SET slotInitialize = slotInitialize + 1;
		END WHILE;
    end if;
    
    select
		result as 'Result',
        messge as 'Message';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_UpdatePaymentVehicleParking` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_UpdatePaymentVehicleParking`(
	in p_ParkingId int,
    in p_Comments text,
    in p_PaidThrow varchar(100),
    in p_TotalHours time,
    in p_TotalCharge decimal(10,2),
    in p_OwnerId varchar(45),
    in p_TransactionId varchar(150)
)
BEGIN
	update tbl_vehicleparking
    set
		Comments = p_Comments,
        PaidThrow = p_PaidThrow,
        TotalHours = p_TotalHours,
        TotalCharges = p_TotalCharge,
        TransactionId = p_TransactionId,
        ModifiedDate = now(),
        CheckOut = now(),
        IsPaid=1
	where
		ParkingId = p_ParkingId
        and IsDeleted = 0
        and OwnerId = p_OwnerId;
        
	select
		p_ParkingId as 'Result',
        'Payment Succeed' as 'Message';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-18 14:29:18

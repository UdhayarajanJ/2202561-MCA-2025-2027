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
	in p_ParkingId int,
    in p_Name varchar(100),
    in p_MobileNo varchar(15),
    in p_VehicleNo varchar(45),
    in p_CheckIn datetime,
    in p_CheckOut datetime,
    in p_TotalHours time,
    in p_VehicleTypeId int,
    in p_PerHourCharge decimal(10,2),
    in p_TransactionId varchar(150),
    in p_PaidThrow varchar(100),
    in p_IsPaid bit(1),
    in p_OwnerId varchar(45),
    in p_IsDeleted bit(1),
    in p_TotalCharges decimal(10,2),
    in p_BlockName varchar(45),
    in p_SlotNo int
)
BEGIN

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-13 12:10:43

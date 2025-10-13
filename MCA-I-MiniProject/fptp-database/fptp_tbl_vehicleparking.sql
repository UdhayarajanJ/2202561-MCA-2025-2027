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
-- Table structure for table `tbl_vehicleparking`
--

DROP TABLE IF EXISTS `tbl_vehicleparking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_vehicleparking` (
  `ParkingId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `MobileNo` varchar(15) NOT NULL,
  `VehicleNo` varchar(45) NOT NULL,
  `CheckIn` datetime NOT NULL,
  `CheckOut` datetime DEFAULT NULL,
  `TotalHours` time DEFAULT NULL,
  `VehicleTypeId` int NOT NULL,
  `PerHourCharge` decimal(10,2) NOT NULL,
  `TransactionId` varchar(150) DEFAULT NULL,
  `PaidThrow` varchar(100) DEFAULT NULL,
  `IsPaid` bit(1) DEFAULT b'0',
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `OwnerId` varchar(45) DEFAULT NULL,
  `IsDeleted` bit(1) DEFAULT b'0',
  `TotalCharges` decimal(10,2) DEFAULT NULL,
  `BlockName` varchar(45) NOT NULL,
  `SlotNo` int NOT NULL,
  PRIMARY KEY (`ParkingId`),
  KEY `FK_tbl_vehicleparking_VehicleTypeId_idx` (`VehicleTypeId`),
  CONSTRAINT `FK_tbl_vehicleparking_VehicleTypeId` FOREIGN KEY (`VehicleTypeId`) REFERENCES `tbl_vehicletypes` (`VehicleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_vehicleparking`
--

LOCK TABLES `tbl_vehicleparking` WRITE;
/*!40000 ALTER TABLE `tbl_vehicleparking` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_vehicleparking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-13 12:10:43

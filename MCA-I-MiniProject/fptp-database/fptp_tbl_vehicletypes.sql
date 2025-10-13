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
-- Table structure for table `tbl_vehicletypes`
--

DROP TABLE IF EXISTS `tbl_vehicletypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_vehicletypes` (
  `VehicleId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `IsEnabled` bit(1) NOT NULL DEFAULT b'1',
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0',
  `PerHourCharge` decimal(10,2) NOT NULL,
  `BlockName` varchar(45) NOT NULL,
  `OwnerId` varchar(45) NOT NULL,
  `TotalSlots` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`VehicleId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_vehicletypes`
--

LOCK TABLES `tbl_vehicletypes` WRITE;
/*!40000 ALTER TABLE `tbl_vehicletypes` DISABLE KEYS */;
INSERT INTO `tbl_vehicletypes` VALUES (1,'Car',_binary '','2025-10-11 09:01:36','2025-10-11 09:02:17',_binary '',100.99,'CNA','1','10'),(2,'Car',_binary '\0','2025-10-11 09:03:39','2025-10-13 00:08:12',_binary '\0',110.00,'CNA','1','10'),(3,'JCB',_binary '','2025-10-12 23:12:39','2025-10-12 23:19:38',_binary '',710.00,'JB','1','100'),(4,'Bike',_binary '','2025-10-12 23:21:21','2025-10-13 12:03:49',_binary '\0',76.00,'BC','1','5'),(5,'Cycle',_binary '','2025-10-12 23:21:52','2025-10-13 00:07:18',_binary '\0',10.00,'C','1','23'),(6,'Truck',_binary '','2025-10-12 23:22:11','2025-10-13 12:04:27',_binary '\0',500.00,'T','1','56'),(7,'Xylo',_binary '','2025-10-12 23:23:00','2025-10-13 00:08:45',_binary '\0',67.00,'XIO','1','13'),(8,'Bus',_binary '','2025-10-12 23:23:20','2025-10-12 23:23:20',_binary '\0',320.00,'VBD','1','56'),(9,'Innovo',_binary '','2025-10-12 23:23:53','2025-10-12 23:24:00',_binary '\0',890.00,'PO','1','78'),(10,'XL',_binary '','2025-10-12 23:24:20','2025-10-12 23:24:20',_binary '\0',30.00,'XL','1','7'),(11,'UI',_binary '','2025-10-12 23:24:59','2025-10-12 23:24:59',_binary '\0',89.00,'OPS','1','8'),(12,'Modern',_binary '','2025-10-12 23:25:36','2025-10-13 00:08:25',_binary '\0',100.00,'98','1','6'),(13,'IOPS',_binary '','2025-10-12 23:26:00','2025-10-12 23:26:00',_binary '\0',78.00,'ICK','1','7'),(14,'LXP',_binary '','2025-10-12 23:26:20','2025-10-12 23:26:20',_binary '\0',898.00,'KL','1','67');
/*!40000 ALTER TABLE `tbl_vehicletypes` ENABLE KEYS */;
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

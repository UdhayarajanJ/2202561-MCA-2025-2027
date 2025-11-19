CREATE DATABASE  IF NOT EXISTS `mca_lab_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mca_lab_test`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mca_lab_test
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `client_master`
--

DROP TABLE IF EXISTS `client_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_master` (
  `client_no` varchar(6) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address1` varchar(30) DEFAULT NULL,
  `address2` varchar(30) DEFAULT NULL,
  `city` varchar(15) DEFAULT NULL,
  `state` varchar(15) DEFAULT NULL,
  `pincode` int DEFAULT NULL,
  `bal_due` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`client_no`),
  KEY `client_no_index` (`client_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_master`
--

LOCK TABLES `client_master` WRITE;
/*!40000 ALTER TABLE `client_master` DISABLE KEYS */;
INSERT INTO `client_master` VALUES ('0001','Ivan','','','Bombay','Maharastra',400054,15000.00),('0002','Vandana','','','Madras','Tamilnadu',780001,0.00),('0003','Pramada','','','Bombay','Maharastra',400057,5000.00),('0004','Basu','','','Bombay','Maharastra',400056,0.00),('0005','Ravi','','','Delhi','',100001,2000.00),('0006','Rukmini','','','Bombay','Maharastra',400050,0.00);
/*!40000 ALTER TABLE `client_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_master`
--

DROP TABLE IF EXISTS `product_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_master` (
  `Product_no` varchar(45) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Profit_percent` int DEFAULT NULL,
  `Unit_measure` varchar(45) DEFAULT NULL,
  `Qty_on_hand` int DEFAULT NULL,
  `Reorder1v1` int DEFAULT NULL,
  `Sell_price` double(10,2) DEFAULT NULL,
  `Cost_price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`Product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_master`
--

LOCK TABLES `product_master` WRITE;
/*!40000 ALTER TABLE `product_master` DISABLE KEYS */;
INSERT INTO `product_master` VALUES ('P00001','1.44floppies',5,'piece',100,20,1150.00,500.00),('P03453','Monitor',6,'piece',10,3,12000.00,11200.00),('P06734','Mouse',5,'piece',20,5,1050.00,500.00),('P07856','1.22floppies',5,'piece',100,20,525.00,500.00),('P07868','Keyboards',2,'piece',10,3,3150.00,3050.00),('P07885','CD Drive',3,'piece',10,3,5250.00,5100.00),('P07965','540 HDD',4,'piece',10,3,8400.00,8000.00),('P07975','1.44 Drive',5,'piece',10,3,1050.00,1000.00),('P08865','1.22 Drive',5,'piece',2,3,1050.00,1000.00);
/*!40000 ALTER TABLE `product_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order`
--

DROP TABLE IF EXISTS `sales_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_order` (
  `S_order_no` varchar(6) NOT NULL,
  `S_order_date` date NOT NULL,
  `Client_no` varchar(25) NOT NULL,
  `Dely_add` varchar(50) DEFAULT NULL,
  `Salesman_no` varchar(6) DEFAULT NULL,
  `Dely_type` char(1) DEFAULT 'f',
  `Billed_yn` char(1) DEFAULT NULL,
  `Dely_date` date DEFAULT NULL,
  `Order_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`S_order_no`),
  KEY `Client_no` (`Client_no`),
  KEY `Salesman_no` (`Salesman_no`),
  KEY `S_OrderNo_index` (`S_order_no`),
  CONSTRAINT `sales_order_ibfk_1` FOREIGN KEY (`Client_no`) REFERENCES `client_master` (`client_no`),
  CONSTRAINT `sales_order_ibfk_2` FOREIGN KEY (`Salesman_no`) REFERENCES `salesman_master` (`Salesman_no`),
  CONSTRAINT `sales_order_chk_1` CHECK (regexp_like(`S_order_no`,_utf8mb4'^0')),
  CONSTRAINT `sales_order_chk_2` CHECK ((`Dely_type` in (_utf8mb4'F',_utf8mb4'P'))),
  CONSTRAINT `sales_order_chk_3` CHECK ((`Order_status` in (_utf8mb4'Ip',_utf8mb4'F',_utf8mb4'C'))),
  CONSTRAINT `sales_order_chk_4` CHECK ((`Dely_date` >= `S_order_date`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order`
--

LOCK TABLES `sales_order` WRITE;
/*!40000 ALTER TABLE `sales_order` DISABLE KEYS */;
INSERT INTO `sales_order` VALUES ('010008','1996-05-24','0005',NULL,'500004','F','N','1996-05-26','Ip'),('016865','1996-02-18','0003',NULL,'500003','F','Y','1996-02-20','F'),('019001','1996-01-12','0001',NULL,'50001','F','N','1996-01-20','Ip'),('019002','1996-01-25','0002',NULL,'50002','P','N','1996-01-27','C'),('019003','1996-04-03','0001',NULL,'500001','F','Y','1996-04-07','F'),('046866','1996-05-20','0004',NULL,'500002','P','N','1996-05-22','C');
/*!40000 ALTER TABLE `sales_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order_details`
--

DROP TABLE IF EXISTS `sales_order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_order_details` (
  `S_order_no` varchar(6) NOT NULL,
  `Product_no` varchar(6) NOT NULL,
  `Qty_order` int DEFAULT NULL,
  `Qty_disp` int DEFAULT NULL,
  `Product_rate` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`S_order_no`,`Product_no`),
  KEY `Product_no` (`Product_no`),
  KEY `idx_sorder_product` (`S_order_no`,`Product_no`),
  CONSTRAINT `sales_order_details_ibfk_1` FOREIGN KEY (`S_order_no`) REFERENCES `sales_order` (`S_order_no`),
  CONSTRAINT `sales_order_details_ibfk_2` FOREIGN KEY (`Product_no`) REFERENCES `product_master` (`Product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order_details`
--

LOCK TABLES `sales_order_details` WRITE;
/*!40000 ALTER TABLE `sales_order_details` DISABLE KEYS */;
INSERT INTO `sales_order_details` VALUES ('010008','P00001',10,5,525.00),('010008','P07975',1,0,1050.00),('019001','P00001',4,4,525.00),('019001','P07885',2,1,5250.00),('019001','P07965',2,1,8400.00),('019002','P00001',10,0,525.00),('019003','P00001',4,4,1050.00),('019003','P03453',2,2,1050.00),('046865','P07868',3,3,3150.00),('046865','P07885',10,10,5250.00),('046866','P06734',1,1,12000.00),('046866','P07965',1,0,8400.00);
/*!40000 ALTER TABLE `sales_order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesman_master`
--

DROP TABLE IF EXISTS `salesman_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesman_master` (
  `Salesman_no` varchar(6) NOT NULL,
  `Sal_name` varchar(20) NOT NULL,
  `Address` text,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `PinCode` int DEFAULT NULL,
  `Sal_amt` decimal(8,2) NOT NULL,
  `Tgt_to_get` decimal(6,2) NOT NULL,
  `Ytd_sales` decimal(6,2) NOT NULL,
  `Remark` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Salesman_no`),
  UNIQUE KEY `uniQuestion` (`Salesman_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesman_master`
--

LOCK TABLES `salesman_master` WRITE;
/*!40000 ALTER TABLE `salesman_master` DISABLE KEYS */;
INSERT INTO `salesman_master` VALUES ('500001','Kiran','A/14 Worli','Bombay','Mah',400002,3000.00,100.00,50.00,'Good'),('500002','Manish','65 Nariman','Bombay','Mah',400001,3000.00,200.00,100.00,'Good'),('500003','Ravi','P-7 Bandra','Bombay','Mah',400032,3000.00,200.00,100.00,'Good'),('500004','Ashish','A/5 Juhu','Bombay','Mah',400044,3500.00,200.00,150.00,'Good');
/*!40000 ALTER TABLE `salesman_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `salesmater_view_1`
--

DROP TABLE IF EXISTS `salesmater_view_1`;
/*!50001 DROP VIEW IF EXISTS `salesmater_view_1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `salesmater_view_1` AS SELECT 
 1 AS `Salesman_no`,
 1 AS `Sal_name`,
 1 AS `Address`,
 1 AS `City`,
 1 AS `State`,
 1 AS `PinCode`,
 1 AS `Sal_amt`,
 1 AS `Tgt_to_get`,
 1 AS `Ytd_sales`,
 1 AS `Remark`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'mca_lab_test'
--

--
-- Dumping routines for database 'mca_lab_test'
--

--
-- Final view structure for view `salesmater_view_1`
--

/*!50001 DROP VIEW IF EXISTS `salesmater_view_1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `salesmater_view_1` AS select `salesman_master`.`Salesman_no` AS `Salesman_no`,`salesman_master`.`Sal_name` AS `Sal_name`,`salesman_master`.`Address` AS `Address`,`salesman_master`.`City` AS `City`,`salesman_master`.`State` AS `State`,`salesman_master`.`PinCode` AS `PinCode`,`salesman_master`.`Sal_amt` AS `Sal_amt`,`salesman_master`.`Tgt_to_get` AS `Tgt_to_get`,`salesman_master`.`Ytd_sales` AS `Ytd_sales`,`salesman_master`.`Remark` AS `Remark` from `salesman_master` where (`salesman_master`.`Sal_amt` < 3500) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-19 23:20:54

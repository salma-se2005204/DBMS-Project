-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: project
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
-- Table structure for table `attribute_metadata`
--

DROP TABLE IF EXISTS `attribute_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_metadata` (
  `TableName` varchar(255) DEFAULT NULL,
  `AttributeName` varchar(255) DEFAULT NULL,
  `DataType` varchar(255) DEFAULT NULL,
  `KeyType` varchar(255) DEFAULT NULL,
  `IsUnique` varchar(255) DEFAULT NULL,
  `Sorted` varchar(255) DEFAULT NULL,
  `TypeOfIndex` varchar(255) DEFAULT NULL,
  `DistinctValues` int DEFAULT NULL,
  `Nullable` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_metadata`
--

LOCK TABLES `attribute_metadata` WRITE;
/*!40000 ALTER TABLE `attribute_metadata` DISABLE KEYS */;
INSERT INTO `attribute_metadata` VALUES ('dept','Dcode','INT','PK','unique','yes','PI',36,'not null'),('dept','DName','VARCHAR','PK','unique','no','SI',36,'not null'),('dept','DOffice','VARCHAR','null','no','no','null',30,'null'),('dept','DPhone','VARCHAR','null','no','no','null',30,'null'),('dept','DstarDate','Date','null','no','no','null',30,'not null'),('dept','CollegeCName','VARCHAR','FK','no','no','null',30,'not null'),('dept','InstructorId','INT','FK','no','no','null',30,'not null'),('Course','CCourse','INT','PK','unique','yes','PI',60,'not null'),('Course','CoName','VARCHAR','PK','unique','no','PI',60,'not null'),('Course','Credits','INT','null','no','no','null',6,'null'),('Course','Level','INT','null','no','no','null',4,'null'),('Course','CDesc','VARCHAR','null','no','no','null',60,'null'),('Course','DeptDName','VARCHAR','FK','unique','no','null',36,'not null'),('Course','DeptDCode','INT','FK','unique','no','null',36,'not null');
/*!40000 ALTER TABLE `attribute_metadata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 10:01:37

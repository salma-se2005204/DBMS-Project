-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
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
-- Table structure for table `metadata`
--

DROP TABLE IF EXISTS `metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metadata` (
  `TableName` varchar(255) DEFAULT NULL,
  `OrderofColumn` int DEFAULT NULL,
  `KeyType` varchar(255) DEFAULT NULL,
  `ColumnName` varchar(255) DEFAULT NULL,
  `DataType` varchar(255) DEFAULT NULL,
  `Nullable` varchar(255) DEFAULT NULL,
  `Describtion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metadata`
--

LOCK TABLES `metadata` WRITE;
/*!40000 ALTER TABLE `metadata` DISABLE KEYS */;
INSERT INTO `metadata` VALUES ('dept',1,'SI','DName','VARCHAR','NOT NULL','the department’s name'),('dept',2,'PKI','DCode','INT','NOT NULL','the department’s code'),('dept',3,'null','DOffice','VARCHAR','NULL','the department’s office'),('dept',4,'null','DPhone','VARCHAR','NULL','the department’s phone'),('dept',5,'null','DStarDate','DATE','NOT NULL','the department’s start date'),('dept',6,'null','CollegeCName','VARCHAR','NOT NULL','the department’s college name'),('dept',7,'null','InstructorId','INT','NOT NULL','the department’s instructor'),('Course',1,'PKI','CCode','INT','NOT NULL','the course code'),('Course',2,'null','CoName','VARCHAR','NOT NULL','the course name'),('Course',3,'null','Credits','INT','NOT NULL','the course credits'),('Course',4,'null','Level','INT','NOT NULL','the course’s level'),('Course',5,'null','CDesc','VARCHAR','NULL','the course’s description'),('Course',6,'CI','DeptDName','VARCHAR','NOT NULL','the course dept name'),('Course',7,'null','CoName','VARCHAR','NOT NULL','the course name');
/*!40000 ALTER TABLE `metadata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 22:08:56

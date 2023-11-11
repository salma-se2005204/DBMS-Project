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
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `DName` varchar(255) NOT NULL,
  `DCode` int NOT NULL,
  `DOffice` varchar(255) DEFAULT NULL,
  `DPhone` varchar(255) DEFAULT NULL,
  `CStartDate` date NOT NULL,
  `CollegeCName` varchar(255) NOT NULL,
  `InstructorId` int DEFAULT NULL,
  PRIMARY KEY (`DName`,`DCode`),
  UNIQUE KEY `primary_idx_DCode` (`DCode`),
  KEY `CollegeCName` (`CollegeCName`),
  KEY `InstructorId` (`InstructorId`),
  KEY `secondary_idx_DName` (`DName`),
  CONSTRAINT `dept_ibfk_1` FOREIGN KEY (`CollegeCName`) REFERENCES `college` (`CName`),
  CONSTRAINT `dept_ibfk_2` FOREIGN KEY (`InstructorId`) REFERENCES `instructor` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES ('Art',300,'Arts Building, Room 300','(555) 555-1234','1888-08-10','Liberal Arts College',1),('Business Administration',400,'Business Building, Office 301','(555) 123-4567','2023-08-25','Business College',NULL),('Computer Engineering',500,'Engineering Building Room 204','(555) 123-4567','1990-01-01','College of Engineering',4),('Computer Science',200,'Engineering Building, Room 203','(555) 123-4567','1990-01-01','College of Engineering',3),('Instrumental Department',600,'Musical Building, Room 204','(555) 123-4567','1990-01-01','Music Academy College',5),('Nursing',100,'Health Building, Suite 33','(666) 321-6789','1890-09-05','Health and Wellness College',2);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
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

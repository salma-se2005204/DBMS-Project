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
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `Id` int NOT NULL,
  `IRank` int DEFAULT NULL,
  `IName` varchar(255) DEFAULT NULL,
  `IPhone` varchar(255) DEFAULT NULL,
  `IOfficee` varchar(255) DEFAULT NULL,
  `CollegeCname` varchar(255) DEFAULT NULL,
  `DeptDCode` int NOT NULL,
  `DeptDName` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `DeptDName` (`DeptDName`,`DeptDCode`),
  KEY `CollegeCname` (`CollegeCname`),
  CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`DeptDName`, `DeptDCode`) REFERENCES `dept` (`DName`, `DCode`),
  CONSTRAINT `instructor_ibfk_2` FOREIGN KEY (`CollegeCname`) REFERENCES `college` (`CName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,1,'Omar Ahmed','(999) 4444 1234','(666) 123-4567','College of Engineering',200,'Computer Science'),(2,2,'Noora Mohammed','(999) 4444 1234','(666) 123-4567','Health and Wellness College',100,'Nursing'),(3,3,'Saad Abdulla','(999) 4444 1234','(666) 123-4567','Liberal Arts College',300,'Art'),(4,4,'Mariam Nasser','(999) 4444 1234','(666) 123-4567','College of Engineering',500,'Computer Engineering'),(5,4,'Noor Kareem','(999) 4444 1234','(666) 123-4567','Music Academy College',600,'Instrumental Department');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 22:08:55

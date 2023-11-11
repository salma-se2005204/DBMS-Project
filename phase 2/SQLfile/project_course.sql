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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `CCode` int NOT NULL,
  `CoName` varchar(255) NOT NULL,
  `Credits` int DEFAULT NULL,
  `Level` int DEFAULT NULL,
  `CDesc` varchar(255) DEFAULT NULL,
  `DeptDName` varchar(255) NOT NULL,
  `DeptDCode` int NOT NULL,
  PRIMARY KEY (`CCode`,`CoName`),
  UNIQUE KEY `primary_idx_CCode` (`CCode`),
  KEY `DeptDName` (`DeptDName`,`DeptDCode`),
  KEY `secondary_idx_CoName` (`CoName`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`DeptDName`, `DeptDCode`) REFERENCES `dept` (`DName`, `DCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (119,'Programming Concepts',3,1,'Programming Concepts course is a foundation for programming','Computer Engineering',500),(155,'Public Health',3,1,'related to public health principles, epidemiology, health policy, and disease prevention','Nursing',100),(255,'OOP',3,1,'OOP is a programming paradigm that is widely used in software development','Computer Science',200),(355,'Art History',3,2,'focuses on the history of art, art movements, and the critical analysis of art','Art',300),(455,' Marketing and Sales',3,4,'marketing strategies, market research, product development, branding','Business Administration',400);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
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

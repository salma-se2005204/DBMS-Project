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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `SId` int NOT NULL,
  `MName` varchar(255) DEFAULT NULL,
  `FName` varchar(255) DEFAULT NULL,
  `LName` varchar(255) DEFAULT NULL,
  `Addr` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Major` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `DeptDName` varchar(255) DEFAULT NULL,
  `DeptDCode` int DEFAULT NULL,
  PRIMARY KEY (`SId`),
  KEY `DeptDName` (`DeptDName`,`DeptDCode`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`DeptDName`, `DeptDCode`) REFERENCES `dept` (`DName`, `DCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Abdullah','Hissa','AL Muhannadi',NULL,'(666) 123-4567','Computer Science','2001-12-02','Computer Science',200),(2,'Muhanad','Aya ','Hassan',NULL,'(666) 123-4567','Computer Science','2001-04-01','Computer Science',200),(3,'Shady','Salma','Eletreby',NULL,'(666) 123-4567','Computer Science','2000-06-01','Computer Science',200),(4,'Ahmed','Asma','Al Mannai',NULL,'(666) 123-4567','Medical-Surgical Nursing','1999-07-01','Nursing',100),(5,'Abdullah','Shiekha','Al kuwari',NULL,'(666) 123-4567','Fine Arts','1998-09-14','Art',300);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
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

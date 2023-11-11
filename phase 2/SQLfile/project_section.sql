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
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `SecId` int NOT NULL,
  `SecNo` int DEFAULT NULL,
  `Sem` varchar(255) DEFAULT NULL,
  `Year` int DEFAULT NULL,
  `RoomNo` int DEFAULT NULL,
  `DaysTime` int DEFAULT NULL,
  `Bldg` varchar(255) DEFAULT NULL,
  `CourseCCode` int NOT NULL,
  `CourseCoName` varchar(255) NOT NULL,
  `InstructorId` int NOT NULL,
  PRIMARY KEY (`SecId`),
  KEY `CourseCCode` (`CourseCCode`,`CourseCoName`),
  KEY `InstructorId` (`InstructorId`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`CourseCCode`, `CourseCoName`) REFERENCES `course` (`CCode`, `CoName`),
  CONSTRAINT `section_ibfk_2` FOREIGN KEY (`InstructorId`) REFERENCES `instructor` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (10,101,'Fall',2023,203,101,'Science Hall',155,'Public Health',2),(20,201,'Spring',2023,305,100,'Engineering Hall',255,'OOP',1),(30,301,'Fall',2022,405,99,'Art Hall',355,'Art History',3),(40,401,'Spring',2022,405,100,'Engineering Hall',119,'Programming Concepts',4),(50,501,'Spring',2023,505,100,'Engineering Hall',255,'OOP',4);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 22:08:54

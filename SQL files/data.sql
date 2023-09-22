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
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college` (
  `CName` varchar(255) NOT NULL,
  `COffice` varchar(255) DEFAULT NULL,
  `CPhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES ('College of Engineering','789 Engineering Avenue  Room 101','(666) 123-4567'),('Health and Wellness College','567 Wellness Center Suite 33','(666) 123-4567'),('Liberal Arts College','123 Main Street Suite 400','(666) 123-4567');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

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
  KEY `DeptDName` (`DeptDName`,`DeptDCode`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`DeptDName`, `DeptDCode`) REFERENCES `dept` (`DName`, `DCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (155,'Public Health',3,1,'related to public health principles, epidemiology, health policy, and disease prevention','Nursing',100),(255,'OOP',3,1,'OOP is a programming paradigm that is widely used in software development','Computer Science',200),(355,'Art History',3,2,'focuses on the history of art, art movements, and the critical analysis of art','Art',300);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

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
  KEY `CollegeCName` (`CollegeCName`),
  KEY `InstructorId` (`InstructorId`),
  CONSTRAINT `dept_ibfk_1` FOREIGN KEY (`CollegeCName`) REFERENCES `college` (`CName`),
  CONSTRAINT `dept_ibfk_2` FOREIGN KEY (`InstructorId`) REFERENCES `instructor` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES ('Art',300,'Arts Building, Room 300','(555) 555-1234','1888-08-10','Liberal Arts College',1),('Computer Science',200,'Engineering Building, Room 203','(555) 123-4567','1990-01-01','College of Engineering',3),('Nursing',100,'Health Building, Suite 33','(666) 321-6789','1890-09-05','Health and Wellness College',2);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `instructor` VALUES (1,1,'Omar Ahmed','(999) 4444 1234','(666) 123-4567','College of Engineering',200,'Computer Science'),(2,2,'Noora Mohammed','(999) 4444 1234','(666) 123-4567','Health and Wellness College',100,'Nursing'),(3,3,'Saad Abdulla','(999) 4444 1234','(666) 123-4567','Liberal Arts College',300,'Art');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `section` VALUES (10,101,'Fall',2023,203,101,'Science Hall',155,'Public Health',2),(20,201,'Spring',2023,305,100,'Engineering Hall',255,'OOP',1),(30,301,'Fall',2022,405,99,'Art Hall',355,'Art History',3);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `student_section`
--

DROP TABLE IF EXISTS `student_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_section` (
  `SecId` int NOT NULL,
  `Grade` int DEFAULT NULL,
  `StudentStd` int NOT NULL,
  PRIMARY KEY (`SecId`,`StudentStd`),
  KEY `StudentStd` (`StudentStd`),
  CONSTRAINT `student_section_ibfk_1` FOREIGN KEY (`SecId`) REFERENCES `section` (`SecId`),
  CONSTRAINT `student_section_ibfk_2` FOREIGN KEY (`StudentStd`) REFERENCES `student` (`SId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_section`
--

LOCK TABLES `student_section` WRITE;
/*!40000 ALTER TABLE `student_section` DISABLE KEYS */;
INSERT INTO `student_section` VALUES (10,3,4),(20,4,1),(30,2,5);
/*!40000 ALTER TABLE `student_section` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-22 10:43:01

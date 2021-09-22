-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: beauty_saloon
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointmentid` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `masterId` int NOT NULL,
  `serviceId` int NOT NULL,
  `timestamp` timestamp NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `paymentStatus` varchar(10) NOT NULL,
  `status` varchar(45) NOT NULL,
  `emailNeeded` varchar(45) NOT NULL,
  PRIMARY KEY (`appointmentid`),
  UNIQUE KEY `timestamp_UNIQUE` (`timestamp`),
  KEY `userId_idx` (`userId`),
  KEY `masterId_idx` (`masterId`),
  KEY `serviceId_idx` (`serviceId`),
  CONSTRAINT `masterId` FOREIGN KEY (`masterId`) REFERENCES `masters` (`masterid`),
  CONSTRAINT `serviceId` FOREIGN KEY (`serviceId`) REFERENCES `services` (`serviceid`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (13,1,1,1,'2021-09-02 14:00:00','testing','payed','done','false'),(14,1,1,1,'2021-09-03 09:00:00','Тестовое сообщение','payed','done','false'),(15,1,1,1,'2021-09-04 06:00:00',NULL,'waiting','waiting','true'),(19,1,2,2,'2021-09-04 13:00:00',NULL,'false','waiting','true'),(20,1,1,4,'2021-09-17 11:00:00','Спасибо!','payed','done','false'),(169,1,2,2,'2021-09-22 09:00:00',NULL,'false','waiting','true'),(231,1,2,2,'2021-09-24 08:00:00',NULL,'false','waiting','true'),(239,1,2,2,'2021-09-23 12:00:00',NULL,'false','waiting','true');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-22 16:17:26

-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: doctorcare
-- ------------------------------------------------------
-- Server version	8.0.28

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

CREATE DATABASE doctorcare;
use  doctorcare;
--
-- Table structure for table `clinics`
--

DROP TABLE IF EXISTS `clinics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `introductionHTML` text COLLATE utf8mb4_unicode_ci,
  `introductionMarkdown` text COLLATE utf8mb4_unicode_ci,
  `description` text COLLATE utf8mb4_unicode_ci,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinics`
--

LOCK TABLES `clinics` WRITE;
/*!40000 ALTER TABLE `clinics` DISABLE KEYS */;
INSERT INTO `clinics` VALUES (1,'HealthyCare Clinic','123 Health Street, Cityville','123-456-7890','<p>Welcome to HealthyCare Clinic!</p>','Welcome to HealthyCare Clinic!','We provide comprehensive healthcare services.','clinic1.jpg','2023-08-01 09:00:00'),(2,'Wellness Clinic','456 Wellness Avenue, Townsville','987-654-3210','<p>Discover a new level of wellness at our clinic.</p>','Discover a new level of wellness at our clinic.','We focus on promoting holistic well-being.','clinic2.jpg','2023-08-02 10:00:00'),(3,'MediCure Clinic','789 Medical Road, Healthcity','555-123-4567','<p>Quality healthcare for all.</p>','Quality healthcare for all.','We provide top-notch medical care.','clinic3.jpg','2023-08-03 11:00:00'),(4,'Family Health Center','101 Family Lane, Caretown','111-222-3333','<p>Your family\'s health is our priority.</p>','Your family\'s health is our priority.','We specialize in family-centered healthcare.','clinic4.jpg','2023-08-04 12:00:00'),(5,'WellMed Clinic','222 Wellness Boulevard, Healville','888-999-0000','<p>Leading the way to wellness.</p>','Leading the way to wellness.','We focus on preventive and integrative medicine.','clinic5.jpg','2023-08-05 13:00:00');
/*!40000 ALTER TABLE `clinics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_users`
--

DROP TABLE IF EXISTS `doctor_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctorId` int DEFAULT NULL,
  `clinicId` int DEFAULT NULL,
  `specializationId` int DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deletedAt` datetime DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `doctorId` (`doctorId`),
  KEY `clinicId` (`clinicId`),
  KEY `specializationId` (`specializationId`),
  CONSTRAINT `doctor_users_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `users` (`id`),
  CONSTRAINT `doctor_users_ibfk_2` FOREIGN KEY (`clinicId`) REFERENCES `clinics` (`id`),
  CONSTRAINT `doctor_users_ibfk_3` FOREIGN KEY (`specializationId`) REFERENCES `specializations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_users`
--

LOCK TABLES `doctor_users` WRITE;
/*!40000 ALTER TABLE `doctor_users` DISABLE KEYS */;
INSERT INTO `doctor_users` VALUES (4,4,4,4,'2023-08-04 12:00:00','2023-08-04 12:00:00','2023-09-02 12:07:08',NULL),(5,5,5,5,'2023-08-05 13:00:00','2023-08-05 13:00:00',NULL,NULL),(6,6,1,6,'2023-08-06 14:00:00','2023-08-06 14:00:00',NULL,NULL),(7,7,2,7,'2023-08-07 15:00:00','2023-08-07 15:00:00',NULL,NULL),(8,13,NULL,3,NULL,NULL,NULL,'Good, Chưa được hơn 100 bệnh nhân ung thư, Đại học y dược');
/*!40000 ALTER TABLE `doctor_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctorId` int DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `doctorId` (`doctorId`),
  KEY `statusId` (`statusId`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `doctor_users` (`id`),
  CONSTRAINT `patients_ibfk_2` FOREIGN KEY (`statusId`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (13,4,3,NULL,2);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `places` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contenMarkdown` text COLLATE utf8mb4_unicode_ci,
  `contentHtml` text COLLATE utf8mb4_unicode_ci,
  `forDoctorId` int DEFAULT NULL,
  `forSpecializationId` int DEFAULT NULL,
  `forClinicId` int DEFAULT NULL,
  `confirmByDoctor` int DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `forDoctorId` (`forDoctorId`),
  KEY `forSpecializationId` (`forSpecializationId`),
  KEY `forClinicId` (`forClinicId`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`forDoctorId`) REFERENCES `doctor_users` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`forSpecializationId`) REFERENCES `specializations` (`id`),
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`forClinicId`) REFERENCES `clinics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN',NULL,NULL,NULL),(2,'USER',NULL,NULL,NULL),(3,'DOCTOR',NULL,NULL,NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctorId` int DEFAULT NULL,
  `date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maxBooking` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sumBooking` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `cancel` int DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `doctorId` (`doctorId`),
  KEY `fk1_user` (`userID`),
  CONSTRAINT `fk1_user` FOREIGN KEY (`userID`) REFERENCES `users` (`id`),
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES (1,1,'2023-08-01','09:00-10:00','10','5','2023-08-01 09:00:00','2023-08-01 09:00:00',NULL,NULL,0,NULL),(2,2,'2023-08-02','10:00-11:00','8','2','2023-08-02 10:00:00','2023-08-02 10:00:00',NULL,NULL,0,NULL),(3,3,'2023-08-03','11:00-12:00','12','8','2023-08-03 11:00:00','2023-08-03 11:00:00',NULL,NULL,0,NULL),(4,4,'2023-08-04','12:00-13:00','15','12','2023-08-04 12:00:00','2023-08-04 12:00:00',NULL,2,2,NULL),(5,5,'2023-08-05','13:00-14:00','10','4','2023-08-05 13:00:00','2023-08-05 13:00:00',NULL,NULL,0,NULL),(6,6,'2023-08-06','14:00-15:00','8','6','2023-08-06 14:00:00','2023-08-06 14:00:00',NULL,NULL,0,NULL),(7,7,'2023-08-07','15:00-16:00','12','9','2023-08-07 15:00:00','2023-08-07 15:00:00',NULL,NULL,0,NULL),(8,1,NULL,'từ 9 giờ đến 11 giờ',NULL,'3000','2023-08-25 10:26:13',NULL,NULL,3,0,NULL);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specializations`
--

DROP TABLE IF EXISTS `specializations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specializations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `creatAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specializations`
--

LOCK TABLES `specializations` WRITE;
/*!40000 ALTER TABLE `specializations` DISABLE KEYS */;
INSERT INTO `specializations` VALUES (1,'Cardiology','Specializing in heart diseases','cardiology.jpg','2023-08-01 08:00:00','2023-08-01 08:00:00',NULL),(2,'Dermatology','Dealing with skin disorders','dermatology.jpg','2023-08-02 09:00:00','2023-08-02 09:00:00',NULL),(3,'Orthopedics','Focusing on musculoskeletal issues','orthopedics.jpg','2023-08-03 10:00:00','2023-08-03 10:00:00',NULL),(4,'Ophthalmology','Specializing in eye diseases','ophthalmology.jpg','2023-08-04 11:00:00','2023-08-04 11:00:00',NULL),(5,'Neurology','Dealing with nervous system disorders','neurology.jpg','2023-08-05 12:00:00','2023-08-05 12:00:00',NULL),(6,'Pediatrics','Focusing on children\'s health','pediatrics.jpg','2023-08-06 13:00:00','2023-08-06 13:00:00',NULL),(7,'Gynecology','Specializing in women\'s reproductive health','gynecology.jpg','2023-08-07 14:00:00','2023-08-07 14:00:00',NULL);
/*!40000 ALTER TABLE `specializations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleteAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (1,'Cancelled','2023-08-25 17:13:18','2023-08-25 17:13:18',NULL),(2,'Inactive','2023-08-25 17:13:26','2023-08-25 17:13:26',NULL),(3,'Pending','2023-08-25 17:13:30','2023-08-25 17:13:30',NULL),(4,'Completed','2023-08-25 17:13:33','2023-08-25 17:13:33',NULL),(5,'Cancelled','2023-08-25 17:13:35','2023-08-25 17:13:35',NULL);
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `roleId` int DEFAULT NULL,
  `isActive` int DEFAULT NULL,
  `createdAt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updateAt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleteAt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patientId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patientId` (`patientId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `fk_patient` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Linda','linda@gmail.com','$2a$10$uMgdBnE8S5N8rTTUB.eub.rtO5qYGvDSspYxa4.xnsEF7TXuZTaHO','2 Trương Định','321312312',NULL,'Nam',NULL,2,0,'20/08/2023',NULL,NULL,NULL),(2,'TESTER','an.lennon2014@gmail.com','$2a$10$i/vXpBWEvfrxxqphACRQ5.q.kw237du7rZCkUNn6hiDUXnTW5C8rm','Số 2 Lê Lợi','234324',NULL,'Male',NULL,2,0,'21/08/2023',NULL,NULL,NULL),(3,'TESTER','an@gmail.com','$2a$10$a8UcRQIOA1cldU799HqHwuh1mnX9isW2vhkyLRHAUr7m41EY4JoqC','Số 2 Lê Lợi','234324',NULL,'Male',NULL,2,0,'21/08/2023',NULL,NULL,NULL),(4,'John Doe','john@example.com','$2a$10$KCqLCDpdlQzPw4DVgV0DTey63Pw3WonIRxfMXeNKuGrsPMkpxGNnq','123 Main Street','555-123-4567','avatar1.jpg','Male','Description 1',3,1,'2023-08-01 08:00:00','2023-08-01 08:00:00',NULL,NULL),(5,'Jane Smith','jane@example.com','hashed_password','456 Elm Street','555-987-6543','avatar2.jpg','Female','Description 2',3,1,'2023-08-02 09:00:00','2023-08-02 09:00:00',NULL,NULL),(6,'Michael Johnson','michael@example.com','hashed_password','789 Oak Avenue','555-555-1234','avatar3.jpg','Male','Description 3',3,1,'2023-08-03 10:00:00','2023-08-03 10:00:00',NULL,NULL),(7,'Emily Williams','emily@example.com','hashed_password','101 Maple Drive','555-222-3333','avatar4.jpg','Female','Description 4',3,1,'2023-08-04 11:00:00','2023-08-04 11:00:00',NULL,NULL),(8,'David Brown','david@example.com','hashed_password','222 Pine Road','555-888-9999','avatar5.jpg','Male','Description 5',3,1,'2023-08-05 12:00:00','2023-08-05 12:00:00',NULL,NULL),(9,'Jessica Taylor','jessica@example.com','hashed_password','333 Cedar Street','555-777-5555','avatar6.jpg','Female','Description 6',3,1,'2023-08-06 13:00:00','2023-08-06 13:00:00',NULL,NULL),(10,'William Wilson','william@example.com','hashed_password','444 Birch Lane','555-444-4444','avatar7.jpg','Male','Description 7',3,1,'2023-08-07 14:00:00','2023-08-07 14:00:00',NULL,NULL),(11,'NewDoctor','newDoctor@email.com','$2a$10$YYzy2YVWh7eaaI9NZEBqEO3wmsJtzd3HAFoQwcJM8hX4dys/vDj2y','Số 2 Lê Lợi','234324',NULL,'Male',NULL,3,0,'27/08/2023',NULL,NULL,NULL),(12,'NewAdmin','NewAdmin@email.com','$2a$10$zin7Jk09YyR.T1GfxlC4xe343NMtkePmpjBZSOM3RH87sgKURI8N6','Số 112 Lê Lợi','234324',NULL,'Male',NULL,1,0,'01/09/2023',NULL,NULL,NULL),(13,'NewDoctor','anahfx21237@funix.edu.vn','$2a$10$kvUH9qCTPrgVTbWmOm1IOesas07hwunvCd.hpsZNiqL0GQp0pOW5.','Số 1125 Lê Lợi','234324',NULL,'Male',NULL,3,0,'02/09/2023',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 19:24:07

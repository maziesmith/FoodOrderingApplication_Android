-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: webserviceData
-- ------------------------------------------------------
-- Server version	5.6.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!50001 DROP VIEW IF EXISTS `detail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `detail` AS SELECT 
 1 AS `iditem`,
 1 AS `name`,
 1 AS `ordernumber`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `idorder` int(11) NOT NULL AUTO_INCREMENT,
  `customer` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`idorder`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'unknown','unknown'),(2,'unknown','unknown'),(3,'unknown','unknown'),(4,'unknown','unknown'),(5,'unknown','unknown'),(6,'unknown','unknown'),(7,'unknown','unknown'),(8,'unknown','unknown'),(9,'unknown','unknown'),(10,'unknown','unknown'),(11,'unknown','unknown'),(12,'unknown','unknown'),(13,'unknown','unknown'),(14,'unknown','unknown'),(15,'unknown','unknown'),(16,'unknown','unknown'),(17,'unknown','unknown'),(18,'unknown','unknown'),(19,'unknown','unknown'),(20,'unknown','unknown'),(21,'unknown','unknown'),(22,'unknown','unknown'),(23,'unknown','unknown'),(24,'unknown','unknown'),(25,'unknown','unknown'),(26,'unknown','unknown'),(27,'unknown','unknown'),(28,'unknown','unknown'),(29,'unknown','unknown'),(30,'unknown','unknown'),(31,'unknown','unknown'),(32,'unknown','unknown'),(33,'unknown','unknown'),(34,'unknown','unknown'),(35,'unknown','unknown'),(36,'unknown','unknown'),(37,'unknown','unknown'),(38,'unknown','unknown'),(39,'unknown','unknown'),(40,'unknown','unknown'),(41,'unknown','unknown'),(42,'unknown','unknown'),(43,'unknown','unknown'),(44,'unknown','unknown'),(45,'unknown','unknown'),(46,'unknown','unknown'),(47,'unknown','unknown'),(48,'unknown','unknown'),(49,'unknown','unknown'),(50,'unknown','unknown'),(51,'unknown','unknown'),(52,'unknown','unknown'),(53,'unknown','unknown');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `iditem` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(45) DEFAULT NULL,
  `ordernumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iditem`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'2','9'),(2,'3','9'),(3,'2','10'),(4,'3','10'),(5,'2','11'),(6,'1','12'),(7,'2','13'),(8,'3','13'),(9,'2','14'),(10,'3','14'),(11,'2','15'),(12,'1','16'),(13,'2','17'),(14,'1','22'),(15,NULL,'0'),(16,'1','0'),(17,'1','0'),(18,'1','0'),(19,'1','0'),(20,'1','0'),(23,'2','30'),(24,'1','31'),(25,'2','31'),(27,'3','32'),(28,'1','33'),(29,'1','34'),(31,'2','35'),(33,'2','36'),(34,'1','37'),(35,'2','37'),(36,'1','38'),(37,'1','39'),(38,'1','40'),(39,'1','41'),(40,'1','42'),(41,'1','43'),(42,'1','44'),(43,'1','45'),(44,'1','46'),(45,'2','47'),(46,'3','48'),(47,'2','49'),(48,'2','50'),(49,'2','51'),(50,'2','52'),(51,'2','53');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'pizza',1),(2,'momo',2),(3,'burger',3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `detail`
--

/*!50001 DROP VIEW IF EXISTS `detail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detail` AS select `item`.`iditem` AS `iditem`,`product`.`name` AS `name`,`item`.`ordernumber` AS `ordernumber` from (`product` join `item` on((`product`.`idproduct` = `item`.`product`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-03 17:57:01

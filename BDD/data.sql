-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gedesaft
-- ------------------------------------------------------
-- Server version	5.6.37-log

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
-- Dumping data for table `affaires`
--

LOCK TABLES `affaires` WRITE;
/*!40000 ALTER TABLE `affaires` DISABLE KEYS */;
/*!40000 ALTER TABLE `affaires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `agentaffecte`
--

LOCK TABLES `agentaffecte` WRITE;
/*!40000 ALTER TABLE `agentaffecte` DISABLE KEYS */;
/*!40000 ALTER TABLE `agentaffecte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `agents`
--

LOCK TABLES `agents` WRITE;
/*!40000 ALTER TABLE `agents` DISABLE KEYS */;
/*!40000 ALTER TABLE `agents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `armeimpliquees`
--

LOCK TABLES `armeimpliquees` WRITE;
/*!40000 ALTER TABLE `armeimpliquees` DISABLE KEYS */;
/*!40000 ALTER TABLE `armeimpliquees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `armes`
--

LOCK TABLES `armes` WRITE;
/*!40000 ALTER TABLE `armes` DISABLE KEYS */;
/*!40000 ALTER TABLE `armes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personnes`
--

LOCK TABLES `personnes` WRITE;
/*!40000 ALTER TABLE `personnes` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `suspect`
--

LOCK TABLES `suspect` WRITE;
/*!40000 ALTER TABLE `suspect` DISABLE KEYS */;
/*!40000 ALTER TABLE `suspect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `suspectimpliques`
--

LOCK TABLES `suspectimpliques` WRITE;
/*!40000 ALTER TABLE `suspectimpliques` DISABLE KEYS */;
/*!40000 ALTER TABLE `suspectimpliques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `temoins`
--

LOCK TABLES `temoins` WRITE;
/*!40000 ALTER TABLE `temoins` DISABLE KEYS */;
/*!40000 ALTER TABLE `temoins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `temoinsimpliques`
--

LOCK TABLES `temoinsimpliques` WRITE;
/*!40000 ALTER TABLE `temoinsimpliques` DISABLE KEYS */;
/*!40000 ALTER TABLE `temoinsimpliques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicules`
--

LOCK TABLES `vehicules` WRITE;
/*!40000 ALTER TABLE `vehicules` DISABLE KEYS */;
INSERT INTO `vehicules` VALUES (1,'voiture','Mercedes','Classe A','THG-666-FD','rouge',NULL);
/*!40000 ALTER TABLE `vehicules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehiculesimpliques`
--

LOCK TABLES `vehiculesimpliques` WRITE;
/*!40000 ALTER TABLE `vehiculesimpliques` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculesimpliques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `victimes`
--

LOCK TABLES `victimes` WRITE;
/*!40000 ALTER TABLE `victimes` DISABLE KEYS */;
/*!40000 ALTER TABLE `victimes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `victimesimpliques`
--

LOCK TABLES `victimesimpliques` WRITE;
/*!40000 ALTER TABLE `victimesimpliques` DISABLE KEYS */;
/*!40000 ALTER TABLE `victimesimpliques` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-29 17:05:03

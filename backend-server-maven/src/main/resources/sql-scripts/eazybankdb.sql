CREATE DATABASE  IF NOT EXISTS `eazybank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eazybank`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eazybank
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `account_transactions`
--

DROP TABLE IF EXISTS `account_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_transactions` (
  `transaction_id` varchar(200) NOT NULL,
  `account_number` int NOT NULL,
  `customer_id` int NOT NULL,
  `transaction_dt` date NOT NULL,
  `transaction_summary` varchar(200) NOT NULL,
  `transaction_type` varchar(100) NOT NULL,
  `transaction_amt` int NOT NULL,
  `closing_balance` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `customer_id` (`customer_id`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
  CONSTRAINT `acct_user_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_transactions`
--

LOCK TABLES `account_transactions` WRITE;
/*!40000 ALTER TABLE `account_transactions` DISABLE KEYS */;
INSERT INTO `account_transactions` VALUES ('200b5685-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-16','Coffee Shop','Withdrawal',30,34500,'2024-05-16'),('2014353c-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-17','Uber','Withdrawal',100,34400,'2024-05-17'),('201d609b-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-18','Self Deposit','Deposit',500,34900,'2024-05-18'),('20236fc8-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-19','Ebay','Withdrawal',600,34300,'2024-05-19'),('2028052f-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-21','OnlineTransfer','Deposit',700,35000,'2024-05-21'),('202fa532-18e7-11ef-ba5a-0242ac110002',1865764534,1,'2024-05-22','Amazon.com','Withdrawal',100,34900,'2024-05-22');
/*!40000 ALTER TABLE `account_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `customer_id` int NOT NULL,
  `account_number` int NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1865764534,'Savings','123 Main Street, New York','2024-05-23');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `authorities_ibf_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (5,1,'ROLE_USER'),(6,1,'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) NOT NULL,
  `customer_id` int NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `amount_used` int NOT NULL,
  `available_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,'4565XXXX4656',1,'Credit',10000,500,9500,'2024-05-23'),(2,'3455XXXX8673',1,'Credit',7500,600,6900,'2024-05-23'),(3,'2359XXXX9346',1,'Credit',20000,4000,16000,'2024-05-23');
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_messages`
--

DROP TABLE IF EXISTS `contact_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_messages` (
  `contact_id` varchar(50) NOT NULL,
  `contact_name` varchar(50) NOT NULL,
  `contact_email` varchar(100) NOT NULL,
  `subject` varchar(500) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_messages`
--

LOCK TABLES `contact_messages` WRITE;
/*!40000 ALTER TABLE `contact_messages` DISABLE KEYS */;
INSERT INTO `contact_messages` VALUES ('SR15519149','sfsaas ','assads@gmai.com','asd','asdsa','2024-06-18'),('SR361436921','sfsaas ','assads@remail.com','asfsasadsa','sdfsada','2024-08-19'),('SR699554793','Teste','sfsadsa@afafa.com','fafaf','asdad','2024-06-18'),('SR788581048','asfasdsa','asdsad@wrafa.com','asdsaddsfaf','assaas','2024-06-18'),('SR818795927','Test','zfafafa@fafaf.com','asfsaasf','safsafsafsa','2024-06-18');
/*!40000 ALTER TABLE `contact_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Happy','happy@example.com','9876548337','$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2','admin','2024-05-23'),(2,'John Doe','john@example.com','456747484','$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2','user','2024-05-24');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `loan_number` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `start_dt` date NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`loan_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,1,'2020-10-13','Home',200000,50000,150000,'2020-10-13'),(2,1,'2020-06-06','Vehicle',40000,10000,30000,'2020-06-06'),(3,1,'2018-02-14','Home',50000,10000,40000,'2018-02-14'),(4,1,'2018-02-14','Personal',10000,3500,6500,'2018-02-14');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_details`
--

DROP TABLE IF EXISTS `notice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice_details` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `notice_summary` varchar(200) NOT NULL,
  `notice_details` varchar(500) NOT NULL,
  `notic_beg_dt` date NOT NULL,
  `notic_end_dt` date DEFAULT NULL,
  `create_dt` date DEFAULT NULL,
  `update_dt` date DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_details`
--

LOCK TABLES `notice_details` WRITE;
/*!40000 ALTER TABLE `notice_details` DISABLE KEYS */;
INSERT INTO `notice_details` VALUES (1,'Home Loan Interest rates reduced','Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately','2024-04-23','2024-06-22','2024-05-23',NULL),(2,'Net Banking Offers','Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher','2024-04-23','2024-06-22','2024-05-23',NULL),(3,'Mobile App Downtime','The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities','2024-04-23','2024-06-22','2024-05-23',NULL),(4,'E Auction notice','There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction','2024-04-23','2024-06-22','2024-05-23',NULL),(5,'Launch of Millennia Cards','Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase','2024-04-23','2024-06-22','2024-05-23',NULL),(6,'COVID-19 Insurance','EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details','2024-04-23','2024-06-22','2024-05-23',NULL);
/*!40000 ALTER TABLE `notice_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth2_registered_client`
--

DROP TABLE IF EXISTS `oauth2_registered_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth2_registered_client` (
  `id` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(255) DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(255) NOT NULL,
  `client_authentication_methods` varchar(1000) NOT NULL,
  `authorization_grant_types` varchar(1000) NOT NULL,
  `redirect_uris` varchar(1000) DEFAULT NULL,
  `scopes` varchar(1000) NOT NULL,
  `client_settings` varchar(2000) NOT NULL,
  `token_settings` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth2_registered_client`
--

LOCK TABLES `oauth2_registered_client` WRITE;
/*!40000 ALTER TABLE `oauth2_registered_client` DISABLE KEYS */;
INSERT INTO `oauth2_registered_client` VALUES ('1','oidc-client','2024-08-09 08:33:46',NULL,NULL,'OIDC Client','none','authorization_code','http://localhost:4200/login','openid','{\"requireAuthorizationConsent\":false,\"requireProofKey\":true}','{\"accessTokenTimeToLive\":\"PT30M\",\"refreshTokenTimeToLive\":\"P30D\",\"reuseRefreshTokens\":true}');
/*!40000 ALTER TABLE `oauth2_registered_client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-19 17:22:34

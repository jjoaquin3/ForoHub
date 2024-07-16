CREATE SCHEMA IF NOT EXISTS FOROHUB_DB;
USE FOROHUB_DB;

--
-- Table structure for table `USER`
--
CREATE TABLE IF NOT EXISTS `USER`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` VARCHAR(64) DEFAULT NULL,
  `username` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `COURSE`
--
CREATE TABLE IF NOT EXISTS `COURSE`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `category` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `TOPIC`
--
CREATE TABLE IF NOT EXISTS `TOPIC`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `creation_date` datetime(6) NOT NULL,
  `message` text NOT NULL,
  `status` varchar(64) NOT NULL,
  `title` varchar(512) NOT NULL,
  `course` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  FOREIGN KEY (`course`) REFERENCES `COURSE` (`id`),
  FOREIGN KEY (`user`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `REPLY`
--
CREATE TABLE IF NOT EXISTS `REPLY`
(
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `creation_date` datetime(6) NOT NULL,
  `message` text NOT NULL,
  `topic` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  FOREIGN KEY (`user`) REFERENCES `USER` (`id`),
  FOREIGN KEY (`topic`) REFERENCES `TOPIC` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;






-- phpMyAdmin SQL Dump
-- version 4.3.11.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 20, 2016 at 04:23 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `galaktika`
--

-- --------------------------------------------------------

--
-- Table structure for table `fleets`
--

CREATE TABLE IF NOT EXISTS `fleets` (
  `fleetId` bigint(20) NOT NULL,
  `name` varchar(32) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `turnId` bigint(20) NOT NULL,
  `nationId` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `nation_nationId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `fleets`
--

INSERT INTO `fleets` (`fleetId`, `name`, `turnId`, `nationId`, `deleted`, `nation_nationId`) VALUES
(1, 'fleetas', 0, 0, b'0', NULL),
(2, 'fleetas2', 0, 2, b'0', NULL),
(3, 'Žalgiris', 0, 0, b'0', NULL),
(4, 'Lietuvos Rytas', 0, 2, b'0', NULL),
(5, 'Atletas', 0, 0, b'0', NULL),
(6, 'Nev?žis', 0, 2, b'1', NULL),
(7, 'Pieva', 0, 0, b'0', NULL),
(9, 'Pieva', 0, 0, b'0', NULL),
(10, 'Kelias', 0, 2, b'0', NULL),
(11, 'Upė', 0, 0, b'0', NULL),
(12, 'lankos', 0, 2, b'0', NULL),
(13, 'namai', 0, 0, b'0', NULL),
(14, 'ąžuolai', 0, 2, b'1', NULL),
(15, 'grybai', 0, 0, b'0', NULL),
(16, 'liepos', 0, 2, b'1', NULL),
(17, 'karklai', 0, 0, b'0', NULL),
(18, 'kandikai', 0, 2, b'1', NULL),
(19, 'Naujas fleetas', 0, 0, b'0', NULL),
(20, NULL, 0, 2, b'1', NULL),
(21, 'fleetas', 0, 0, b'1', NULL),
(22, 'krokodilai', 0, 0, b'0', NULL),
(23, 'krokodilai', 0, 0, b'0', NULL),
(24, 'katini?zas', 0, 0, b'0', NULL),
(25, 'ggg', 0, 0, b'0', NULL),
(26, 'ggg', 0, 0, b'0', NULL),
(27, 'fleetas2', 0, 0, b'0', NULL),
(28, 'fleetas2', 0, 0, b'0', NULL),
(29, 'katinynas', 0, 0, b'0', NULL),
(30, 'ggg', 0, 0, b'0', NULL),
(31, 'gugas', 0, 2, b'1', NULL),
(32, 'Dzibuhacikai', 0, 2, b'1', NULL),
(33, 'kietiakai', 0, 2, b'0', NULL),
(34, 'asdf', 0, 2, b'0', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fleets`
--
ALTER TABLE `fleets`
  ADD PRIMARY KEY (`fleetId`), ADD KEY `FK_hr20w8546l6qygpjao899u4kv` (`nation_nationId`), ADD KEY `FK_mvad931elbiih1so82c9o7xvo` (`nationId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fleets`
--
ALTER TABLE `fleets`
  MODIFY `fleetId` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `fleets`
--
ALTER TABLE `fleets`
ADD CONSTRAINT `FK_hr20w8546l6qygpjao899u4kv` FOREIGN KEY (`nation_nationId`) REFERENCES `nation` (`nationId`),
ADD CONSTRAINT `FK_mvad931elbiih1so82c9o7xvo` FOREIGN KEY (`nationId`) REFERENCES `nation` (`nationId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

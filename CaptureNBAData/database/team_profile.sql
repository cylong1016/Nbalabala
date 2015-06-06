-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2015-06-06 16:34:18
-- 服务器版本: 5.5.43-0ubuntu0.14.04.1
-- PHP 版本: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `nbalabala`
--

-- --------------------------------------------------------

--
-- 表的结构 `team_profile`
--

CREATE TABLE IF NOT EXISTS `team_profile` (
  `abbr` char(3) NOT NULL COMMENT '球队缩写',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `location` varchar(30) DEFAULT NULL COMMENT '所在地',
  `league` char(1) DEFAULT NULL COMMENT '球队所属联盟，E为东部，W为西部',
  `division` varchar(30) DEFAULT NULL COMMENT '所在赛区',
  `home` varchar(40) DEFAULT NULL COMMENT '主场',
  `since` smallint(4) DEFAULT NULL COMMENT '建队时间，形如1970',
  PRIMARY KEY (`abbr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球队资料';

--
-- 转存表中的数据 `team_profile`
--

INSERT INTO `team_profile` (`abbr`, `name`, `location`, `league`, `division`, `home`, `since`) VALUES
('ATL', 'Hawks		', 'Atlanta', 'E', 'Southeast', 'Philips Arena', 1949),
('BKN', 'Nets		', 'Brooklyn', 'E', 'Atlantic', 'Barclays Center', 1976),
('BOS', 'Celtics	', 'Boston', 'W', 'Atlantic', 'TD Garden', 1946),
('CHA', 'Hornets	', 'Charlotte', 'E', 'Southeast', 'Time Warner Cable Arena', 1988),
('CHI', 'Bulls		', 'Chicago', 'E', 'Central', 'United Center', 1966),
('CLE', 'Cavaliers	', 'Cleveland', 'E', 'Central', 'Quicken Loans Arena', 1970),
('DAL', 'Mavericks	', 'Dallas', 'W', 'Southwest', 'American Airlines Center', 1980),
('DEN', 'Nuggets	', 'Denver', 'W', 'Northwest', 'Pepsi Center', 1976),
('DET', 'Pistons	', 'Detroit', 'E', 'Central', 'The Palace of Auburn Hills', 1948),
('GSW', 'Warriors	', 'Golden State', 'W', 'Pacific', 'Oracle Arena', 1946),
('HOU', 'Rockets	', 'Houston', 'W', 'Southwest', 'Toyota Center', 1967),
('IND', 'Pacers		', 'Indiana', 'E', 'Central', 'Bankers Life Fieldhouse', 1976),
('LAC', 'Clippers	', 'Los Angeles', 'W', 'Pacific', 'STAPLES Center', 1970),
('LAL', 'Lakers		', 'Los Angeles', 'W', 'Pacific', 'STAPLES Center', 1948),
('MEM', 'Grizzlies	', 'Memphis', 'W', 'Southwest', 'FedEx Forum', 1995),
('MIA', 'Heat		', 'Miami', 'E', 'Southeast', 'AmericanAirlines Arena', 1988),
('MIL', 'Bucks		', 'Milwaukee', 'E', 'Central', 'BMO Harris Bradley Center', 1968),
('MIN', 'Timberwolves	', 'Minnesota', 'W', 'Northwest', 'Target Center', 1989),
('NOP', 'Pelicans	', 'New Orleans', 'W', 'Southwest', 'Smoothie King Arena', 2002),
('NYK', 'Knicks		', 'New York', 'E', 'Atlantic', 'Madison Square Garden (IV)', 1946),
('OKC', 'Thunder	', 'Oklahoma City', 'W', 'Northwest', 'Chesapeake Energy Arena', 1967),
('ORL', 'Magic		', 'Orlando', 'E', 'Southeast', 'Amway Center', 1989),
('PHI', '76ers		', 'Philadelphia', 'E', 'Atlantic', 'Wells Fargo Center', 1949),
('PHX', 'Suns		', 'Phoenix', 'W', 'Pacific', 'US Airways Center', 1968),
('POR', 'Trail Blazers	', 'Portland', 'W', 'Northwest', 'Moda Center', 1970),
('SAC', 'Kings		', 'Sacramento', 'W', 'Pacific', 'Sleep Train Arena', 1948),
('SAS', 'Spurs		', 'San Antonio', 'W', 'Southwest', 'AT&T Center', 1976),
('TOR', 'Raptors	', 'Toronto', 'E', 'Atlantic', 'Air Canada Centre', 1995),
('UTA', 'Jazz		', 'Utah', 'W', 'Northwest', 'EnergySolutions Arena', 1974),
('WAS', 'Wizards	', 'Washington', 'E', 'Southeast', 'Verizon Center', 1961);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

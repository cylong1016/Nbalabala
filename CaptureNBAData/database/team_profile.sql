/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbalabala

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-06-02 21:18:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `team_profile`
-- ----------------------------
DROP TABLE IF EXISTS `team_profile`;
CREATE TABLE `team_profile` (
  `abbr` char(3) NOT NULL COMMENT '球队缩写',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `location` varchar(30) DEFAULT NULL COMMENT '所在地',
  `league` char(1) DEFAULT NULL COMMENT '球队所属联盟，E为东部，W为西部',
  `division` varchar(30) DEFAULT NULL COMMENT '所在赛区',
  `home` varchar(40) DEFAULT NULL COMMENT '主场',
  `since` smallint(4) DEFAULT NULL COMMENT '建队时间，形如1970',
  PRIMARY KEY (`abbr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球队资料';

-- ----------------------------
-- Records of team_profile
-- ----------------------------
INSERT INTO `team_profile` VALUES ('ATL', 'Hawks		', 'Atlanta', 'E', 'Southeast', 'Philips Arena', '1949');
INSERT INTO `team_profile` VALUES ('BKN', 'Nets		', 'Brooklyn', 'E', 'Atlantic', 'Barclays Center', '1976');
INSERT INTO `team_profile` VALUES ('BOS', 'Celtics	', 'Boston', 'W', 'Atlantic', 'TD Garden', '1946');
INSERT INTO `team_profile` VALUES ('CHA', 'Hornets	', 'Charlotte', 'E', 'Southeast', 'Time Warner Cable Arena', '1988');
INSERT INTO `team_profile` VALUES ('CHI', 'Bulls		', 'Chicago', 'E', 'Central', 'United Center', '1966');
INSERT INTO `team_profile` VALUES ('CLE', 'Cavaliers	', 'Cleveland', 'E', 'Central', 'Quicken Loans Arena', '1970');
INSERT INTO `team_profile` VALUES ('DAL', 'Mavericks	', 'Dallas', 'W', 'Southwest', 'American Airlines Center', '1980');
INSERT INTO `team_profile` VALUES ('DEN', 'Nuggets	', 'Denver', 'W', 'Northwest', 'Pepsi Center', '1976');
INSERT INTO `team_profile` VALUES ('DET', 'Pistons	', 'Detroit', 'E', 'Central', 'The Palace of Auburn Hills', '1948');
INSERT INTO `team_profile` VALUES ('GSW', 'Warriors	', 'Golden State', 'W', 'Pacific', 'Oracle Arena', '1946');
INSERT INTO `team_profile` VALUES ('HOU', 'Rockets	', 'Houston', 'W', 'Southwest', 'Toyota Center', '1967');
INSERT INTO `team_profile` VALUES ('IND', 'Pacers		', 'Indiana', 'E', 'Central', 'Bankers Life Fieldhouse', '1976');
INSERT INTO `team_profile` VALUES ('LAC', 'Clippers	', 'Los Angeles', 'W', 'Pacific', 'STAPLES Center', '1970');
INSERT INTO `team_profile` VALUES ('LAL', 'Lakers		', 'Los Angeles', 'W', 'Pacific', 'STAPLES Center', '1948');
INSERT INTO `team_profile` VALUES ('MEM', 'Grizzlies	', 'Memphis', 'W', 'Southwest', 'FedEx Forum', '1995');
INSERT INTO `team_profile` VALUES ('MIA', 'Heat		', 'Miami', 'E', 'Southeast', 'AmericanAirlines Arena', '1988');
INSERT INTO `team_profile` VALUES ('MIL', 'Bucks		', 'Milwaukee', 'E', 'Central', 'BMO Harris Bradley Center', '1968');
INSERT INTO `team_profile` VALUES ('MIN', 'Timberwolves	', 'Minnesota', 'W', 'Northwest', 'Target Center', '1989');
INSERT INTO `team_profile` VALUES ('NOP', 'Pelicans	', 'New Orleans', 'W', 'Southwest', 'Smoothie King Arena', '2002');
INSERT INTO `team_profile` VALUES ('NYK', 'Knicks		', 'New York', 'E', 'Atlantic', 'Madison Square Garden (IV)', '1946');
INSERT INTO `team_profile` VALUES ('OKC', 'Thunder	', 'Oklahoma City', 'W', 'Northwest', 'Chesapeake Energy Arena', '1967');
INSERT INTO `team_profile` VALUES ('ORL', 'Magic		', 'Orlando', 'E', 'Southeast', 'Amway Center', '1989');
INSERT INTO `team_profile` VALUES ('PHI', '76ers		', 'Philadelphia', 'E', 'Atlantic', 'Wells Fargo Center', '1949');
INSERT INTO `team_profile` VALUES ('PHX', 'Suns		', 'Phoenix', 'W', 'Pacific', 'US Airways Center', '1968');
INSERT INTO `team_profile` VALUES ('POR', 'Trail Blazers	', 'Portland', 'W', 'Northwest', 'Moda Center', '1970');
INSERT INTO `team_profile` VALUES ('SAC', 'Kings		', 'Sacramento', 'W', 'Pacific', 'Sleep Train Arena', '1948');
INSERT INTO `team_profile` VALUES ('SAS', 'Spurs		', 'San Antonio', 'W', 'Southwest', 'AT&T Center', '1976');
INSERT INTO `team_profile` VALUES ('TOR', 'Raptors	', 'Toronto', 'E', 'Atlantic', 'Air Canada Centre', '1995');
INSERT INTO `team_profile` VALUES ('UTA', 'Jazz		', 'Utah', 'W', 'Northwest', 'EnergySolutions Arena', '1974');
INSERT INTO `team_profile` VALUES ('WAS', 'Wizards	', 'Washington', 'E', 'Southeast', 'Verizon Center', '1961');

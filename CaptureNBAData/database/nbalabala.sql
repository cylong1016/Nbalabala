-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2015-06-03 13:13:20
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
-- 表的结构 `extra_time`
--

CREATE TABLE IF NOT EXISTS `extra_time` (
  `match_id` int(8) NOT NULL COMMENT '比赛ID',
  `extra_order` tinyint(2) NOT NULL COMMENT '加时赛序号[第几场加时赛]',
  `road_score` tinyint(2) NOT NULL COMMENT '客场得分',
  `home_score` tinyint(2) NOT NULL COMMENT '主场得分',
  PRIMARY KEY (`extra_order`,`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加时赛';

-- --------------------------------------------------------

--
-- 表的结构 `match_player`
--

CREATE TABLE IF NOT EXISTS `match_player` (
  `match_id` int(8) NOT NULL COMMENT '比赛ID',
  `player_name` varchar(60) NOT NULL COMMENT '球员名',
  `home_or_road` char(1) NOT NULL COMMENT 'H:主场球员、R客场球员',
  `team_abbr` char(3) DEFAULT NULL COMMENT '本场比赛所在球队缩写',
  `is_starter` tinyint(1) NOT NULL COMMENT '是否先发：1是、0否',
  `time_played` varchar(6) NOT NULL COMMENT '在场时间，形如25:59',
  `field_made` smallint(6) NOT NULL COMMENT '进球数【包括3分球，不包括罚球】',
  `field_attempt` smallint(6) NOT NULL COMMENT '总投球数',
  `field_percent` float DEFAULT NULL COMMENT '投篮命中率',
  `threepoint_made` smallint(6) NOT NULL COMMENT '3分进球数',
  `threepoint_attempt` smallint(6) NOT NULL COMMENT '3分投篮数',
  `threepoint_percent` float DEFAULT NULL COMMENT '3分命中率',
  `freethrow_made` smallint(6) NOT NULL COMMENT '罚球进球数',
  `freethrow_attempt` smallint(6) NOT NULL COMMENT '罚球出手数',
  `freethrow_percent` float DEFAULT NULL COMMENT '罚球命中率',
  `offensive_rebound` smallint(6) NOT NULL COMMENT '进攻篮板',
  `defensive_rebound` smallint(6) NOT NULL COMMENT '防守篮板',
  `total_rebound` smallint(6) NOT NULL COMMENT '总篮板',
  `assist` smallint(6) NOT NULL COMMENT '助攻',
  `steal` smallint(6) NOT NULL COMMENT '抢断',
  `block` smallint(6) NOT NULL COMMENT '盖帽',
  `turnover` smallint(6) NOT NULL COMMENT '失误',
  `foul` smallint(6) NOT NULL COMMENT '犯规',
  `score` smallint(6) NOT NULL COMMENT '得分',
  `plus_minus` smallint(6) DEFAULT NULL COMMENT '球员在场时间内，球队净得分',
  PRIMARY KEY (`match_id`,`player_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `match_profile`
--

CREATE TABLE IF NOT EXISTS `match_profile` (
  `match_id` int(8) NOT NULL COMMENT '比赛ID',
  `season` char(8) DEFAULT NULL COMMENT '赛季，常规赛R结尾，季后赛P结尾，如2013-14R，1998-99P',
  `date` date DEFAULT NULL COMMENT '比赛日期',
  `road_abbr` char(3) NOT NULL COMMENT '客场',
  `home_abbr` char(3) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '主场',
  `section` tinyint(2) NOT NULL DEFAULT '4' COMMENT '小节数',
  `home_total_score` smallint(6) DEFAULT NULL COMMENT '主场总分',
  `road_total_score` smallint(6) DEFAULT NULL COMMENT '客场总分',
  `road_section_1` int(11) DEFAULT NULL COMMENT '客场第一节分数',
  `road_section_2` tinyint(4) DEFAULT NULL COMMENT '客场第二节分数',
  `road_section_3` tinyint(4) DEFAULT NULL COMMENT '客场第三节分数',
  `road_section_4` tinyint(4) DEFAULT NULL COMMENT '客场第四节分数',
  `home_section_1` tinyint(4) DEFAULT NULL COMMENT '主场第一节分数',
  `home_section_2` tinyint(4) DEFAULT NULL COMMENT '主场第二节分数',
  `home_section_3` tinyint(4) DEFAULT NULL COMMENT '主场第三节分数',
  `home_section_4` tinyint(4) DEFAULT NULL COMMENT '主场第四节分数',
  PRIMARY KEY (`match_id`),
  UNIQUE KEY `gameID` (`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球队赛季数据';

-- --------------------------------------------------------

--
-- 表的结构 `player_profile`
--

CREATE TABLE IF NOT EXISTS `player_profile` (
  `name` varchar(60) NOT NULL COMMENT '球员名，形如John Thomas01\n如果有同名的，形如John Thomas02',
  `from_year` smallint(4) DEFAULT NULL COMMENT '加入NBA年份',
  `to_year` smallint(4) DEFAULT NULL COMMENT '最后一次参赛年份',
  `position` varchar(5) DEFAULT NULL COMMENT '位置，形如C或C-F',
  `height_foot` tinyint(4) DEFAULT NULL COMMENT '身高英尺数',
  `height_inch` tinyint(4) DEFAULT NULL COMMENT '身高英寸数',
  `weight` smallint(6) DEFAULT NULL COMMENT '体重（磅）',
  `birth_date` date DEFAULT NULL COMMENT '生日',
  `school` varchar(80) DEFAULT NULL COMMENT '毕业学校',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球员资料';

-- --------------------------------------------------------

--
-- 表的结构 `player_season`
--

CREATE TABLE IF NOT EXISTS `player_season` (
  `name` varchar(60) NOT NULL,
  `season` char(8) NOT NULL COMMENT '赛季，如2014-15R表示2014-2015赛季常规赛。1998-99P表示1998-1999赛季季后赛',
  `latest_match_id` int(8) DEFAULT NULL COMMENT '最近一场比赛的id(用于查询热点球员)',
  `latest_match_date` date DEFAULT NULL COMMENT '最近一场比赛的日期(用于查询热点球员)',
  `team_abbr` char(3) DEFAULT NULL COMMENT '球队缩写',
  `match_count` tinyint(3) unsigned DEFAULT NULL COMMENT '比赛场数',
  `first_count` tinyint(3) unsigned DEFAULT NULL COMMENT '首发场数',
  `first_count_avg` float DEFAULT NULL COMMENT '场均首发',
  `minutes` float DEFAULT NULL COMMENT '参赛总分钟数',
  `minutes_avg` float DEFAULT NULL COMMENT '场均上场分钟数',
  `field_made` smallint(4) DEFAULT NULL COMMENT '投篮命中',
  `field_made_avg` float DEFAULT NULL COMMENT '场均投篮命中',
  `field_attempt` smallint(4) DEFAULT NULL COMMENT '投篮出手',
  `field_attempt_avg` float DEFAULT NULL COMMENT '场均投篮出手',
  `field_percent` float DEFAULT NULL COMMENT '投篮命中率',
  `threepoint_made` smallint(4) DEFAULT NULL COMMENT '三分命中',
  `threepoint_made_avg` float DEFAULT NULL,
  `threepoint_attempt` smallint(4) DEFAULT NULL,
  `threepoint_attempt_avg` float DEFAULT NULL,
  `threepoint_percent` float DEFAULT NULL,
  `freethrow_made` smallint(4) DEFAULT NULL COMMENT '罚球命中',
  `freethrow_made_avg` float DEFAULT NULL,
  `freethrow_attempt` smallint(4) DEFAULT NULL,
  `freethrow_attempt_avg` float DEFAULT NULL,
  `freethrow_percent` float DEFAULT NULL,
  `offensive_rebound` smallint(4) DEFAULT NULL COMMENT '进攻篮板',
  `offensive_rebound_avg` float DEFAULT NULL,
  `defensive_rebound` smallint(4) DEFAULT NULL COMMENT '防守篮板',
  `defensive_rebound_avg` float DEFAULT NULL,
  `total_rebound` smallint(4) DEFAULT NULL COMMENT '总篮板',
  `total_rebound_avg` float DEFAULT NULL,
  `assist` smallint(4) DEFAULT NULL COMMENT '助攻',
  `assist_avg` float DEFAULT NULL,
  `steal` smallint(4) DEFAULT NULL COMMENT '抢断',
  `steal_avg` float DEFAULT NULL,
  `block` smallint(4) DEFAULT NULL COMMENT '盖帽',
  `block_avg` float DEFAULT NULL,
  `turnover` smallint(4) DEFAULT NULL COMMENT '失误',
  `turnover_avg` float DEFAULT NULL,
  `foul` smallint(4) DEFAULT NULL COMMENT '犯规',
  `foul_avg` float DEFAULT NULL,
  `score` smallint(4) DEFAULT NULL COMMENT '得分',
  `score_avg` float DEFAULT NULL,
  `double_double` smallint(4) DEFAULT NULL COMMENT '两双',
  `double_double_avg` float DEFAULT NULL,
  `efficiency` smallint(5) DEFAULT NULL COMMENT '效率',
  `efficiency_avg` float DEFAULT NULL,
  `score_rebound_assist` smallint(5) DEFAULT NULL COMMENT '得分+篮板+助攻',
  `score_rebound_assist_avg` float DEFAULT NULL,
  `gmsc` float DEFAULT NULL COMMENT 'gmsc效率值',
  `gmsc_avg` float DEFAULT NULL,
  `real_field_percent` float DEFAULT NULL COMMENT '真实命中率',
  `field_eff` float DEFAULT NULL COMMENT '投篮效率',
  `offensive_rebound_percent` float DEFAULT NULL COMMENT '进攻篮板效率',
  `defensive_rebound_percent` float DEFAULT NULL,
  `total_rebound_percent` float DEFAULT NULL,
  `steal_percent` float DEFAULT NULL COMMENT '抢断率',
  `block_percent` float DEFAULT NULL,
  `turnover_percent` float DEFAULT NULL,
  `foul_percent` float DEFAULT NULL,
  `use_percent` float DEFAULT NULL COMMENT '使用率',
  `assist_percent` float DEFAULT NULL COMMENT '助攻率',
  `position` varchar(5) DEFAULT NULL COMMENT '位置，形如C 或者C-F',
  PRIMARY KEY (`name`,`season`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录球员每个赛季的各种数据，key为球员名和赛季';

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

-- --------------------------------------------------------

--
-- 表的结构 `team_season`
--

CREATE TABLE IF NOT EXISTS `team_season` (
  `abbr` char(3) NOT NULL COMMENT '球队缩写',
  `season` char(8) NOT NULL COMMENT '赛季，如2014-15R表示2014-2015赛季常规赛。1998-99P表示1998-1999赛季季后赛',
  `match_count` tinyint(3) unsigned DEFAULT NULL COMMENT '比赛场数',
  `wins` tinyint(3) unsigned DEFAULT NULL COMMENT '胜利场数',
  `winning` float DEFAULT NULL COMMENT '胜率',
  `field_made` smallint(4) DEFAULT NULL COMMENT '投篮命中',
  `field_made_avg` float DEFAULT NULL COMMENT '场均投篮命中',
  `field_attempt` smallint(4) DEFAULT NULL COMMENT '投篮出手',
  `field_attempt_avg` float DEFAULT NULL COMMENT '场均投篮出手',
  `field_percent` float DEFAULT NULL COMMENT '投篮命中率',
  `threepoint_made` smallint(4) DEFAULT NULL COMMENT '三分命中',
  `threepoint_made_avg` float DEFAULT NULL,
  `threepoint_attempt` smallint(4) DEFAULT NULL,
  `threepoint_attempt_avg` float DEFAULT NULL,
  `threepoint_percent` float DEFAULT NULL,
  `freethrow_made` smallint(4) DEFAULT NULL COMMENT '罚球命中',
  `freethrow_made_avg` float DEFAULT NULL,
  `freethrow_attempt` smallint(4) DEFAULT NULL,
  `freethrow_attempt_avg` float DEFAULT NULL,
  `freethrow_percent` float DEFAULT NULL,
  `offensive_rebound` smallint(4) DEFAULT NULL COMMENT '进攻篮板',
  `offensive_rebound_avg` float DEFAULT NULL,
  `defensive_rebound` smallint(4) DEFAULT NULL COMMENT '防守篮板',
  `defensive_rebound_avg` float DEFAULT NULL,
  `total_rebound` smallint(4) DEFAULT NULL COMMENT '总篮板',
  `total_rebound_avg` float DEFAULT NULL,
  `assist` smallint(4) DEFAULT NULL COMMENT '助攻',
  `assist_avg` float DEFAULT NULL,
  `steal` smallint(4) DEFAULT NULL COMMENT '抢断',
  `steal_avg` float DEFAULT NULL,
  `block` smallint(4) DEFAULT NULL COMMENT '盖帽',
  `block_avg` float DEFAULT NULL,
  `turnover` smallint(4) DEFAULT NULL COMMENT '失误',
  `turnover_avg` float DEFAULT NULL,
  `foul` smallint(4) DEFAULT NULL COMMENT '犯规',
  `foul_avg` float DEFAULT NULL,
  `score` smallint(4) DEFAULT NULL COMMENT '得分',
  `score_avg` float DEFAULT NULL,
  `offensive_round` float DEFAULT NULL COMMENT '总进攻回合数',
  `offensive_round_avg` float DEFAULT NULL COMMENT '场均进攻回合数',
  `defensive_round` float DEFAULT NULL COMMENT '总防守回合数',
  `defensive_round_avg` float DEFAULT NULL COMMENT '场均防守回合数',
  `offensive_eff` float DEFAULT NULL COMMENT '进攻效率',
  `defensive_eff` float DEFAULT NULL COMMENT '防守效率',
  `assist_eff` float DEFAULT NULL COMMENT '助攻效率',
  `steal_eff` float DEFAULT NULL COMMENT '抢断效率',
  `oppo_field_percent` float DEFAULT NULL COMMENT '对手投篮命中率',
  `oppo_score_avg` float DEFAULT NULL COMMENT '对手场均得分',
  PRIMARY KEY (`abbr`,`season`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录球队每个赛季的各种数据，key为球队缩写和赛季';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

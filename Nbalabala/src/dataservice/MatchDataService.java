package dataservice;

import java.sql.Date;
import java.util.ArrayList;

import po.MatchDetailPO;
import po.MatchPlayerPO;
import po.MatchProfilePO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:16:00
 */
public interface MatchDataService {

	/** 根据日期返回某天的比赛详情 */
	public ArrayList<MatchDetailPO> getMatchDetailByDate(Date date);
	
	/** 返回两天之间的全部的比赛详情 */
	public ArrayList<MatchDetailPO> getMatchDetailByDates(Date start, Date end);
	
	/** 返回一个赛季的全部的常规赛或季后赛比赛详情。如2013-14R将返回13-14赛季常规赛的全部比赛 */
	public ArrayList<MatchDetailPO> getMatchDetailBySeason(String season);
	
	/** 根据参赛队伍返回比赛详情 */
	public ArrayList<MatchDetailPO> getMatchDetailByTeam(String roadAbbr, String homeAbbr, String season);
	
	/** 根据队伍缩写返回和赛季比赛简报，abbr形如SAS */
	public ArrayList<MatchProfilePO> getMatchProfileByTeamAndSeason(String abbr,String season);

	/** 通过比赛ID返回详情 */
	public MatchDetailPO getMatchDetailByMatchID(int matchID);
	
	/** 通过比赛ID和球员名字返回这场比赛他的表现情况 */
	public MatchPlayerPO getPlayerMatchRecordByNameAndID(String matchID, String playerName);

	/** 通过运动员名字和赛季返回其该赛季全部比赛记录 */
	public ArrayList<MatchPlayerPO> getMatchRecordByPlayerName(
			String playerName, String season);
	
	/** 返回球员参加过的全部比赛，从最近赛季开始 */
	public ArrayList<MatchPlayerPO> getPlayerCareerMatches(String name);

}

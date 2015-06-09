package bl.matchquerybl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import po.MatchDetailPO;
import po.MatchPlayerPO;
import po.MatchProfilePO;
import po.TeamSeasonPO;
import utility.Constants;
import vo.LiveRowVO;
import vo.MatchDetailVO;
import vo.MatchProfileVO;
import bl.teamquerybl.TeamQuery;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.MatchQueryBLService;
import data.matchdata.MatchData;
import data.teamdata.TeamLogoCache;
import dataservice.MatchDataService;

/**
 * 负责查询比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:33:13
 */
public class MatchQuery implements MatchQueryBLService{
	
	private MatchDataService matchData = new MatchData();
	
	private ArrayList<MatchDetailVO> getVOsByPOs(ArrayList<MatchDetailPO> pos) {
		ArrayList<MatchDetailVO> result = new ArrayList<MatchDetailVO>();
		if (pos == null) return result;
		for (MatchDetailPO po : pos) {
			MatchProfilePO profile = po.getMatchProfile();
			result.add(new MatchDetailVO(po, TeamLogoCache.getTeamLogo(profile.homeAbbr),
					TeamLogoCache.getTeamLogo(profile.roadAbbr)));
		}
		return result;
	}
	
	/**
	 * @see blservice.MatchQueryBLService#screenMatchByDate(java.util.Date)
	 */
	@Override
	public ArrayList<MatchDetailVO> screenMatchByDate(Date date) {
		ArrayList<MatchDetailPO> pos = matchData.getMatchDetailByDate(new java.sql.Date(date.getTime()));
		return getVOsByPOs(pos);
	}
	
	/**
	 * @see blservice.MatchQueryBLService#screenMatchByTeam(java.lang.String, java.lang.String)
	 */
	@Override 
	public ArrayList<MatchDetailVO> screenMatchByTeam(String abbr1, String abbr2) {
		ArrayList<MatchDetailPO> list1 = matchData.getMatchDetailByTeam(abbr1,abbr2,Constants.LATEST_SEASON);
		ArrayList<MatchDetailPO> list2 = matchData.getMatchDetailByTeam(abbr2,abbr1,Constants.LATEST_SEASON);
		list2.removeAll(list1);
		list1.addAll(list2);	//先取出两队的季后赛交锋(如果有的话)
		ArrayList<MatchDetailPO> list3 = matchData.getMatchDetailByTeam(abbr1,abbr2,Constants.LATEST_SEASON_REGULAR);
		ArrayList<MatchDetailPO> list4 = matchData.getMatchDetailByTeam(abbr2,abbr1,Constants.LATEST_SEASON_REGULAR);
		list4.removeAll(list3);
		list3.addAll(list4);	//再取出两队的常规赛交锋(如果有的话)
		list1.addAll(list3);
		// 考虑到黄蜂、鹈鹕、篮网队改名的问题
//		if (abbr1.equals("NOP")) {
//			ArrayList<MatchDetailPO> list3 = matchData.getMatchDetailByTeam("NOH",abbr2);
//			list3.removeAll(list1);
//			list1.addAll(list3);
//		}else if (abbr1.equals("BKN")) {
//			ArrayList<MatchDetailPO> list4 = matchData.getMatchDetailByTeam("NJN",abbr2);
//			list4.removeAll(list1);
//			list1.addAll(list4);
//		}
//		if (abbr2.equals("NOP")) {
//			ArrayList<MatchDetailPO> list3 = matchData.getMatchDetailByTeam(abbr1,"NOH");
//			list3.removeAll(list1);
//			list1.addAll(list3);
//		}else if (abbr2.equals("BKN")) {
//			ArrayList<MatchDetailPO> list4 = matchData.getMatchDetailByTeam(abbr1,"NJN");
//			list4.removeAll(list1);
//			list1.addAll(list4);
//		}
		return getVOsByPOs(list1);
	}
	
//	private ArrayList<MatchDetailVO> getMatchDetailByProfile(ArrayList<MatchProfileVO> profileVOs) {
//		ArrayList<MatchDetailVO> result = new ArrayList<MatchDetailVO>();
//		for (MatchProfileVO profileVO : profileVOs) {
//			String[] teams = profileVO.getTeam().split("-");
//			result.add(getMatchDetail(profileVO.getSeason(), profileVO.getTime(), 
//					teams[0], teams[1]));
//		}
//		return result;
//	}
	
	/** 根据球员名字返回其所有比赛记录 */
	public ArrayList<MatchPlayerPO> getMatchRecordByPlayerName(String playerName, String season) {
		return matchData.getMatchRecordByPlayerName(playerName, season);
	}
	
	/** 根据球队缩写返回其参加的所有比赛简报 */
	public ArrayList<MatchProfileVO> getMatchRecordByTeamAbbrAndSeason(String abbr, String season){
		ArrayList<MatchProfileVO> result = new ArrayList<MatchProfileVO>();
		ArrayList<MatchProfilePO> pos = matchData.getMatchProfileByTeamAndSeason(abbr, season);
		for (MatchProfilePO po : pos) {
			result.add(new MatchProfileVO(po));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.MatchQueryBLService#getTeamRamkByAbbr(java.lang.String)
	 */
	@Override
	public int getTeamRamkByAbbr(String abbr) {
		TeamQuery teamQuery = new TeamQuery();
		return teamQuery.getRanks(abbr, Constants.LATEST_SEASON_REGULAR)[0];
	}

	/* (non-Javadoc)
	 * @see blservice.MatchQueryBLService#getTeamWinsLosesByAbbr(java.lang.String)
	 */
	@Override
	public int[] getTeamWinsLosesByAbbr(String abbr) {
		TeamSeasonAnalysis seasonAnalysis = new TeamSeasonAnalysis();
		TeamSeasonPO seasonVO = seasonAnalysis.getTeamDataByAbbr(abbr, Constants.LATEST_SEASON);
		if (seasonVO == null) {
			seasonVO =  seasonAnalysis.getTeamDataByAbbr(abbr, Constants.LATEST_SEASON_REGULAR);
		}
		return new int[] {seasonVO.getWins(), seasonVO.getMatchCount() - seasonVO.getWins()};
	}

	/* (non-Javadoc)
	 * @see blservice.MatchQueryBLService#getLatestMatches()
	 */
	@Override
	public ArrayList<MatchDetailVO> getLatestMatches() {
		Calendar calendar = Calendar.getInstance();
		java.sql.Date end = new java.sql.Date(calendar.getTimeInMillis());
		calendar.add(Calendar.DATE, -14);	//显示两周内的比赛
		java.sql.Date start = new java.sql.Date(calendar.getTimeInMillis());
		return getVOsByPOs(matchData.getMatchDetailByDates(start, end));
	}

	/* (non-Javadoc)
	 * @see blservice.MatchQueryBLService#getMatchDetailByID()
	 */
	@Override
	public MatchDetailVO getMatchDetailByID(int matchID) {
		MatchDetailPO po = matchData.getMatchDetailByMatchID(matchID);
		MatchProfilePO profilePO = po.getMatchProfile();
		MatchDetailVO vo = new MatchDetailVO(po, 
				TeamLogoCache.getTeamLogo(profilePO.roadAbbr), TeamLogoCache.getTeamLogo(profilePO.homeAbbr));
		return vo;
	}

	/* (non-Javadoc)
	 * @see blservice.MatchQueryBLService#getLives(java.util.Date, java.lang.String)
	 */
	@Override
	public ArrayList<LiveRowVO> getLives(Date date, String homeAbbr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		int year = calendar.get(Calendar.YEAR);
		System.out.println(year);
		int month = calendar.get(Calendar.MONTH) + 1;
		System.out.println(month);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String monthStr;
		if(month < 10) {
			monthStr = "0" + month;
		}else {
			monthStr = "" + month;
		}
		String dayStr;
		if(day < 10) {
			dayStr = "0" + day;
		}else {
			dayStr = "" + day;
		}
		String url = "http://www.basketball-reference.com/boxscores/pbp/"
				+ String.valueOf(year) + monthStr + dayStr + "0" + homeAbbr + ".html";
		return new MatchRecordCrawler().getLives(url);
	}
}

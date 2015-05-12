package bl.matchquerybl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vo.MatchDetailVO;
import vo.MatchProfileVO;
import vo.PlayerMatchPerformanceVO;
import blservice.MatchQueryBLService;
import data.matchdata.MatchData;
import dataservice.MatchDataService;

/**
 * 负责查询比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:33:13
 */
public class MatchQuery implements MatchQueryBLService{
	
	private MatchDataService matchData = new MatchData();
	
	/**
	 * @see blservice.MatchQueryBLService#screenMatchByDate(java.util.Date)
	 */
	@Override
	public ArrayList<MatchDetailVO> screenMatchByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int seasonStart;
		int seasonEnd;
		if (month < 8) {
			seasonStart = year -1;
			seasonEnd = year;
		}else {
			seasonStart = year;
			seasonEnd = year +1;
		}
		String seasonString = String.valueOf(seasonStart).substring(2) + "-" + 
				String.valueOf(seasonEnd).substring(2);
		String monthString;
		String dayString;
		if (month < 10) {
			monthString = "0" + String.valueOf(month);
		}else {
			monthString = String.valueOf(month);
		}
		if (day < 10) {
			dayString = "0" + String.valueOf(day);
		}else {
			dayString = String.valueOf(day);
		}
		return getMatchDetailByProfile(matchData.getMatchProfileBySeasonAndDate(seasonString, monthString + "-" + dayString));
	}
	
	/**
	 * @see blservice.MatchQueryBLService#screenMatchByTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<MatchDetailVO> screenMatchByTeam(String abbr1,
			String abbr2) {
		ArrayList<MatchProfileVO> list1 = matchData.getMatchProfileByTeam(abbr1 + "-" + abbr2);
		ArrayList<MatchProfileVO> list2 = matchData.getMatchProfileByTeam(abbr2 + "-" + abbr1);
		list2.removeAll(list1);
		list1.addAll(list2);
		// 考虑到黄蜂、鹈鹕、篮网队改名的问题
		if (abbr1.equals("NOP")) {
			ArrayList<MatchProfileVO> list3 = matchData.getMatchProfileByTeam("NOH-" + abbr2);
			list3.removeAll(list1);
			list1.addAll(list3);
		}else if (abbr1.equals("BKN")) {
			ArrayList<MatchProfileVO> list4 = matchData.getMatchProfileByTeam("NJN-" + abbr2);
			list4.removeAll(list1);
			list1.addAll(list4);
		}
		if (abbr2.equals("NOP")) {
			ArrayList<MatchProfileVO> list3 = matchData.getMatchProfileByTeam(abbr1 + "-NOH");
			list3.removeAll(list1);
			list1.addAll(list3);
		}else if (abbr2.equals("BKN")) {
			ArrayList<MatchProfileVO> list4 = matchData.getMatchProfileByTeam(abbr1 + "-NJN");
			list4.removeAll(list1);
			list1.addAll(list4);
		}
		return getMatchDetailByProfile(list1);
	}
	
	private ArrayList<MatchDetailVO> getMatchDetailByProfile(ArrayList<MatchProfileVO> profileVOs) {
		ArrayList<MatchDetailVO> result = new ArrayList<MatchDetailVO>();
		for (MatchProfileVO profileVO : profileVOs) {
			String[] teams = profileVO.getTeam().split("-");
			result.add(getMatchDetail(profileVO.getSeason(), profileVO.getTime(), 
					teams[0], teams[1]));
		}
		return result;
	}

	/**
	 * @see blservice.MatchQueryBLService#getMatchDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MatchDetailVO getMatchDetail(String season, String date,
			String homeAbbr, String roadAbbr) {
		String fileName = season + "_" + date + "_" + homeAbbr + "-" + roadAbbr;
		return matchData.getMatchDetailByFileName(fileName);
	}
	
	/** 根据球员名字返回其所有比赛记录 */
	public ArrayList<PlayerMatchPerformanceVO> getMatchRecordByPlayerName(String playerName, String season) {
		return matchData.getMatchRecordByPlayerName(playerName, season);
	}
	
	/** 根据球队缩写返回其参加的所有比赛简报 */
	public ArrayList<MatchProfileVO> getMatchRecordByTeamAbbr(String abbr){
		return matchData.getMatchProfileByTeam(abbr);
	}

}

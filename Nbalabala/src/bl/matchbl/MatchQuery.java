package bl.matchbl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import data.matchdata.MatchData;
import vo.MatchDetailVO;
import vo.MatchProfileVO;
import blservice.MatchQueryBLService;

/**
 * 负责查询比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:33:13
 */
public class MatchQuery implements MatchQueryBLService{
	
	private MatchData matchData = new MatchData();
	
	/**
	 * @see blservice.MatchQueryBLService#screenMatchByDate(java.util.Date)
	 */
	@Override
	public ArrayList<MatchProfileVO> screenMatchByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
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
		return matchData.getMatchByKeyword(monthString + "-" + dayString);
	}

	/**
	 * @see blservice.MatchQueryBLService#screenMatchByTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<MatchProfileVO> screenMatchByTeam(String abbr1,
			String abbr2) {
		ArrayList<MatchProfileVO> list1 = matchData.getMatchByKeyword(abbr1 + "-" + abbr2);
		ArrayList<MatchProfileVO> list2 = matchData.getMatchByKeyword(abbr2 + "-" + abbr1);
		list2.removeAll(list1);
		list1.addAll(list2);
		return list1;
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

}

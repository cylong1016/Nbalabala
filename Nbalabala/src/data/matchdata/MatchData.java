package data.matchdata;

import java.util.ArrayList;

import vo.MatchDetailVO;
import vo.MatchProfileVO;

/**
 * 读取比赛信息，检索并返回符合条件的比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:34:35
 */
public class MatchData {

	/** 根据日期返回符合的比赛简报，date形如01-01和12-12 */
	public ArrayList<MatchProfileVO> getMatchByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MatchProfileVO> getMatchByTeam(String abbr1,
			String abbr2) {
		// TODO Auto-generated method stub
		return null;
	}

	public MatchDetailVO getMatchDetail(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}

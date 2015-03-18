package bl.matchbl;

import java.util.ArrayList;
import java.util.Date;

import vo.MatchDetailVO;
import vo.MatchProfileVO;
import blservice.MatchQueryBLService;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月18日  上午10:33:13
 */
public class MatchQuery implements MatchQueryBLService{

	/**
	 * @see blservice.MatchQueryBLService#screenMatchByDate(java.util.Date)
	 */
	@Override
	public ArrayList<MatchProfileVO> screenMatchByDate(Date date) {
		
		return null;
	}

	/**
	 * @see blservice.MatchQueryBLService#screenMatchByTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<MatchProfileVO> screenMatchByTeam(String abbr1,
			String abbr2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see blservice.MatchQueryBLService#getMatchDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MatchDetailVO getMatchDetail(String season, String date,
			String homeAbbr, String roadAbbr) {
		// TODO Auto-generated method stub
		return null;
	}

}

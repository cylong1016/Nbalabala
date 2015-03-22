package dataservice;

import vo.TeamProfileVO;
import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:25:20
 */
public interface TeamDataService {

	public TeamProfileVO getTeamProfileByAbbr(String abbr);

	public ScreenDivision getAreaByAbbr(String abbr);

	public ScreenDivision getDivisionByAbbr(String abbr);

}
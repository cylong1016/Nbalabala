package dataservice;

import vo.TeamProfileVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:25:20
 */
public interface TeamDataService {

	public TeamProfileVO getTeamProfileByAbbr(String abbr);

}
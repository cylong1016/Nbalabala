package dataservice;

import po.TeamProfilePO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:25:20
 */
public interface TeamDataService {

	public TeamProfilePO getTeamProfileByAbbr(String abbr);

}

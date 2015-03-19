package blservice;

import vo.TeamDetailVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:19:35
 */
public interface TeamQueryBLService {
	
	public TeamDetailVO getTeamDetailByAbbr(String abbr);

}

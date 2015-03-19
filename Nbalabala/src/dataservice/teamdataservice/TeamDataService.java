package dataservice.teamdataservice;

import vo.TeamProfileVO;

/**
 * 球队信息
 * @author cylong
 * @version 2015年3月13日 下午8:34:29
 */
public interface TeamDataService {

	/**
	 * 根据球队缩写查看球队基本信息
	 * @param abbr 球队缩写名
	 * @return TeamProfileVO
	 * @author cylong
	 * @version 2015年3月13日  下午8:58:20
	 */
	public TeamProfileVO getTeamProfileByAbbr(String abbr);
}

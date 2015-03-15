package dataservice.playerdataservice;

import po.TeamPO;

/**
 * 球队信息
 * @author cylong
 * @version 2015年3月13日 下午8:34:29
 */
public interface TeamDataService {

	/**
	 * 根据球队缩写查看球队基本信息
	 * @param name 球队缩写名
	 * @return TeamPO
	 * @author cylong
	 * @version 2015年3月13日  下午8:58:20
	 */
	public TeamPO findTeam(String name);
}

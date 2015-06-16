package dataservice;

import po.AdvancedDataPO;
import po.ClutchPO;

/**
 * 
 * @author cylong
 * @version 2015年6月16日 下午9:31:34
 */
public interface AdvancedDataService {

	/**
	 * 获得球员高阶数据，包括ORPM DRPM RPM WAR
	 * @author cylong
	 * @version 2015年6月17日  上午5:09:10
	 */
	public AdvancedDataPO getAdvancedData(String playerName, String season);

	/**
	 * 获得球员决胜时刻数据，包括上场时间和得分
	 * @author cylong
	 * @version 2015年6月17日  上午5:09:21
	 */
	public ClutchPO getClutchData(String playerName, String season);
}

package dataservice;

import po.AdvancedDataPO;
import po.ClutchPO;

/**
 * @author cylong
 * @version 2015年6月16日 下午9:31:34
 */
public interface AdvancedDataService {

	public AdvancedDataPO getAdvancedData(String playerName, String season);

	public ClutchPO getClutchData(String playerName, String season);
}

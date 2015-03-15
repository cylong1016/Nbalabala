package dataservice.playerdataservice;

import po.PlayerPO;

public interface PlayerDataService {

	/**
	 * 根据球员姓名查询球员个人信息
	 * @param name 球员姓名
	 * @return PlayerPO
	 * @author cylong
	 * @version 2015年3月13日 下午7:01:08
	 */
	public PlayerPO findPlayer(String name);
}

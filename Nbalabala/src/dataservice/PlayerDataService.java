package dataservice;

import java.util.ArrayList;

import po.PlayerProfilePO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:19:11
 */
public interface PlayerDataService {

	/** 根据姓氏首字母得到球员资料列表 */
	public ArrayList<PlayerProfilePO> getPlayerProfileByInitial(
			char initial);

	/** 根据名字得到球员资料 */
	public PlayerProfilePO getPlayerProfileByName(String name);

	/** 根据名字列表得到资料列表 */
	public ArrayList<PlayerProfilePO> getPlayerProfileByNames(
			ArrayList<String> names);
	
	/** 根据关键字查找名字匹配的球员，如kob将会查找到Kobe Bryant */
	public ArrayList<PlayerProfilePO> searchPlayers(String keyword);

}

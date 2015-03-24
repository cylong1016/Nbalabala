package dataservice;

import java.awt.Image;
import java.util.ArrayList;

import vo.PlayerProfileVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:19:11
 */
public interface PlayerDataService {

	/**
	 * @see dataservice.playerdataservice.PlayerDataService#getActionImageByName(java.lang.String)
	 */
	public Image getActionImageByName(String name);

	/**
	 * @see dataservice.playerdataservice.PlayerDataService#getPlayerProfileByInitial(char)
	 */
	public ArrayList<PlayerProfileVO> getPlayerProfileByInitial(
			char initial);

	/**
	 * @see dataservice.playerdataservice.PlayerDataService#getPlayerProfileByName(java.lang.String)
	 */
	public PlayerProfileVO getPlayerProfileByName(String name);

	public ArrayList<PlayerProfileVO> getPlayerProfileByNames(
			ArrayList<String> names);
	
	public ArrayList<PlayerProfileVO> searchPlayers(String keyword);

}
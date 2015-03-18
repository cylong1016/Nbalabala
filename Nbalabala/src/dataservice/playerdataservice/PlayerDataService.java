package dataservice.playerdataservice;

import java.awt.Image;
import java.util.ArrayList;

import vo.PlayerProfileVO;

public interface PlayerDataService {

	/**
	 * 根据球员姓名读取其全身像
	 * @param name 球员姓名
	 * @return Image
	 * @author cylong
	 * @version 2015年3月13日 下午7:01:08
	 */
	public Image getActionImageByName(String name);
	
	/**
	 * 根据首字母返 回所有符合的PO
	 * @param initial球员名字首字母
	 * @author cylong
	 */
	public ArrayList<PlayerProfileVO> getPlayerProfileByInitial(char initial); 
	
	/**
	 * 根据姓名返回球员简况
	 * @param name qiuyuanming
	 * @author Issac Ding
	 */
	public PlayerProfileVO getPlayerProfileByName(String name);
}

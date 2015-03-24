package blservice;

import java.util.ArrayList;

import vo.PlayerDetailVO;
import vo.PlayerProfileVO;

/**
 * 查询球员信息，可以按首字母得到球员简况列表，按照球员名得到球员详情
 * @author Issac Ding
 * @version 2015年3月18日  下午9:14:36
 */
public interface PlayerQueryBLService {
	
	public ArrayList<PlayerProfileVO> getPlayerProfileByInitial(char initial);
	
	public PlayerDetailVO getPlayerDetailByName(String name);
	
	/** 在搜索框内输入文字后调用的是这个方法 */
	public ArrayList<PlayerProfileVO> searchPlayers(String keyword);

}

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
	
	public PlayerDetailVO getPlayerDetailByName(String name, String season);
	
	/** 在搜索框内输入文字后调用的是这个方法 */
	public ArrayList<PlayerProfileVO> searchPlayers(String keyword);
	
	/** 返回一个长度为3数组，分别是所有球员中场均得分篮板助攻的最高值 */
	public double [] getHighestScoreReboundAssist(String season);
	
	/** 返回一个长度为5数组，分别是所有球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值*/
	public double [] getFiveArgsAvg(String season);
	

}

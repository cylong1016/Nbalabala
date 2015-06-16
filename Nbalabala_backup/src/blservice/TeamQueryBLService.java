package blservice;

import vo.KingVO;
import vo.TeamDetailVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:19:35
 */
public interface TeamQueryBLService {
	
	public TeamDetailVO getTeamDetailByAbbr(String abbr, String season);
	
	/** 返回一个长度为3数组，分别是所有球员中场均得分篮板助攻的最高值 */
	public double [] getHighestScoreReboundAssist(String season);
	
	/** 返回一个长度为5数组，分别是所有球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值*/
	public double [] getFiveArgsAvg(String season);
	
	/** 返回长度为4数组，分别是联盟内胜率、得分、篮板、助攻的排名。若无，则为0 */
	public int [] getRanks(String abbr);
	
	/** 得到得分数据王 */
	public KingVO[] getScoreKings(String abbr);
	
	/** 得到篮板数据王  */
	public KingVO[] getReboundKings(String abbr);
	
	/** 得到助攻数据王  */
	public KingVO[] getAssistKings(String abbr);
	
//	public ArrayList<PlayerProfileVO>

}

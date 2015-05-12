package blservice;

import java.util.ArrayList;
import java.util.Date;

import vo.MatchDetailVO;
import vo.MatchProfileVO;

/**
 * 比赛信息查询界面的接口
 * @author Issac Ding
 * @version 2015年3月18日  上午9:42:57
 */
public interface MatchQueryBLService {
	
	/** 根据日期返回符合条件的比赛记录详情*/
	public ArrayList<MatchDetailVO> screenMatchByDate(Date date);
	
	/** 根据球队缩写筛选，两个String为""表示不作限制 */
	public ArrayList<MatchDetailVO> screenMatchByTeam(String abbr1, String abbr2);
	
	/** 根据赛季、日期、主客场球队缩写，返回比赛详情 */
	//在筛选出来的比赛简况中每一行都显示该场比赛的赛季、日期、两队缩写，这样要查看详情的话只要把这四个参数从表格中拿出来就行了
	public MatchDetailVO getMatchDetail(String season, String date, String homeAbbr, String roadAbbr);
}

package blservice;

import java.util.ArrayList;

import vo.HotFastestPlayerVO;
import vo.HotSeasonPlayerVO;
import vo.HotSeasonTeamVO;
import vo.HotTodayPlayerVO;
import enums.HotFastestPlayerProperty;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;
import enums.HotTodayPlayerProperty;

/**
 * 热点功能模块所需接口
 * @author Issac Ding
 * @version 2015年4月8日  下午6:59:35
 */
public interface HotBLService {
	
	/** 根据筛选指标返回该指标的当日前5名球员 */
	public ArrayList<HotTodayPlayerVO> getHotTodayPlayers(HotTodayPlayerProperty property);
	
	/** 赛季热点球员 */
	public ArrayList<HotSeasonPlayerVO> getHotSeasonPlayers(HotSeasonPlayerProperty property);
	
	/** 赛季热点球队 */
	public ArrayList<HotSeasonTeamVO> getHotSeasonTeams(HotSeasonTeamProperty property);
	
	/** 进步最快球员 */
	public ArrayList<HotFastestPlayerVO> getHotFastestPlayers(HotFastestPlayerProperty property);
	
}

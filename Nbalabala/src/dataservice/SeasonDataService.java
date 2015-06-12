package dataservice;

import java.util.ArrayList;

import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import enums.Position;
import enums.ScreenDivision;

/**
 * 赛季数据层接口
 * @author Issac Ding
 * @version 2015年3月19日  下午6:21:33
 */
public interface SeasonDataService {

	/** 根据位置、赛区、赛季来返回符合条件的球员 */
	public ArrayList<PlayerSeasonPO> getScreenedPlayerSeasonData(
			Position position, ScreenDivision division, String season);

	/** 返回某一赛季全部球员赛季数据 */
	public ArrayList<PlayerSeasonPO> getAllPlayerSeasonData(String season);
	
	/** 返回最近赛季全部球员数据 */
	public ArrayList<PlayerSeasonPO> getAllPlayerRecentSeasonData();
	
	/** 返回最近赛季全部球队数据 */
	public ArrayList<TeamSeasonPO> getAllTeamRecentSeasonData();

	/** 根据赛区返回符合条件的球队赛季数据 */
	public ArrayList<TeamSeasonPO> getScreenedTeamSeasonData(
			ScreenDivision division, String season);

	/** 根据球员名字和赛季返回其该赛季所属球队 */
	public String getTeamAbbrByPlayer(String playerName, String season);

	/** 根据球员名字和赛季返回其赛季数据 */
	public PlayerSeasonPO getPlayerSeasonDataByName(String playerName, String season);

	/** 根据球队缩写和赛季返回其赛季数据 */
	public TeamSeasonPO getTeamDataByAbbr(String abbr, String season);

	/** 根据球队缩写返回其阵容名单 */
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr, String season);
	
	/** 设置保留在内存中的赛季数据数量。增大这一数值会减少访问数据库次数并增加内存消耗 */
	public void setSeasonCacheSize(int size);
	
	/** 返回最近赛季某球队的球员名单 */
	public ArrayList<String> getRecentPlayerNamesByTeamAbbr(String abbr);
	
}

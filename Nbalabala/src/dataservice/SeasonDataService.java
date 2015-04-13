package dataservice;

import java.util.ArrayList;

import vo.PlayerSeasonVO;
import vo.TeamSeasonVO;
import enums.Position;
import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:21:33
 */
public interface SeasonDataService {

	/** 根据位置、赛区来返回符合条件的球员 */
	public ArrayList<PlayerSeasonVO> getScreenedPlayerSeasonData(
			Position position, ScreenDivision division, String season);

	/** 返回全部球员赛季数据 */
	public ArrayList<PlayerSeasonVO> getAllPlayerSeasonData(String season);
	
	/** 返回最近赛季全部球员数据 */
	public ArrayList<PlayerSeasonVO> getAllPlayerRecentSeasonData();
	
	/** 返回最近赛季当天上场的球员数据 */
	public ArrayList<PlayerSeasonVO> getAllPlayerRecentSeasonTodayData();
	
	/** 返回最近赛季全部球队数据 */
	public ArrayList<TeamSeasonVO> getAllTeamRecentSeasonData();

	/** 根据赛区返回符合条件的记录 */
	public ArrayList<TeamSeasonVO> getScreenedTeamSeasonData(
			ScreenDivision division, String season);

	/** 根据球员名字返回其最后一次比赛的球队 */
	public String getTeamAbbrByPlayer(String playerName, String season);

	public PlayerSeasonVO getPlayerSeasonDataByName(
			String playerName, String season);

	public TeamSeasonVO getTeamDataByAbbr(String abbr, String season);

	/** 根据球队缩写返回其阵容名单 */
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr);

}
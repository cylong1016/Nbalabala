package dataservice;

import java.util.ArrayList;

import data.seasondata.PlayerSeasonRecord;
import data.seasondata.TeamSeasonRecord;
import enums.Position;
import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:21:33
 */
public interface SeasonDataService {

	/** 根据位置、赛区来返回符合条件的球员 */
	public abstract ArrayList<PlayerSeasonRecord> getScreenedPlayerSeasonData(
			Position position, ScreenDivision division);

	/** 返回全部球员赛季数据 */
	public abstract ArrayList<PlayerSeasonRecord> getAllPlayerSeasonData();

	/** 根据赛区返回符合条件的记录 */
	public abstract ArrayList<TeamSeasonRecord> getScreenedTeamSeasonData(
			ScreenDivision division);

	/** 根据球员名字返回其最后一次比赛的球队 */
	public abstract String getTeamAbbrByPlayer(String playerName);

	public abstract PlayerSeasonRecord getPlayerSeasonDataByName(
			String playerName);

	public abstract TeamSeasonRecord getTeamDataByAbbr(String abbr);

	/** 根据球队缩写返回其阵容名单 */
	public abstract ArrayList<String> getPlayerNamesByTeamAbbr(String abbr);

}
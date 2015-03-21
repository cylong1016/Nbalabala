package bl.playerquerybl;

import java.awt.Image;
import java.util.ArrayList;

import vo.PlayerDetailVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerProfileVO;
import bl.matchquerybl.MatchQuery;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerQueryBLService;
import data.playerdata.PlayerData;
import data.seasondata.PlayerSeasonRecord;
import dataservice.PlayerDataService;

/**
 * 负责查询球员信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午8:57:59
 */
public class PlayerQuery implements PlayerQueryBLService{
	
	private PlayerDataService playerData = new PlayerData();

	/**
	 * @see blservice.PlayerQueryBLService#getPlayerProfileByInitial(char)
	 */
	@Override
	public ArrayList<PlayerProfileVO> getPlayerProfileByInitial(char initial) {
		return playerData.getPlayerProfileByInitial(initial);
	}

	/**
	 * @see blservice.PlayerQueryBLService#getPlayerDetailByName(java.lang.String)
	 */
	@Override
	public PlayerDetailVO getPlayerDetailByName(String playerName) {
		PlayerProfileVO profile = playerData.getPlayerProfileByName(playerName);
		Image actionImage = playerData.getActionImageByName(playerName);
		
		//从seasonbl获取球员的赛季数据
		PlayerSeasonAnalysis playerSeasonAnalysis = new PlayerSeasonAnalysis();
		PlayerSeasonRecord seasonRecord = playerSeasonAnalysis.getPlayerSeasonDataByName(playerName);
		
		//从matchbl获取球员所有比赛的数据
		MatchQuery matchQuery = new MatchQuery();
		ArrayList<PlayerMatchPerformanceVO> matchRecords = matchQuery.getMatchRecordByPlayerName(playerName);

		return new PlayerDetailVO(profile, seasonRecord, matchRecords, actionImage);
	}
	
	/** 根据若干球员的名字返回其简况，提供给teambl用 */
	public ArrayList<PlayerProfileVO> getPlayerProfilesByNames(ArrayList<String> names) {
		return playerData.getPlayerProfileByNames(names);
	}
}

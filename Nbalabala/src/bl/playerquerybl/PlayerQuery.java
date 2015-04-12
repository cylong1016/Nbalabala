package bl.playerquerybl;

import java.awt.Image;
import java.util.ArrayList;

import vo.PlayerDetailVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerProfileVO;
import vo.PlayerSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerQueryBLService;
import data.playerdata.PlayerData;
import data.playerdata.PlayerImageCache;
import data.seasondata.SeasonData;
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
	public PlayerDetailVO getPlayerDetailByName(String playerName, String season) {
		PlayerProfileVO profile = playerData.getPlayerProfileByName(playerName);
		Image actionImage = PlayerImageCache.getActionImageByName(playerName);

		//从seasonbl获取球员的赛季数据
		PlayerSeasonAnalysis playerSeasonAnalysis = new PlayerSeasonAnalysis();
		PlayerSeasonVO seasonRecord = playerSeasonAnalysis.getPlayerSeasonDataByName(playerName, season);
		
		//从matchbl获取球员所有比赛的数据
		MatchQuery matchQuery = new MatchQuery();
		ArrayList<PlayerMatchPerformanceVO> matchRecords = matchQuery.getMatchRecordByPlayerName(playerName);

		return new PlayerDetailVO(profile, seasonRecord, matchRecords, actionImage);
	}
	
	/** 根据若干球员的名字返回其简况，提供给teambl用 */
	public ArrayList<PlayerProfileVO> getPlayerProfilesByNames(ArrayList<String> names) {
		return playerData.getPlayerProfileByNames(names);
	}

	/**
	 * @see blservice.PlayerQueryBLService#searchPlayers(java.lang.String)
	 */
	@Override
	public ArrayList<PlayerProfileVO> searchPlayers(String keyword) {
		return playerData.searchPlayers(keyword);
	}

	/**
	 * @see blservice.PlayerQueryBLService#getHighestScoreReboundAssist()
	 */
	@Override
	public double[] getHighestScoreReboundAssist(String season) {
		ArrayList<PlayerSeasonVO> list = new SeasonData().getAllPlayerSeasonData(season);
		double highestScore = 0;
		double highestRebound = 0;
		double highestAssist = 0;
		for (PlayerSeasonVO vo : list) {
			if (vo.scoreAvg > highestScore)
				highestScore = vo.scoreAvg;
			if (vo.totalReboundAvg > highestRebound)
				highestRebound = vo.totalReboundAvg;
			if (vo.assistAvg > highestAssist)
				highestAssist = vo.assistAvg;
		}
		double[] result = {highestScore, highestRebound, highestRebound};
		return result;
	}

	/**
	 * @see blservice.PlayerQueryBLService#getFiveArgsAvg()
	 */
	@Override
	public double[] getFiveArgsAvg(String season) {
		ArrayList<PlayerSeasonVO> list = new SeasonData().getAllPlayerSeasonData(season);
		int matchCount = 0;
		int score = 0;
		int rebound = 0;
		int assist = 0;
		int threePointGoal = 0;
		int threePointAttempt = 0;
		int freethrowGoal = 0;
		int freethrowAttempt = 0;
		for (PlayerSeasonVO vo : list) {
			matchCount += vo.matchCount;
			score += vo.score;
			rebound += vo.totalRebound;
			assist += vo.assist;
			threePointGoal += vo.threePointGoal;
			threePointAttempt += vo.threePointAttempt;
			freethrowAttempt += vo.freethrowAttempt;
			freethrowGoal += vo.freethrowGoal;
		}
		double[]result = {(double)score / matchCount, (double)rebound / matchCount,
				(double)assist / matchCount, (double)freethrowGoal / freethrowAttempt,
				(double)threePointGoal / threePointAttempt};
		return result;
	}
}

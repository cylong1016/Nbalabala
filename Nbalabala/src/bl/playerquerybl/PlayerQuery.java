package bl.playerquerybl;

import java.util.ArrayList;

import po.MatchPlayerPO;
import po.PlayerProfilePO;
import po.PlayerSeasonPO;
import vo.PlayerDetailVO;
import bl.matchquerybl.MatchQuery;
import bl.playerseasonbl.PlayerAvgSorter;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerQueryBLService;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
import dataservice.PlayerDataService;
import dataservice.SeasonDataService;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;

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
	public ArrayList<PlayerProfilePO> getPlayerProfileByInitial(char initial) {
		return playerData.getPlayerProfileByInitial(initial);
	}

	/**
	 * @see blservice.PlayerQueryBLService#getPlayerDetailByName(java.lang.String)
	 */
	@Override
	public PlayerDetailVO getPlayerDetailByName(String playerName, String season) {
		PlayerProfilePO profile = playerData.getPlayerProfileByName(playerName);

		//从seasonbl获取球员的赛季数据
		PlayerSeasonAnalysis playerSeasonAnalysis = new PlayerSeasonAnalysis();
		PlayerSeasonPO seasonRecord = playerSeasonAnalysis.getPlayerSeasonDataByName(playerName, season);
		
		//从matchbl获取球员所有比赛的数据
		MatchQuery matchQuery = new MatchQuery();
		ArrayList<MatchPlayerPO> matchRecords = matchQuery.getMatchRecordByPlayerName(playerName, season);

		return new PlayerDetailVO(profile, seasonRecord, matchRecords);
	}
	
	/** 根据若干球员的名字返回其简况，提供给teambl用 */
	public ArrayList<PlayerProfilePO> getPlayerProfilesByNames(ArrayList<String> names) {
		return playerData.getPlayerProfileByNames(names);
	}

	/**
	 * @see blservice.PlayerQueryBLService#searchPlayers(java.lang.String)
	 */
	@Override
	public ArrayList<PlayerProfilePO> searchPlayers(String keyword) {
		return playerData.searchPlayers(keyword);
	}

	/**
	 * @see blservice.PlayerQueryBLService#getHighestScoreReboundAssist()
	 */
	@Override
	public double[] getHighestScoreReboundAssist(String season) {
		ArrayList<PlayerSeasonPO> list = new SeasonData().getAllPlayerSeasonData(season);
		double highestScore = 0;
		double highestRebound = 0;
		double highestAssist = 0;
		for (PlayerSeasonPO vo : list) {
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
		ArrayList<PlayerSeasonPO> list = new SeasonData().getAllPlayerSeasonData(season);
		int matchCount = 0;
		int score = 0;
		int rebound = 0;
		int assist = 0;
		int threePointMade = 0;
		int threePointAttempt = 0;
		int freethrowMade = 0;
		int freethrowAttempt = 0;
		for (PlayerSeasonPO vo : list) {
			matchCount += vo.matchCount;
			score += vo.score;
			rebound += vo.totalRebound;
			assist += vo.assist;
			threePointMade += vo.threePointMade;
			threePointAttempt += vo.threePointAttempt;
			freethrowAttempt += vo.freethrowAttempt;
			freethrowMade += vo.freethrowMade;
		}
		double[]result = {(double)score / matchCount, (double)rebound / matchCount,
				(double)assist / matchCount, (double)freethrowMade / freethrowAttempt,
				(double)threePointMade / threePointAttempt};
		return result;
	}

	/**
	 * @see blservice.PlayerQueryBLService#getScoreRankAvg(java.lang.String, java.lang.String)
	 */
	@Override
	public int[] getScoreReboundAssistRank(String name, String season) {
		SeasonDataService dataService = new SeasonData();
		int [] result = new int[3];
		ArrayList<PlayerSeasonPO> vos = dataService.getAllPlayerSeasonData(season);
		if (vos.size() == 0) {
			result[0]=0;
			result[1]=0;
			result[2]=0;
			return result;
		}
		PlayerAvgSorter sorter = new PlayerAvgSorter();
		sorter.sort(vos, PlayerAvgSortBasis.SCORE_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).name.equals(name)) {
				result[0] = i + 1;
				break;
			}
			result[0] = 0;
		}
		sorter.sort(vos, PlayerAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).name.equals(name)) {
				result[1] = i + 1;
				break;
			}
			result[1] = 0;
		}
		sorter.sort(vos, PlayerAvgSortBasis.ASSIST_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).name.equals(name)) {
				result[2] = i + 1;
				break;
			}
			result[2] = 0;
		}
		return result;
	}
}

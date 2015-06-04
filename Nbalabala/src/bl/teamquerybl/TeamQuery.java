package bl.teamquerybl;

import java.util.ArrayList;

import po.PlayerProfilePO;
import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import utility.Constants;
import vo.KingVO;
import vo.MatchProfileVO;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import bl.matchquerybl.MatchQuery;
import bl.playerseasonbl.PlayerAvgSorter;
import bl.teamseasonbl.TeamAvgSorter;
import blservice.TeamQueryBLService;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
import data.teamdata.TeamData;
import dataservice.PlayerDataService;
import dataservice.SeasonDataService;
import dataservice.TeamDataService;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;
import enums.TeamAvgSortBasis;

/**
 * 负责查询球队信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午8:54:00
 */
public class TeamQuery implements TeamQueryBLService{
	
	/**
	 * @see blservice.TeamQueryBLService#getTeamDetailByAbbr(java.lang.String)
	 */
	@Override
	public TeamDetailVO getTeamDetailByAbbr(String abbr, String season) {
		TeamDataService dataService = new TeamData();
		SeasonDataService seasonService = new SeasonData();
		PlayerDataService playerService = new PlayerData();
		
		TeamProfileVO profileVO = new TeamProfileVO(dataService.getTeamProfileByAbbr(abbr));
		
		ArrayList<String> playerNames = seasonService.getRecentPlayerNamesByTeamAbbr(abbr);
		ArrayList<PlayerProfilePO> playerProfiles = new ArrayList<PlayerProfilePO>();
		for (String name : playerNames) {
			playerProfiles.add(playerService.getPlayerProfileByName(name));
		}
		
		TeamSeasonPO seasonRecord = seasonService.getTeamDataByAbbr(abbr, season);
		if (seasonRecord == null) {
			seasonRecord = seasonService.getTeamDataByAbbr(abbr, season);
		}
		ArrayList<MatchProfileVO> matches = new MatchQuery().getMatchRecordByTeamAbbrAndSeason(abbr, season); 
		TeamDetailVO detailVO = new TeamDetailVO(profileVO, playerProfiles, seasonRecord, matches);
		return detailVO;
	}

	/**
	 * @see blservice.TeamQueryBLService#getHighestScoreReboundAssist(java.lang.String)
	 */
	@Override
	public double[] getHighestScoreReboundAssist(String season) {
		ArrayList<TeamSeasonPO> list = new SeasonData().getAllTeamSeasonData(season);
		double highestScore = 0;
		double highestRebound = 0;
		double highestAssist = 0;
		for (TeamSeasonPO vo : list) {
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
	 * @see blservice.TeamQueryBLService#getFiveArgsAvg(java.lang.String)
	 */
	@Override
	public double[] getFiveArgsAvg(String season) {
		ArrayList<TeamSeasonPO> list = new SeasonData().getAllTeamSeasonData(season);
		int matchCount = 0;
		int score = 0;
		int rebound = 0;
		int assist = 0;
		int threePointMade = 0;
		int threePointAttempt = 0;
		int freethrowMade = 0;
		int freethrowAttempt = 0;
		for (TeamSeasonPO vo : list) {
			matchCount += vo.matchCount;
			score += vo.score;
			rebound += vo.totalRebound;
			assist += vo.assist;
			threePointMade += vo.threePointMade;
			threePointAttempt += vo.threePointAttempt;
			freethrowAttempt += vo.freethrowAttempt;
			freethrowMade += vo.freethrowMade;
		}
		double arg1,arg2,arg3,arg4,arg5;
		if (matchCount == 0) {
			arg1 = 0;
			arg2 = 0;
			arg3 = 0;
		}else {
			arg1 = (double)score / matchCount;
			arg2 = (double)rebound / matchCount;
			arg3 = (double)assist / matchCount;
		}
		if (freethrowAttempt == 0)
			arg4 = 0;
		else 
			arg4 = (double)freethrowMade / freethrowAttempt;
		
		if (threePointAttempt == 0)
			arg5 = 0;
		else 
			arg5 = (double)threePointMade / threePointAttempt;
	
		double[]result = {arg1,arg2,arg3,arg4,arg5};
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.TeamQueryBLService#getRanks(java.lang.String, java.lang.String)
	 */
	@Override
	public int[] getRanks(String abbr) {
		ArrayList<TeamSeasonPO> vos = new SeasonData().getTeamRecentSeasonDataInSameLeague(abbr);
		int [] result = new int[4];
		if (vos.size() == 0) {
			result[0]=0;
			result[1]=0;
			result[2]=0;
			result[3]=0;
			return result;
		}
		TeamAvgSorter sorter = new TeamAvgSorter();
		sorter.sort(vos, TeamAvgSortBasis.WINNING, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).abbr.equals(abbr)) {
				result[0] = i + 1;
				break;
			}
			result[0] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.SCORE_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).abbr.equals(abbr)) {
				result[1] = i + 1;
				break;
			}
			result[1] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).abbr.equals(abbr)) {
				result[2] = i + 1;
				break;
			}
			result[2] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.ASSIST_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).abbr.equals(abbr)) {
				result[3] = i + 1;
				break;
			}
			result[3] = 0;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.TeamQueryBLService#getScoreKings()
	 */
	@Override
	public KingVO[] getScoreKings(String abbr) {
		KingVO[] result = new KingVO[5];
		int index = 0;
		SeasonData seasonData = new SeasonData();
		PlayerData playerData = new PlayerData();
		ArrayList<PlayerSeasonPO> allPlayers = seasonData.getAllPlayerRecentSeasonData();
		ArrayList<String> teamPlayers = seasonData.getRecentPlayerNamesByTeamAbbr(abbr);
		new PlayerAvgSorter().sort(allPlayers, PlayerAvgSortBasis.SCORE_AVG, SortOrder.DE);
		for (int i=0;i<allPlayers.size();i++) {
			PlayerSeasonPO vo = allPlayers.get(i);
			if (teamPlayers.contains(vo.name)) {
				result[index] = new KingVO(index + 1, vo.getName(), vo.getScoreAvg(), i + 1, 
						Constants.translatePosition(playerData.getPlayerProfileByName(vo.getName())
								.getPosition()));
				index ++;
			}
			if (index > 4) break;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.TeamQueryBLService#getReboundKings()
	 */
	@Override
	public KingVO[] getReboundKings(String abbr) {
		KingVO[] result = new KingVO[5];
		int index = 0;
		SeasonData seasonData = new SeasonData();
		PlayerData playerData = new PlayerData();
		ArrayList<PlayerSeasonPO> allPlayers = seasonData.getAllPlayerRecentSeasonData();
		ArrayList<String> teamPlayers = seasonData.getRecentPlayerNamesByTeamAbbr(abbr);
		new PlayerAvgSorter().sort(allPlayers, PlayerAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
		for (int i=0;i<allPlayers.size();i++) {
			PlayerSeasonPO vo = allPlayers.get(i);
			if (teamPlayers.contains(vo.name)) {
				result[index] = new KingVO(index + 1, vo.getName(), vo.getTotalReboundAvg(), i + 1, 
						Constants.translatePosition(playerData.getPlayerProfileByName(vo.getName())
								.getPosition()));
				index ++;
			}
			if (index > 4) break;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.TeamQueryBLService#getAssistKings()
	 */
	@Override
	public KingVO[] getAssistKings(String abbr) {
		KingVO[] result = new KingVO[5];
		int index = 0;
		SeasonData seasonData = new SeasonData();
		PlayerData playerData = new PlayerData();
		ArrayList<PlayerSeasonPO> allPlayers = seasonData.getAllPlayerRecentSeasonData();
		ArrayList<String> teamPlayers = seasonData.getRecentPlayerNamesByTeamAbbr(abbr);
		new PlayerAvgSorter().sort(allPlayers, PlayerAvgSortBasis.ASSIST_AVG, SortOrder.DE);
		for (int i=0;i<allPlayers.size();i++) {
			PlayerSeasonPO vo = allPlayers.get(i);
			if (teamPlayers.contains(vo.name)) {
				result[index] = new KingVO(index + 1, vo.getName(), vo.getAssistAvg(), i + 1, 
						Constants.translatePosition(playerData.getPlayerProfileByName(vo.getName())
								.getPosition()));
				index ++;
			}
			if (index > 4) break;
		}
		return result;
	}

}

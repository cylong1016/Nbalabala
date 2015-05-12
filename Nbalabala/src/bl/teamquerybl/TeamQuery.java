package bl.teamquerybl;

import java.util.ArrayList;
import java.util.Collections;

import vo.MatchProfileVO;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import vo.TeamSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.playerquerybl.PlayerQuery;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import bl.teamseasonbl.TeamAvgSorter;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamQueryBLService;
import data.seasondata.SeasonData;
import data.teamdata.SVGHandler;
import data.teamdata.TeamData;
import dataservice.TeamDataService;
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
		TeamDataService teamData = new TeamData();
		TeamProfileVO profile = teamData.getTeamProfileByAbbr(abbr);
		
		PlayerSeasonAnalysis playerSeasonAnalysis = new PlayerSeasonAnalysis();
		ArrayList<String> playerNames = playerSeasonAnalysis.getPlayerNamesByTeamAbbr(abbr, season);
		Collections.sort(playerNames);
		
		PlayerQuery playerQuery = new PlayerQuery();
		ArrayList<PlayerProfileVO> playerProfiles = playerQuery.getPlayerProfilesByNames(playerNames);
		
		TeamSeasonAnalysis teamSeasonAnalysis = new TeamSeasonAnalysis();
		TeamSeasonVO record = teamSeasonAnalysis.getTeamDataByAbbr(abbr, season);
		
		MatchQuery matchQuery = new MatchQuery();
		ArrayList<MatchProfileVO> matchRecords = matchQuery.getMatchRecordByTeamAbbr(abbr);
		
		return new TeamDetailVO(profile, playerProfiles, record, SVGHandler.getTeamLogo(abbr),
				matchRecords);
	}

	/**
	 * @see blservice.TeamQueryBLService#getHighestScoreReboundAssist(java.lang.String)
	 */
	@Override
	public double[] getHighestScoreReboundAssist(String season) {
		ArrayList<TeamSeasonVO> list = new SeasonData().getAllTeamSeasonData(season);
		double highestScore = 0;
		double highestRebound = 0;
		double highestAssist = 0;
		for (TeamSeasonVO vo : list) {
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
		ArrayList<TeamSeasonVO> list = new SeasonData().getAllTeamSeasonData(season);
		int matchCount = 0;
		int score = 0;
		int rebound = 0;
		int assist = 0;
		int threePointGoal = 0;
		int threePointAttempt = 0;
		int freethrowGoal = 0;
		int freethrowAttempt = 0;
		for (TeamSeasonVO vo : list) {
			matchCount += vo.matchCount;
			score += vo.score;
			rebound += vo.totalRebound;
			assist += vo.assist;
			threePointGoal += vo.threePointGoal;
			threePointAttempt += vo.threePointAttempt;
			freethrowAttempt += vo.freethrowAttempt;
			freethrowGoal += vo.freethrowGoal;
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
			arg4 = (double)freethrowGoal / freethrowAttempt;
		
		if (threePointAttempt == 0)
			arg5 = 0;
		else 
			arg5 = (double)threePointGoal / threePointAttempt;
	
		double[]result = {arg1,arg2,arg3,arg4,arg5};
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.TeamQueryBLService#getRanks(java.lang.String, java.lang.String)
	 */
	@Override
	public int[] getRanks(String abbr) {
		ArrayList<TeamSeasonVO> vos = new SeasonData().getAllTeamRecentSeasonData();
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
			if (vos.get(i).teamName.equals(abbr)) {
				result[0] = i + 1;
				break;
			}
			result[0] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.SCORE_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).teamName.equals(abbr)) {
				result[1] = i + 1;
				break;
			}
			result[1] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).teamName.equals(abbr)) {
				result[2] = i + 1;
				break;
			}
			result[2] = 0;
		}
		sorter.sort(vos, TeamAvgSortBasis.ASSIST_AVG, SortOrder.DE);
		for (int i=0;i<vos.size();i++) {
			if (vos.get(i).teamName.equals(abbr)) {
				result[3] = i + 1;
				break;
			}
			result[3] = 0;
		}
		return result;
	}
}

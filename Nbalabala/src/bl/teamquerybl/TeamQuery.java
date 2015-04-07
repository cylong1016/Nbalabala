package bl.teamquerybl;

import java.util.ArrayList;
import java.util.Collections;

import data.teamdata.SVGHandler;
import data.teamdata.TeamData;
import dataservice.TeamDataService;
import vo.MatchProfileVO;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import vo.TeamSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.playerquerybl.PlayerQuery;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamQueryBLService;

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
		ArrayList<String> playerNames = playerSeasonAnalysis.getPlayerNamesByTeamAbbr(abbr);
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
	
}

package bl.teambl;

import java.util.ArrayList;
import java.util.Collections;

import data.seasondata.TeamSeasonRecord;
import data.teamdata.TeamData;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import bl.playerbl.PlayerQuery;
import bl.seasonbl.PlayerSeasonAnalysis;
import bl.seasonbl.TeamSeasonAnalysis;
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
	public TeamDetailVO getTeamDetailByAbbr(String abbr) {
		TeamData teamData = new TeamData();
		TeamProfileVO profile = teamData.getTeamProfileByAbbr(abbr);
		
		PlayerSeasonAnalysis playerSeasonAnalysis = new PlayerSeasonAnalysis();
		ArrayList<String> playerNames = playerSeasonAnalysis.getPlayerNamesByTeamAbbr(abbr);
		Collections.sort(playerNames);
		
		PlayerQuery playerQuery = new PlayerQuery();
		ArrayList<PlayerProfileVO> playerProfiles = playerQuery.getPlayerProfilesByNames(playerNames);
		
		TeamSeasonAnalysis teamSeasonAnalysis = new TeamSeasonAnalysis();
		TeamSeasonRecord record = teamSeasonAnalysis.getTeamDataByAbbr(abbr);
		
		return new TeamDetailVO(profile, playerProfiles, record);
	}
}

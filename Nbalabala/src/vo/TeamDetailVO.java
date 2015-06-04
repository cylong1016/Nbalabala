package vo;

import java.util.ArrayList;

import po.PlayerProfilePO;
import po.TeamSeasonPO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:22:10
 */
public class TeamDetailVO {
	
	private TeamProfileVO profile;
	
	/** 球员阵容 */
	private ArrayList<PlayerProfilePO> players;
	
	/** 赛季数据 */
	private TeamSeasonPO seasonRecord;
	
	/** 比赛记录 */
	private ArrayList<MatchProfileVO> matchRecords;

	public TeamDetailVO(TeamProfileVO profile,
			ArrayList<PlayerProfilePO> players, TeamSeasonPO seasonRecord,
			ArrayList<MatchProfileVO> matchRecords) {
		super();
		this.profile = profile;
		this.players = players;
		this.seasonRecord = seasonRecord;
		this.matchRecords = matchRecords;
	}

	public TeamProfileVO getProfile() {
		return profile;
	}

	public ArrayList<PlayerProfilePO> getPlayers() {
		return players;
	}

	public TeamSeasonPO getSeasonRecord() {
		return seasonRecord;
	}
	
	public ArrayList<MatchProfileVO> getMatchRecords(){
		return matchRecords;
	}

}

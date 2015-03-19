package vo;

import java.util.ArrayList;

import data.seasondata.TeamSeasonRecord;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:22:10
 */
public class TeamDetailVO {
	
	public TeamProfileVO profile;
	
	public ArrayList<PlayerProfileVO> players;
	
	public TeamSeasonRecord seasonRecord;

	public TeamDetailVO(TeamProfileVO profile,
			ArrayList<PlayerProfileVO> players, TeamSeasonRecord seasonRecord) {
		super();
		this.profile = profile;
		this.players = players;
		this.seasonRecord = seasonRecord;
	}

}

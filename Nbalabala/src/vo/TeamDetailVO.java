package vo;

import java.awt.Image;
import java.util.ArrayList;

import data.seasondata.TeamSeasonRecord;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:22:10
 */
public class TeamDetailVO {
	
	private TeamProfileVO profile;
	
	private ArrayList<PlayerProfileVO> players;
	
	private TeamSeasonRecord seasonRecord;
	
	private Image logo;

	public TeamDetailVO(TeamProfileVO profile,
			ArrayList<PlayerProfileVO> players, TeamSeasonRecord seasonRecord, Image logo) {
		super();
		this.profile = profile;
		this.players = players;
		this.seasonRecord = seasonRecord;
		this.logo = logo;
	}

	public TeamProfileVO getProfile() {
		return profile;
	}

	public ArrayList<PlayerProfileVO> getPlayers() {
		return players;
	}

	public TeamSeasonRecord getSeasonRecord() {
		return seasonRecord;
	}
	
	public Image getLogo() {
		return logo;
	}

}

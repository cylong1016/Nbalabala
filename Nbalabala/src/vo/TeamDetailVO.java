package vo;

import java.awt.Image;
import java.util.ArrayList;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午3:22:10
 */
public class TeamDetailVO {
	
	private TeamProfileVO profile;
	
	/** 球员阵容 */
	private ArrayList<PlayerProfileVO> players;
	
	/** 赛季数据 */
	private TeamSeasonVO seasonRecord;
	
	private Image logo;
	
	/** 比赛记录 */
	private ArrayList<MatchProfileVO> matchRecords;

	public TeamDetailVO(TeamProfileVO profile,
			ArrayList<PlayerProfileVO> players, TeamSeasonVO seasonRecord, Image logo,
			ArrayList<MatchProfileVO> matchRecords) {
		super();
		this.profile = profile;
		this.players = players;
		this.seasonRecord = seasonRecord;
		this.logo = logo;
		this.matchRecords = matchRecords;
	}

	public TeamProfileVO getProfile() {
		return profile;
	}

	public ArrayList<PlayerProfileVO> getPlayers() {
		return players;
	}

	public TeamSeasonVO getSeasonRecord() {
		return seasonRecord;
	}
	
	public Image getLogo() {
		return logo;
	}
	
	public ArrayList<MatchProfileVO> getMatchRecords(){
		return matchRecords;
	}

}

package vo;

import java.awt.Image;
import java.util.ArrayList;

import data.seasondata.PlayerSeasonRecord;

/**
 * 球员信息详情，包括个人简况、赛季数据分析、所有比赛的数据、全身像
 * @author lsy
 * @version 2015年3月16日  下午8:32:45
 */
public class PlayerDetailVO {
	
	private PlayerProfileVO profile;
	
	private PlayerSeasonRecord seasonRecord;
	
	private ArrayList<PlayerMatchPerformanceVO> matchRecords;
	
	/** 全身像 */
	private Image action;
	
	public PlayerDetailVO(PlayerProfileVO profile,
			PlayerSeasonRecord seasonRecord,
			ArrayList<PlayerMatchPerformanceVO> matchRecords, Image action) {
		super();
		this.profile = profile;
		this.seasonRecord = seasonRecord;
		this.matchRecords = matchRecords;
		this.action = action;
	}

	public PlayerProfileVO getProfile() {
		return profile;
	}

	public PlayerSeasonRecord getSeasonRecord() {
		return seasonRecord;
	}

	public ArrayList<PlayerMatchPerformanceVO> getMatchRecords() {
		return matchRecords;
	}

	public Image getAction() {
		return action;
	}
	
}

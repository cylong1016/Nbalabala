package vo;

import po.MatchProfilePO;
import utility.Constants;

/**
 * 比赛简况
 * @author lsy
 * @version 2015年3月16日  下午7:58:16
 */
public class MatchProfileVO {
	
	/** 赛季，形式为2013-14R 2012-13P */
	private String season;
	
	/**比赛时间 格式为"01-01"*/
	private String time;
	
	/** 对阵球队 格式为"CHA-LAC" */
	private String team;
	
	/** 比分,格式为“85-112” */
	private String score;
	
	public MatchProfileVO(MatchProfilePO po) {
		this.season = po.season;
		this.time = Constants.translateDate(po.date);
		this.team = po.roadAbbr + "-" + po.homeAbbr;
		this.score = po.roadTotalScore + "-" + po.homeTotalScore;
	}

	public MatchProfileVO(String season, String time, String team, String score, String eachSectionScore) {
		super();
		this.season = season;
		this.time = time;
		this.team = team;
		this.score = score;
	}
	
	public boolean equals(Object o) {
		MatchProfileVO vo = (MatchProfileVO)o;
		return time.equals(vo.time) && team.equals(vo.team);
	}

	public String getSeason() {
		return season;
	}

	public String getTime() {
		return time;
	}

	public String getTeam() {
		return team;
	}

	public String getScore() {
		return score;
	}
}

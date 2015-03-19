package vo;

/**
 * 比赛简况
 * @author lsy
 * @version 2015年3月16日  下午7:58:16
 */
public class MatchProfileVO {
	
	/** 赛季，形式为13-14 */
	public String season;
	
	/**比赛时间 格式为"01-01"*/
	public String time;
	
	/** 对阵球队 格式为"CHA-LAC" */
	public String team;
	
	/** 比分,格式为“85-112” */
	public String score;
	
	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;”*/
	public String eachSectionScore;

	public MatchProfileVO(String season, String time, String team, String score, String eachSectionScore) {
		super();
		this.time = time;
		this.team = team;
		this.score = score;
		this.eachSectionScore = eachSectionScore;
	}
	
	public boolean equals(Object o) {
		MatchProfileVO vo = (MatchProfileVO)o;
		return time.equals(vo.time) && team.equals(vo.team);
	}
	
	
}

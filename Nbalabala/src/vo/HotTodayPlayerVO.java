package vo;


/**
 * 当日热点球员VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:40:13
 */
public class HotTodayPlayerVO {
	
	public HotTodayPlayerVO(int top, String name, String teamAbbr,
			String position, int property, PlayerMatchPerformanceVO matchPerformanceVO) {
		super();
		this.top = top;
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.position = position;
		this.property = property;
		this.matchPerformanceVO = matchPerformanceVO;
	}
	
	private int top;
	private String name;
	private String teamAbbr;
	private String position;
	private int property;
	private PlayerMatchPerformanceVO matchPerformanceVO;
	
	public PlayerMatchPerformanceVO getMatchPerformance() {
		return matchPerformanceVO;
	}
	
	public int getTop() {
		return top;
	}
	
	public String getName() {
		return name;
	}
	public String getTeamAbbr() {
		return teamAbbr;
	}
	public String getPosition() {
		return position;
	}
	public int getProperty() {
		return property;
	}
	
	

}

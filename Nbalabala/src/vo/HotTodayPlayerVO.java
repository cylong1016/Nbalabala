package vo;


/**
 * 当日热点球员VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:40:13
 */
public class HotTodayPlayerVO {
	
	public HotTodayPlayerVO(String name, String teamAbbr,
			String position, int property) {
		super();
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.position = position;
		this.property = property;
	}
	
	private String name;
	private String teamAbbr;
	private String position;
	private int property;
	
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

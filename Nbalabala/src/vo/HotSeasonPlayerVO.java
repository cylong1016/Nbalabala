package vo;


/**
 * 赛季热点球员VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:35:35
 */
public class HotSeasonPlayerVO {
	public HotSeasonPlayerVO(int top, String name, String teamAbbr,
			String position, double property) {
		super();
		this.top = top;
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.position = position;
		this.property = property;
	}
	
	private int top;
	private String name;
	private String teamAbbr;
	private String position;
	private double property;
	
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
	public double getProperty() {
		return property;
	}
}

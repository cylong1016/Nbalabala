package vo;


/**
 * 进步最快球员的VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:29:48
 */
public class HotFastestPlayerVO {
	
	public HotFastestPlayerVO(String name, String teamAbbr,
			String position, double property) {
		super();
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.position = position;
		this.property = property;
	}
	
	private String name;
	private String teamAbbr;
	private String position;
	private double property;
	
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

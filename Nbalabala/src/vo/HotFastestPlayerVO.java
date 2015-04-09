package vo;


/**
 * 进步最快球员的VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:29:48
 */
public class HotFastestPlayerVO {
	
	public HotFastestPlayerVO(int top, String name, String teamAbbr,
			String position, double formerFiveAvg, double[]recentFive, double promotion) {
		super();
		this.top = top;
		this.name = name;
		this.teamAbbr = teamAbbr;
		this.position = position;
		this.formerFiveAvg = formerFiveAvg;
		this.recentFive = recentFive;
		this.promotion = promotion;
	}
	
	private int top;
	private String name;
	private String teamAbbr;
	private String position;
	private double formerFiveAvg;
	private double [] recentFive;
	private double promotion;
	
	public double[] getRecentFive() {
		return recentFive;
	}
	
	public double getPromotion() {
		return promotion;
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
	public double getProperty() {
		return formerFiveAvg;
	}

}

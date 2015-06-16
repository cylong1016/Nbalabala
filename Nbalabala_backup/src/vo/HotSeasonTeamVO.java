package vo;


/**
 * 赛季热点球队VO
 * @author Issac Ding
 * @version 2015年4月8日  下午7:33:01
 */
public class HotSeasonTeamVO {
	
	public HotSeasonTeamVO(int top, String abbr, String league,
			double property) {
		super();
		this.top = top;
		this.abbr = abbr;
		this.league = league;
		this.property = property;
	}
	
	/** 第几名 */
	private int top;
	private String abbr;
	private String league; //值为 "东部" 或者 "西部"
	
	/** 作为依据的那一项属性的数值 */
	private double property;
	
	public int getTop() {
		return top;
	}
	
	public String getAbbr() {
		return abbr;
	}
	public String getLeague() {
		return league;
	}
	public double getProperty() {
		return property;
	}
	

}

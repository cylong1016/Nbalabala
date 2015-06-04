/**
 * 
 */
package po;


/**
 * 球队资料
 * @author Issac Ding
 * @since 2015年6月1日 下午9:46:56
 * @version 1.0
 */
public class TeamProfilePO {
	
	/** 缩写 */
	public String abbr;
	/** 球队名 */
	public String name;
	/** 所在地 */
	public String location;
	/** 所属联盟 */
	public char league;
	/** 赛区 */
	public String division;
	/** 主场 */
	public String home;
	/** 建队时间 */
	public int since;
	
	
	public String getAbbr() {
		return abbr;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public char getLeague() {
		return league;
	}
	public String getDivision() {
		return division;
	}
	public String getHome() {
		return home;
	}
	public int getSince() {
		return since;
	}
	
	

}

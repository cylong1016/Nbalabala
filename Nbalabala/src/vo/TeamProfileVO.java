package vo;

import utility.Constants;
import enums.ScreenDivision;

/**
 * 球队简况
 * @author Issac Ding
 * @version 2015年3月19日  下午4:56:50
 */
public class TeamProfileVO {
	
	/** 球队全名 */
	private String name;
	/** 球队缩写 */
	private String abbr;
	/** 所在地*/
	private String location;
	/** 东部还是西部*/
	private ScreenDivision area;
	/** 赛区，取值为六个区 */
	private ScreenDivision division;
	/** 主场 */
	private String home;
	/** 建立时间 */
	private String since;

	public TeamProfileVO(String name, String abbr, String location, String area, String division, String home, String since) {
		this.name = name;
		this.abbr = abbr;
		this.location = location;
		
		if (area.equals("E")) this.area = ScreenDivision.EAST;
		else this.area = ScreenDivision.WEST;
		
		switch (division) {
		case "Southeast":
			this.division = ScreenDivision.SOUTH_EAST;
			break;
		case "Atlantic":
			this.division = ScreenDivision.ATLANTIC;
			break;
		case "Central":
			this.division = ScreenDivision.CENTRAL;
			break;
		case "Southwest":
			this.division = ScreenDivision.SOUTH_WEST;
			break;
		case "Northwest":
			this.division = ScreenDivision.NORTH_WEST;
			break;
		case "Pacific":
			this.division = ScreenDivision.PACIFIC;
			break;
		default:
			break;
		}
		
		this.home = home;
		this.since = since;
	}

	public String getName() {
		return this.name;
	}

	public String getAbbr() {
		return this.abbr;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public ScreenDivision getArea() {
		return area;
	}

	public ScreenDivision getDivision() {
		return this.division;
	}

	public String getHome() {
		return this.home;
	}

	public String getSince() {
		return this.since;
	}
	
	public String getDivisionString() {
		String areaString = Constants.translateDivision(area);
		String divisionString = Constants.translateDivision(division);
		return areaString + "-" + divisionString;
	}

}

package po;

/**
 * 球队基本信息
 * @author cylong
 * @version 2015年3月13日 下午9:02:54
 */
public class TeamPO {

	/** 球队全名 */
	private String name;
	/** 球队缩写 */
	private String abbr;
	/** 所在地 */
	private String location;
	/** 赛区 */
	private String division;
	/** 分区 */
	private String partition;
	/** 主场 */
	private String home;
	/** 建立时间 */
	private String time;

	public TeamPO(String name, String abbr, String location, String division, String partition, String home, String time) {
		this.name = name;
		this.abbr = abbr;
		this.location = location;
		this.division = division;
		this.partition = partition;
		this.home = home;
		this.time = time;
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

	public String getDivision() {
		return this.division;
	}

	public String getPartition() {
		return this.partition;
	}

	public String getHome() {
		return this.home;
	}

	public String getTime() {
		return this.time;
	}

}

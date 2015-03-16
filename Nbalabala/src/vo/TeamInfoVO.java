package vo;

import java.util.ArrayList;

/**
 * 球队简况
 * @author lsy
 * @version 2015年3月16日  下午7:52:45
 */
public class TeamInfoVO {
	/** 球队全名 */
	public String name;
	/** 球队缩写 */
	public String abbr;
	/** 所在地 */
	public String location;
	/** 赛区 */
	public String division;
	/** 分区 */
	public String partition;
	/** 主场 */
	public String home;
	/** 建立时间 */
	public String time;
	/** 球员*/
	public ArrayList<String> player;
	
	public TeamInfoVO(String name, String abbr, String location, String division, String partition, String home,
			String time, ArrayList<String> player) {
		super();
		this.name = name;
		this.abbr = abbr;
		this.location = location;
		this.division = division;
		this.partition = partition;
		this.home = home;
		this.time = time;
		this.player = player;
	}
	
	
	
}

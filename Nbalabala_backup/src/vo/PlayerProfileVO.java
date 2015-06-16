package vo;

import java.awt.Image;

import utility.Constants;

/**
 * 球员简况，前5个是要显示在所有球员列表中的
 * @author lsy
 * @version 2015年3月16日  下午7:25:54
 */

public class PlayerProfileVO {

	/** 头像 */
	private Image portrait;
	
	/**球员姓名*/
	private String name;
	
	/**所属球队*/
	private String team;
	
	/** 球员号码 */
	private String number;
	
	/** 球员位置 */
	private String position;
	
	/** 生日 */
	private String birth;
	
	/** 身高 */
	private String height;
	
	/** 体重 */
	private String weight;
	
	/** 年龄 */
	private String age;
	
	/** 球龄 */
	private String veteran;
	
	/** 毕业学校 */
	private String school;
	
	public PlayerProfileVO(Image portrait, String name, String team,
			String number, String position, String height, String weight,
			String birth, String age, String exp, String school) {
		super();
		this.portrait = portrait;
		this.name = name;
		this.team = team;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.veteran = exp;
		this.school = school;
	}
	
	public PlayerProfileVO(Image portrait, String name) {
		this.portrait = portrait;
		this.name = name;
		this.team = Constants.UNKNOWN;
		this.number = Constants.UNKNOWN;
		this.position = Constants.UNKNOWN;
		this.height = Constants.UNKNOWN;
		this.weight = Constants.UNKNOWN;
		this.birth = Constants.UNKNOWN;
		this.age = Constants.UNKNOWN;
		this.veteran = Constants.UNKNOWN;
		this.school = Constants.UNKNOWN;
	}

	public Image getPortrait() {
		return portrait;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public String getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public String getBirth() {
		return Constants.translateDate(birth);
	}
	
	public String getWeight() {
		return Constants.translateWeight(weight);
	}
	
	public String getExp() {
		return Constants.translateVeteran(veteran);
	}

	public String getHeight() {
		return Constants.translateHeight(height);
	}

	public String getAge() {
		return age;
	}

	public String getSchool() {
		return school;
	}
	
	public PlayerProfileVO(Image portrait, String name, String abbr) {
		this.portrait = portrait;
		this.name = name;
		this.team = abbr;
		this.team = Constants.UNKNOWN;
		this.number = Constants.UNKNOWN;
		this.position = Constants.UNKNOWN;
		this.height = Constants.UNKNOWN;
		this.weight = Constants.UNKNOWN;
		this.birth = Constants.UNKNOWN;
		this.age = Constants.UNKNOWN;
		this.veteran = Constants.UNKNOWN;
		this.school = Constants.UNKNOWN;
	}

}

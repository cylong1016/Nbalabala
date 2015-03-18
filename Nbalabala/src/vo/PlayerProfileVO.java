package vo;

import java.awt.Image;

/**
 * 球员简况
 * @author lsy
 * @version 2015年3月16日  下午7:25:54
 */

public class PlayerProfileVO {

	/** 头像 */
	public Image photo;
	
	/**球员姓名*/
	public String name;
	
	/**所属球队*/
	public String team;
	
	/** 球员号码 */
	public String number;
	
	/** 球员位置 */
	public String position;
	
	/** 身高 */
	public String height;
	
	/** 体重 */
	public String weight;
	
	/** 生日 */
	public String birth;
	
	/** 年龄 */
	public String age;
	
	/** 球龄 */
	public String exp;
	
	/** 毕业学校 */
	public String school;
	
	public PlayerProfileVO(Image photo, String name, String team,
			String number, String position, String height, String weight,
			String birth, String age, String exp, String school) {
		super();
		this.photo = photo;
		this.name = name;
		this.team = team;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}

	
}

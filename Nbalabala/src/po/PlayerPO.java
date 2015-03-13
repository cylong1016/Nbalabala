package po;

import java.util.ArrayList;

/**
 * 球员基本信息
 * @author cylong
 * @version 2015年3月13日 下午6:52:57
 */
public class PlayerPO {

	/** 名字 */
	private String name;
	/** 球衣号码 */
	private String number;
	/** 位置 */
	private String position;
	/** 身高（英尺 – 英寸） */
	private String height;
	/** 体重（磅） */
	private int weight;
	/** 生日（月 日, 年） */
	private String birth;
	/** 年龄 */
	private int age;
	/** 球龄 */
	private String exp;
	/** 毕业学校 */
	private String school;

	public PlayerPO(String name, String number, String position, String height, int weight, String birth, int age, String exp, String school) {
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}

	/**
	 * 以存储在ArrayList中的球员信息创建PlayerPO
	 * @param playerInfo
	 * @author cylong
	 * @version 2015年3月13日 下午7:39:27
	 */
	public PlayerPO(ArrayList<String> playerInfo) {
		this.name = playerInfo.get(0);
		this.number = playerInfo.get(1);
		this.position = playerInfo.get(2);
		this.height = playerInfo.get(3);
		this.weight = Integer.parseInt(playerInfo.get(4));
		this.birth = playerInfo.get(5);
		this.age = Integer.parseInt(playerInfo.get(6));
		this.exp = playerInfo.get(7);
		this.school = playerInfo.get(8);
	}

	public String getName() {
		return this.name;
	}

	public String getNumber() {
		return this.number;
	}

	public String getPosition() {
		return this.position;
	}

	public String getHeight() {
		return this.height;
	}

	public int getWeight() {
		return this.weight;
	}

	public String getBirth() {
		return this.birth;
	}

	public int getAge() {
		return this.age;
	}

	public String getExp() {
		return this.exp;
	}

	public String getSchool() {
		return this.school;
	}

}

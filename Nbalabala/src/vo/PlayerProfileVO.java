package vo;

/**
 * 球员简况
 * @author lsy
 * @version 2015年3月16日  下午7:25:54
 */

public class PlayerProfileVO {
	
	/**球员姓名*/
	public String name;
	
	/** 球员号码 */
	public int number;
	
	/** 球员位置 */
	public String location;
	
	/** 身高 */
	public String height;
	
	/** 体重 */
	public int weight;
	
	/** 生日 */
	public String birth;
	
	/** 年龄 */
	public int age;
	
	/** 球龄 */
	public int exp;
	
	/** 毕业学校 */
	public String school;

	public PlayerProfileVO(String name, int number, String location, String height, int weight, String birth,
			int age, int exp, String school) {
		super();
		this.name = name;
		this.number = number;
		this.location = location;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}
}

package vo;

import java.awt.Image;
import java.text.DecimalFormat;

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
	private String exp;
	
	/** 毕业学校 */
	private String school;
	
	/** 无资料的时候显示的提示 */
	private static final String UNKNOWN = "无资料";
	
	public PlayerProfileVO(Image portrait, String name, String team,
			String number, String position, String height, String weight,
			String birth, String age, String exp, String school) {
		super();
		this.portrait = portrait;
		this.name = name;
		this.team = team;
		this.number = number;
		this.position = position;
		
		// 身高除了显示英尺、英寸以外还换算为厘米，保留一位小数
		String [] s = height.split("-");
		int feet = Integer.parseInt(s[0]);
		int inches = Integer.parseInt(s[1]);
		double cms = feet * 30.48 + inches * 2.54;
		DecimalFormat decimalFormat = new DecimalFormat(".#");
		String cmsString = decimalFormat.format(cms);	
		this.height = s[0] + "英尺" + s[1] + "英寸（" +cmsString + "厘米）";
		
		//体重显示磅和千克，保留一位小数
		double kgs = 0.4536 * Integer.parseInt(weight);
		String kgsString = decimalFormat.format(kgs);
		this.weight = weight + "磅（" + kgsString +"千克）";
		
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}
	
	public PlayerProfileVO(Image portrait, String name) {
		this.portrait = portrait;
		this.name = name;
		this.team = UNKNOWN;
		this.number = UNKNOWN;
		this.position = UNKNOWN;
		this.height = UNKNOWN;
		this.weight = UNKNOWN;
		this.birth = UNKNOWN;
		this.age = UNKNOWN;
		this.exp = UNKNOWN;
		this.school = UNKNOWN;
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
		return birth;
	}

	public String getHeight() {
		return height;
	}

	public String getWeight() {
		return weight;
	}

	public String getAge() {
		return age;
	}

	public String getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
	}

	public static String getUnknown() {
		return UNKNOWN;
	}
}

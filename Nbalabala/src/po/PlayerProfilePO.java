/**
 * 
 */
package po;

import java.sql.Date;

/**
 * 球员资料持久化对象
 * @author Issac Ding
 * @since 2015年6月2日 下午5:20:45
 * @version 1.0
 */
public class PlayerProfilePO {
	
	/** 球员姓名 */
	public String name;
	/** 加入NBA年份 */
	public int fromYear;
	/** 最后一次上场年份 */
	public int toYear;
	/** 位置，形如F或C-F */
	public String position;
	/** 身高英尺数 */
	public int heightFoot;
	/** 身高英寸数 */
	public int heightInch;
	/** 体重（磅） */
	public int weight;
	/** 出生日期 */
	public Date birthDate;
	/** 毕业学校 */
	public String school;
	
	public String getName() {
		return name;
	}
	public int getFromYear() {
		return fromYear;
	}
	public int getToYear() {
		return toYear;
	}
	public String getPosition() {
		return position;
	}
	public int getHeightFoot() {
		return heightFoot;
	}
	public int getHeightInch() {
		return heightInch;
	}
	public int getWeight() {
		return weight;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getSchool() {
		return school;
	}

	public String getShortName() {
		return this.name.substring(0, name.length() - 3); //去掉形如$01的编号
	}
}

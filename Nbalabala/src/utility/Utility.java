package utility;

import java.sql.Date;
import java.util.Calendar;


/**
 * 放一些在多处使用的算法
 * @author Issac Ding
 * @version 2015年3月18日  下午10:20:35
 */
public class Utility {
	
	
	public static String getRankStr(int rank) {
		if (rank ==0) return "";
		return rank + getRankEnd(rank);
	}
	
	public static String getRankEnd(int rank) {
		String th = null;
		int end = rank % 10;
		switch (end) {
		case 1:
			th = "st";
			break;
		case 2:
			th = "nd";
			break;
		case 3:
			th = "rd";
			break;
		default:
			th = "th";
		}
		return th;
	}
	
	public static int getAgeByBirthday(Date birthDate) {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		calendar.setTimeInMillis(birthDate.getTime());
		int birthYear = calendar.get(Calendar.YEAR);
		return thisYear - birthYear;
	}

}

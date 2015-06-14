package utility;

import java.sql.Date;
import java.util.Calendar;


/**
 * 放一些在多处使用的算法
 * @author Issac Ding
 * @version 2015年3月18日  下午10:20:35
 */
public class Utility {
	
	public static String trimName(String name) {
		return name.substring(0, name.length() - 3);
	}
	
	public static String increaseRegularSeason(String season) {
		int startYear = Integer.parseInt(season.substring(0, 4)) + 1;
		return getRegularStringByStartYear(startYear);
	}
	
	public static String shortenName(String name) {
		String trimed = trimName(name);
		if (trimed.length() <= 13) return trimed;
		String [] names = trimed.split(" ");
		if (names.length > 2) {
			return names[0].charAt(0) + ". " + names[1].charAt(0) + ". " +names[2];
		}else if (names.length > 1) {
			return names[0].charAt(0) + ". " + names[1];
		}else {
			return trimed;
		}
	}
	
	public static String shortenTrimedName(String trimed) {
		if (trimed.length() <= 13) return trimed;
		String [] names = trimed.split(" ");
		if (names.length > 2) {
			return names[0].charAt(0) + ". " + names[1].charAt(0) + ". " +names[2];
		}else if (names.length > 1) {
			return names[0].charAt(0) + ". " + names[1];
		}else {
			return trimed;
		}
	}
	
	public static String getCurrentAbbr(String oldAbbr) {
		switch (oldAbbr) {
		case "CHA":
			return "CHO";
		case "CHH":
		case "NOH":
		case "NOK":
			return "NOP";
		case "WSB":
			return "WAS";
		case "VAN":
			return "MEM";
		case "SEA":
			return "OKC";
		case "NJN":
			return "BRK";
		default:
			return oldAbbr;
		}
	}
	
	/** 考虑球队改名的情况，根据赛季追溯球队的缩写 */
	public static String getOldAbbr(String season, String abbr) {
		int startYear = Integer.parseInt(season.substring(0, 4));
		switch (abbr) {
		case "BRK":
			if (startYear < 2012) return "NJN";
			break;
		case "OKC":
			if (startYear < 2008) return "SEA";
			break;
		case "MEM":
			if (startYear < 2001) return "VAN";
			break;
		case "WAS":
			if (startYear < 1997) return "WSB";
			break;
		case "CHO":
			if (startYear < 2014) return "CHA";
			break;
		case "NOP":
			if (startYear == 2005 || startYear == 2006) return "NOK";
			else if (startYear < 2001) return "CHH";
			else if (startYear >= 2013) return "NOP";
			else return "NOH";
		default:
			break;
		}
		return abbr;
	}
	
	
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
		if (birthDate == null) return 0;
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		calendar.setTimeInMillis(birthDate.getTime());
		int birthYear = calendar.get(Calendar.YEAR);
		return thisYear - birthYear;
	}
	
	public static String getRegularStringByStartYear(int start) {
		String endYear = String.valueOf(start + 1);
		return start + "-" + endYear.substring(2,4) + "R";
	}
	
	public static String getOverallSeason(int start) {
		String endYear = String.valueOf(start + 1);
		return start + "-" + endYear.substring(2,4);
	}
	
	
	public static String getPlayerIDByName(String name) {
		if (name.equals("Harrison Barnes") || name.equals("James Jones") 
				|| name.equals("David Lee")) {
			return name + "$02";
		}else {
			return name + "$01";
		}
	}

}

package utility;

import java.util.HashMap;

import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月18日  下午8:39:08
 */
public class Constants {
	
	/** 无资料的时候显示的提示 */
	public static final String UNKNOWN = "无资料";
	
	private static HashMap<String, String> abbrToEngMandarin;
	
	public static String translateDivision(ScreenDivision division) {
		switch (division) {
		case EAST:
			return "东部";
		case WEST:
			return "西部";
		case PACIFIC:
			return "太平洋";
		case ATLANTIC:
			return "大西洋";
		case SOUTH_EAST:
			return "东南";
		case SOUTH_WEST:
			return "西南";
		case CENTRAL:
			return "中央";
		case NORTH_WEST:
			return "西北";
		default:
			return "";
		}
	}
	
	static {
		abbrToEngMandarin = new HashMap<String, String>();
		
		abbrToEngMandarin.put("BOS", "凯尔特人 Celtics");
		abbrToEngMandarin.put("BKN", "篮网 Nets");
		abbrToEngMandarin.put("NYK", "尼克斯 Knicks");
		abbrToEngMandarin.put("PHI", "76人 76ers");
		abbrToEngMandarin.put("TOR", "猛龙 Raptors");
		
		abbrToEngMandarin.put("DEN", "掘金 Nuggets");
		abbrToEngMandarin.put("MIN", "森林狼 Timberwolves");
		abbrToEngMandarin.put("OKC", "雷霆 Thunder");
		abbrToEngMandarin.put("POR", "开拓者 Trail Blazers");
		abbrToEngMandarin.put("UTA", "爵士 Jazz");
		
		abbrToEngMandarin.put("CHI", "公牛 Bulls");
		abbrToEngMandarin.put("CLE", "骑士 Cavaliers");
		abbrToEngMandarin.put("DET", "活塞 Pistons");
		abbrToEngMandarin.put("IND", "步行者 Pacers");
		abbrToEngMandarin.put("MIL", "雄鹿 Bucks");
		
		abbrToEngMandarin.put("GSW", "勇士 Warriors");
		abbrToEngMandarin.put("LAC", "快船 Clippers");
		abbrToEngMandarin.put("LAL", "湖人 Lakers");
		abbrToEngMandarin.put("PHX", "太阳 Suns");
		abbrToEngMandarin.put("SAC", "国王 Kings");
		
		abbrToEngMandarin.put("ATL", "老鹰 Hawks");
		abbrToEngMandarin.put("CHA", "黄蜂 Hornets");
		abbrToEngMandarin.put("MIA", "热火 Heat");
		abbrToEngMandarin.put("ORL", "魔术 Magic");
		abbrToEngMandarin.put("WAS", "奇才 Wizards");
		
		abbrToEngMandarin.put("DAL", "小牛 Mavericks");
		abbrToEngMandarin.put("HOU", "火箭 Rockets");
		abbrToEngMandarin.put("MEM", "灰熊 Grizzlies");
		abbrToEngMandarin.put("NOP", "鹈鹕 Pelicans");
		abbrToEngMandarin.put("SAS", "马刺 Spurs");
	}
	
	public static String getTeamMandarinEngByAbbr(String abbr) {
		return abbrToEngMandarin.get(abbr);
	}
	
	public static String translateDate(String date) {
		System.out.println(date);
		String[] strings = date.split(" |,");
		int month;
		if (strings.length == 0) {
			return UNKNOWN;
		}
		switch (strings[0]) {
		case "JAN":
			month = 1;
			break;
		case "FEB":
			month = 2;
			break;
		case "MAR":
			month = 3;
			break;
		case "APR":
			month = 4;
			break;
		case "MAY":
			month = 5;
			break;
		case "JUN":
			month = 6;
			break;
		case "JUL":
			month = 7;
			break;
		case "AUG":
			month = 8;
			break;
		case "SEP":
			month = 9;
			break;
		case "OCT":
			month = 10;
			break;
		case "NOV":
			month = 11;
			break;
		default:
			month = 12;
		}
		return strings[3] + "年" + month + "月" + strings[1] + "日"; 
	}

}

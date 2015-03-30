package utility;

import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月18日  下午8:39:08
 */
public class Constants {
	
	/** 无资料的时候显示的提示 */
	public static final String UNKNOWN = "无资料";
	
	public static final String [] TEAM_SEASON_HEADERS = {"序号", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
		"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率",
		"进攻回合", "进攻效率", "防守回合", "防守效率", "抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分"};
	
	
	public static final String [] PLAYER_SEASON_HEADERS = {"序号", "球员名称", "所属球队", "参赛场数", "先发场数", "在场时间", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
		"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "助攻", "助攻率", "盖帽",
		"盖帽率", "犯规", "犯规率", "得分", "两双", "得分/篮板/助攻", "效率", "GmSc", "真实命中率", "投篮效率", "进攻篮板率",
		"防守篮板率", "总篮板率", "抢断", "抢断率", "失误", "失误率", "使用率"};
	
	public static final String [] TEAM_ABBR = {"BOS","BKN","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHA","MIA","ORL","WAS",
		"GSW","LAC","LAL","PHX","SAC","DEN","MIN","OKC","POR","UTA","DAL","HOU","MEM","NOP","SAS"};
	
	public static final String [] TEAM_NAMES = {"凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂", "热火",
		"魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛",
		"火箭", "灰熊", "鹈鹕", "马刺"};
	
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
	
	public static String translateTeamAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
			return "凯尔特人";
		case "BKN":
			return "篮网";
		case "NYK":
			return "尼克斯";
		case "PHI":
			return "76人";
		case "TOR":
			return "猛龙";
		case "DAL":
			return "小牛";
		case "HOU":
			return "火箭";
		case "MEM":
			return "灰熊";
		case "NOP":
			return "鹈鹕";
		case "SAS":
			return "马刺"; 
		case "ATL":
			return "老鹰";
		case "CHA":
			return "黄蜂";
		case "MIA":
			return "热火";
		case "ORL":
			return "魔术";
		case "WAS":
			return "奇才"; 
		case "GSW":
			return "勇士";
		case "LAC":
			return "快船";
		case "LAL":
			return "湖人";
		case "PHX":
			return "太阳";
		case "SAC":
			return "国王";
		case "CHI":
			return "公牛";
		case "CLE":
			return "骑士";
		case "DET":
			return "活塞";
		case "IND":
			return "步行者";
		case "MIL":
			return "雄鹿";
		case "DEN":
			return "掘金";
		case "MIN":
			return "森林狼";
		case "OKC":
			return "雷霆";
		case "POR":
			return "开拓者";
		case "UTA":
			return "爵士";
		default:
			return "未知";
		}
	}
	
//	static {
//		abbrToEngMandarin = new HashMap<String, String>();
//		
//		abbrToEngMandarin.put("BOS", "凯尔特人 Celtics");
//		abbrToEngMandarin.put("BKN", "篮网 Nets");
//		abbrToEngMandarin.put("NYK", "尼克斯 Knicks");
//		abbrToEngMandarin.put("PHI", "76人 76ers");
//		abbrToEngMandarin.put("TOR", "猛龙 Raptors");
//		
//		abbrToEngMandarin.put("DEN", "掘金 Nuggets");
//		abbrToEngMandarin.put("MIN", "森林狼 Timberwolves");
//		abbrToEngMandarin.put("OKC", "雷霆 Thunder");
//		abbrToEngMandarin.put("POR", "开拓者 Trail Blazers");
//		abbrToEngMandarin.put("UTA", "爵士 Jazz");
//		
//		abbrToEngMandarin.put("CHI", "公牛 Bulls");
//		abbrToEngMandarin.put("CLE", "骑士 Cavaliers");
//		abbrToEngMandarin.put("DET", "活塞 Pistons");
//		abbrToEngMandarin.put("IND", "步行者 Pacers");
//		abbrToEngMandarin.put("MIL", "雄鹿 Bucks");
//		
//		abbrToEngMandarin.put("GSW", "勇士 Warriors");
//		abbrToEngMandarin.put("LAC", "快船 Clippers");
//		abbrToEngMandarin.put("LAL", "湖人 Lakers");
//		abbrToEngMandarin.put("PHX", "太阳 Suns");
//		abbrToEngMandarin.put("SAC", "国王 Kings");
//		
//		abbrToEngMandarin.put("ATL", "老鹰 Hawks");
//		abbrToEngMandarin.put("CHA", "黄蜂 Hornets");
//		abbrToEngMandarin.put("MIA", "热火 Heat");
//		abbrToEngMandarin.put("ORL", "魔术 Magic");
//		abbrToEngMandarin.put("WAS", "奇才 Wizards");
//		
//		abbrToEngMandarin.put("DAL", "小牛 Mavericks");
//		abbrToEngMandarin.put("HOU", "火箭 Rockets");
//		abbrToEngMandarin.put("MEM", "灰熊 Grizzlies");
//		abbrToEngMandarin.put("NOP", "鹈鹕 Pelicans");
//		abbrToEngMandarin.put("SAS", "马刺 Spurs");
//	}
	
	public static String translateDate(String date) {
		String[] strings = date.split(" |,");
		if (strings.length == 1) {
			return UNKNOWN;
		}
		int month;
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
		return strings[3] + "/" + month + "/" + strings[1]; 
	}

}

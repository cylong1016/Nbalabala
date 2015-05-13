package utility;

import data.playerdata.PlayerData;
import data.playerdata.PlayerImageCache;
import data.seasondata.SeasonData;
import data.teamdata.TeamData;
import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月18日  下午8:39:08
 */
public class Constants {
	
//	public static void changeENtoCN(){
//		onePlayerDataHeaders = ONE_PLAYER_DATA_HEADERS_CN;
//		
//	}
//	
//	public static void changeCNtoEN(){
//		onePlayerDataHeaders = ONE_PLAYER_DATA_HEADERS_EN;
//		
//	}
	
	/** 无资料的时候显示的提示 */
	public static final String UNKNOWN = "无资料";
	
	public static String dataSourcePath = "NBAdata\\";
	
	/** 如果在程序运行中改变数据目录，需要清空已经读取的数据，同时由Controller控制返回首页 */
	public static void changeDataSourcePath(String newPath) {
		dataSourcePath = newPath + "\\";
		PlayerImageCache.reloadImages();
		PlayerData.reloadPlayers();
		SeasonData.reloadMatches();
		TeamData.reloadTeams();
	}
	
	/** 球员个人信息页面中的赛季数据的表头 */
	public static String [] onePlayerDataHeaders = {
		"赛季","场数","先发","在场时间","投篮%","三分%","罚球%","进攻","防守","篮板","助攻","抢断","盖帽","失误","犯规","得分"
	};
	/** 球员个人信息页面中的赛程数据的表头 */
	public static String [] onePlayerMatchHeaders = {
		"日期","对手","首发","时间","投篮命中","投篮出手","三分命中","三分出手","罚球命中","罚球出手",
			"进攻篮板","防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分"
	};
	
	/** 所有球员赛季数据中的表格分成四部分，为基本、进攻、防守、高阶 */
	public static String []basicPlayerHeaders = {"序号","球员名称","所属球队","参赛","先发","在场时间","得分","篮板",
		"助攻","盖帽","抢断","两双","得分篮板助攻","失误","犯规"};
	public static String []advancedPlayerHeaders = {"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率",
		"总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","失误率","犯规率"};
	public static String []offensivePlayerHeaders = {"序号","球员名称","投篮命中","投篮出手","投篮%","三分命中","三分出手","三分%",
		"罚球命中","罚球出手", "罚球%","助攻","助攻率","真实命中率","投篮效率"};
	public static String []defensivePlayerHeaders = {"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板",
		"总篮板","盖帽","抢断","进攻篮板率","防守篮板率","总篮板率","盖帽率","抢断率"};
	
	/** 所有球员赛季数据中的表格分成三部分，为基本、进攻、防守 */
	public static String []basicTeamHeaders = {"序号","球队名称","胜场数","总场数","胜率","得分","篮板","助攻","盖帽",
		"抢断","失误","犯规","进攻效率","防守效率"}; //14
	public static String []offensiveTeamHeaders = {"序号","球队名称","投篮命中","投篮出手","投篮%","三分命中","三分出手","三分%",
		"罚球命中","罚球出手","罚球%","助攻率","进攻回合","进攻效率"};
	public static String []defensiveTeamHeaders = {"序号","球队名称","进攻篮板","防守篮板","总篮板","进攻篮板效率",
		"防守篮板效率","盖帽","抢断","抢断效率","防守回合","防守效率","对手得分","对手投篮%"};
	
	
	
	/** 球员个人信息页面中的赛季数据的空表 */
	public static Object [][] onePlayerDataTableEmptyContent = 
		{{"赛季平均","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"赛季总计","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
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
	
	public static final String [] ALL_TEAM_NAMES = {"所有球队","凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂", "热火",
		"魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛",
		"火箭", "灰熊", "鹈鹕", "马刺"};
	
	public static String translateDivision(ScreenDivision division) {
		return translater.translateTeamDivision(division);
	}
	
	public static String translateLeague(ScreenDivision league) {
		return translater.translateTeamLeague(league);
	}
	
	public static String correctOldAbbr(String old) {
		if (old.equals("NJN")) return "BKN";
		else if (old.equals("NOH")) return "NOP";
		else return old;
	}
	
	public static String getLeagueStringByAbbr(String abbr) {
		return translater.translateTeamLeague(getAreaByAbbr(abbr));
	}
	
	public static ScreenDivision getAreaByAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
		case "BKN":
		case "NJN":	//篮网队以前 新泽西篮网队，缩写NJN
		case "NYK":
		case "PHI":
		case "TOR":
		case "CHI":
		case "CLE":
		case "DET":
		case "IND":
		case "MIL":
		case "ATL":
		case "CHA":
		case "MIA":
		case "ORL":
		case "WAS":
			return ScreenDivision.EAST;
		default:
			return ScreenDivision.WEST;
		}
	}
	
	public static ScreenDivision getDivisionByAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
		case "BKN":
		case "NJN":
		case "NYK":
		case "PHI":
		case "TOR":
			return ScreenDivision.ATLANTIC;
		case "CHI":
		case "CLE":
		case "DET":
		case "IND":
		case "MIL":
			return ScreenDivision.CENTRAL;
		case "ATL":
		case "CHA":
		case "MIA":
		case "ORL":
		case "WAS":
			return ScreenDivision.SOUTH_EAST;
		case "GSW":
		case "LAC":
		case "LAL":
		case "PHX":
		case "SAC":
			return ScreenDivision.PACIFIC;
		case "DEN":
		case "MIN":
		case "OKC":
		case "POR":
		case "UTA":
			return ScreenDivision.NORTH_WEST;
		default:
			return ScreenDivision.SOUTH_WEST;
		}
	}
	
	public static String translateTeamAbbr(String abbr) {
		return translater.translateTeamAbbr(abbr);
	}
	
	public static String getAbbrByName(String name) {
		switch (name) {
		case "凯尔特人":
			return "BOS";
		case "篮网":
			return "BKN";
		case "尼克斯":
			return "NYK";
		case "76人":
			return "PHI";
		case "猛龙":
			return "TOR";
		case "小牛":
			return "DAL";
		case "火箭":
			return "HOU";
		case "灰熊":
			return "MEM";
		case "鹈鹕":
			return "NOP";
		case "马刺":
			return "SAS"; 
		case "老鹰":
			return "ATL";
		case "黄蜂":
			return "CHA";
		case "热火":
			return "MIA";
		case "魔术":
			return "ORL";
		case "奇才":
			return "WAS"; 
		case "勇士":
			return "GSW";
		case "快船":
			return "LAC";
		case "湖人":
			return "LAL";
		case "太阳":
			return "PHX";
		case "国王":
			return "SAC";
		case "公牛":
			return "CHI";
		case "骑士":
			return "CLE";
		case "活塞":
			return "DET";
		case "步行者":
			return "IND";
		case "雄鹿":
			return "MIL";
		case "掘金":
			return "DEN";
		case "森林狼":
			return "MIN";
		case "雷霆":
			return "OKC";
		case "开拓者":
			return "POR";
		case "爵士":
			return "UTA";
		default:
			return "未知";
		}
	}
	
	public static String getNameByAbbr(String abbr) {
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
	
	private static Translater translater = new TranslaterCN();
	
	public static String briefText = "资料";
	public static String seasonDataText = "赛季数据";
	public static String matchesDataText = "比赛数据";
	
	public static String portraitText = "球员头像";
	public static String nameText = "球员名称";
	public static String teamText = "球队名称";
	public static String numberText = "球衣号码";
	public static String positionText = "位置";
	public static String birthdayText = "生日";
	public static String schoolText = "毕业学校";
	public static String veteranText = "球龄";
	
	public static String winsText = "胜";
	public static String losesText = "负";
	public static String divisionText = "所属赛区:";
	public static String homeText = "主场:";
	public static String sinceText = "建队时间:";
	public static String kingText = "数据王";
	public static String overallRankText = "总排名";
	public static String lineupText = "阵容";
	
	public static String scoreAvgText = "场均得分";
	public static String reboundAvgText = "场均篮板";
	public static String assistAvgText = "场均助攻";
	
	public static String translateHeight(String height){
		return translater.translateHeight(height);
	}
	public static String translateWeight(String weight){
		return translater.translateWeight(weight);
	}
	public static String translateDate(String date){
		return translater.translateDate(date);
	}
	/** veteran即球龄 */
	public static String translateVeteran(String veteran){	
		return translater.translateVeteran(veteran);
	}
	public static String translatePosition(String position) {
		return translater.translatePosition(position);
	}
	
	/** 球队的所在地，显示在球队信息标题上 如   休斯顿 火箭 */
	public static String translateTeamAbbrToLocation(String abbr) {
		return translater.translateTeamAbbrToLocation(abbr);
	}

}

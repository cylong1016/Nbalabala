package utility;

import java.sql.Date;

import data.playerdata.PlayerImageCache;
import data.teamdata.TeamLogoCache;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;
import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月18日  下午8:39:08
 */
public class Constants {
	
	public static boolean isEng = false;
	
	
	public static void setCHNTraditional() {
		
	}
	
	public static void setCHNSimplified() {
		isEng = false;
		Constants.translater = new TranslaterCN();
		
		playerType = TextCHN.playerType;
		
		leagueText = TextCHN.leagueText;
		teamShortText = TextCHN.teamShortText;
		promotionText = TextCHN.promotionText;
		positionShortText = TextCHN.positionShortText;
		
		leagueAvg = TextCHN.leagueAvg;
		hotType	= TextCHN.hotType;
		scoreText = TextCHN.scoreText;
		formerFiveAvgText = TextCHN.formerFiveAvgText;
		POSITION_SELECT_TEXT = TextCHN.POSITION_SELECT_TEXT;
		DIVISION_SELECT_TEXT = TextCHN.DIVISION_SELECT_TEXT;
		BASIS_SELECT_TEXT = TextCHN.BASIS_SELECT_TEXT;
		TOTAL_AVG_TEXT = TextCHN.TOTAL_AVG_TEXT;
		 
		GAME_SORT_RP = TextCHN.GAME_SORT_RP;
		LIVE = TextCHN.LIVE;
		UNKNOWN = TextCHN.UNKNOWN;
		onePlayerDataHeaders = TextCHN.onePlayerDataHeaders;
		onePlayerMatchHeaders = TextCHN.onePlayerMatchHeaders;
		matchPlayerHeaders = TextCHN.matchPlayerHeaders;
		basicPlayerHeaders = TextCHN.basicPlayerHeaders;
		advancedPlayerHeaders = TextCHN.advancedPlayerHeaders;
		offensivePlayerHeaders = TextCHN.offensivePlayerHeaders;
		defensivePlayerHeaders = TextCHN.defensivePlayerHeaders;
		basicTeamHeaders = TextCHN.basicTeamHeaders;
		offensiveTeamHeaders = TextCHN.offensiveTeamHeaders;
		defensiveTeamHeaders = TextCHN.defensiveTeamHeaders;
		teamMatchHeaders = TextCHN.teamMatchHeaders;
		oneTeamDataHeaders = TextCHN.oneTeamDataHeaders;
		onePlayerDataTableEmptyContent = TextCHN.onePlayerDataTableEmptyContent;
		oneTeamDataTableEmptyContent = TextCHN.oneTeamDataTableEmptyContent;
		teamLineupHeaders = TextCHN.teamLineupHeaders;
		TEAM_NAMES = TextCHN.TEAM_NAMES;
		ALL_TEAM_NAMES = TextCHN.ALL_TEAM_NAMES;
		TEAM_PLACES = TextCHN.TEAM_PLACES;
		GAME_SORT = TextCHN.GAME_SORT;
		PLAYER_DATA_SORT = TextCHN.PLAYER_DATA_SORT;
		TEAM_DATA_SORT = TextCHN.TEAM_DATA_SORT;
		LBSTR = TextCHN.LBSTR;
		HOT_COLUMNS = TextCHN.HOT_COLUMNS;
		ANALYSE = TextCHN.ANALYSE;
		
		briefText = TextCHN.briefText;
		seasonDataText = TextCHN.seasonDataText;
		matchesDataText = TextCHN.matchesDataText;
		
		portraitText = TextCHN.portraitText;
		nameText = TextCHN.nameText;
		teamText = TextCHN.teamText;
		numberText = TextCHN.numberText;
		positionText = TextCHN.positionText;
		birthdayText = TextCHN.birthdayText;
		schoolText = TextCHN.schoolText;
		veteranText = TextCHN.veteranText;
		
		winsText = TextCHN.winsText;
		losesText = TextCHN.losesText;
		divisionText = TextCHN.divisionText;
		homeText = TextCHN.homeText;
		sinceText = TextCHN.sinceText;
		kingText = TextCHN.kingText;
		overallRankText = TextCHN.overallRankText;
		lineupText = TextCHN.lineupText;
		
		scoreAvgText = TextCHN.scoreAvgText;
		reboundAvgText = TextCHN.reboundAvgText;
		assistAvgText = TextCHN.assistAvgText;
		
		totalScoreText = TextCHN.totalScoreText;
		techText = TextCHN.techText;
		
		scoreKingText = TextCHN.scoreKingText;
		reboundKingText = TextCHN.reboundKingText;
		assistKingText = TextCHN.assistKingText;
		recordText = TextCHN.recordText;
		
		eastText = TextCHN.eastText;
		westText = TextCHN.westText;
		
		contrastText = TextCHN.contrastText;
		
		teamAvgText = TextCHN.teamAvgText;
		
		regularText = TextCHN.regularText;
		playoffText = TextCHN.playoffText;
		
		lastestTwo = 	TextCHN.lastestTwo;
		titleAvgData = TextCHN.titleAvgData;
		gameLog = TextCHN.gameLog;
		
		lineUp = TextCHN.lineUp;
		gameDate = TextCHN.gameDate;
		
		blank = TextCHN.blank;
		allPlayers =TextCHN.allPlayers;
		allTeams =TextCHN.allTeams;
		gamesData = TextCHN.gamesData;
		playersData = TextCHN.playersData;
		teamsData = TextCHN.teamsData;
		hot =  TextCHN.hot;
		analysis = TextCHN.analysis;
		
		hotShort = TextCHN.hotShort;
		game = TextCHN.game;
		ret = TextCHN.ret;
		
		ot = TextCHN.ot;
		
		
 	}
	
	public static void setEnglish() {
		isEng = true;
		Constants.translater = new TranslaterENG();
		
		playerType = TextENG.playerType;
		
		leagueText = TextENG.leagueText;
		teamShortText = TextENG.teamShortText;
		promotionText = TextENG.promotionText;
		positionShortText = TextENG.positionShortText;
		
		leagueAvg = TextENG.leagueAvg;
		hotType	= TextENG.hotType;
		scoreText = TextENG.scoreText;
		formerFiveAvgText = TextENG.formerFiveAvgText;
		POSITION_SELECT_TEXT = TextENG.POSITION_SELECT_TEXT;
		DIVISION_SELECT_TEXT = TextENG.DIVISION_SELECT_TEXT;
		BASIS_SELECT_TEXT = TextENG.BASIS_SELECT_TEXT;
		TOTAL_AVG_TEXT = TextENG.TOTAL_AVG_TEXT;
		
		GAME_SORT_RP = TextENG.GAME_SORT_RP;
		LIVE = TextENG.LIVE;
		UNKNOWN = TextENG.UNKNOWN;
		allPlayerHeaders = TextENG.allPlayerHeaders;
		onePlayerDataHeaders = TextENG.onePlayerDataHeaders;
		onePlayerMatchHeaders = TextENG.onePlayerMatchHeaders;
		matchPlayerHeaders = TextENG.matchPlayerHeaders;
		basicPlayerHeaders = TextENG.basicPlayerHeaders;
		advancedPlayerHeaders = TextENG.advancedPlayerHeaders;
		offensivePlayerHeaders = TextENG.offensivePlayerHeaders;
		defensivePlayerHeaders = TextENG.defensivePlayerHeaders;
		basicTeamHeaders = TextENG.basicTeamHeaders;
		offensiveTeamHeaders = TextENG.offensiveTeamHeaders;
		defensiveTeamHeaders = TextENG.defensiveTeamHeaders;
		teamMatchHeaders = TextENG.teamMatchHeaders;
		oneTeamDataHeaders = TextENG.oneTeamDataHeaders;
		onePlayerDataTableEmptyContent = TextENG.onePlayerDataTableEmptyContent;
		oneTeamDataTableEmptyContent = TextENG.oneTeamDataTableEmptyContent;
		teamLineupHeaders = TextENG.teamLineupHeaders;
		TEAM_NAMES = TextENG.TEAM_NAMES;
		ALL_TEAM_NAMES = TextENG.ALL_TEAM_NAMES;
		TEAM_PLACES = TextENG.TEAM_PLACES;
		GAME_SORT = TextENG.GAME_SORT;
		PLAYER_DATA_SORT = TextENG.PLAYER_DATA_SORT;
		TEAM_DATA_SORT = TextENG.TEAM_DATA_SORT;
		LBSTR = TextENG.LBSTR;
		HOT_COLUMNS = TextENG.HOT_COLUMNS;
		ANALYSE = TextENG.ANALYSE;
		
		briefText = TextENG.briefText;
		seasonDataText = TextENG.seasonDataText;
		matchesDataText = TextENG.matchesDataText;
		
		portraitText = TextENG.portraitText;
		nameText = TextENG.nameText;
		teamText = TextENG.teamText;
		numberText = TextENG.numberText;
		positionText = TextENG.positionText;
		birthdayText = TextENG.birthdayText;
		schoolText = TextENG.schoolText;
		veteranText = TextENG.veteranText;
		
		winsText = TextENG.winsText;
		losesText = TextENG.losesText;
		divisionText = TextENG.divisionText;
		homeText = TextENG.homeText;
		sinceText = TextENG.sinceText;
		kingText = TextENG.kingText;
		overallRankText = TextENG.overallRankText;
		lineupText = TextENG.lineupText;
		
		scoreAvgText = TextENG.scoreAvgText;
		reboundAvgText = TextENG.reboundAvgText;
		assistAvgText = TextENG.assistAvgText;
		
		totalScoreText = TextENG.totalScoreText;
		techText = TextENG.techText;
		
		scoreKingText = TextENG.scoreKingText;
		reboundKingText = TextENG.reboundKingText;
		assistKingText = TextENG.assistKingText;
		recordText = TextENG.recordText;
		
		eastText = TextENG.eastText;
		westText = TextENG.westText;
		
		contrastText = TextENG.contrastText;
		
		teamAvgText = TextENG.teamAvgText;
		
		regularText = TextENG.regularText;
		playoffText = TextENG.playoffText;
		
		lastestTwo = 	TextENG.lastestTwo;
		titleAvgData = TextENG.titleAvgData;
		gameLog = TextENG.gameLog;
		
		lineUp = TextENG.lineUp;
		gameDate = TextENG.gameDate;
		
		blank = TextENG.blank;
		allPlayers =TextENG.allPlayers;
		allTeams =TextENG.allTeams;
		gamesData = TextENG.gamesData;
		playersData = TextENG.playersData;
		teamsData = TextENG.teamsData;
		hot =  TextENG.hot;
		analysis = TextENG.analysis;
		
		hotShort = TextENG.hotShort;
		game = TextENG.game;
		ret = TextENG.ret;
		
		playerContrastColumns = TextENG.playerContrastColumns;
	}
	

	
	public static final String [] TEAM_ABBR = {"BOS","BRK","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHO","MIA","ORL","WAS",
		"GSW","LAC","LAL","PHO","SAC","DEN","MIN","OKC","POR","UTA","DAL","HOU","MEM","NOP","SAS"};
	
	public static String LATEST_SEASON = "2014-15P";
	
	public static String LATEST_SEASON_REGULAR = "2014-15R";
	
	public static int EARLIEST_YEAR = 1986;
	
	public static final int THIS_YEAR = 2015;
	
	public static String EARLIEST_SEASON_REGULAR = "1986-87R";
	
	public static String[] GAME_YEAR = setGameYear();
	
	public static String[] setGameYear(){
		String[] yearArr = new String[29];
		String year = "2015";
		for(int i = 0; i < 29; i++){
			yearArr[i] = ((Integer.parseInt(year)-1)+"").substring(2,4)+ " - " + year.substring(2,4);
			year = (Integer.parseInt(year)-1)+"";
		}
		return yearArr;
	}
	
	public static String dataSourcePath = "NBAdata\\";
	
	/** 如果在程序运行中改变数据目录，需要清空已经读取的数据，同时由Controller控制返回首页 */
	public static void changeDataSourcePath(String newPath) {
		dataSourcePath = newPath + "\\";
		PlayerImageCache.reloadImages();
		TeamLogoCache.reloadLogos();
	}
	
	public static String []playerType = TextCHN.playerType;
	
	public static String leagueText = TextCHN.leagueText;
	public static String teamShortText = TextCHN.teamShortText;
	public static String promotionText = TextCHN.promotionText;
	public static String positionShortText = TextCHN.positionShortText;
	
	public static String leagueAvg = TextCHN.leagueAvg;
	public static String[] hotType	= TextCHN.hotType;
	public static String scoreText = TextCHN.scoreText;
	public static String formerFiveAvgText = TextCHN.formerFiveAvgText;
	public static String[] POSITION_SELECT_TEXT = TextCHN.POSITION_SELECT_TEXT;
	public static String[] DIVISION_SELECT_TEXT = TextCHN.DIVISION_SELECT_TEXT;
	public static String[] BASIS_SELECT_TEXT = TextCHN.BASIS_SELECT_TEXT;
	public static String[] TOTAL_AVG_TEXT = TextCHN.TOTAL_AVG_TEXT;
	
	public static String ot = TextCHN.ot;
	
	public static String [] playerContrastColumns = TextCHN.playerContrastColumns;
	
	public static String[] GAME_SORT_RP = TextCHN.GAME_SORT_RP;
	
	public static String[] LIVE = TextCHN.LIVE;
	
	/** 无资料的时候显示的提示 */
	public static String UNKNOWN = TextCHN.UNKNOWN;
	
	public static String[] allPlayerHeaders = TextCHN.allPlayerHeaders;
	
	/** 球员个人信息页面中的赛季数据的表头 */
	public static String [] onePlayerDataHeaders = TextCHN.onePlayerDataHeaders;
	/** 球员个人信息页面中的赛程数据的表头 */
	public static String [] onePlayerMatchHeaders = TextCHN.onePlayerMatchHeaders;
	
	public static String [] matchPlayerHeaders = TextCHN.matchPlayerHeaders;
	
	public static String[] livePlayerHeaders = TextCHN.livePlayerHeaders;
	
	public static String[] liveDetailHeaders = TextCHN.liveDetailHeaders;
	
	/** 所有球员赛季数据中的表格分成四部分，为基本、进攻、防守、高阶 */
	public static String []basicPlayerHeaders = TextCHN.basicPlayerHeaders;
	
	public static String []advancedPlayerHeaders = TextCHN.advancedPlayerHeaders;
	
	public static String []offensivePlayerHeaders = TextCHN.offensivePlayerHeaders;
	
	public static String []defensivePlayerHeaders = TextCHN.defensivePlayerHeaders;
	
	/** 所有球员赛季数据中的表格分成三部分，为基本、进攻、防守 */
	public static String []basicTeamHeaders = TextCHN.basicTeamHeaders;
	
	public static String []offensiveTeamHeaders = TextCHN.offensiveTeamHeaders;
	
	public static String []defensiveTeamHeaders = TextCHN.defensiveTeamHeaders;
	
	/** 球队界面中的赛程的表头 */
	public static String []teamMatchHeaders = TextCHN.teamMatchHeaders;
	/** 球队信息页面中的赛季数据的表头 */
	public static String [] oneTeamDataHeaders = TextCHN.oneTeamDataHeaders;
	
	/** 球员个人信息页面中的赛季数据的空表 */
	public static Object [][] onePlayerDataTableEmptyContent = TextCHN.onePlayerDataTableEmptyContent;
	
	/** 球队信息页面中的赛季数据的空表 */
	public static Object [][] oneTeamDataTableEmptyContent = TextCHN.oneTeamDataTableEmptyContent;
	
	public static String[] teamLineupHeaders = TextCHN.teamLineupHeaders;
	
	public static String [] TEAM_NAMES = TextCHN.TEAM_NAMES;
	
	public static String[] LIVE_TEXT = TextCHN.LIVE_tEXT;
	
	public static String [] ALL_TEAM_NAMES = TextCHN.ALL_TEAM_NAMES;
	
	public static String[] TEAM_PLACES = TextCHN.TEAM_PLACES;
	
	public static String[] GAME_SORT = TextCHN.GAME_SORT;
	
	public static String[] PLAYER_DATA_SORT = TextCHN.PLAYER_DATA_SORT;
	
	public static String[] TEAM_DATA_SORT = TextCHN.TEAM_DATA_SORT;
	
	public static  String[] LBSTR = TextCHN.LBSTR;
	
	public static  String[] HOT_COLUMNS = TextCHN.HOT_COLUMNS;
	
	public static  String[] ANALYSE = TextCHN.ANALYSE;
	
	public static String[] HOT_BTN = TextCHN.HOT_BTN;
	
	public static String[] ANY_SELECT = TextCHN.ANY_SELECT;
	
	public static String[] SCATTER_CHART = TextCHN.SCATTER_CHART;
	
	public static String[] LINE_CHART = TextCHN.LINE_CHART;
	
	public static String[] BAR_CHART = TextCHN.BAR_CHART;
	
	public static String[] BAR_SELECT = TextCHN.BAR_SELECT;
	
	public static String translateDivision(ScreenDivision division) {
		return translater.translateTeamDivision(division);
	}
	
	public static String translateLeague(ScreenDivision league) {
		return translater.translateTeamLeague(league);
	}
	
	public static String getLeagueStringByAbbr(String abbr) {
		return translater.translateTeamLeague(getAreaByAbbr(abbr));
	}
	
	public static ScreenDivision getAreaByAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
		case "BKN":
		case "BRK":
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
		case "CHO":
		case "MIA":
		case "ORL":
		case "WAS":
		case "WSB":
			return ScreenDivision.EAST;
		default:
			return ScreenDivision.WEST;
		}
	}
	
	public static String getAreaByEnglish(ScreenDivision AREA){
		if(AREA.equals(ScreenDivision.WEST)){
			return Constants.westText;
		}else{
			return Constants.eastText;
		}
	}
	
	public static ScreenDivision getDivisionByAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
		case "BKN":
		case "BRK":
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
		case "CHO":
		case "MIA":
		case "ORL":
		case "WAS":
		case "WSB":
			return ScreenDivision.SOUTH_EAST;
		case "GSW":
		case "LAC":
		case "LAL":
		case "PHX":
		case "PHO":
		case "SAC":
			return ScreenDivision.PACIFIC;
		case "DEN":
		case "MIN":
		case "OKC":
		case "SEA":
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
			return "BRK";
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
	
	public static String briefText = TextCHN.briefText;
	public static String seasonDataText = TextCHN.seasonDataText;
	public static String matchesDataText = TextCHN.matchesDataText;
	
	public static String portraitText = TextCHN.portraitText;
	public static String nameText = TextCHN.nameText;
	public static String teamText = TextCHN.teamText;
	public static String numberText = TextCHN.numberText;
	public static String positionText = TextCHN.positionText;
	public static String birthdayText = TextCHN.birthdayText;
	public static String schoolText = TextCHN.schoolText;
	public static String veteranText = TextCHN.veteranText;
	
	public static String winsText = TextCHN.winsText;
	public static String losesText = TextCHN.losesText;
	public static String divisionText = TextCHN.divisionText;
	public static String homeText = TextCHN.homeText;
	public static String sinceText = TextCHN.sinceText;
	public static String kingText = TextCHN.kingText;
	public static String overallRankText = TextCHN.overallRankText;
	public static String lineupText = TextCHN.lineupText;
	
	public static String scoreAvgText = TextCHN.scoreAvgText;
	public static String reboundAvgText = TextCHN.reboundAvgText;
	public static String assistAvgText = TextCHN.assistAvgText;
	
	public static String totalScoreText = TextCHN.totalScoreText;
	public static String techText = TextCHN.techText;
	
	public static String scoreKingText = TextCHN.scoreKingText;
	public static String reboundKingText = TextCHN.reboundKingText;
	public static String assistKingText = TextCHN.assistKingText;
	public static String recordText = TextCHN.recordText;
	
	public static String eastText = TextCHN.eastText;
	public static String westText = TextCHN.westText;
	
	public static String contrastText = TextCHN.contrastText;
	
	public static String teamAvgText = TextCHN.teamAvgText;
	
	public static String regularText = TextCHN.regularText;
	public static String playoffText = TextCHN.playoffText;
	
	public static String lastestTwo = 	TextCHN.lastestTwo;
	public static String titleAvgData = TextCHN.titleAvgData;
	public static String gameLog = TextCHN.gameLog;
	
	public static String lineUp = TextCHN.lineUp;
	public static String gameDate = TextCHN.gameDate;
	
	public static String blank = TextCHN.blank;
	public static String allPlayers =TextCHN.allPlayers;
	public static String allTeams =TextCHN.allTeams;
	public static String gamesData = TextCHN.gamesData;
	public static String playersData = TextCHN.playersData;
	public static String teamsData = TextCHN.teamsData;
	public static String hot =  TextCHN.hot;
	public static String analysis = TextCHN.analysis;
	public static String playerOnCourt = TextCHN.playerOnCourt;
	public static String live = TextCHN.live;
	
	public static String hotShort = TextCHN.hotShort;
	public static String game = TextCHN.game;
	public static String ret = TextCHN.ret;
	
	public static String history = TextCHN.history;
	public static String waitlive = TextCHN.waitlive;
	public static String tolive = TextCHN.tolive;
	
	public static String playerPosition = TextCHN.playerPosition;
	public static String playerDivision = TextCHN.playerDivision;
	public static String screenSelection = TextCHN.screenSelection;
	
	public static String translateHeight(String height){
		return translater.translateHeight(height);
	}
	public static String translateWeight(int weight){
		return translater.translateWeight(weight);
	}
	public static String translateDate(Date date){
		return translater.translateDate(date);
	}
	/** veteran即球龄 */
	public static String translateVeteran(int fromYear){	
		return translater.translateVeteran(fromYear);
	}
	public static String translatePosition(String position) {
		return translater.translatePosition(position);
	}
	
	/** 球队的所在地，显示在球队信息标题上 如   休斯顿 火箭 */
	public static String translateTeamAbbrToLocation(String abbr) {
		return translater.translateTeamAbbrToLocation(abbr);
	}
	
	public static String getPropertyName(HotSeasonPlayerProperty property) {
		return translater.translateProperty(property);
	}
	public static String getPropertyName(HotSeasonTeamProperty property) {
		return translater.translateProperty(property);
	}

}

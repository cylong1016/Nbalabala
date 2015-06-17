/**
 * 
 */
package utility;

/**
 *
 * @author Issac Ding
 * @since 2015年6月11日 下午2:40:26
 * @version 1.0
 */
public class TextCTR {
	
	public static String[] playerType = {"現役球員","退役球員","全部球員"};
	
	public static String leagueText = "聯盟：";
	public static String teamShortText = "球隊：";
	public static String promotionText = "五場提升：";
	public static String positionShortText = "位置：";
	
	public static String ot = "加時";
	public static String leagueAvg = "聯盟平均";
	public static String[] hotType	=new String[] { "場均得分", "場均籃板", "場均助攻", 
		"場均蓋帽", "場均搶斷", "三分命中率", "投籃命中率", "罰球命中率"};
	public static String scoreText = "得分";
	public static String formerFiveAvgText = "五場前平均";
	public static String[] POSITION_SELECT_TEXT = new String[] { "所有", "前鋒", "中鋒", "後衛" };
	public static String[] DIVISION_SELECT_TEXT = new String[] { "所有", "東南", "中央", 
		"大西洋", "東部", "西部", "太平洋", "西北", "西南" };
	public static String[] BASIS_SELECT_TEXT = new String[] { "所有", "得分", "籃板", "助攻", 
		"得分/籃板/助攻", "蓋帽", "搶斷", "犯規", "失誤", "分鐘","效率", "投籃", "三分", "罰球", "兩雙"};
	public static String[] TOTAL_AVG_TEXT = new String[] { "總計", "平均" };
	
	public static String briefText = "簡況";
	public static String seasonDataText = "賽季數據";
	public static String matchesDataText = "比賽數據";
	
	public static String portraitText = "球員頭像";
	public static String nameText = "球員名稱";
	public static String teamText = "球隊名稱";
	public static String numberText = "球衣號碼";
	public static String positionText = "位置";
	public static String birthdayText = "生日";
	public static String schoolText = "畢業學校";
	public static String veteranText = "球齡";
	
	public static String winsText = "勝";
	public static String losesText = "負";
	public static String divisionText = "所屬賽區:";
	public static String homeText = "主場:";
	public static String sinceText = "建隊時間:";
	public static String kingText = "數據王";
	public static String overallRankText = "總排名";
	public static String lineupText = "陣容";
	
	public static String scoreAvgText = "場均得分";
	public static String reboundAvgText = "場均籃板";
	public static String assistAvgText = "場均助攻";
	
	public static String totalScoreText = "總分";
	public static String techText = "技術統計";
	
	public static String scoreKingText = "得分";
	public static String reboundKingText = "籃板";
	public static String assistKingText = "助攻";
	public static String recordText = "戰績";
	
	public static String eastText = "東部聯盟";
	public static String westText = "西部聯盟";
	
	public static String contrastText = "對比";
	
	public static String teamAvgText = "球隊平均";
	
	public static String regularText = "常規賽";
	public static String playoffText = "季後賽";
	
	public static String lastestTwo = 	" 最近    場比賽";
	public static String titleAvgData = " 總數據／平均數據";
	public static String gameLog = " 比賽日誌";
	
	public static String lineUp = " 陣容";
	public static String gameDate = " 比賽日程";
	
	public static String blank = "               ";
	public static String allPlayers ="所有球員";
	public static String allTeams ="所有球隊";
	public static String gamesData = "賽程";
	public static String playersData = "球員數據";
	public static String teamsData = "球隊數據";
	public static String hot =  "熱點";
	public static String analysis = "價值分析";
	
	public static String hotShort = "熱點";
	public static String game = "賽程";
	public static String ret = "返回首頁";
	
	public static String playerOnCourt = "在場球員";
	public static String playerPosition = "球員位置";
	public static String playerDivision = "所在賽區";
	public static String screenSelection = "篩選依據";
	
	public static String[] playerContrastColumns = {"場均得分", "場均籃板", "場均助攻", "罰球%", "三分%"};
	
	public static String[] GAME_SORT_RP = {"常規賽","季後賽"};
	
	public static String[] LIVE = {"技術統計","文字直播","對比"};
	
	public static String[] GAME_LIVE = {"球隊","比賽實錄","對比"};
	
	/** 無資料的時候顯示的提示 */
	public static final String UNKNOWN = "無資料";
	
	
	public static String[] allPlayerHeaders = {"球員頭像","姓名", "加入聯盟", "最近參賽", "位置", "生日" };
	
	/** 球員個人信息頁面中的賽季數據的表頭 */
	public static String [] onePlayerDataHeaders = {
		"賽季","場數","先發","在場時間","投籃%","三分%","罰球%","進攻","防守","籃板","助攻","搶斷","蓋帽","失誤","犯規","得分"
	};
	/** 球員個人信息頁面中的賽程數據的表頭 */
	public static String [] onePlayerMatchHeaders = {
		"日期","對手","首發","時間","投籃","三分","罰球",
			"進攻籃板","防守籃板","籃板","助攻","搶斷","蓋帽","失誤","犯規","得分"
	};
	
	public static String [] matchPlayerHeaders = new String[] { "序號","球員名", "首發", "時間", "投籃", "三分", "罰球",
		"進攻籃板", "防守籃板", "總籃板", "助攻", "搶斷", "蓋帽", "失誤", "犯規", "個人得分", "+/-" };
	
	public static String[] livePlayerHeaders = new String[] {"序號","中文名","位置", "首發", "在場時間", "投籃", "三分", " 罰球", 
		  "籃板", "助攻", "搶斷", "蓋帽", "失誤", "犯規", "得分", "+/-"};
	
	public static String[] liveDetailHeaders = new String[]{"時間","比分"};
	
	/** 所有球員賽季數據中的表格分成四部分，為基本、進攻、防守、高階 */
	public static String []basicPlayerHeaders = {"序號","球員名稱","所屬球隊","參賽","先發","在場時間","得分","籃板",
		"助攻","蓋帽","搶斷","兩雙","得分籃板助攻","失誤","犯規"};
	public static String []advancedPlayerHeaders = {"序號","球員名稱","效率","GmSc","使用率","進攻籃板%","防守籃板%",
		"總籃板%","助攻%","真實命中%","投籃效率","蓋帽%","搶斷%","失誤%","犯規%"};
	public static String []offensivePlayerHeaders = {"序號","球員名稱","投籃命中","投籃出手","投籃%","三分命中","三分出手","三分%",
		"罰球命中","罰球出手", "罰球%","助攻","助攻率","真實命中率","投籃效率"};
	public static String []defensivePlayerHeaders = {"序號","球員名稱","所屬球隊","參賽","先發","進攻籃板","防守籃板",
		"總籃板","蓋帽","搶斷","進攻籃板%","防守籃板%","總籃板%","蓋帽%","搶斷%"};
	
	/** 所有球員賽季數據中的表格分成三部分，為基本、進攻、防守 */
	public static String []basicTeamHeaders = {"序號","球隊名稱","勝場數","總場數","勝率","得分","籃板","助攻","蓋帽",
		"搶斷","失誤","犯規","進攻效率","防守效率"}; //14
	public static String []offensiveTeamHeaders = {"序號","球隊名稱","投籃命中","投籃出手","投籃%","三分命中","三分出手","三分%",
		"罰球命中","罰球出手","罰球%","助攻率","進攻回合","進攻效率"};
	public static String []defensiveTeamHeaders = {"序號","球隊名稱","進攻籃板","防守籃板","總籃板","進攻籃板效率",
		"防守籃板效率","蓋帽","搶斷","搶斷效率","防守回合","防守效率","對手得分","對手投籃%"};
	
	/** 球隊界面中的賽程的表頭 */
	public static String []teamMatchHeaders = {"日期", "對手", "勝負", "比分"};
	/** 球隊信息頁面中的賽季數據的表頭 */
	public static String [] oneTeamDataHeaders = {
		"賽季","場數","勝場","負場","勝率","投籃%","三分%","罰球%","進攻","防守","籃板","助攻","搶斷","蓋帽","失誤","犯規","得分"
	};
	
	
	/** 球員個人信息頁面中的賽季數據的空表 */
	public static Object [][] onePlayerDataTableEmptyContent = 
		{{"賽季總計","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"賽季場均","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"球隊場均","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	
	/** 球隊信息頁面中的賽季數據的空表 */
	public static Object [][] oneTeamDataTableEmptyContent = 
		{{"賽季平均","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"賽季總計","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public static String[] teamLineupHeaders = new String[] {"姓名", "位置", "年齡","球齡","身高","體重","生日","畢業學校"};

	public static String live = "直播";

	public static String history = "交手歷史";
	public static String waitlive = "等待直播";
	public static String tolive = "進入直播";

	public static String today = "今日";
	
	
	public static final String [] TEAM_ABBR = {"BOS","BRK","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHO","MIA","ORL","WAS",
		"GSW","LAC","LAL","PHO","SAC","DEN","MIN","OKC","POR","UTA","DAL","HOU","MEM","NOP","SAS"};
	
	public static final String [] TEAM_NAMES = {"凱爾特人", "籃網", "尼克斯", "76人", "猛龍", "公牛", "騎士", "活塞", "步行者", "雄鹿", "老鷹", "黃蜂", "熱火",
		"魔術", "奇才", "勇士", "快船", "湖人", "太陽", "國王", "掘金", "森林狼", "雷霆", "開拓者", "爵士", "小牛",
		"火箭", "灰熊", "鵜鶘", "馬刺"};
	
	public static final String [] ALL_TEAM_NAMES = {"選擇球隊","凱爾特人", "籃網", "尼克斯", "76人", "猛龍", "公牛", "騎士", "活塞", "步行者", "雄鹿", "老鷹", "黃蜂", "熱火",
		"魔術", "奇才", "勇士", "快船", "湖人", "太陽", "國王", "掘金", "森林狼", "雷霆", "開拓者", "爵士", "小牛",
		"火箭", "灰熊", "鵜鶘", "馬刺"};
	
	public static final String[] TEAM_PLACES = { "波士頓", "布魯克林", "紐約", "費城", "多倫多", "芝加哥", "克利夫蘭", "底特律", "印第安納", "密爾沃基", "亞特蘭大", "夏洛特",
			"邁阿密", "奧蘭多", "華盛頓", "金州", "洛杉磯", "洛杉磯", "菲尼克斯", "薩克拉門托", "丹佛", "明尼蘇達", "俄克拉荷馬", "波特蘭", "猶他", "達拉斯",
			"休斯敦", "孟菲斯", "新奧爾良", "聖安東尼奧" };
	
	public static final String[] GAME_SORT = {"%","三分%","罰球%","籃板","助攻"};
	
	public static final String[] PLAYER_DATA_SORT = {"基本數據","進攻數據","防守數據","高階數據"};
	
	public static final String[] TEAM_DATA_SORT = {"基本數據","進攻數據","防守數據"};
	
	public static final String[] LBSTR = {"得分", "籃板",  "助攻", "蓋帽","搶斷"};
	
	public static final String[] HOT_COLUMNS = {"名次", "頭像", "姓名", "球隊", "對手", "位置", "在場時間", "得分", "籃板", "助攻", "蓋帽",
			"搶斷"};
	
	public static final String[] LIVE_tEXT = {"全場","第壹節","第二節","第三節","第四節"};
	
//	public static final String[] ANALYSE = {"決勝時刻","生涯數據","球隊貢獻","前程展望","轉會分析","球員對比"};
	
	public static final String[] HOT_BTN = {"今日熱點球員",  "賽季熱點球員", "賽季熱點球隊", "進步最快球員"};

	public static final String[] ANY_SELECT = {"得分","籃板","助攻","投籃命中率","三分命中率","罰球命中率","效率","GMSC",
		"投籃效率","真實命中率"};

	public static final String[] SCATTER_CHART = {"球員走向圖", "比賽組數", "數據"};
	public static final String[] LINE_CHART = {"球員轉會圖", "比賽組數", "數據"};

	public static final String[] BAR_CHART = {"球隊貢獻", "球員", "數據"};

	//參賽場數 場均上場時間 ORPM DRPM RPM WAR
	public static final String[] BAR_SELECT = {"參賽場數","上場時間","ORPM","DRPM","RPM","WAR"};

	public static final String[] ANALYSE = {"決勝時刻","生涯數據","球隊貢獻","前程展望","轉會分析","球員對比"};

	public static final String[] NEW_LINE_CHART = {"球員對比圖", "比賽組數", "數據"};


}

/**
 * 
 */
package utility;

/**
 *
 * @author Issac Ding
 * @since 2015年6月11日 下午12:40:24
 * @version 1.0
 */
public class TextCHN {
	
	public static String[] playerType = {"现役球员","退役球员","全部球员"};
	
	public static String leagueText = "联盟：";
	public static String teamShortText = "球队：";
	public static String promotionText = "五场提升：";
	public static String positionShortText = "位置：";
	
	public static String ot = "加时";
	public static String leagueAvg = "联盟平均";
	public static String[] hotType	=new String[] { "场均得分", "场均篮板", "场均助攻", 
		"场均盖帽", "场均抢断", "三分命中率", "投篮命中率", "罚球命中率"};
	public static String scoreText = "得分";
	public static String formerFiveAvgText = "五场前平均";
	public static String[] POSITION_SELECT_TEXT = new String[] { "所有", "前锋", "中锋", "后卫" };
	public static String[] DIVISION_SELECT_TEXT = new String[] { "所有", "东南", "中央", 
		"大西洋", "东部", "西部", "太平洋", "西北", "西南" };
	public static String[] BASIS_SELECT_TEXT = new String[] { "所有", "得分", "篮板", "助攻", 
		"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟","效率", "投篮", "三分", "罚球", "两双"};
	public static String[] TOTAL_AVG_TEXT = new String[] { "总计", "平均" };
	
	public static String briefText = "简况";
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
	
	public static String totalScoreText = "总分";
	public static String techText = "技术统计";
	
	public static String scoreKingText = "得分";
	public static String reboundKingText = "篮板";
	public static String assistKingText = "助攻";
	public static String recordText = "战绩";
	
	public static String eastText = "东部联盟";
	public static String westText = "西部联盟";
	
	public static String contrastText = "对比";
	
	public static String teamAvgText = "球队平均";
	
	public static String regularText = "常规赛";
	public static String playoffText = "季后赛";
	
	public static String lastestTwo = 	" 最近    场比赛";
	public static String titleAvgData = " 总数据／平均数据";
	public static String gameLog = " 比赛日志";
	
	public static String lineUp = " 阵容";
	public static String gameDate = " 比赛日程";
	
	public static String blank = "               ";
	public static String allPlayers ="所有球员";
	public static String allTeams ="所有球队";
	public static String gamesData = "赛程";
	public static String playersData = "球员数据";
	public static String teamsData = "球队数据";
	public static String hot =  "热点";
	public static String analysis = "价值分析";
	
	public static String hotShort = "热点";
	public static String game = "赛程";
	public static String ret = "返回首页";
	
	public static String playerOnCourt = "在场球员";
	public static String playerPosition = "球员位置";
	public static String playerDivision = "所在赛区";
	public static String screenSelection = "筛选依据";
	
	public static String[] playerContrastColumns = {"场均得分", "场均篮板", "场均助攻", "罚球%", "三分%"};
	
	public static String[] GAME_SORT_RP = {"常规赛","季后赛"};
	
	public static String[] LIVE = {"技术统计","文字直播","对比"};
	
	/** 无资料的时候显示的提示 */
	public static final String UNKNOWN = "无资料";
	
	
	public static String[] allPlayerHeaders = {"球员头像","姓名", "加入联盟", "最近参赛", "位置", "生日" };
	
	/** 球员个人信息页面中的赛季数据的表头 */
	public static String [] onePlayerDataHeaders = {
		"赛季","场数","先发","在场时间","投篮%","三分%","罚球%","进攻","防守","篮板","助攻","抢断","盖帽","失误","犯规","得分"
	};
	/** 球员个人信息页面中的赛程数据的表头 */
	public static String [] onePlayerMatchHeaders = {
		"日期","对手","首发","时间","投篮","三分","罚球",
			"进攻篮板","防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分"
	};
	
	public static String [] matchPlayerHeaders = new String[] { "序号","球员名", "首发", "时间", "投篮", "三分", "罚球",
		"进攻篮板", "防守篮板", "总篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "个人得分", "+/-" };
	
	public static String[] livePlayerHeaders = new String[] {"序号","中文名","位置", "首发", "在场时间", "投篮", "三分", " 罚球", 
		  "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "得分", "+/-"};
	
	public static String[] liveDetailHeaders = new String[]{"时间","比分"};
	
	/** 所有球员赛季数据中的表格分成四部分，为基本、进攻、防守、高阶 */
	public static String []basicPlayerHeaders = {"序号","球员名称","所属球队","参赛","先发","在场时间","得分","篮板",
		"助攻","盖帽","抢断","两双","得分篮板助攻","失误","犯规"};
	public static String []advancedPlayerHeaders = {"序号","球员名称","效率","GmSc","使用率","进攻篮板%","防守篮板%",
		"总篮板%","助攻%","真实命中%","投篮效率","盖帽%","抢断%","失误%","犯规%"};
	public static String []offensivePlayerHeaders = {"序号","球员名称","投篮命中","投篮出手","投篮%","三分命中","三分出手","三分%",
		"罚球命中","罚球出手", "罚球%","助攻","助攻率","真实命中率","投篮效率"};
	public static String []defensivePlayerHeaders = {"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板",
		"总篮板","盖帽","抢断","进攻篮板%","防守篮板%","总篮板%","盖帽%","抢断%"};
	
	/** 所有球员赛季数据中的表格分成三部分，为基本、进攻、防守 */
	public static String []basicTeamHeaders = {"序号","球队名称","胜场数","总场数","胜率","得分","篮板","助攻","盖帽",
		"抢断","失误","犯规","进攻效率","防守效率"}; //14
	public static String []offensiveTeamHeaders = {"序号","球队名称","投篮命中","投篮出手","投篮%","三分命中","三分出手","三分%",
		"罚球命中","罚球出手","罚球%","助攻率","进攻回合","进攻效率"};
	public static String []defensiveTeamHeaders = {"序号","球队名称","进攻篮板","防守篮板","总篮板","进攻篮板效率",
		"防守篮板效率","盖帽","抢断","抢断效率","防守回合","防守效率","对手得分","对手投篮%"};
	
	/** 球队界面中的赛程的表头 */
	public static String []teamMatchHeaders = {"日期", "对手", "胜负", "比分"};
	/** 球队信息页面中的赛季数据的表头 */
	public static String [] oneTeamDataHeaders = {
		"赛季","场数","胜场","负场","胜率","投篮%","三分%","罚球%","进攻","防守","篮板","助攻","抢断","盖帽","失误","犯规","得分"
	};
	
	
	/** 球员个人信息页面中的赛季数据的空表 */
	public static Object [][] onePlayerDataTableEmptyContent = 
		{{"赛季总计","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"赛季场均","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"球队场均","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	
	/** 球队信息页面中的赛季数据的空表 */
	public static Object [][] oneTeamDataTableEmptyContent = 
		{{"赛季平均","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"赛季总计","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public static String[] teamLineupHeaders = new String[] {"姓名", "位置", "年龄","球龄","身高","体重","生日","毕业学校"};

	public static String live = "直播";

	public static String history = "交手历史";
	public static String waitlive = "等待直播";
	public static String tolive = "进入直播";
	
	
	public static final String [] TEAM_ABBR = {"BOS","BRK","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHO","MIA","ORL","WAS",
		"GSW","LAC","LAL","PHO","SAC","DEN","MIN","OKC","POR","UTA","DAL","HOU","MEM","NOP","SAS"};
	
	public static final String [] TEAM_NAMES = {"凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂", "热火",
		"魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛",
		"火箭", "灰熊", "鹈鹕", "马刺"};
	
	public static final String [] ALL_TEAM_NAMES = {"所有球队","凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂", "热火",
		"魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛",
		"火箭", "灰熊", "鹈鹕", "马刺"};
	
	public static final String[] TEAM_PLACES = { "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金州", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", "达拉斯",
			"休斯敦", "孟菲斯", "新奥尔良", "圣安东尼奥" };
	
	public static final String[] GAME_SORT = {"%","三分%","罚球%","篮板","助攻"};
	
	public static final String[] PLAYER_DATA_SORT = {"基本数据","进攻数据","防守数据","高阶数据"};
	
	public static final String[] TEAM_DATA_SORT = {"基本数据","进攻数据","防守数据"};
	
	public static final String[] LBSTR = {"得分", "篮板",  "助攻", "盖帽","抢断"};
	
	public static final String[] HOT_COLUMNS = {"名次", "头像", "姓名", "球队", "对手", "位置", "在场时间", "得分", "篮板", "助攻", "盖帽",
			"抢断"};
	
	public static final String[] ANALYSE = {"最后五分钟","生涯数据","球员贡献","球员走向","转会变化"};
	public static final String[] LIVE_tEXT = {"全场","第一节","第二节","第三节","第四节"};
	
	public static final String[] HOT_BTN = {"今日热点球员",  "赛季热点球员", "赛季热点球队", "进步最快球员"};

}

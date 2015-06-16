/**
 * 
 */
package utility;

/**
 *
 * @author Issac Ding
 * @since 2015年6月11日 下午12:40:47
 * @version 1.0
 */
public class TextENG {
	
	public static String[] playerType = {"Active","Retired","All"};
	
	public static String live = "Live";
	public static String leagueText = "League：";
	public static String teamShortText = "TM:";
	public static String promotionText = "Promotion:";
	public static String positionShortText = "POS:";
	
	public static String leagueAvg = "League";
	public static String[] hotType	=new String[] { "Points", "Rebound", "Assist", 
		"Block", "Steal", "3P%", "FG%", "FT%"};
	public static String scoreText = "Points";
	public static String formerFiveAvgText = "Former";
	public static String[] POSITION_SELECT_TEXT = new String[] { "All", "F", "C", "G" };
	public static String[] DIVISION_SELECT_TEXT = new String[] { "All", "SE", "Central", 
		"Atlantic", "East", "West", "Pacific", "NW", "SW" };
	public static String[] BASIS_SELECT_TEXT = new String[] { "All", "PTS", "RB", "AST", 
		"PTS/RB/AST", "BLK", "STL", "PF", "TOV", "MP","EFF", "FG", "3P", "FT", "DD"};
	public static String[] TOTAL_AVG_TEXT = new String[] { "Total", "Avg" };
	
	public static String briefText = "Profile";
	public static String seasonDataText = "Season Stats";
	public static String matchesDataText = "Match Stats";
	
	public static String portraitText = "Portrait";
	public static String nameText = "Name";
	public static String teamText = "Team";
	public static String numberText = "Number";
	public static String positionText = "Position";
	public static String birthdayText = "Birth";
	public static String schoolText = "School";
	public static String veteranText = "Veteran";
	
	public static String winsText = "W";
	public static String losesText = "L";
	public static String divisionText = "Division:";
	public static String homeText = "Home:";
	public static String sinceText = "Since:";
	public static String kingText = "King";
	public static String overallRankText = "Rank ";
	public static String lineupText = "Lineup";
	
	public static String scoreAvgText = "Points";
	public static String reboundAvgText = "Rebound";
	public static String assistAvgText = "Assist";
	
	public static String totalScoreText = "Total";
	public static String techText = "Stats";
	
	public static String scoreKingText = "Points";
	public static String reboundKingText = "Rebound";
	public static String assistKingText = "Assist";
	public static String recordText = " ";	//TODO ...怎么翻译
	
	public static String eastText = "EAST";
	public static String westText = "WEST";
	
	public static String contrastText = "Contrast";
	
	public static String teamAvgText = "Team Avg";
	
	public static String regularText = "REGU";
	public static String playoffText = "POST";
	
	public static String lastestTwo = 	"Last     Matches";
	public static String titleAvgData = " Total／Average";
	public static String gameLog = " Matches";
	
	public static String lineUp = " Lineup";
	public static String gameDate = " Schedule";
	
	public static String blank = "               ";
	public static String allPlayers ="Players";
	public static String allTeams ="Teams";
	public static String gamesData = "Matches";
	public static String playersData = "Player Stats";
	public static String teamsData = "Team Stats";
	public static String hot =  "Hotspot";
	public static String analysis = "Analysis";
	
	public static String hotShort = "Hot";
	public static String game = "Schedule";
	public static String ret = "Back";
	
	public static String today = "today";
	
	public static String [] playerContrastColumns = {"Points", "Rebound", "Assist", "FT%", "3P%"};
	
	public static String[] GAME_SORT_RP = {"Regular","Playoff"};
	
	public static String[] LIVE = {"Stats","Live","Contrast"};
	
	/** 无资料的时候显示的提示 */
	public static final String UNKNOWN = "unknown";
	
	public static String[] allPlayerHeaders = {"Portrait","Name", "From", "To", "Position", "Birth" };
	
	/** 球员个人信息页面中的赛季数据的表头 */
	public static String [] onePlayerDataHeaders = {
		"Season","GP","ST","MP","FG%","3P%","FT%","ORB","DRB","TRB","AST","STL","BLK","TOV","PF","PTS"
	};
	/** 球员个人信息页面中的赛程数据的表头 */
	public static String [] onePlayerMatchHeaders = {
		"Date","VS","ST","MP","FG","3P","FT",
			"ORB","DRB","TRB","AST","STL","BLK","TOV","PF","PTS"
	};
	
	public static String [] matchPlayerHeaders = new String[] { "","Name", "ST", "Minutes", "FG", "3P", "FT",
		"ORB", "DRB", "TRB", "AST", "STL", "BLK", "TOV", "PF", "PTS", "+/-" };
	
	/** 所有球员赛季数据中的表格分成四部分，为基本、进攻、防守、高阶 */
	public static String []basicPlayerHeaders = {"RK","Name","Team","GP","ST","MP","PTS","TRB",
		"AST","BLK","STL","DD","PTS/RB/AST","TOV","PF"};
	public static String []advancedPlayerHeaders = {"RK","Name","EFF","GmSc","USG%","ORB%","DRB%",
		"TRB%","AST%","Real%","EFF%","BLK%","STL%","TOV%","PF%"};
	public static String []offensivePlayerHeaders = {"RK","Name","FG","FGA","FG%","3P","3PA","3P%",
		"FT","FTA", "FT%","AST","AST%","Real%","EFF%"};
	public static String []defensivePlayerHeaders = {"RK","Name","Team","GP","ST","ORB","DRB",
		"TRB","BLK","STL","ORB%","DRB%","TRB%","BLK%","STL%"};
	
	/** 所有球队赛季数据中的表格分成三部分，为基本、进攻、防守 */
	public static String []basicTeamHeaders = {"RK","Team","Wins","GP","Win%","PTS","RB","AST","BLK",
		"STL","TOV","PF","OEff","DEff"}; //14
	public static String []offensiveTeamHeaders = {"RK","Team","FG","FGA","FG%","3P","3PA","3P%",
		"FT","FTA","FT%","AST","ORound","OEff"};
	public static String []defensiveTeamHeaders = {"RK","Team","ORB","DRB","TRB","ORBEff",
		"DRBEff","BLK","STL","STL%","DRound","DEff","OppoPTS","OppoFG%"};
	
	/** 球队界面中的赛程的表头 */
	public static String []teamMatchHeaders = {"Date", "VS", "Win/Lose", "Score"};
	/** 球队信息页面中的赛季数据的表头 */
	public static String [] oneTeamDataHeaders = {
		"Season","GP","Win","Lose","Win%","FG%","3P%","FT%","ORB","DRB","TRB","AST","STL","BLK","TOV","PF","PTS"
	};
	
	
	/** 球员个人信息页面中的赛季数据的空表 */
	public static Object [][] oneTeamDataTableEmptyContent = 
		{{"Total","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"Average","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	/** 球队信息页面中的赛季数据的空表 */
	public static Object [][] onePlayerDataTableEmptyContent = 
		{{"Total","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"Average","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{"TeamAvg","","","","","","","","","","","","","","","",""},
		{"",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public static String[] teamLineupHeaders = new String[] {"Name", "Pos", "Age","Exp","Height","Weight","Birth","School"};

	
	
	public static final String [] TEAM_NAMES = {"Celtics", "Nets", "Knicks", "76ers", 
		"Raptors", "Bulls", "Cavaliers", "Pistons", "Pacers", "Bucks", "Hawks", "Hornets", 
		"Heat", "Magic", "Wizards", "Warriors", "Clippers", "Lakers", "Suns", 
		"Kings", "Nuggets", "Timberwolves", "Thunder", "Trail Blazers", "Jazz", "Mavericks",
		"Rockets", "Grizzlies", "Pelicans", "Spurs"};
	
	public static final String [] ALL_TEAM_NAMES = {"All", "Celtics", "Nets", "Knicks", "76ers", 
		"Raptors", "Bulls", "Cavaliers", "Pistons", "Pacers", "Bucks", "Hawks", "Hornets", 
		"Heat", "Magic", "Wizards", "Warriors", "Clippers", "Lakers", "Suns", 
		"Kings", "Nuggets", "Timberwolves", "Thunder", "Trail Blazers", "Jazz", "Mavericks",
		"Rockets", "Grizzlies", "Pelicans", "Spurs"};
	
	public static final String[] TEAM_PLACES = { "Boston", "Brooklyn", "New York", "Philadelphia", 
		"Toronto", "Chicago", "Cleveland", "Detroit", "Indiana", "Milwaukee", "Atlanta",
		"Charlotte" ,"Miami", "Orlando", "Washington", "Golden State", "Los Angeles", 
		"Los Angeles", "Phoenix", "Sacramento", "Denver", "Minnesota", "Oklahoma", 
		"Portland","Utah","Dallas","Houston", "Memphis", "New Orleans", "San Antonio" };
	
	public static final String[] GAME_SORT = {"%","3P%","FT%","RB","AST"};
	
	public static final String[] PLAYER_DATA_SORT = {"Basic","Offensive","Defensive","Advanced"};
	
	public static final String[] TEAM_DATA_SORT = {"Basic","Offensive","Defensive"};
	
	public static final String[] LBSTR = {"Points", "Rebound",  "Assist", "Block","Steal"};
	
	public static final String[] HOT_COLUMNS = {"RK", "Portrait", "Name", "Team", "Oppo", "Start", "MP", "PTS", "RB", "AST", "BLK",
			"STL"};
	
	public static final String[] ANALYSE = {"最后五分钟","生涯数据","球员贡献","球员走向","转会变化"};

}

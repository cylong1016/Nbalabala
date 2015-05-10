package autotest;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月11日  下午1:53:43
 */
public class SimpleConstants {
	
	public static String lastMonth = "";
	
	public static String lastDay = "";
	
	public static String sourcePath = "NBAdata\\";
	
	public static String getLeagueByAbbr(String abbr) {
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
			return "East";
		default:
			return "West";
		}
	}
	

}

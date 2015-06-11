/**
 * 
 */
package utility;

import java.sql.Date;

import enums.ScreenDivision;

/**
 *
 * @author Issac Ding
 * @since 2015年6月11日 下午2:09:37
 * @version 1.0
 */
public class TranslaterENG implements Translater{

	/* (non-Javadoc)
	 * @see utility.Translater#translateHeight(java.lang.String)
	 */
	@Override
	public String translateHeight(String height) {
		String [] heights = height.split("-");
		if (heights.length < 2 || heights[0].equals("0")) return Constants.UNKNOWN;
		return heights[0]+" feet "+heights[0] + " inches";
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateWeight(int)
	 */
	@Override
	public String translateWeight(int weight) {
		return String.valueOf(weight) + "pounds";
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateDate(java.sql.Date)
	 */
	@Override
	public String translateDate(Date date) {
		if (date == null) return Constants.UNKNOWN;
		return date.toString();
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateVeteran(int)
	 */
	@Override
	public String translateVeteran(int fromYear) {
		return String.valueOf(fromYear);
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translatePosition(java.lang.String)
	 */
	@Override
	public String translatePosition(String position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateTeamAbbr(java.lang.String)
	 */
	@Override
	public String translateTeamAbbr(String abbr) {
		if (abbr == null) return Constants.UNKNOWN;
		switch (abbr) {
		case "BOS":
			return "Celtics";
		case "BKN":
		case "NJN":
		case "BRK":
			return "Nets";
		case "NYK":
			return "Knicks";
		case "PHI":
			return "76ers";
		case "TOR":
			return "Raptors";
		case "DAL":
			return "Mavericks";
		case "HOU":
			return "Rockets";
		case "MEM":
		case "VAN":
			return "Grizzlies";
		case "NOP":
		case "NOK":
		case "NOH":
		case "CHH":
			return "Pelicans";
		case "SAS":
			return "Spurs"; 
		case "ATL":
			return "Hawks";
		case "CHA":
		case "CHO":
			return "Hornets";
		case "MIA":
			return "Heat";
		case "ORL":
			return "Magic";
		case "WAS":
			return "Wizard"; 
		case "WSB":
			return "Bullets";
		case "GSW":
			return "Warriors";
		case "LAC":
			return "Clippers";
		case "LAL":
			return "Lakers";
		case "PHX":
		case "PHO":
			return "Suns";
		case "SAC":
			return "Kings";
		case "CHI":
			return "Bulls";
		case "CLE":
			return "Cavaliers";
		case "DET":
			return "Pistons";
		case "IND":
			return "Pacers";
		case "MIL":
			return "Bucks";
		case "DEN":
			return "Nuggets";
		case "MIN":
			return "Timberwolves";
		case "OKC":
			return "Thunder";
		case "POR":
			return "Trail Blazers";
		case "UTA":
			return "Jazz";	
		case "SEA":
			return "超音速";
		default:
			return Constants.UNKNOWN;
		}
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateTeamAbbrToLocation(java.lang.String)
	 */
	@Override
	public String translateTeamAbbrToLocation(String abbr) {
		if (abbr == null) return Constants.UNKNOWN;
		switch (abbr) {
		case "BOS":
			return "Boston";
		case "NJN":
			return "New Jersey";
		case "BKN":
		case "BRK":
			return "Brooklyn";
		case "NYK":
			return "New York";
		case "PHI":
			return "Philadelphia";	
		case "TOR":
			return "Toronto";
		case "DAL":
			return "Dallas";
		case "HOU":
			return "Houston";
		case "MEM":
			return "Memphis";
		case "NOP":
		case "NOH":
			return "New Orleans";
		case "SAS":
			return "San Antonio"; 
		case "ATL":
			return "Atlanta";
		case "CHA":
		case "CHO":
			return "Charlotte";
		case "MIA":
			return "Miami";
		case "ORL":
			return "Orlando";
		case "WAS":
		case "WSB":
			return "Washington"; 
		case "GSW":
			return "Golden State";
		case "LAC":
		case "LAL":
			return "Los Angeles";
		case "PHX":
		case "PHO":
			return "Phoenix";
		case "SAC":
			return "Sacramento";
		case "CHI":
			return "Chicago";
		case "CLE":
			return "Cleveland";
		case "DET":
			return "Detroit";
		case "IND":
			return "Indiana";
		case "MIL":
			return "Milwaukee";
		case "DEN":
			return "Denver";
		case "MIN":
			return "Minnesota";
		case "OKC":
			return "Oklahoma";
		case "POR":
			return "Portland";
		case "UTA":
			return "Utah";
		case "VAN":
			return "Vancouver";
		case "SEA":
			return "Seattle";
		default:
			return Constants.UNKNOWN;
		}
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateTeamDivision(enums.ScreenDivision)
	 */
	@Override
	public String translateTeamDivision(ScreenDivision division) {
		switch (division) {
		case EAST:
			return "East";
		case WEST:
			return "West";
		case PACIFIC:
			return "Pacific";
		case ATLANTIC:
			return "Atlantic";
		case SOUTH_EAST:
			return "Southeast";
		case SOUTH_WEST:
			return "Southwest";
		case CENTRAL:
			return "Central";
		case NORTH_WEST:
			return "Northwest";
		default:
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see utility.Translater#translateTeamLeague(enums.ScreenDivision)
	 */
	@Override
	public String translateTeamLeague(ScreenDivision league) {
		if (league == ScreenDivision.EAST) {
			return "EAST";
		}else {
			return "WEST";
		}
	}

}

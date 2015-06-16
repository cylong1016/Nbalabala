package utility;

import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月26日  下午11:55:03
 */
public interface Translater {
	
	String translateHeight(String height);
	String translateWeight(String weight);
	String translateDate(String date);
	String translateVeteran(String veteran);
	String translatePosition(String position);
	String translateTeamAbbr(String abbr);
	String translateTeamAbbrToLocation(String abbr);
	String translateTeamDivision(ScreenDivision division);
	String translateTeamLeague(ScreenDivision league);

}

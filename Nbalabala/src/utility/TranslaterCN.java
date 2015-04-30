package utility;

import java.text.DecimalFormat;

import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午2:59:05
 */
public class TranslaterCN implements Translater{

	/**
	 * @see utility.Translater#translateHeight(java.lang.String)
	 */
	@Override
	public String translateHeight(String height) {
		// 身高换算为公制的2米13的形式
		try{
			String [] s = height.split("-");
			int feet = Integer.parseInt(s[0]);
			int inches = Integer.parseInt(s[1]);
			int cms = (int)(feet * 30.48 + inches * 2.54);
			int meters = cms / 100;
			return meters + "米" + (cms % 100);
		}catch(Exception e){
			return "无资料";
		}
	}

	/**
	 * @see utility.Translater#translateWeight(java.lang.String)
	 */
	@Override
	public String translateWeight(String weight) {
		// 体重显示磅和千克，保留一位小数
		try {
			double kgs = 0.4536 * Integer.parseInt(weight);
			DecimalFormat decimalFormat = new DecimalFormat(".#");
			String kgsString = decimalFormat.format(kgs);
			return  kgsString + "公斤";
		} catch (Exception e) {
			return "无资料";
		}
	}

	/**
	 * @see utility.Translater#translateBirthday(java.lang.String)
	 */
	@Override
	public String translateDate(String date) {
		String[] strings = date.split(" |,");
		if (strings.length == 1) {
			return Constants.UNKNOWN;
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

	/**
	 * @see utility.Translater#translateVeteran(java.lang.String)
	 */
	@Override
	public String translateVeteran(String veteran) {
		if (veteran.equals("R")){
			return "新秀";
		}else if (veteran.equals("无资料")){
			return veteran;
		}else {
			return veteran + "年";
		}
	}

	/**
	 * @see utility.Translater#translatePosition(java.lang.String)
	 */
	@Override
	public String translatePosition(String position) {
		if (position.contains("-")) {
			String[]s = position.split("-");
			return getPositionName(s[0] + "-" + s[1]);
		}else{
			return getPositionName(position);
		}
	}
	
	private String getPositionName(String position) {
		switch (position) {
		case "F":
			return "前锋";
		case "C":
			return "中锋";
		case "G":
			return "后卫";
		default:
			return "无";
		}
	}

	/**
	 * @see utility.Translater#translateTeamAbbr(java.lang.String)
	 */
	@Override
	public String translateTeamAbbr(String abbr) {
		switch (abbr) {
		case "BOS":
			return "凯尔特人";
		case "BKN":
		case "NJN":
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
		case "NOH":
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
			return "无资料";
		}
	}

	/**
	 * @see utility.Translater#translateTeamAbbrToLocation(java.lang.String)
	 */
	@Override
	public String translateTeamAbbrToLocation(String abbr) {
		switch (abbr) {
		case "BOS":
			return "波士顿";
		case "BKN":
		case "NJN":
			return "布鲁克林";
		case "NYK":
			return "纽约";
		case "PHI":
			return "费城";	
		case "TOR":
			return "多伦多";
		case "DAL":
			return "达拉斯";
		case "HOU":
			return "休斯敦";
		case "MEM":
			return "孟菲斯";
		case "NOP":
		case "NOH":
			return "新奥尔良";
		case "SAS":
			return "圣安东尼奥"; 
		case "ATL":
			return "亚特兰大";
		case "CHA":
			return "夏洛特";
		case "MIA":
			return "迈阿密";
		case "ORL":
			return "奥兰多";
		case "WAS":
			return "华盛顿"; 
		case "GSW":
			return "金洲";
		case "LAC":
		case "LAL":
			return "洛杉矶";
		case "PHX":
			return "菲尼克斯";
		case "SAC":
			return "萨克拉门托";
		case "CHI":
			return "芝加哥";
		case "CLE":
			return "克利夫兰";
		case "DET":
			return "底特律";
		case "IND":
			return "印第安纳";
		case "MIL":
			return "密尔沃基";
		case "DEN":
			return "丹佛";
		case "MIN":
			return "明尼苏达";
		case "OKC":
			return "俄克拉荷马";
		case "POR":
			return "波特兰";
		case "UTA":
			return "犹他";
		default:
			return "无资料";
		}
	}

	/**
	 * @see utility.Translater#translateTeamDivision(enums.ScreenDivision)
	 */
	@Override
	public String translateTeamDivision(ScreenDivision division) {
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

	/**
	 * @see utility.Translater#translateTeamLeague(enums.ScreenDivision)
	 */
	@Override
	public String translateTeamLeague(ScreenDivision league) {
		if (league == ScreenDivision.EAST) {
			return "东部联盟";
		}else {
			return "西部联盟";
		}
	}
	
}

/**
 * 
 */
package utility;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

import enums.ScreenDivision;

/**
 *
 * @author Issac Ding
 * @since 2015年6月11日 下午2:42:15
 * @version 1.0
 */
public class TranslaterCTR implements Translater{
	

	/**
	 * @see utility.Translater#translateHeight(java.lang.String)
	 */
	@Override
	public String translateHeight(String height) {
		// 身高換算為公制的2米13的形式
		try{
			String [] s = height.split("-");
			int feet = Integer.parseInt(s[0]);
			int inches = Integer.parseInt(s[1]);
			int cms = (int)(feet * 30.48 + inches * 2.54);
			int meters = cms / 100;
			return meters + "米" + (cms % 100);
		}catch(Exception e){
			return "無資料";
		}
	}

	/**
	 * @see utility.Translater#translateWeight(java.lang.String)
	 */
	@Override
	public String translateWeight(int weight) {
		// 體重顯示磅和千克，保留壹位小數
		try {
			double kgs = 0.4536 * (weight);
			DecimalFormat decimalFormat = new DecimalFormat(".#");
			String kgsString = decimalFormat.format(kgs);
			return  kgsString + "公斤";
		} catch (Exception e) {
			return "無資料";
		}
	}

	/**
	 * @see utility.Translater#translateBirthday(java.lang.String)
	 */
	@Override
	public String translateDate(Date date) {
		if (date == null) return Constants.UNKNOWN;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) 
				+ "/" + calendar.get(Calendar.DAY_OF_MONTH); 
	}

	/**
	 * @see utility.Translater#translateVeteran(java.lang.String)
	 */
	@Override
	public String translateVeteran(int fromYear) {
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		if (fromYear == thisYear) {
			return "新秀";
		}else {
			return String.valueOf(thisYear - fromYear)+"年";
		}
	}

	/**
	 * @see utility.Translater#translatePosition(java.lang.String)
	 */
	@Override
	public String translatePosition(String position) {
		if (position.contains("-")) {
			String[]s = position.split("-");
			return getPositionName(s[0]) + "-" + getPositionName(s[1]);
		}else{
			return getPositionName(position);
		}
	}
	
	private String getPositionName(String position) {
		switch (position) {
		case "F":
			return "前鋒";
		case "C":
			return "中鋒";
		case "G":
			return "後衛";
		default:
			return "無";
		}
	}

	/**
	 * @see utility.Translater#translateTeamAbbr(java.lang.String)
	 */
	@Override
	public String translateTeamAbbr(String abbr) {
		if (abbr == null) return Constants.UNKNOWN;
		switch (abbr) {
		case "BOS":
			return "凱爾特人";
		case "BKN":
		case "NJN":
		case "BRK":
			return "籃網";
		case "NYK":
			return "尼克斯";
		case "PHI":
			return "76人";
		case "TOR":
			return "猛龍";
		case "DAL":
			return "小牛";
		case "HOU":
			return "火箭";
		case "MEM":
		case "VAN":
			return "灰熊";
		case "NOP":
		case "NOK":
		case "NOH":
		case "CHH":
			return "鵜鶘";
		case "SAS":
			return "馬刺"; 
		case "ATL":
			return "老鷹";
		case "CHA":
		case "CHO":
			return "黃蜂";
		case "MIA":
			return "熱火";
		case "ORL":
			return "魔術";
		case "WAS":
			return "奇才"; 
		case "WSB":
			return "子彈";
		case "GSW":
			return "勇士";
		case "LAC":
			return "快船";
		case "LAL":
			return "湖人";
		case "PHX":
		case "PHO":
			return "太陽";
		case "SAC":
			return "國王";
		case "CHI":
			return "公牛";
		case "CLE":
			return "騎士";
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
			return "開拓者";
		case "UTA":
			return "爵士";
		case "SEA":
			return "超音速";
		default:
			return Constants.UNKNOWN;
		}
	}

	/**
	 * @see utility.Translater#translateTeamAbbrToLocation(java.lang.String)
	 */
	@Override
	public String translateTeamAbbrToLocation(String abbr) {
		if (abbr == null) return Constants.UNKNOWN;
		switch (abbr) {
		case "BOS":
			return "波士頓";
		case "NJN":
			return "新澤西";
		case "BKN":
		case "BRK":
			return "布魯克林";
		case "NYK":
			return "紐約";
		case "PHI":
			return "費城";	
		case "TOR":
			return "多倫多";
		case "DAL":
			return "達拉斯";
		case "HOU":
			return "休斯敦";
		case "MEM":
			return "孟菲斯";
		case "NOP":
		case "NOH":
			return "新奧爾良";
		case "SAS":
			return "聖安東尼奧"; 
		case "ATL":
			return "亞特蘭大";
		case "CHA":
		case "CHO":
			return "夏洛特";
		case "MIA":
			return "邁阿密";
		case "ORL":
			return "奧蘭多";
		case "WAS":
		case "WSB":
			return "華盛頓"; 
		case "GSW":
			return "金洲";
		case "LAC":
		case "LAL":
			return "洛杉磯";
		case "PHX":
		case "PHO":
			return "菲尼克斯";
		case "SAC":
			return "薩克拉門托";
		case "CHI":
			return "芝加哥";
		case "CLE":
			return "克利夫蘭";
		case "DET":
			return "底特律";
		case "IND":
			return "印第安納";
		case "MIL":
			return "密爾沃基";
		case "DEN":
			return "丹佛";
		case "MIN":
			return "明尼蘇達";
		case "OKC":
			return "俄克拉荷馬";
		case "POR":
			return "波特蘭";
		case "UTA":
			return "猶他";
		case "VAN":
			return "溫哥華";
		case "SEA":
			return "西雅圖";
		default:
			return "無資料";
		}
	}

	/**
	 * @see utility.Translater#translateTeamDivision(enums.ScreenDivision)
	 */
	@Override
	public String translateTeamDivision(ScreenDivision division) {
		switch (division) {
		case EAST:
			return "東部";
		case WEST:
			return "西部";
		case PACIFIC:
			return "太平洋";
		case ATLANTIC:
			return "大西洋";
		case SOUTH_EAST:
			return "東南";
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
			return "東部聯盟";
		}else {
			return "西部聯盟";
		}
	}

}

package capture;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mysql.MySQL;

/**
 * 抓取NBA数据
 * @author cylong
 * @version 2015年5月21日 下午10:37:48
 */
public abstract class NBAData {

	/** 网站链接 */
	protected String root = "http://www.basketball-reference.com";
	/** 将要抓取数据的url */
	protected String captureUrl;
	/** 数据库连接 */
	protected static Connection mysqlConn;
	/** 英文月份=>数字月份 */
    private Hashtable<String, String> month = new Hashtable<String, String>(24);
    /** 英文月份缩写 */
    private String[] monthAbbrStrArr = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                                 "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    /** 英文月份全称 */
    private String[] monthStrArr = {"January", "February", "March", "April", "May", "June", 
                                 "July", "August", "September", "October", "November", "December"};
	
	static {
		MySQL mysql = new MySQL();
		mysqlConn = mysql.getConn(); // 获得数据库连接
	}
	
	public NBAData() {
		for(int i = 0; i < monthAbbrStrArr.length; i++) {
    		month.put(monthAbbrStrArr[i], String.valueOf(i + 1));
    		month.put(monthStrArr[i], String.valueOf(i + 1));
		}
	}

	public abstract void capture();

	/**
	 * 得到某个url的连接
	 * @param url
	 * @return HttpURLConnection
	 * @author cylong
	 * @version 2015年5月21日 下午2:33:03
	 */
	protected HttpURLConnection getConn(String url) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = (HttpURLConnection)new URL(url).openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setUseCaches(true);
			urlConn.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlConn;
	}
	
	/**
	 * 将网站上的日期修改成mysql的格式
	 * @param date 形如 Tue, Oct 28, 2014的日期格式
	 * @return 形如 2014-10-28 的格式
	 * @author cylong
	 * @version 2015年6月4日  上午1:43:36
	 */
	protected String dateFormat(String date) {
		String splitReg = "([A-Z][a-z]{2}, )?(?<month>[A-Z][a-z]+?) (?<day>[0-9]{1,2}), (?<year>[0-9]{4})"; // 拆分抓取下来的时间字符串
		Pattern patternDate = Pattern.compile(splitReg);
		Matcher matcherDate = patternDate.matcher(date);
		String formatDate = null;
		if(matcherDate.find()) {
			String year = matcherDate.group("year");
			String month = this.month.get(matcherDate.group("month"));
			String day = matcherDate.group("day");
			formatDate = year + "-" + month + "-" + day;
		}
		return formatDate;
	}

}
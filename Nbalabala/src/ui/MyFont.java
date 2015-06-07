package ui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

/*
 * 一共有四种常用字体
 * 其中只有冬青黑即Hiragino是需要导入的，其他都是自带的
 * 分别是：
 * 冬青黑体：Hiragino---->
 * 方正姚体：方正姚体---->所有的球衣号、挑出来写的得分、胜负得分、换页数字
 * 微软雅黑：微软雅黑---->选项、按钮
 * ArialMT：ArialMT---->小字体
 */

public class MyFont{
	
	public static Font Hiragino;
	static{
		try {
	        java.io.FileInputStream fi = new java.io.FileInputStream(new File("Hiragino.ttf"));
	        java.io.BufferedInputStream fb = new java.io.BufferedInputStream(fi);
	        Font nf = Font.createFont(Font.TRUETYPE_FONT, fb);
	        Hiragino = nf.deriveFont(Font.PLAIN, 12);
	      }
	      catch (Exception e) {
	      }
	}
	
	/** 场均排名的th、nd之类的 */
	public static final Font YT_XS = new Font("方正姚体", Font.PLAIN, 14);
	/** 场均排名的数字 */
	public static final Font YT_S = new Font("方正姚体", Font.PLAIN, 20);
	/** 数据王2345名的名字 */
	public static final Font YT_M = new Font("方正姚体", Font.PLAIN, 20);
	/** 大的胜负场数、数据王2345名的数据、第一名的名字 */
	public static final Font YT_L = new Font("方正姚体", Font.PLAIN, 32);
	/** 数据王第一名的分数 */
	public static final Font YT_XL = new Font("方正姚体", Font.PLAIN, 36);
	 
	public static final Font Arial_S = new Font("ArialMT", Font.PLAIN, 12);
	public static final Font Arial_B = new Font("ArialMT", Font.PLAIN, 13);
	
	public static final Font YH_XS = new Font("微软雅黑", Font.PLAIN, 12);
	public static final Font YH_S = new Font("微软雅黑", Font.PLAIN, 14);
	public static final Font YH_B = new Font("微软雅黑", Font.PLAIN, 16);
	
	public static final Color BLACK_GRAY = new Color(65, 65, 65);
	public static final Color DARK_GRAY = new Color(91, 91, 91);
	public static final Color LIGHT_GRAY = new Color(122, 122, 122);

}

package ui;

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
	 public static final Font YT_S = new Font("方正姚体", Font.PLAIN, 30);
	 public static final Font YT_B = new Font("方正姚体", Font.PLAIN, 50);
	 
	 public static final Font Arial_S = new Font("ArialMT", Font.PLAIN, 12);
	 public static final Font Arial_B = new Font("ArialMT", Font.PLAIN, 13);
	 
	 public static final Font YH = new Font("微软雅黑", Font.PLAIN, 16);

}

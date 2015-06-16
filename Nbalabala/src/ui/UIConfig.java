package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.text.DecimalFormat;

/**
 * UI配置
 * @author cylong
 * @version 2015年3月16日 下午7:29:26
 */
public class UIConfig {

	/*----------------------------Frame配置--------------------------------*/
	/** 界面的宽 */
	public static final int WIDTH = 1153;
	/** 界面的高 */
	public static final int HEIGHT = 631;
	/** 主界面的背景色 */
	public static final Color MAIN_COLOR = Color.WHITE;
	/** 右边边框的宽度 */
	public static final int RIGHT_WIDTH = 153;
	/** 会弹出左边栏的宽度 */
	public static final int PROMPT_WIDTH = 20;

	/*----------------------------Frame配置--------------------------------*/

	/** 图片路径 */
	public static final String IMG_PATH = "images/";
	
	/** 2.0图片路径 */
	public static final String IMG_PATH_2 = "images2.0/";

	/** 界面显示的字体 */
	public static final Font FONT = new Font("微软雅黑", 0, 14);

	/** button选中之后的颜色 */
	public static final Color DARK_BUTTON_COLOR = new Color(15, 24, 44);
	
	public static final Color BUTTON_COLOR = new Color(60, 79, 179);

	/*----------------------------表格配置--------------------------------*/
	/** 表格字体 */
	public static final Font TABLE_FONT = MyFont.YH_S;
	public static final Font TABLE_HEADER_FONT = MyFont.YH_XS;
	
	/** 表格字体颜色 */
	public static final Color TABLE_FORE_COLOR = MyFont.DARK_GRAY;
	/** 表格背景颜色 */
	public static final Color TABLE_BACK_COLOR = Color.WHITE;
	/** 表格边框颜色 */
	public static final Color TABLE_BORDER_COLOR = Color.LIGHT_GRAY;
	/** 表头前景颜色 */
	public static final Color TABLE_HEADER_FORE_COLOR = Color.WHITE;
	/** 表头背景颜色 */
	public static final Color TABLE_HEADER_BACK_COLOR = MyFont.DARK_GRAY;
	/** 选择后的前景色 */
	public static final Color TABLE_SELECTIONFORE = Color.WHITE;
	/** 选择后的背景色 */
	public static final Color TABLE_SELECTIONBACK = MyFont.BLACK_GRAY;
	/** 表格预设大小 */
	public static final Dimension TABLE_DIMEN = new Dimension(888, 290);
	/** 表格中数据显示的小数点位数 */
	public static final DecimalFormat FORMAT = new DecimalFormat("0.0");
	/** 表格中百分数的数据格式*/
	public static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("0.0%");
	/** 表格每一行的高 */
	public static final int ROW_HEIGHT = 40;
	/** 表格的表头高度*/
	public static final int TABLE_HEADER_HEIGHT = 30;
	/** 表格的宽度 */
	public static final int TABLE_WID = 888;
	/** 表格的 高度 */
	public static final int TABLE_H = 290;
	/** 表格的相对坐标X */
	public static final int RELA_X = 29;
	/** 表格纵向滚动条的宽度 */
	public static final int SCROLLBAR_WIDTH = 16;
	/** 所有球员的表格行高 */
	public static final int ALL_PLAYERS_ROW_H = 70;
	/*----------------------------表格配置--------------------------------*/
	/*----------------------------柱状图配置--------------------------------*/
	/** 平均的柱状图颜色 */
	public static final Color HIST_AVG_COLOR = new Color(206, 206, 206);
	/** 球员的柱状图颜色 */
	public static final Color HIST_PLAYER_COLOR = new Color(56, 167, 229);
	/** 第一名的柱子颜色 */
	public static final Color HIST_FIRST_COLOR = new Color(238, 189, 58);
	/** 第二名 */
	public static final Color HIST_SECOND_COLOR = new Color(180, 63, 86);
	/** 第三名 */
	public static final Color HIST_THIRD_COLOR = new Color(165, 107, 141);
	/** 第四名 */
	public static final Color HIST_FORTH_COLOR = new Color(115, 122, 171);
	/** 第五名 */
	public static final Color HIST_FIFTH_COLOR = new Color(85, 145, 204);
	public static final Color LIGHT_BLUE = new Color(135, 189, 245);
	/** 颜色组合 */
	public static final Color[] HIST_COLORS= {HIST_FIRST_COLOR, LIGHT_BLUE,
		LIGHT_BLUE, LIGHT_BLUE, LIGHT_BLUE,HIST_FIRST_COLOR};
	
	
	/** 第一级选项卡按钮的字体 */
	public static final Font FIRST_LEVEL_TAB_FONT = MyFont.HiraginoM;
	/** 第二级选项卡按钮的字体 */
	public static final Font SECOND_LEVEL_TAB_FONT = MyFont.HiraginoS;
	
	/** 显示 中锋 等文字的字体 */
	public static final Font LABEL_PLAIN_FONT = new Font("微软雅黑", Font.PLAIN, 12);
	
	/** 显示 毕业学校  等文字的字体 */
	public static final Font LABEL_SMALL_FONT =  MyFont.YH_XS;
	
	public static final Color ORANGE_TEXT_COLOR = new Color(235,148,24);
	
	public static final Color BLUE_TEXT_COLOR = new Color(50, 126, 196);
	public static final Color DARK_BLUE_TEXT_COLOR = new Color(42, 107, 162);
	public static final Color NAV_BAR_BLUE_COLOR = new Color(14, 74, 155);
	
	/** 所有代表胜利的红色字体的颜色 */
	public static final Color RED_WIN_COLOR = new Color(190,45,45);
	
	/** 球员和球队界面，简况Label之间的Y的差值 */
	public static final int PROFILE_LABEL_INTER_Y = 20;
	
	public static Font HiraginoFont;
	static{
		try {
	        java.io.FileInputStream fi = new java.io.FileInputStream(new File("Hiragino.ttf"));
	        java.io.BufferedInputStream fb = new java.io.BufferedInputStream(fi);
	        Font nf = Font.createFont(Font.TRUETYPE_FONT, fb);
	        HiraginoFont = nf.deriveFont(Font.PLAIN, 12);
	      }
	      catch (Exception e) {
	      }
	}
	 public static final Font FZYT_FONT = new Font("方正姚体", Font.PLAIN, 10);
	 public static final Font ArialMT_FONT = new Font("ArialMT Regular", Font.PLAIN, 10);
	 
	 public static final int TITLE_LABEL_H = 43;
	 public static final int TITLE_LABEL_W = 141;
	 
	 /** 返回按钮的位置 */
	 public static final int RETURN_X = 10;
	 public static final int RETURN_Y = 26;
	 
	 /*----------------------------图表配置--------------------------------*/
	 public static final Color CHAR_BG_COLOR = new Color(249, 249, 249);
	 
	 public static final int RIGHT_CHAR_X = 26;
	 
	 public static final int CHAR_WIDTH = 600;
	 public static final int CHAR_HEIGHT = 400;
	 
	 public static final int CHAR_X = 20;
	 public static final int CHAR_Y = 70;

		
	 public static final int CONCLUSION_Y = 128;
	 public static final int CONCLUSION_X = 687;
	 
	 public static final Color CHART_ORANGE = new Color(249, 191, 67);

}

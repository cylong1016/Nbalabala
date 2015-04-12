package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

/**
 * UI配置
 * @author cylong
 * @version 2015年3月16日 下午7:29:26
 */
public class UIConfig {

	/*----------------------------Frame配置--------------------------------*/
	/** 界面的宽 */
	public static final int WIDTH = 1000;
	/** 界面的高 */
	public static final int HEIGHT = 632;
	/** 主界面的背景色 */
	public static final Color MAIN_COLOR = Color.WHITE;
	/** 左边边框的宽度 */
	public static final int LEFT_WIDTH = 153;
	/** 会弹出左边栏的宽度 */
	public static final int PROMPT_WIDTH = 20;

	/*----------------------------Frame配置--------------------------------*/

	/** 图片路径 */
	public static final String IMG_PATH = "images/";

	/** 界面显示的字体 */
	public static final Font FONT = new Font("微软雅黑", 0, 14);

	/** button选中之后的颜色 */
	public static final Color BUTTON_COLOR = new Color(15, 24, 44);

	/*----------------------------表格配置--------------------------------*/
	/** 表格字体 */
	public static final Font TABLE_FONT = new Font("黑体", Font.PLAIN, 13);
	/** 表格字体颜色 */
	public static final Color TABLE_FORE_COLOR = Color.BLACK;
	/** 表格背景颜色 */
	public static final Color TABLE_BACK_COLOR = Color.WHITE;
	/** 表格边框颜色 */
	public static final Color TABLE_BORDER_COLOR = Color.LIGHT_GRAY;
	/** 表头前景颜色 */
	public static final Color TABLE_HEADER_FORE_COLOR = new Color(255, 221, 31);
	/** 表头背景颜色 */
	public static final Color TABLE_HEADER_BACK_COLOR = new Color(25, 43, 102);
	/** 选择后的前景色 */
	public static final Color TABLE_SELECTIONFORE = new Color(255, 150, 0);
	/** 选择后的背景色 */
	public static final Color TABLE_SELECTIONBACK = Color.BLACK;
	/** 表格预设大小 */
	public static final Dimension TABLE_DIMEN = new Dimension(888, 290);
	/** 表格中数据显示的小数点位数 */
	public static final DecimalFormat FORMAT = new DecimalFormat("0.000");
	/*----------------------------表格配置--------------------------------*/
	/*----------------------------柱状图配置--------------------------------*/
	/** 平均的柱状图颜色 */
	public static final Color HIST_AVG_COLOR = new Color(41, 80, 160);
	/** 球员的柱状图颜色 */
	public static final Color HIST_PLAYER_COLOR = new Color(243, 196, 72);
	/** 第一名的柱子颜色 */
	public static final Color HIST_FIRST_COLOR = new Color(236, 31, 34);
	/** 第二名 */
	public static final Color HIST_SECOND_COLOR = new Color(180, 63, 86);
	/** 第三名 */
	public static final Color HIST_THIRD_COLOR = new Color(165, 107, 141);
	/** 第四名 */
	public static final Color HIST_FORTH_COLOR = new Color(115, 122, 171);
	/** 第五名 */
	public static final Color HIST_FIFTH_COLOR = new Color(85, 145, 204);
	/** 颜色组合 */
	public static final Color[] HIST_COLORS= {HIST_FIRST_COLOR, HIST_SECOND_COLOR,
		HIST_THIRD_COLOR, HIST_FORTH_COLOR, HIST_FIFTH_COLOR};
}

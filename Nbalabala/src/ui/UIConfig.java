package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * UI配置
 * @author cylong
 * @version 2015年3月16日 下午7:29:26
 */
public class UIConfig {

	/*----------------------------Frame配置--------------------------------*/
	/** 界面的宽 */
	public static int WIDTH = 1000;
	/** 界面的高 */
	public static int HEIGHT = 632;
	/** 主界面的背景色 */
	public static Color MAIN_COLOR = Color.WHITE;
	/** 左边边框的宽度 */
	public static int LEFT_WIDTH = 189;
	/** 会弹出左边栏的宽度 */
	public static int PROMPT_WIDTH = 20;

	/*----------------------------Frame配置--------------------------------*/

	/** 图片路径 */
	public static String IMG_PATH = "images/";

	/** 界面显示的字体 */
	public static Font FONT = new Font("微软雅黑", 0, 14);
	
	/** button选中之后的颜色 */
	public static Color BUTTON_COLOR = new Color(15,24,44);
	
	/*----------------------------表格配置--------------------------------*/
	/** 表格字体 */
	public static Font TABLE_FONT = new Font("黑体", Font.PLAIN, 13);
	/** 表格字体颜色 */
	public static Color TABLE_FORE_COLOR = Color.BLACK;
	/** 表格边框颜色 */
	public static Color TABLE_BORDER_COLOR = Color.LIGHT_GRAY;
	/** 表头前景颜色 */
	public static Color TABLE_HEADER_FORE_COLOR = new Color(0x12203A);
	/** 表头背景颜色 */
	public static Color TABLE_HEADER_BACK_COLOR = new Color(0x22355F);
	/** 选择后的前景色 */
	public static Color TABLE_SELECTIONFORE = Color.BLUE;
	/** 选择后的背景色 */
	public static Color TABLE_SELECTIONBACK = Color.BLACK;
	/** 表格预设大小 */
	public static Dimension TABLE_DIMEN = new Dimension(888, 290);
	/*----------------------------表格配置--------------------------------*/

}

package ui.panel.playerData;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;

/**
 * @author lsy
 * @version 2015年3月18日 下午6:28:36
 */
public class PlayerDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 代表所有button集合 */
	private TextButton[] button;
	/** button数量为30 */
	private int buttonNum = 30;
	/** button的宽度 高度 */
	private int width = 60, height = 24, widthThree = 75, widthLong = 135;

	/** 分别代表第一行到第五行的纵坐标 */
	private int y1 = 27, y2 = 66, y3 = 107, y4 = 138, y5 = 171;
	/** 代表所有列的前三个button的横坐标 */
	// private int x1 = 227, x2 = 278, x3 = 330;
	private int x1 = 227, x2 = 288, x3 = 349;
	/** “所有”的横坐标 */
	private int allX = 156;
	/** 间隔 */
	private int inter = 61;
	/** 东部 西部 太平洋 西北 西南 */
	private int east = 440, west = east + inter, pacific = west + width + east - x3 - widthThree,
			northWest = pacific + inter + widthThree - width, southWest = northWest + inter;
	/** 得分/篮板/助攻 盖帽 抢断 犯规 失误 分钟 */
	private int three = x3 + inter, block = three + inter + widthLong - width, steal = block + inter, foul = block
			+ 2 * inter, turnover = block + 3 * inter, minute = block + 4 * inter;
	/** 罚球 两双 */
	private int freeThrow = x3 + inter, double_double = freeThrow + inter;
	/** 总计 平均 */
	private int total = 756, average = 823;

	/** 所有button横坐标集合 */
	private int[] buttonX = new int[] { allX, x1, x2, x3, allX, x1, x2, x3, east, west, pacific, northWest,
			southWest, allX, x1, x2, x3, three, block, steal, foul, turnover, minute, x1, x2, x3, freeThrow,
			double_double, total, average };
	/** 所有button上文字部分横坐标集合 */
	private String[] text = new String[] { "所有", "前锋", "中锋", "后卫", "所有", "东南", "中央", "大西洋", "东部", "西部", "太平洋",
			"西北", "西南", "所有", "得分", "篮板", "助攻", "得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球",
			"两双", "总计", "平均" };
	private TextButton[]line1=new TextButton[4];
	private TextButton[]line2=new TextButton[9];
	private TextButton[]line3=new TextButton[15];
	private TextButton[]line4=new TextButton[2];
	private boolean[] isClicked1 = new boolean[4];
	private boolean[] isClicked2 = new boolean[9];
	private boolean[] isClicked3 = new boolean[15];
	private boolean[] isClicked4 = new boolean[2];

	/** “所有”按钮组成一个数组 */
	private int[] all = new int[] { 0, 4, 13 };
	public PlayerDataPanel(String url) {
		super(url);
		setButton();
		// setEffect(all);
		addButton();
		setArray();
		addListener(0,4,line1,isClicked1);
		addListener(4,13,line2,isClicked2);
		addListener(13,28,line3,isClicked3);
		addListener(28,30,line4,isClicked4);
	}
	
	public void setArray(){
		for(int i = 0;i<4;i++){
			line1[i] = button[i];
			isClicked1[i] = false;
		}
		for(int i = 4;i<13;i++){
			line2[i-4] = button[i];
			isClicked2[i-4] = false;
		}
		for(int i = 13;i<28;i++){
			line3[i-13] = button[i];
			isClicked3[i-13] = false;
		}
		for(int i = 28;i<30;i++){
			line3[i-28] = button[i];
			isClicked4[i-28] = false;
		}
	}

	public void setButton() {
		button = new TextButton[buttonNum];
		for (int i = 0; i < 4; i++) {
			button[i] = new TextButton(buttonX[i], y1, width, height, text[i]);
		}
		for (int i = 4; i < 13; i++) {
			if (i == 7 || i == 10) {
				button[i] = new TextButton(buttonX[i], y2, widthThree, height, text[i]);
			} else {
				button[i] = new TextButton(buttonX[i], y2, width, height, text[i]);
			}
		}
		for (int i = 13; i < 23; i++) {
			if (i == 17) {
				button[i] = new TextButton(buttonX[i], y3, widthLong, height, text[i]);
			} else {
				button[i] = new TextButton(buttonX[i], y3, width, height, text[i]);
			}
		}
		for (int i = 23; i < 28; i++) {
			button[i] = new TextButton(buttonX[i], y4, width, height, text[i]);
		}
		for (int i = 28; i < buttonNum; i++) {
			button[i] = new TextButton(buttonX[i], y5, width, height, text[i]);
		}
	}

	public void addButton() {
		for (int i = 0; i < buttonNum; i++) {
			this.add(button[i]);
		}
	}

	/**
	 * 设置按钮初始被选中的效果
	 * 
	 * @author lsy
	 * @version 2015年3月18日 下午11:43:53
	 */
	public void setEffect(int[] all) {
		for (int i = 0; i < all.length; i++) {
			button[all[i]].setForeground(new Color(15, 24, 44));
			button[all[i]].setForeground(Color.WHITE);
		}
	}

	/** 计数器 */
	int index = 0,k=0;
	
	public void addListener(final int firstIndex,final int lth,TextButton[] buttonArray,final boolean[] isClicked) {
		for (index = firstIndex; index < lth; index++) {
			button[index].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					for (int j = firstIndex; j < lth; j++) {
						isClicked[j - firstIndex] = false;
					}
					for (k = firstIndex; k < lth; k++) {
						if (e.getSource() == button[k]) {
							 isClicked[k-firstIndex]= true;
							 resetButton(isClicked,firstIndex);
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

			});
		}
	}

	public void resetButton(boolean[]isClicked,int first) {
		
		for (int i=0 ; i < isClicked.length; i++) {
			if (isClicked[i] == false) {
				button[i+first].back();
			}
		}
	}

}

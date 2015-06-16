package ui.common.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;

import po.ClutchPO;
import ui.UIConfig;


/**
 * 二维表,x轴是time、y轴是score
 * @author cylong
 * @version 2015年6月16日  下午10:49:31
 */
public class TwoDChart extends JTable {
	
	private ArrayList<ClutchPO> cluthPOs;
	/** serialVersionUID */
	private static final long serialVersionUID = -3980137740237014807L;
	
	/** 图中点的大小，最好是偶数 */
	private int dotSize = 10;
	/** 圆点在panel上的的坐标 */
	private Point dotPoint;
	/** x轴长 */
	private int xLen;
	/** y轴长 */
	private int yLen;
	/** 所有球员的最大分数 */
	private double maxScore;
	private DecimalFormat df = new DecimalFormat("#.00");
	
	/** 当前选中的球员 */
	private String currentName;
	
	public TwoDChart(ArrayList<ClutchPO> cluthPOs, String currentName) {
		this.currentName = currentName;
		this.cluthPOs = cluthPOs;
		this.setBackground(UIConfig.CHAR_BG_COLOR);
	}
	
	/**
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		dotPoint = new Point(50, height - 50);
		xLen = width - 100;
		yLen = height - 100;
		super.setBounds(x, y, width, height);
		addDots(); // 向图中添加点
		this.repaint();
	}
	
	private void addDots() {
		maxScore = 0;
		for(ClutchPO po : cluthPOs) { // 找到最大的得分
			po.clutchScore = Double.parseDouble(df.format(po.clutchScore));
			po.clutchTime = Double.parseDouble(df.format(po.clutchTime));
			if(po.clutchScore > maxScore) {
				maxScore = po.clutchScore;
			}
		}
		maxScore += 4;
		for(ClutchPO po : cluthPOs) {
			Dot dot = new Dot(dotPoint.x + (int)(po.clutchTime * xLen), 
					dotPoint.y - (int)((po.clutchScore / maxScore) * yLen) - dotSize, dotSize, 
					po.name + " (" + po.clutchTime + ", " + po.clutchScore + ")");
			if(po.name.equals(currentName)) {
				dot.setBackground(Color.ORANGE); // 当前选中的球员点颜色改变
			}
			this.add(dot);
		}
	}

	/**
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int yNum = 10; // y坐标轴上显示的点
		for(int i = 0; i <= yNum; i++) {
			int y = dotPoint.y - (yLen / yNum) * i;
			g.drawString(df.format(i * maxScore / yNum), dotPoint.x - 40, y + 5);
			g.drawLine(dotPoint.x - 6, y, dotPoint.x - 1, y);
		}
		g.drawString("得分/48分钟", dotPoint.x - 30, dotPoint.y - yLen - 10);
		
		int xNum = 10; // x坐标轴上显示的点
		for(int i = 0; i <= xNum; i++) {
			int x = dotPoint.x + (xLen / xNum) * i;
			g.drawString(String.valueOf(i * 1.0 / xNum), x - 10, dotPoint.y + 20);
			g.drawLine(x, dotPoint.y + 1, x, dotPoint.y + 4);
		}
		g.drawString("上场时间比率", dotPoint.x + xLen / 2, dotPoint.y + 35);
		g.drawRect(dotPoint.x, dotPoint.y - yLen, xLen, yLen);
	}
}

class Dot extends JButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -5581141648284955466L;
	
	public Dot(int x, int y, int size, String name) {
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBounds(x, y, size, size);
		this.setMargin(new Insets(0,0,0,0));
		this.setToolTipText(name);
		this.setOpaque(true);
		this.setBackground(UIConfig.LIGHT_BLUE);
	}
	
}
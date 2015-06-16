package ui.common.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import po.ClutchPO;


/**
 * 二维表,x轴是time、y轴是score
 * @author cylong
 * @version 2015年6月16日  下午10:49:31
 */
public class TwoDChart extends JTable {
	
	public static void main(String[] args) {
		ArrayList<ClutchPO> cluthPOs = new ArrayList<ClutchPO>();
		for(int i = 0; i <= 10; i++) {
			ClutchPO po = new ClutchPO("lsy", "2014-15");
			po.clutchScore = i;
			po.clutchTime = i / 10.0;
			cluthPOs.add(po);
		}
		TwoDChart chart = new TwoDChart(cluthPOs);
		chart.setBounds(10, 10, 400, 300);
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.add(chart);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private ArrayList<ClutchPO> cluthPOs;
	/** serialVersionUID */
	private static final long serialVersionUID = -3980137740237014807L;
	
	/** 图中点的大小，最好是偶数 */
	private int dotSize = 6;
	/** 圆点在panel上的的坐标 */
	private Point dotPoint;
	/** x轴长 */
	private int xLen;
	/** y轴长 */
	private int yLen;
	/** 所有球员的最大分数 */
	private double maxScore;
	
	public TwoDChart(ArrayList<ClutchPO> cluthPOs) {
		this.cluthPOs = cluthPOs;
		this.setBackground(Color.LIGHT_GRAY);
	}
	
	/**
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
			if(po.clutchScore > maxScore) {
				DecimalFormat df = new DecimalFormat("#.00");
				po.clutchScore = Double.parseDouble(df.format(po.clutchScore));
				maxScore = po.clutchScore;
			}
		}
		maxScore += 4;
		for(ClutchPO po : cluthPOs) {
			Dot dot = new Dot(dotPoint.x + (int)(po.clutchTime * xLen), 
					dotPoint.y - (int)((po.clutchScore / maxScore) * yLen) - dotSize, dotSize, 
					po.name + "(" + po.clutchTime + ", " + po.clutchScore + ")");
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
			g.drawString(String.valueOf(i * maxScore / yNum), dotPoint.x - 30, y + 5);
			g.drawLine(dotPoint.x - 4, y, dotPoint.x - 1, y);
		}
		
		int xNum = 10; // x坐标轴上显示的点
		for(int i = 0; i <= xNum; i++) {
			int x = dotPoint.x + (xLen / xNum) * i;
			g.drawString(String.valueOf(i * 1.0 / xNum), x - 10, dotPoint.y + 20);
			g.drawLine(x, dotPoint.y + 1, x, dotPoint.y + 4);
		}
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
		this.setBackground(Color.BLUE);
	}
	
}
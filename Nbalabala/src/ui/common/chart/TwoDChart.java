package ui.common.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
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
		for(int i = 0; i < 10; i++) {
			ClutchPO po = new ClutchPO("lsy", "2014-15");
			po.clutchScore = i + 5;
			po.clutchTime = i / 20.0;
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
	
	private int x;
	private int y;
	private int width;
	private int height;
	/** 图中点的大小，最好是偶数 */
	private int dotSize = 6;
	/** 圆点在panel上的的坐标 */
	private Point dotPoint;
	/** x轴长 */
	private int xLen;
	/** y轴长 */
	private int yLen;
	
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
		double maxScore = 0;
		for(ClutchPO po : cluthPOs) { // 找到最大的得分
			if(po.clutchScore > maxScore) {
				maxScore = po.clutchScore;
			}
		}
		maxScore += 5;
		for(ClutchPO po : cluthPOs) {
			Dot dot = new Dot(dotPoint.x + (int)(po.clutchTime * xLen), dotPoint.y - (int)((po.clutchScore / maxScore) * yLen), dotSize, po.name);
			this.add(dot);
		}
	}

	/**
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i < 10; i++) {
//			g.drawLine(dotPoint.x - 4, dotPoint.y, dotPoint.x, y2);
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
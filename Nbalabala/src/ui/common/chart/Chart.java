package ui.common.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 柱状图
 * @author cylong
 * @version 2015年4月12日 上午12:47:19
 */
public class Chart extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -7071018403808043886L;

	/** 标题字体 */
	private Font titleFont = new Font("黑体", Font.PLAIN, 15);
	
	/** 柱状图内部的panel */
	private InPanel inPanel;

	/** 柱状图的所有列 */
	private ArrayList<Column> columns;
	
	/** 内边距 */
	private int padding = 30;
	
	/** 用来比较的最大数据范围，计算每一个柱的高 */
	private double maxRange;
	
	// 决定还是 列间距 * 2 = 列宽度
	/** 每列之间的间距 */
	private int interval;
	/** 每个柱的宽，由列间距和柱状图大小决定 */
	private int columnWidth;

	/**
	 * @param title 标题
	 * @param columns 柱状图的全部柱子
	 * @param maxRange 数据的最大范围，计算每个柱的比例
	 * @author cylong
	 * @version 2015年4月12日  上午3:08:12
	 */
	public Chart(String title, ArrayList<Column> columns, double maxRange) {
		this.columns = columns;
		this.maxRange = maxRange;
		this.setBorder(BorderFactory.createTitledBorder(getBorder(), title, TitledBorder.CENTER, TitledBorder.TOP, titleFont));
		// this.setBackground(new Color(243, 243, 243, 70)); // 半透明背景 刷新会出问题
		this.setOpaque(false);
		this.setLayout(null);
		inPanel = new InPanel();
		this.add(inPanel);
	}

	public void setTitle(String title) {
		this.setBorder(BorderFactory.createTitledBorder(getBorder(), title, TitledBorder.CENTER, TitledBorder.TOP, titleFont));
	}
	
	/**
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		inPanel.setBounds(padding, height / 5, width - padding * 2, height * 4 / 5 - 5);
	}

	/**
	 * 柱状图内部的panel
	 * @author cylong
	 * @version 2015年4月12日 上午1:51:13
	 */
	private class InPanel extends JPanel {

		/** serialVersionUID */
		private static final long serialVersionUID = -5964393499545913607L;
		
		public InPanel() {
			this.setLayout(null);
			this.setOpaque(false);
			// 添加数据
			for(int i = 0; i < columns.size(); i++) {
				this.add(columns.get(i));
			}
		}
		
		@Override
		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);
			interval = width / (3 * columns.size() + 1);
			columnWidth = interval * 2; // 这里设置 ：列宽度 = 2 * 列间距
			for(int i = 0; i < columns.size(); i++) {
				Column col = columns.get(i);
				int colHeight = (int)((col.getValue() / maxRange) * (height * 3 / 4));
				col.setBounds(i * columnWidth + (i + 1) * interval, height - colHeight, columnWidth, colHeight);
			}
		}
		
		/**
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.GRAY);
			g.drawLine(0, getHeight() - Column.labelDimen.height, getWidth(), getHeight() - Column.labelDimen.height);
		}

	}
}

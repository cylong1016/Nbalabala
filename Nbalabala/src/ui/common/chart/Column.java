package ui.common.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 柱状图的每一列
 * @author cylong
 * @version 2015年4月12日 上午1:18:20
 */
public class Column extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5074200609980001743L;

	/** 此柱的值 */
	private double value;

	/** 显示名称 */
	private Label nameLabel;
	/** 显示数值 */
	private Label valLabel;
	/** 显示名称和数值的label的大小 */
	public static Dimension labelDimen = new Dimension(10, 15);
	/** 中间的面板 */
	private JPanel center;

	public Column(String name, double value, Color bgColor) {
		this.value = value;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		nameLabel = new Label(name);
		nameLabel.setPreferredSize(labelDimen);
		this.add(nameLabel, BorderLayout.SOUTH);

		DecimalFormat format = new DecimalFormat("0.00");
		valLabel = new Label(format.format(value));
		valLabel.setPreferredSize(labelDimen);
		this.add(valLabel, BorderLayout.NORTH);

		center = new JPanel();
		center.setBackground(bgColor);
		this.add(center);
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		int offset = (int)(valLabel.getPreferredSize().height + nameLabel.getPreferredSize().height);
		super.setBounds(x, y - offset, width, height + offset);
	}

}

class Label extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = 527156768211092168L;

	public Label(String str) {
		super(str, JLabel.CENTER);
	}
}

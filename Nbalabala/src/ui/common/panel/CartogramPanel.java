package ui.common.panel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 球员详细数据界面的球员数据和平均数据比较的统计表
 * @author cylong
 * @version 2015年4月9日 下午8:09:51
 */
public class CartogramPanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 6172308896580893147L;
	
	private Dimension size = new Dimension(150, 170);
	/** 每一条柱最大宽度 */
	private int maxHeight = 150;
	/** 每一条柱的宽度 */
	private int width = 50;
	
	private double playerData;
	private double avgData;
	private double max;
	
	/**
	 * @param playerData 球员数据
	 * @param avgData 联盟平均数据
	 * @param max 联盟最大数据
	 * @author cylong
	 * @version 2015年4月9日  下午8:43:02
	 */
	public CartogramPanel(double playerData, double avgData, double max) {
		this.setSize(size);
		this.playerData = playerData;
		this.avgData = avgData;
		this.max = max;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(10, 10, width, (int)((playerData / max) * maxHeight));
		g.fillRect(70, 10, width, (int)((avgData / max) * maxHeight));
	}

}

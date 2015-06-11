package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ui.UIConfig;
import utility.Constants;

/**
 * 球员数据与联盟平均的对比图
 * @author cylong
 * @version 2015年4月11日 上午12:47:18
 */
public class ContrastDiagram extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -3557856466615071597L;

	private String type;

	private Histogram[] histogram = new Histogram[5];

	/**
	 * @param fivePlayersData 长度为5数组，分别是该球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值
	 * @param fiveArgsAvg 长度为5数组，分别是所有球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值
	 * @param highestScoreReboundAssist 长度为3数组，分别是所有球员中场均得分篮板助攻的最高值
	 * @param type 表示"球员平均"还是"球队平均"
	 * @author cylong
	 * @version 2015年4月11日 上午12:54:02
	 */
	public ContrastDiagram(double[] fivePlayersData, double[] fiveArgsAvg, double[] highestScoreReboundAssist, String type) {
		double[] temp = {1, 1};
		this.type = type;
		for(int i = 0; i < histogram.length; i++) {
			if (i < 3) {
				histogram[i] = new Histogram(fivePlayersData[i], fiveArgsAvg[i], highestScoreReboundAssist[i], Constants.playerContrastColumns[i]);
			} else {
				histogram[i] = new Histogram(fivePlayersData[i], fiveArgsAvg[i], temp[i - 3], Constants.playerContrastColumns[i]);
			}
			this.add(histogram[i]);
		}
		// this.setBackground(new Color(243, 243, 243, 70)); // 半透明在刷新数据的时候会闪
		this.setOpaque(false);
	}
	
	public void setData(double[] fivePlayersData, double[] fiveArgsAvg, double[] highestScoreReboundAssist) {
		double[] temp = {1, 1};
		for(int i = 0; i < histogram.length; i++) {
			if (i < 3) {
				histogram[i].setData(fivePlayersData[i], fiveArgsAvg[i], highestScoreReboundAssist[i]);
			} else {
				histogram[i].setData(fivePlayersData[i], fiveArgsAvg[i], temp[i - 3]);
			}
			this.add(histogram[i]);
		}
	}

	/**
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int interval = 10;
		int size = 10;
		g.setColor(UIConfig.HIST_PLAYER_COLOR);
		g.fillRect(interval, interval, size, size);
		g.setColor(UIConfig.HIST_AVG_COLOR);
		g.fillRect(interval, interval * 2 + size, size, size);

		g.setColor(Color.BLACK);
		g.drawString(type, interval * 2 + size, interval + size);
		g.drawString(Constants.leagueAvg, interval * 2 + size, (interval + size) * 2);
	}

}

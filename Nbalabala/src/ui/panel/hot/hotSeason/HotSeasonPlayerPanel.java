package ui.panel.hot.hotSeason;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotSeasonPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotSeasonPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotSeasonPlayerProperty;

/**
 * 赛季热点球员界面
 * @author lsy
 * @version 2015年4月11日 下午4:02:59
 */
public class HotSeasonPlayerPanel extends HotThreeFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 9066290175387899594L;
	private static final HotSeasonPlayerProperty[] HOT_PLAYER_ARRAY = HotSeasonPlayerProperty.values();
	ArrayList<HotSeasonPlayerVO> playerVO;
	HotBLService hot = new HotQuery();
	HotSeasonPlayerLabel label[] = new HotSeasonPlayerLabel[5];
	Chart chart;

	public HotSeasonPlayerPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
		addChart();
	}

	public void refresh() {
		playerVO = hot.getHotSeasonPlayers(ThreeButton.current.player);
		updateLabel();
		updateChart();
	}

	private void addChart() {
		if (chart != null) {
			this.remove(chart);
		}
		if (playerVO.size() < 5)
			return;
		chart = new Chart(text, getColumns(), getMax());
		chart.setBounds(511, 121, 399, 306);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}

	/**
	 * 修改柱状图数据
	 * @author cylong
	 * @version 2015年4月13日 下午10:18:34
	 */
	public void updateChart() {
		playerVO = hot.getHotSeasonPlayers(ThreeButton.current.player);
		if (playerVO.size() < 5)
			return;
		if (chart != null)
			chart.setData(getColumns(), getMax());
	}

	/**
	 * @return 柱状图的所有列
	 * @author cylong
	 * @version 2015年4月13日 下午10:30:54
	 */
	private ArrayList<Column> getColumns() {
		ArrayList<Column> columns = new ArrayList<Column>();
		double property = playerVO.get(0).getProperty();
		for(int i = 0; i < 5; i++) {
			property = playerVO.get(i).getProperty();
			columns.add(new Column(Integer.toString(i + 1), property, UIConfig.HIST_COLORS[i]));
		}
		return columns;
	}

	/**
	 * @return 柱状图数据最大值
	 * @author cylong
	 * @version 2015年4月13日 下午10:31:07
	 */
	private double getMax() {
		double max = playerVO.get(0).getProperty();
		double property = max;
		for(int i = 0; i < 5; i++) {
			property = playerVO.get(i).getProperty();
			if (max < property) {
				max = property;
			}
		}
		return max;
	}

	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日 下午6:09:38
	 */
	public void add_bt_Listener() {
		for(int i = 0; i < select.length; i++) {
			hotButton[i].player = HOT_PLAYER_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					for(int i = 0; i < label.length; i++) {
						if (label[i] != null)
							HotSeasonPlayerPanel.this.remove(label[i]);
					}
					addLabel();
					addChart();
				}

			});
		}
	}

	/**
	 * 添加具体每个球员的label
	 * @author lsy
	 * @version 2015年4月11日 下午6:09:44
	 */
	public void addLabel() {
		playerVO = hot.getHotSeasonPlayers(ThreeButton.current.player);
		if (playerVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			label[j] = new HotSeasonPlayerLabel(playerVO.get(j), ThreeButton.current.player);
			this.add(label[j]);
		}
		this.repaint();
	}
	
	/**
	 * PlayerVO更新后，调用此方法刷新5个label，而不会产生重影
	 * @author Issac Ding
	 * @version 2015年4月14日  下午12:43:13
	 */
	public void updateLabel() {
		if (playerVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			if (label[j] != null)
				label[j].updateContent(playerVO.get(j), ThreeButton.current.player);
		}
		this.repaint();
	}
}

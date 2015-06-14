package ui.panel.hot.hotSeason;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotSeasonTeamLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotSeasonTeamVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotSeasonTeamProperty;

/**
 * 赛季热点球队界面
 * @author lsy
 * @version 2015年4月11日 下午4:03:34
 */
public class HotSeasonTeamPanel extends HotThreeFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 6863346413128750821L;
	HotSeasonTeamLabel teamLabel;
	private static final HotSeasonTeamProperty[] HOT_TEAM_ARRAY = HotSeasonTeamProperty.values();
	ArrayList<HotSeasonTeamVO> teamVO;
	HotBLService hot = new HotQuery();
	HotSeasonTeamLabel label[] = new HotSeasonTeamLabel[5];
	Chart chart;

	public HotSeasonTeamPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel(0);
		addChart();
		
		setCurrent(2);
	}

	public void refresh() {
		teamVO = hot.getHotSeasonTeams(ThreeButton.current.team);
		if (teamVO == null || teamVO.size() < 5)
			return;
		updateLabel();
		updateChart();
	}

	private void addChart() {
		if (chart != null) {
			this.remove(chart);
		}
		if (teamVO == null || teamVO.size() < 5)
			return;
		chart = new Chart(text, getColumns(), getMax());
		chart.setBounds(511, 121, 399, 306);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}

	/**
	 * 更新柱状图数据
	 * @author cylong
	 * @version 2015年4月13日 下午10:33:59
	 */
	public void updateChart() {
		teamVO = hot.getHotSeasonTeams(ThreeButton.current.team);
		if (teamVO == null || teamVO.size() < 5)
			return;
		if (chart != null)
			chart.setData(getColumns(), getMax());
	}

	/**
	 * @return 柱状图所有列
	 * @author cylong
	 * @version 2015年4月13日 下午10:31:54
	 */
	private ArrayList<Column> getColumns() {
		ArrayList<Column> columns = new ArrayList<Column>();
		double property = teamVO.get(0).getProperty();
		for(int i = 0; i < 5; i++) {
			property = teamVO.get(i).getProperty();
			columns.add(new Column(teamVO.get(i).getAbbr(), property, UIConfig.HIST_COLORS[i]));
		}
		return columns;
	}

	/**
	 * @return 柱状图最大值
	 * @author cylong
	 * @version 2015年4月13日 下午10:31:44
	 */
	private double getMax() {
		double max = teamVO.get(0).getProperty();
		double property = max;
		for(int i = 0; i < 5; i++) {
			property = teamVO.get(i).getProperty();
			if (max < property) {
				max = property;
			}
		}
		return max;
	}

	public void add_bt_Listener() {
		for(int i = 0; i < select.length; i++) {
			hotButton[i].team = HOT_TEAM_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					for(int i = 0; i < label.length; i++) {
						if (label[i] != null)
							HotSeasonTeamPanel.this.remove(label[i]);
					}
					addLabel(ThreeButton.current.index);
					addChart();
				}
			});
		}
	}

	public void addLabel(int index) {
		teamVO = hot.getHotSeasonTeams(ThreeButton.current.team);
		if (teamVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			label[j] = new HotSeasonTeamLabel(teamVO.get(j), ThreeButton.current.team,index);
			this.add(label[j]);
		}
		this.repaint();
	}
	
	/**
	 * 在TeamVO更新以后，调用此方法刷新5个label，不会产生重影
	 * @author Issac Ding
	 * @version 2015年4月14日  下午12:45:59
	 */
	public void updateLabel() {
		if (teamVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			if (label[j] != null)
				label[j].updateContent(teamVO.get(j), ThreeButton.current.team);
		}
		this.repaint();
	}

}

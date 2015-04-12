package ui.panel.hot.hotSeason;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotSeasonTeamLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import utility.Constants;
import vo.HotSeasonTeamVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotSeasonTeamProperty;

/**
 * 赛季热点球队界面
 * @author lsy
 * @version 2015年4月11日  下午4:03:34
 */
public class HotSeasonTeamPanel extends HotThreeFatherPanel{

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
		addLabel();
		addChart();
	}
	
	private void addChart() {
		if(chart!=null){
			this.remove(chart);
		}
		ArrayList<Column> columns = new ArrayList<Column>();
		columns.add(new Column(Constants.translateTeamAbbr(teamVO.get(0).getAbbr()), teamVO.get(0).getProperty(), Color.blue));
		columns.add(new Column(Constants.translateTeamAbbr(teamVO.get(1).getAbbr()),teamVO.get(1).getProperty(), Color.blue));
		columns.add(new Column(Constants.translateTeamAbbr(teamVO.get(2).getAbbr()), teamVO.get(2).getProperty(), Color.blue));
		columns.add(new Column(Constants.translateTeamAbbr(teamVO.get(3).getAbbr()), teamVO.get(3).getProperty(), Color.blue));
		columns.add(new Column(Constants.translateTeamAbbr(teamVO.get(4).getAbbr()), teamVO.get(4).getProperty(), Color.blue));
		chart = new Chart(text, columns, 21.1);
		chart.setBounds(511, 121, 399, 306);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}
	
	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].team = HOT_TEAM_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						HotSeasonTeamPanel.this.remove(label[i]);
					}
					addLabel();
					addChart();
				}

			});
		}
	}

	public void addLabel() {
		teamVO = hot.getHotSeasonTeams(ThreeButton.current.team);
		for (int j = 0; j < 5; j++) {
			label[j] = new HotSeasonTeamLabel(teamVO.get(j), ThreeButton.current.team);
			this.add(label[j]);
		}
		this.repaint();
	}

}

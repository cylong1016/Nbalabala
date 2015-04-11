package ui.panel.hot.hotSeason;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	
	public HotSeasonTeamPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
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

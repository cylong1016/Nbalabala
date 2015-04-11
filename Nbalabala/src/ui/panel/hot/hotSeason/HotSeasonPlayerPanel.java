package ui.panel.hot.hotSeason;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.label.HotSeasonPlayerLabel;
import vo.HotSeasonPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotSeasonPlayerProperty;

/**
 * 赛季热点球员界面
 * @author lsy
 * @version 2015年4月11日 下午4:02:59
 */
public class HotSeasonPlayerPanel extends HotSeasonFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 9066290175387899594L;
	ArrayList<HotSeasonPlayerVO> playerVO;
	private static final HotSeasonPlayerProperty[] HOT_PLAYER_ARRAY = HotSeasonPlayerProperty.values();
	HotBLService hot = new HotQuery();
	HotSeasonPlayerLabel label[] = new HotSeasonPlayerLabel[5];

	public HotSeasonPlayerPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
	}

	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].player = HOT_PLAYER_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						HotSeasonPlayerPanel.this.remove(label[i]);
					}
					addLabel();
				}

			});
		}
	}

	public void addLabel() {
		playerVO = hot.getHotSeasonPlayers(HotSeasonButton.current.player);
		for (int j = 0; j < 5; j++) {
			label[j] = new HotSeasonPlayerLabel(playerVO.get(j), HotSeasonButton.current.player);
			this.add(label[j]);
		}
		this.repaint();
	}
}

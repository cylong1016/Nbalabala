package ui.panel.allplayers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import vo.PlayerProfileVO;

/**
 * 球员赛季数据
 * @author lsy
 * @version 2015年3月24日  下午6:53:52
 */
public class PlayerSeasonPanel extends PlayerInfoPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -1936824766623215286L;

	public PlayerSeasonPanel(MainController controller, String url, PlayerProfileVO vo, AllPlayersPanel allPlayers) {
		super(controller, url, vo, allPlayers);
	}
	
	public void addButton() {
		total = new GameDetailButton(totalX, y, totalWidth, height, "总数据");
		game = new GameDetailButton(gameX, y, gameWidth, height, "比赛数据");
		game.setOpaque(true);
		game.setBackground(UIConfig.BUTTON_COLOR);
		game.setForeground(Color.white);
		this.add(total);
		this.add(game);
		total.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.toPlayerInfoPanel(PlayerSeasonPanel.this, vo, allPlayers);
			}
		});
		game.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			}
		});
	}

}

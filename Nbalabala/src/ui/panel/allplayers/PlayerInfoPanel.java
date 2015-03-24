package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.label.ImgLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import ui.panel.gamedata.GamePanel;
import vo.PlayerDetailVO;
import vo.PlayerProfileVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;

/**
 * 具体球员信息界面
 * @author lsy
 * @version 2015年3月24日  上午10:26:48
 */
public class PlayerInfoPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 2506795997614982399L;
	int totalX=676,gameX=765,y=190,totalWidth=63,gameWidth=83,height=25;
	TextButton total,game;
	PlayerProfileVO vo;
	MainController controller;
	Image headPicture,totalPicture;
	PlayerQueryBLService playerQuery;
	PlayerDetailVO detailVO;
	ImgButton back;
	String url = UIConfig.IMG_PATH+"players/";
	AllPlayersPanel allPlayers;
	
	public PlayerInfoPanel(MainController controller, String url,PlayerProfileVO vo,AllPlayersPanel allPlayers) {
		super(controller, url);
		this.vo = vo;
		this.controller = controller;
		this.allPlayers = allPlayers;
		playerQuery = new PlayerQuery();
		detailVO = playerQuery.getPlayerDetailByName(vo.getName());
		addButton();
		setTableTotal();
		addHead();
		addPicture();
		addBack();
	}
	
	public void addBack() {
		back = new ImgButton(url + "back.png", 50, 50, url + "back.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.backToGameDataPanel(PlayerInfoPanel.this, allPlayers);
			}

		});
	}
	
	/**
	 * 添加头像
	 * @author lsy
	 * @version 2015年3月24日  上午11:17:35
	 */
	public void addHead(){
		headPicture = vo.getPortrait();
		ImgLabel label = new ImgLabel(136,10,200,160,headPicture);
		this.add(label);
	}
	
	/**
	 * 添加全身像
	 * @author lsy
	 * @version 2015年3月24日  上午11:17:42
	 */
	public void addPicture(){
		totalPicture = detailVO.getAction();
		ImgLabel label = new ImgLabel(885,6,151,240,totalPicture);
		this.add(label);
	}

	public void addButton() {
		total = new GameDetailButton(totalX, y, totalWidth, height, "总数据");
		total.setOpaque(true);
		total.setBackground(UIConfig.BUTTON_COLOR);
		total.setForeground(Color.white);
		game = new GameDetailButton(gameX, y, gameWidth, height, "比赛数据");
		this.add(total);
		this.add(game);
		total.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.back();
				scroll.removeAll();
				setTableTotal();
			}
		});
		game.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				total.back();
				scroll.removeAll();
				setTableGame();
			}
		});
	}
		

		String[] columns;
		Object[][] rowData;
		BottomScrollPane scroll;
		ImageIcon icon;
		ArrayList<Image> imgArr = new ArrayList<Image>();
		BottomTable table;
		
		
		//TODO  setTabel 内容为球员总数据
		public void setTableTotal() {
//			table = new BottomTable(rowData, columns);
//		
//			table.setRowHeight(100);
//			scroll = new BottomScrollPane(table);
//			scroll.setLocation(57, 239);
//			this.add(scroll);
		}
	
		//TODO  setTabel 内容为球员每场比赛数据
		public void setTableGame() {
//				table = new BottomTable(rowData, columns);
//			
//				table.setRowHeight(100);
//				scroll = new BottomScrollPane(table);
//				scroll.setLocation(57, 239);
//				this.add(scroll);
			}
		
	
}

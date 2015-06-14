package ui.common.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.controller.MainController;
import utility.Constants;

/**
 * 左边边框
 * 
 * @author lsy
 * @version 2015年3月17日 下午6:19:16
 */
public class LeftPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private int secPanelX = UIConfig.RIGHT_WIDTH * (-1);
	private ThreadIn threadIn;
	private ThreadOut threadOut;
	/** 边框移动速度 */
	private int speed = 5;
	/** 背景图片 */
	private Image bgImage;
	
	/** button */
	private TabButton allPlayersBtn, allTeamsBtn, playerDataBtn, teamDataBtn;
	private TabButton gameBtn, hotBtn, analysisBtn, returnBtn;
	
	private static final int FIRST_ZONE_Y = 100;
	private static final int SECOND_ZONE_Y = 296;
	private static final int THIRD_ZONE_Y = 408;
	private static final int RET_Y = 524;
	
	private TabButton currentBtn = new TabButton();
		
	public LeftPanel() {
		bgImage = Images.SIDEBAR_BG;
		// this.setBounds(0, 0, UIConfig.RIGHT_WIDTH, bgImage.getHeight(null));
		this.setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
		setButton();

	}

	/**
	 * 设置按钮
	 * 
	 * @author lsy
	 * @version 2015年3月20日 上午12:02:28
	 */
	public void setButton() {
		
		Image btnOnImg = Images.SIDEBAR_BTN_ON;
		Image btnClickImg = Images.SIDEBAR_BTN_CLICK;
		int inter = 42;
		String smallBlank = "        ";
		String bigBlank = "              ";
		String arrow = "  >";
		if (Constants.isEng) {
			smallBlank = "";
			bigBlank = "";
		}
		
		allPlayersBtn = new TabButton(smallBlank + Constants.allPlayers + arrow, btnOnImg, btnClickImg);
		allPlayersBtn.setLocation(0, FIRST_ZONE_Y);
		allPlayersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(allPlayersBtn);
				MainController.toAllPlayersPanel();
			}
		});
		this.add(allPlayersBtn);
		
		allTeamsBtn = new TabButton(smallBlank + Constants.allTeams + arrow, btnOnImg, btnClickImg);
		allTeamsBtn.setLocation(0, FIRST_ZONE_Y + inter*1);
		allTeamsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(allTeamsBtn);
				MainController.toAllTeamsPanel();
			}
		});
		this.add(allTeamsBtn);
		
		playerDataBtn = new TabButton(smallBlank + Constants.playersData + arrow, btnOnImg, btnClickImg);
		playerDataBtn.setLocation(0, FIRST_ZONE_Y + inter * 2);
		playerDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(playerDataBtn);
				MainController.toPlayerPanel();
			}
		});
		this.add(playerDataBtn);
		
		teamDataBtn = new TabButton(smallBlank + Constants.teamsData + arrow, btnOnImg, btnClickImg);
		teamDataBtn.setLocation(0, FIRST_ZONE_Y + inter * 3);
		teamDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(teamDataBtn);
				MainController.toTeamPanel();
			}
		});
		this.add(teamDataBtn);
		
		
		gameBtn = new TabButton(bigBlank + Constants.game + arrow, btnOnImg, btnClickImg);
		gameBtn.setLocation(0, SECOND_ZONE_Y);
		gameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(gameBtn);
				MainController.toGamePanel();
			}
		});
		this.add(gameBtn);
		
		hotBtn = new TabButton(bigBlank + Constants.hotShort + arrow, btnOnImg, btnClickImg);
		hotBtn.setLocation(0, SECOND_ZONE_Y + inter * 1);
		hotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(hotBtn);
				MainController.toHotPanel();
			}
		});
		this.add(hotBtn);
		
		analysisBtn = new TabButton(smallBlank + Constants.analysis + arrow, btnOnImg, btnClickImg);
		analysisBtn.setLocation(0, THIRD_ZONE_Y);
		analysisBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentOn(analysisBtn);
				// TODO 到分析界面 
			}
		});
		this.add(analysisBtn);
		
		returnBtn = new TabButton("<  " + Constants.ret + smallBlank, Images.SIDEBAR_RET_ON, Images.SIDEBAR_RET_CLICK);
		returnBtn.setLocation(0, RET_Y);
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				MainController.toMainPanel();
			}
		});
		this.add(returnBtn);
		
		setInit();
		
	}
	
	public void setCurrentBtn(TabButton btn){
		this.currentBtn = btn;
		currentBtn.setForeground(Color.white);
		currentBtn.setOn();
	}
	
	protected void setCurrentOn(TabButton btn) {
		currentBtn.setOff();
		currentBtn.setForeground(MyFont.DARK_GRAY);
		btn.setOn();
		btn.setForeground(Color.white);
		currentBtn = btn;
	}
	
	protected void setInit(){
		TabButton button[] = {allPlayersBtn, allTeamsBtn, playerDataBtn, teamDataBtn, 
				gameBtn, hotBtn, analysisBtn, returnBtn};
		for (int i = 0; i < button.length; i++) {
			button[i].setFont(MyFont.YH_B);
			button[i].setForeground(MyFont.BLACK_GRAY);
		}
		returnBtn.setForeground(Color.white);
	}


	public void paint(Graphics g) {
		g.drawImage(bgImage, 0, 0, this);
		super.paint(g);
	}

	public void moveIn() {
		threadIn = new ThreadIn();
		threadIn.start();
	}

	public void moveOut() {
		threadOut = new ThreadOut();
		threadOut.start();
	}

	class ThreadIn extends Thread {

		public void run() {
			while (true) {
				if (secPanelX >= 0) {
					secPanelX = 0;
					LeftPanel.this.setLocation(secPanelX, 0);
					LeftPanel.this.repaint();
					break;
				}
				try {
					secPanelX = secPanelX + speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					Thread.sleep(5);
					LeftPanel.this.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ThreadOut extends Thread {

		public void run() {
			while (true) {
				if (secPanelX <= UIConfig.RIGHT_WIDTH * (-1)) {
					secPanelX = UIConfig.RIGHT_WIDTH * (-1);
					LeftPanel.this.setLocation(secPanelX, 0);
					LeftPanel.this.repaint();
					break;
				}
				try {
					secPanelX = secPanelX - speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					LeftPanel.this.repaint();
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public TabButton getAllPlayersBtn() {
		return allPlayersBtn;
	}

	public TabButton getAllTeamsBtn() {
		return allTeamsBtn;
	}

	public TabButton getPlayerDataBtn() {
		return playerDataBtn;
	}

	public TabButton getTeamDataBtn() {
		return teamDataBtn;
	}

	public TabButton getGameBtn() {
		return gameBtn;
	}

	public TabButton getHotBtn() {
		return hotBtn;
	}

	public TabButton getAnalysisBtn() {
		return analysisBtn;
	}
	
	

}

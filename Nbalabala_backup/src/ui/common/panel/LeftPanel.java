package ui.common.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.controller.MainController;

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
	
	/** 横纵坐标 间距 宽高 */
	private int x = 6, y = 314, inter = 41;
	/** 图片地址 */
	private static final String URL = UIConfig.IMG_PATH + "sidebar/";
	private static final String trans = URL + "trans.png";
	private static final String on = URL + "mouseOn.png";
	/** button */
	private ImgButton allPlayers, allTeams, game, playerData, returnButton, teamData, hot;
	/** button数组 */
	private ImgButton[] buttonArr;
	private MouListener mou = new MouListener();
	
	public LeftPanel() {
		bgImage = new ImageIcon(URL + "BG.png").getImage();
		// this.setBounds(0, 0, UIConfig.RIGHT_WIDTH, bgImage.getHeight(null));
		this.setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
		setButton();
		addButton();
	}

	/**
	 * 设置按钮
	 * 
	 * @author lsy
	 * @version 2015年3月20日 上午12:02:28
	 */
	public void setButton() {
		returnButton = new ImgButton(URL + "return.png", 20, 557, URL + "returnOn.png", URL
				+ "returnClick.png");

		allPlayers = new ImgButton(trans, x, y, on, on);
		allTeams = new ImgButton(trans, x, y + inter, on, on);
		game = new ImgButton(trans, x, y + 2 * inter, on, on);
		playerData = new ImgButton(trans, x, y + 3 * inter, on, on);
		teamData = new ImgButton(trans, x, y + 4 * inter, on, on);
		hot = new ImgButton(trans, x, y + 5 * inter, on, on);
	}
	
	public void addButton(){
		buttonArr = new ImgButton[]{allPlayers,allTeams,game,playerData,teamData,hot,returnButton};
		for(int i =0;i<buttonArr.length;i++){
			this.add(buttonArr[i]);
			buttonArr[i].addMouseListener(mou);
		}
	}
	
	class MouListener extends MouseAdapter{
		 public void mousePressed(MouseEvent e) {
			 if(e.getSource() == allPlayers){
				 MainController.toAllPlayersPanel();
			 }else if(e.getSource() == allTeams){
				 MainController.toAllTeamsPanel();
			 }else if(e.getSource() == game){
				 MainController.toGamePanel();
			 }else if(e.getSource() == playerData){
				 MainController.toPlayerPanel();
			 }else if(e.getSource() == teamData){
				 MainController.toTeamPanel();
			 }else if (e.getSource() == hot) {
				MainController.toHotPanel();
			}else if(e.getSource() == returnButton){
				 MainController.toMainPanel();
			 }
		 }
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

}

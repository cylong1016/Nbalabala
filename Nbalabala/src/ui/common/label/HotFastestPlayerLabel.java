package ui.common.label;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.allplayers.ActionPhotoPanel;
import utility.Constants;
import vo.HotFastestPlayerVO;
import data.playerdata.PlayerImageCache;

/**
 * 进步最快球员的5块板子，使用时只需要传进vo并且add到panel上即可，不需要setBounds
 * @author Issac Ding
 * @version 2015年4月9日  下午11:27:56
 */
public class HotFastestPlayerLabel extends JLabel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5477070050894403050L;
	private String playerName;
	
	public HotFastestPlayerLabel(HotFastestPlayerVO vo) {
		setOpaque(false);
		setLayout(null);
		int top = vo.getTop();
		switch (top) {
		case 1:
			setBounds(95, 331, 402, 253);
			break;
		case 2:
			setBounds(512, 331, 189, 119);
			break;
		case 3:
			setBounds(715, 331, 189, 119);
			break;
		case 4:
			setBounds(512, 465, 189, 119);
			break;
		default:
			setBounds(715, 465, 189, 119);
			break;
		}
		setContent(vo);
	}
	
	public void updateContent(HotFastestPlayerVO vo) {
		playerName = vo.getName();
		if (actionPhotoPanel != null) {
			actionPhotoPanel.setImage(PlayerImageCache.getActionImageByName(playerName));
		}
		if (portraitLabel != null) {
			portraitLabel.setImage(PlayerImageCache.getPortraitByName(vo.getName()));
		}
		nameLabel.setText(playerName);
		String propertyStr = UIConfig.FORMAT.format(vo.getPromotion());
		propertyLabel.setText("五场提升："+propertyStr);
		teamLabel.setText("球队：" + Constants.translateTeamAbbr(vo.getTeamAbbr()));
		positionLabel.setText("位置：" + vo.getPosition());
	}
	
	ActionPhotoPanel actionPhotoPanel;
	ImgLabel portraitLabel;
	MyLabel nameLabel;
	MyLabel propertyLabel;
	MyLabel teamLabel;
	MyLabel positionLabel;
	
	private void setContent(HotFastestPlayerVO vo) {
		playerName = vo.getName();
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(playerName);
			actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setBounds(262,0,157,295);
			this.add(actionPhotoPanel);
			
			int labelX = 80;
			
			nameLabel = new MyLabel(labelX, 30, 300, 33, playerName);
			nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
			nameLabel.setLeft();
			this.add(nameLabel);
			
			String propertyStr = UIConfig.FORMAT.format(vo.getPromotion());
			propertyLabel = new MyLabel(labelX, 70, 200, 26, "五场提升："+propertyStr);
			propertyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			propertyLabel.setForeground(UIConfig.HIST_FIRST_COLOR);
			propertyLabel.setLeft();
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(labelX, 120, 146, 26, "球队：" + team);
			teamLabel.setLeft();
			this.add(teamLabel);
			
			positionLabel = new MyLabel(labelX, 150, 146, 26, "位置：" + vo.getPosition());
			positionLabel.setLeft();
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			portraitLabel = new ImgLabel(0, 36, 92, 75, portrait);
			this.add(portraitLabel);
			
			int labelWid = 175;
			
			nameLabel = new MyLabel(0,7,labelWid,20,vo.getName());
			
			String propertyStr = UIConfig.FORMAT.format(vo.getPromotion());
			propertyLabel = new MyLabel(0, 37, labelWid, 20, "五场提升："+propertyStr);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(0, 67, labelWid, 20, "球队：" + team);
			
			positionLabel = new MyLabel(0, 97, labelWid, 20, "位置：" + vo.getPosition());
			
			MyLabel labels[] = {nameLabel, propertyLabel, teamLabel, positionLabel};
			for (int i = 0; i < labels.length; i++) {
				labels[i].setRight();
			}
			this.add(teamLabel);
			this.add(propertyLabel);
			this.add(nameLabel);
			this.add(positionLabel);
		}
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				MainController.toPlayerInfoPanel((BottomPanel)HotFastestPlayerLabel.this.getParent(), 
						playerName, (BottomPanel)HotFastestPlayerLabel.this.getParent());
			}
		});
	}
}

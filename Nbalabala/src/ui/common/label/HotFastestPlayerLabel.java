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
	
	private void setContent(HotFastestPlayerVO vo) {
		playerName = vo.getName();
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(playerName);
			ActionPhotoPanel actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setBounds(262,0,157,295);
			this.add(actionPhotoPanel);
			
			int labelX = 80;
			
			MyLabel nameLabel = new MyLabel(labelX, 30, 300, 33, playerName);
			nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
			nameLabel.setLeft();
			this.add(nameLabel);
			
			String propertyStr = UIConfig.FORMAT.format(vo.getPromotion());
			MyLabel propertyLabel = new MyLabel(labelX, 70, 200, 26, "五场提升："+propertyStr);
			propertyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			propertyLabel.setForeground(UIConfig.HIST_FIRST_COLOR);
			propertyLabel.setLeft();
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(labelX, 120, 146, 26, "球队：" + team);
			teamLabel.setLeft();
			this.add(teamLabel);
			
			MyLabel positionLabel = new MyLabel(labelX, 150, 146, 26, "位置：" + vo.getPosition());
			positionLabel.setLeft();
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			ImgLabel portaitLabel = new ImgLabel(0, 36, 92, 75, portrait);
			this.add(portaitLabel);
			
			int labelWid = 175;
			
			MyLabel nameLabel = new MyLabel(0,7,labelWid,20,vo.getName());
			
			String propertyStr = UIConfig.FORMAT.format(vo.getPromotion());
			MyLabel propertyLabel = new MyLabel(0, 37, labelWid, 20, "五场提升："+propertyStr);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(0, 67, labelWid, 20, "球队：" + team);
			
			MyLabel positionLabel = new MyLabel(0, 97, labelWid, 20, "位置：" + vo.getPosition());
			
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

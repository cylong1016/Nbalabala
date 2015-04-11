package ui.common.label;

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
			
			MyLabel nameLabel = new MyLabel(108, 59, 146, 26, playerName);
			this.add(nameLabel);
			
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(108, 110, 146, 26, "五场提升："+propertyStr);
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(108, 161, 146, 26, "球队：" + team);
			this.add(teamLabel);
			
			MyLabel positionLabel = new MyLabel(108, 212, 146, 26, "位置：" + vo.getPosition());
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			ImgLabel portaitLabel = new ImgLabel(0, 36, 92, 75, portrait);
			this.add(portaitLabel);
			
			MyLabel nameLabel = new MyLabel(30,7,132,20,vo.getName());
			this.add(nameLabel);
			
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(70, 37, 120, 20, "五场提升："+propertyStr);
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(80, 67, 87, 20, "球队：" + team);
			this.add(teamLabel);
			
			MyLabel positionLabel = new MyLabel(95, 97, 100, 20, "位置：" + vo.getPosition());
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

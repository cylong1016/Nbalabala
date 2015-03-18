package ui.panel.teamdata;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ui.UIConfig;
import ui.common.panel.BottomPanel;

/**
 * 球队数据界面
 * @author cylong
 * @version 2015年3月18日 上午11:40:37
 */
public class TeamDataPanel extends BottomPanel {

	/**
	 * @param url
	 * @author cylong
	 * @version 2015年3月18日 上午11:51:32
	 */
	public TeamDataPanel(String url) {
		super(url);
	}

	/** serialVersionUID */
	private static final long serialVersionUID = -4296014620804951285L;

	/** 球队数据界面的图片url */
	private static String imgUrl = UIConfig.imgPath + "teamData/";

	/** 背景图片 */
	private static Image bgImg = new ImageIcon(imgUrl + "teamDataBG.png").getImage();

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}

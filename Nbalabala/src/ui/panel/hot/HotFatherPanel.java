package ui.panel.hot;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;

/**
 * 热点的父类方法，包括上面的选项卡
 * 
 * @author lsy
 * @version 2015年4月11日 下午3:45:23
 */
public class HotFatherPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -6969705072142965289L;
	protected ImgButton[] button = new ImgButton[4];
	protected int fa_width = 123, fa_height = 31, fa_x = 68, fa_y = 2, fa_inter = 124;
	protected String allURL = "images/Hot/";
	protected String[] url = new String[] { allURL + "todayOn.png", allURL + "seasonPlayerOn.png",
			allURL + "seasonTeamOn.png", allURL + "fastestOn.png" };

	public HotFatherPanel(String url) {
		super(url);
		addSelectButton();
	}

	public void addSelectButton() {
		for (int i = 0; i < 4; i++) {
			button[i] = new ImgButton(allURL + "blank.png", fa_x + i * fa_inter, fa_y, url[i], allURL
					+ "blank.png");
			this.add(button[i]);
		}
		for (int i = 0; i < 4; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getSource() == button[0]){
						MainController.toHotPanel(HotFatherPanel.this);
					}else if(e.getSource() == button[1]){
						MainController.toHotSeasonPlayerPanel(HotFatherPanel.this);
					}else if(e.getSource() == button[2]){
						MainController.toHotSeasonTeamPanel(HotFatherPanel.this);
					}else if(e.getSource() == button[3]){
						MainController.toHotFastPanel(HotFatherPanel.this);
					}
				}
			});
			
		}
	}
}

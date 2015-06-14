package ui.panel.hot;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Images;
import ui.MyFont;
import ui.common.button.TabButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;

/**
 * 热点的父类方法，包括上面的选项卡
 * 
 * @author lsy
 * @version 2015年4月11日 下午3:45:23
 */
public class HotFatherPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -6969705072142965289L;
	
//	private TabButton todayBtn;
//	private TabButton seasonPlayerBtn;
//	private TabButton seasonTeamBtn;
//	private TabButton fastestBtn;
	
	protected TabButton[] buttons = new TabButton[4];
	
	private static final int BTN_Y = 2;
	private static final int BTN_FIRST_X = 56;
	private static final int BTN_INTER = 116;
	
	private static final Image ON = Images.HOT_BTN_ON;
	private static final Image CLICK = Images.HOT_BTN_CLICK;


	public HotFatherPanel(String url) {
		super(url);
		addSelectButton();
		setCurrent(0);
	}

	public void addSelectButton() {		
		for (int i = 0; i < 4; i++) {
			buttons[i] = new TabButton(Constants.HOT_BTN[i], ON, CLICK);
			buttons[i].setLocation(BTN_FIRST_X + i * BTN_INTER, BTN_Y);
			buttons[i].setFont(MyFont.YH_S);
			this.add(buttons[i]);
		}
		
		
		for (int i = 0; i < 4; i++) {
			buttons[i].addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e){
					if(e.getSource() == buttons[0]){
						MainController.toHotPanel();
					}else if(e.getSource() == buttons[1]){
						MainController.toHotSeasonPlayerPanel();
					}else if(e.getSource() == buttons[2]){
						MainController.toHotSeasonTeamPanel();
					}else if(e.getSource() == buttons[3]){
						MainController.toHotFastPanel();
					}					
				}

			});	
		

		}
	}
	
	protected void setCurrent(int index){
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setOff();
			buttons[i].setForeground(Color.WHITE);
		}
		buttons[index].setOn();
		buttons[index].setForeground(MyFont.DARK_GRAY);
		
	}
}

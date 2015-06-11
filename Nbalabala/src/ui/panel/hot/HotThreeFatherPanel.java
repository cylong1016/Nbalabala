package ui.panel.hot;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import utility.Constants;

/**
 * 赛季热点球队和赛季热点球员和进步最快球员的父类panel,包括筛选按钮
 * 
 * @author lsy
 * @version 2015年4月11日 下午4:52:27
 */
public class HotThreeFatherPanel extends HotFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 9165825538486207274L;
	protected String[] select = Constants.hotType;
	protected ThreeButton[] hotButton = new ThreeButton[8];
	int bt_x = 90, bt_y = 52, bt_width = 80, bt_height = 25, inter = 90;
	protected String text = Constants.scoreAvgText;

	public HotThreeFatherPanel(String url) {
		super(url);
		addButton();
		ThreeButton.current = hotButton[0];
		addListener();
		setEffect();
	}

	public void addButton() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i] = new ThreeButton(bt_x + i * inter, bt_y, bt_width, bt_height, select[i]);
			hotButton[i].index = i;
			this.add(hotButton[i]);
			hotButton[i].text = select[i];
		}
	}

	public void addListener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == ThreeButton.current) {
						return;
					}
					ThreeButton.current.back();
					ThreeButton.current = (ThreeButton) e.getSource();
					ThreeButton tempButton = (ThreeButton) e.getSource();
					text = tempButton.text;
				}

			});
		}
	}

	/**
	 * 设置第一个筛选条件的选中效果
	 * 
	 * @author lsy
	 * @version 2015年4月11日 下午5:09:35
	 */
	public void setEffect() {
		hotButton[0].setOpaque(true);
		hotButton[0].setBackground(UIConfig.BUTTON_COLOR);
		hotButton[0].setForeground(Color.white);
	}
}

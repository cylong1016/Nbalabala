package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.TextButton;

/**
 * 数据统计按钮
 * @author lsy
 * @version 2015年3月21日  下午4:50:23
 */
public class GameDataButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = 2368672076114330760L;
	GameDataButton current;
	public GameDataButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.setFont(new Font("微软雅黑",0,17));
		this.setOpaque(true);
		this.setBackground(UIConfig.BUTTON_COLOR);
		this.setForeground(Color.white);
	}

	public void addListener(){
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				GameDataButton.this.setForeground(new Color(50,60,90));
			}
		});
		
	}
	
}

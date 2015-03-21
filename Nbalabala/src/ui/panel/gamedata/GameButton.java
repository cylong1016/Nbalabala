package ui.panel.gamedata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.TextButton;

/**
 * 数据统计按钮
 * @author lsy
 * @version 2015年3月21日  下午4:50:23
 */
public class GameButton extends TextButton{

	GameButton current;
	public GameButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

	public void addListener(){
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				GameButton.this.setForeground(new Color(50,60,90));
			}
		});
		
	}
	
}

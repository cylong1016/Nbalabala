package ui.common.label;

import java.awt.Color;

import javax.swing.JLabel;

import ui.MyFont;
import ui.UIConfig;

public class NavLabel extends JLabel{

	private static final long serialVersionUID = 1L;
	
	public NavLabel() {
	}
	
	public NavLabel(String text) {
		super(text);
		this.setBackground(UIConfig.NAV_BAR_BLUE_COLOR);
		this.setForeground(Color.WHITE);
		this.setSize(UIConfig.TABLE_WID, 40);
		this.setFont(MyFont.HiraginoB);
		
		this.setOpaque(true);
	}

}

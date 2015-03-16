package ui.common.panel;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * 所有panel的父类
 * @author cylong
 * @version 2015年3月16日 下午6:59:25
 */
public class Panel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7959972677335276198L;

	public Panel() {
		this.setLayout(null);
		// this.setOpaque(false);
		this.setBackground(Color.LIGHT_GRAY);
	}

}

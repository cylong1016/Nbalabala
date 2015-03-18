package ui.common.panel;

import javax.swing.JPanel;

/**
 * 不带左边边框的底层panel
 * @author lsy
 * @version 2015年3月17日 下午6:11:09
 */
public class Panel extends JPanel {

	private static final long serialVersionUID = -4321340946565227856L;

	public Panel() {
		this.setOpaque(false);
		this.setLayout(null);
	}

}

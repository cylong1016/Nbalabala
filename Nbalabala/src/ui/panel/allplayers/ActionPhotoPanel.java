package ui.panel.allplayers;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 显示球员半透明半身像的面板
 * @author Issac Ding
 * @version 2015年3月27日 下午8:18:46
 */
public class ActionPhotoPanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5072767650175982844L;
	private static final int WIDTH = 151;
	private static final int HEIGHT = 240;
	private Image action;

	public ActionPhotoPanel(Image action) {
		this.action = action;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .85f);
		g2d.setComposite(newComposite);
		g2d.drawImage(action, 0, 0, WIDTH, HEIGHT, null);
	}

}

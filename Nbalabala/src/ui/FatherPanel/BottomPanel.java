package ui.FatherPanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 不带左边边框的底层panel
 * @author lsy
 * @version 2015年3月17日  下午6:11:09
 */
public class BottomPanel extends JPanel{
	private static final long serialVersionUID = -4321340946565227856L;
	protected int width;
	protected int height;
	protected Image bgImage;
	protected String url;
	protected JFrame frame;

	public BottomPanel(String url) {
		this.url = url;
		bgImage = new ImageIcon(url).getImage();
		this.width = bgImage.getWidth(null);
		this.height = bgImage.getHeight(null);
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(width, height);
		this.setOpaque(false);
		this.repaint();
	}


	public void paint(Graphics g) {
		g.drawImage(bgImage, 0, 0, this);
		super.paint(g);
	}

}

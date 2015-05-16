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
	private  int width= 151;
	private  int height = 240;
	private Image action;

	public ActionPhotoPanel(Image action) {
		setOpaque(false);
		this.action = action;

	}
	
	public void setImage(Image image) {
		this.action = image;
		repaint();
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .85f);
		g2d.setComposite(newComposite);
		// 使图片不失真的方法
		Image smallImg = action.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		g2d.drawImage(smallImg, 0, 0, width, height, null);
	}

}

package ui.panel.allplayers;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月27日  下午8:18:46
 */
public class ActionPhotoPanel extends JPanel{
	
	private Image action;
	
	public ActionPhotoPanel(Image action){
		this.action = action;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;  
		AlphaComposite newComposite = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, .85f);
		g2d.setComposite(newComposite);
		g2d.drawImage(action, 0, 0, 151,240,null);
	}

}

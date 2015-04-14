package ui.common.label;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 带有图片的label
 * @author lsy
 * @version 2015年3月22日 下午6:06:56
 */
public class ImgLabel extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = 4941915864868989205L;
	
	int x;
	int y;
	int width;
	int height;

	public void setImage(Image img) {
		Image smallImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(smallImg);
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		this.setBounds(x, y, icon.getImage().getWidth(null), icon.getImage().getWidth(null));
		this.setIcon(icon);
		this.repaint();
	}
	public ImgLabel(int x, int y, int width, int height, Image img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setOpaque(false);
		// 使图片不失真的方法
		Image smallImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(smallImg);
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		this.setBounds(x, y, icon.getImage().getWidth(null), icon.getImage().getWidth(null));
		this.setIcon(icon);
		this.repaint();
	}
	
}

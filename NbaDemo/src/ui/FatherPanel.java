package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FatherPanel extends JPanel {

	private static final long serialVersionUID = -4321340946565227856L;
	protected UIController controller;
	protected int width;
	protected int height;
	protected Image bgImage;
	protected String url;
	protected JFrame frame;
	private int exitX = 870;
	private int exitY = 1;
	private int miniX = 822;

	protected MyButton mini, close;

	public FatherPanel(JFrame frame, String url, UIController controller) {
		this.controller = controller;
		this.url = url;
		this.frame = frame;
		bgImage = new ImageIcon(url).getImage();
		this.width = bgImage.getWidth(null);
		this.height = bgImage.getHeight(null);
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(width, height);
		this.addCloseLabel();
		this.addMiniLabel();
		this.setOpaque(false);
		this.repaint();
	}

	private void addMiniLabel() {

		mini = new MyButton("Image/mini.png", miniX, exitY);
		mini.addMouseListener(new Listener());
		this.add(mini);
	}

	private void addCloseLabel() {
		close = new MyButton("Image/exit.png", exitX, exitY);
		close.addMouseListener(new Listener());
		this.add(close);
	}

	class Listener implements MouseListener {

		int times = 0;

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == mini) {
				frame.setExtendedState(JFrame.ICONIFIED);
			} else if (e.getSource() == close) {
				System.exit(0);
			}
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {

		}

	}

	public void paint(Graphics g) {
		g.drawImage(bgImage, 0, 0, this);
		super.paint(g);
	}

}

package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author lsy
 * @version 2015年3月12日下午3:32:56
 */
public class MainPanel extends FatherPanel{

	
	private static final long serialVersionUID = 7108387309547483359L;
	private JButton button;
	private LeftPanel leftPanel;
	private JFrame frame;
	private UIController controller;
	private Random random = new Random();
	
	public MainPanel(JFrame frame,String url,UIController controller){
		super(frame,url,controller);
		setButton();
		this.frame = frame;
		this.controller = controller;
		this.add(button);
		this.addLeftPanel();
		
	}
	
	public void addLeftPanel(){
		leftPanel = new LeftPanel(frame,"Image/2.png",controller);
		this.add(leftPanel);
	}
	
	public void toLeftPanel(){
		leftPanel.moveIn();
		this.repaint();
	}
	
	public void outLeftPanel(){
		leftPanel.moveOut();
		this.repaint();
	}
	
	public void setButton(){
		
		button = new JButton();
		button.setContentAreaFilled(false);
		button.setBounds(0,0, 43, 550);
		button.setBorder(null);
		button.addMouseListener(new ButtonListener());
	}
	
	class ButtonListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			toLeftPanel();
		}

		public void mouseExited(MouseEvent e) {
			outLeftPanel();
		}
		
	}
	
}

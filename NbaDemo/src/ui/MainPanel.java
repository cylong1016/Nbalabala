package ui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	public MainPanel(JFrame frame,String url,UIController controller){
		super(frame,url,controller);
		setButton();
		this.frame = frame;
		this.controller = controller;
		this.add(button);
		this.setLeftPanel();
		
	}
	
	public void setLeftPanel(){
		leftPanel = new LeftPanel(frame,"Image/2.png",controller);
	}
	
	public void toLeftPanel(){
		leftPanel.setVisible(true);
		this.add(leftPanel);
		this.repaint();
	}
	
	public void outLeftPanel(){
		leftPanel.setVisible(false);
		this.repaint();
	}
	
	public void setButton(){
		
		button = new JButton();
		button.setContentAreaFilled(false);
		button.setBounds(204,0, 43, 550);
		button.setBorder(null);
		button.addMouseListener(new ButtonListener());
	}
	
	class ButtonListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
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

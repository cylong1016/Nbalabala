package ui.common.frame;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ui.UIConfig;
import ui.common.frame.title.TitlePanel;
import ui.common.panel.LeftPanel;
import ui.common.panel.Panel;
import ui.panel.allplayers.AllPlayersPanel;
import ui.panel.allteams.AllTeamsPanel;
import ui.panel.analyse.AnalysePanel;
import ui.panel.gamedata.GameDataPanel;
import ui.panel.gamedata.live.LiveInPanel;
import ui.panel.hot.hotTodayPlayer.HotTodayPlayerPanel;
import ui.panel.main.MainPanel;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.TeamDataPanel;

/**
 * 自定义Frame
 * @author cylong
 * @version 2015年3月16日 上午12:50:04
 */
public class Frame extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 5359481363535358093L;

	public static Panel currentPanel;
	/** 标题栏 */
	protected TitlePanel title;
	
	/** 左边的侧边栏 */
	protected LeftPanel leftPanel;

	/** Frame透明度 */
	protected float hyalineValue = 0f;
	/** 线程内部类，透明渐变显示Frame */
	protected HyalineValue hy;

	public Frame() {
		hy = new HyalineValue();	// 需要在外部调用
		// 标题栏
		title = new TitlePanel(this);
		this.add(title, BorderLayout.NORTH); // 添加标题
		
		// 左侧边栏
		leftPanel = new LeftPanel();
		
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT);
		this.setLocationRelativeTo(null); // 居中，要在设置大小之后 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setOpacity(hyalineValue); // 设置透明
		this.setVisible(true);
		this.setDragable(); // 设置可以拖动
		this.setOSStyle(); // 设置为当前系统风格
		Image img = Toolkit.getDefaultToolkit().getImage("images/icon.png");
		this.setIconImage(img);
	}

	/**
	 * 透明度渐变启动界面，需要外部或者子类自己调用
	 * @author cylong
	 */
	public void start() {
		hy.start();
	}
	
	public void setPanel(Panel panel) {
		if(currentPanel != null) {
			this.remove(currentPanel);	// 移出当前界面
			
			if (currentPanel.getClass() == MainPanel.class) {
				leftPanel = new LeftPanel();
				setLeftPanelBtnOn(panel);
			}
		}	

		currentPanel = panel;
		this.add(leftPanel, BorderLayout.WEST);
		if (panel.getClass() == MainPanel.class) {
			if (leftPanel != null) {
				this.remove(leftPanel);
			}
		}
		
		this.add(panel);
		this.setVisible(true);
		this.repaint();
	}

	private void setLeftPanelBtnOn(Panel panel) {
		if (panel.getClass() == AllPlayersPanel.class) {
			leftPanel.setCurrentBtn(leftPanel.getAllPlayersBtn());
		}
		else if(panel.getClass() == AllTeamsPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getAllTeamsBtn());
		}
		else if(panel.getClass() == PlayerDataPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getPlayerDataBtn());
		}
		else if(panel.getClass() == TeamDataPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getTeamDataBtn());
		}
		else if(panel.getClass() == GameDataPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getGameBtn());
		}
		else if(panel.getClass() == LiveInPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getLiveBtn());
		}
		else if(panel.getClass() == AnalysePanel.class){
			leftPanel.setCurrentBtn(leftPanel.getAnalysisBtn());
		}
		else if(panel.getClass() == HotTodayPlayerPanel.class){
			leftPanel.setCurrentBtn(leftPanel.getHotBtn());
		}
		
	}

	/**
	 * 透明度渐变启动界面
	 * @author cylong
	 */
	protected class HyalineValue extends Thread {

		public void run() {
			while(true) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				hyalineValue += 0.05f;
				if (hyalineValue > 1) {
					hyalineValue = 1;
				}
				setOpacity(hyalineValue);
				if (hyalineValue == 1) {
					break;
				}

			}
		}
	}

	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;

	/**
	 * 设置Frame可以拖动
	 * @author cylong
	 */
	public void setDragable() {
		this.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				isDragged = false;
			}

			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
			}

		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(getLocation().x + e.getX() - tmp.x, getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
	}

	/**
	 * 设置为windows操作系统风格
	 * @author cylong
	 */
	private void setOSStyle() {
		Properties props = System.getProperties(); //系统属性
		String osName = props.getProperty("os.name");	// 操作系统名称
		if (osName.contains("Windows")) {
			try {
				String lookAndfeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				UIManager.setLookAndFeel(lookAndfeel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

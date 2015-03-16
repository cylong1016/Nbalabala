package ui.common.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ui.common.frame.title.TitlePanel;

/**
 * 自定义Frame
 * @author cylong
 * @version 2015年3月16日  上午12:50:04
 */
public class Frame extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 5359481363535358093L;

	/*----------------------------Frame配置--------------------------------*/
	/** 界面的宽 */
	public static int WIDTH = 1000;
	/** 界面的高 */
	public static int HEIGHT = 600;
	/** 主界面的背景色 */
	public static Color MAIN_COLOR = Color.WHITE;

	/*----------------------------Frame配置--------------------------------*/

	/** 标题栏 */
	protected TitlePanel title;

	/** Frame透明度 */
	protected float hyalineValue = 0f;
	/** 线程内部类，透明渐变显示Frame */
	protected HyalineValue hy;

	public Frame() {
		hy = new HyalineValue();	// 需要在外部调用
		// 标题栏
		title = new TitlePanel(this);
		this.add(title, BorderLayout.NORTH); // 添加标题
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null); // 居中，要在设置大小之后 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setOpacity(hyalineValue); // 设置透明
		this.setVisible(true);
		this.setDragable(); // 设置可以拖动
		this.setOSStyle(); // 设置为当前系统风格
	}

	/**
	 * 透明度渐变启动界面，需要外部或者子类自己调用
	 * @author cylong
	 */
	public void start() {
		hy.start();
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
	 * 设置为相应操作系统风格
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
		} else if (osName.contains("Mac")) {
			try {
				String lookAndfeel = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
				UIManager.setLookAndFeel(lookAndfeel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

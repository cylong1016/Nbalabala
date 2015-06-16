package ui.common.date;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.UIConfig;

/**
 * 直接出现的日历
 * 
 * @author lsy
 * @version 2015年4月8日 下午6:06:04
 */
public class NewDateChooser extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final Font DATE_CHOOSER_FONT = new Font("微软雅黑", 0, 15);

	private static final Color CAL_MAIN = new Color(239, 168, 16);
	private static final Color CAL_SUB = new Color(241, 220, 182);

	private Date initDate;
	private Calendar now = Calendar.getInstance();
	private Calendar select;
	private JPanel monthPanel;// 月历
	private JP1 jp1;// 四块面板,组成
	@SuppressWarnings("unused")
	private JP2 jp2;
	private JP3 jp3;
	@SuppressWarnings("unused")
	private JP4 jp4;
	private final LabelManager lm = new LabelManager();
	private JLabel showDate;// ,toSelect;
	private SimpleDateFormat sdf = new SimpleDateFormat(" yyyy年MM月dd日 ");

	/**
	 * Creates a new instance of DateChooser
	 */
	public NewDateChooser() {
		this(new Date());
	}

	public NewDateChooser(Date date) {
		initDate = date;
		select = Calendar.getInstance();
		select.setTime(initDate);
		initPanel();
		initLabel();
		setOpaque(false);
		setBorder(null);
		this.setBounds(0,0,307,180);
	}

	

	public void setEnabled(boolean b) {
		super.setEnabled(b);
		showDate.setEnabled(b);
	}

	/**
	 * 得到当前选择框的日期
	 */
	public Date getDate() {
		return select.getTime();
	}
	
	 private void initLabel(){
	        showDate=new JLabel(sdf.format(initDate));
	        showDate.setFont(DATE_CHOOSER_FONT);
	        showDate.setOpaque(true);
	        showDate.setForeground(Color.white);
	        showDate.setBackground(UIConfig.BUTTON_COLOR);
	        showDate.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
	        showDate.setRequestFocusEnabled(true);
	        showDate.addMouseListener(new MouseAdapter(){
	            public void mousePressed(MouseEvent me){
	                showDate.requestFocusInWindow();
	            }
	        });
	        
//	        this.setBackground(Color.WHITE);
	        this.add(showDate,BorderLayout.CENTER);
	        this.setPreferredSize(new Dimension(90,30));
	        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	       
	    }

	// 根据初始化的日期,初始化面板
	private void initPanel() {
		monthPanel = new JPanel(new BorderLayout());
		monthPanel.setBorder(null);
		// BorderFactory.createLineBorder(Color.BLUE)
		JPanel up = new JPanel(new BorderLayout());
		up.add(jp1 = new JP1(), BorderLayout.NORTH);
		up.add(jp2 = new JP2(), BorderLayout.CENTER);
		monthPanel.add(jp3 = new JP3(), BorderLayout.CENTER);
		monthPanel.add(up, BorderLayout.NORTH);
		monthPanel.add(jp4 = new JP4(), BorderLayout.SOUTH);
		this.add(monthPanel);
	}


	// 根据新的日期刷新
	private void refresh() {
		jp1.updateDate();
		jp3.updateDate();
		SwingUtilities.updateComponentTreeUI(this);
	}

	// 提交日期
	private void commit() {
		showDate.setText(sdf.format(select.getTime()));
	}

	private class JP1 extends JPanel {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		JLabel left, right, center;

		public JP1() {
			super(new BorderLayout());
			this.setBackground(CAL_MAIN);
			// new Color(160,185,215)
			initJP1();
		}

		private void initJP1() {
			left = new JLabel(" << ", JLabel.CENTER);
			left.setForeground(Color.white);
			left.setToolTipText("上一月");
			right = new JLabel(" >> ", JLabel.CENTER);
			right.setForeground(Color.white);
			right.setToolTipText("下一月");
			left.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			right.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			center = new JLabel("", JLabel.CENTER);
			updateDate();
			this.add(left, BorderLayout.WEST);
			this.add(center, BorderLayout.CENTER);
			this.add(right, BorderLayout.EAST);
			this.setPreferredSize(new Dimension(295, 25));
			left.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					left.setCursor(new Cursor(Cursor.HAND_CURSOR));
					left.setForeground(Color.WHITE);
				}

				public void mouseExited(MouseEvent me) {
					left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					left.setForeground(Color.white);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, -1);
					left.setForeground(UIConfig.BUTTON_COLOR);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					left.setForeground(Color.white);
				}
			});
			right.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					right.setCursor(new Cursor(Cursor.HAND_CURSOR));
					right.setForeground(Color.WHITE);
				}

				public void mouseExited(MouseEvent me) {
					right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					right.setForeground(Color.white);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, 1);
					right.setForeground(UIConfig.BUTTON_COLOR);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					right.setForeground(Color.white);
				}
			});
		}

		private void updateDate() {
			center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
			center.setForeground(Color.white);
		}
	}

	private class JP2 extends JPanel {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		public JP2() {
			this.setBackground(CAL_SUB);
			this.setPreferredSize(new Dimension(295, 25));
			JLabel week = new JLabel("SUN    MON    TUE    WED    THU    FRI    SAT", JLabel.CENTER);
			this.add(week, CENTER_ALIGNMENT);
		}
	}

	private class JP3 extends JPanel {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		public JP3() {
			super(new GridLayout(6, 7));
			this.setPreferredSize(new Dimension(295, 100));
			initJP3();
		}

		private void initJP3() {
			updateDate();
		}

		public void updateDate() {
			this.removeAll();
			lm.clear();
			Date temp = select.getTime();
			Calendar select = Calendar.getInstance();
			select.setTime(temp);
			select.set(Calendar.DAY_OF_MONTH, 1);
			int index = select.get(Calendar.DAY_OF_WEEK);
			int sum = (index == 1 ? 8 : index);
			select.add(Calendar.DAY_OF_MONTH, 0 - sum);
			for (int i = 0; i < 42; i++) {
				select.add(Calendar.DAY_OF_MONTH, 1);
				lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH), select
						.get(Calendar.DAY_OF_MONTH)));
			}
			for (MyLabel my : lm.getLabels()) {
				this.add(my);
			}
			select.setTime(temp);
		}
	}

	private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		private int year, month, day;
		private boolean isSelected;

		public MyLabel(int year, int month, int day) {
			super("" + day, JLabel.CENTER);
			this.year = year;
			this.day = day;
			this.month = month;
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.setFont(DATE_CHOOSER_FONT);
			if (month == select.get(Calendar.MONTH)) {
				this.setForeground(Color.BLACK);
			} else {
				this.setForeground(Color.LIGHT_GRAY);
			}
			if (day == select.get(Calendar.DAY_OF_MONTH)) {
				this.setBackground(UIConfig.BUTTON_COLOR);
			} else {
				this.setBackground(Color.WHITE);
			}
		}

		public boolean getIsSelected() {
			return isSelected;
		}

		public void setSelected(boolean b, boolean isDrag) {
			isSelected = b;
			if (b && !isDrag) {
				int temp = select.get(Calendar.MONTH);
				select.set(year, month, day);
				if (temp == month) {
					SwingUtilities.updateComponentTreeUI(jp3);
				} else {
					refresh();
				}
			}
			this.repaint();
		}

		protected void paintComponent(Graphics g) {
			if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
				// 如果当前日期是选择日期,则高亮显示
				g.setColor(CAL_SUB);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH)
					&& day == now.get(Calendar.DAY_OF_MONTH)) {
				// 如果日期和当前日期一样,则用红框
				Graphics2D gd = (Graphics2D) g;
				gd.setColor(Color.yellow);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			if (isSelected) {// 如果被选中了就画出一个虚线框出来
				Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f,
						new float[] { 2.0f, 2.0f }, 1.0f);
				Graphics2D gd = (Graphics2D) g;
				gd.setStroke(s);
				gd.setColor(Color.BLACK);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			super.paintComponent(g);
		}

		public boolean contains(Point p) {
			return this.getBounds().contains(p);
		}

		private void update() {
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			isSelected = true;
			update();
		}

		public void mouseReleased(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, false);
			commit();
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, true);
		}

		public void mouseMoved(MouseEvent e) {
		}

		public int compare(MyLabel o1, MyLabel o2) {
			Calendar c1 = Calendar.getInstance();
			c1.set(o1.year, o2.month, o1.day);
			Calendar c2 = Calendar.getInstance();
			c2.set(o2.year, o2.month, o2.day);
			return c1.compareTo(c2);
		}
	}

	private class LabelManager {
		private List<MyLabel> list;

		public LabelManager() {
			list = new ArrayList<MyLabel>();
		}

		public List<MyLabel> getLabels() {
			return list;
		}

		public void addLabel(MyLabel my) {
			list.add(my);
		}

		public void clear() {
			list.clear();
		}

		@SuppressWarnings("unused")
		public void setSelect(MyLabel my, boolean b) {
			for (MyLabel m : list) {
				if (m.equals(my)) {
					m.setSelected(true, b);
				} else {
					m.setSelected(false, b);
				}
			}
		}

		public void setSelect(Point p, boolean b) {
			// 如果是拖动,则要优化一下,以提高效率
			if (b) {
				// 表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和
				// 将要显示的标签找到了就可以了
				boolean findPrevious = false, findNext = false;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						findNext = true;
						if (m.getIsSelected()) {
							findPrevious = true;
						} else {
							m.setSelected(true, b);
						}
					} else if (m.getIsSelected()) {
						findPrevious = true;
						m.setSelected(false, b);
					}
					if (findPrevious && findNext) {
						return;
					}
				}
			} else {
				MyLabel temp = null;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						temp = m;
					} else if (m.getIsSelected()) {
						m.setSelected(false, b);
					}
				}
				if (temp != null) {
					temp.setSelected(true, b);
				}
			}
		}
	}

	private class JP4 extends JPanel {
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		public JP4() {
			super(new BorderLayout());
			this.setPreferredSize(new Dimension(295, 20));
			this.setBackground(CAL_MAIN);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			final JLabel jl = new JLabel("   今天: " + sdf.format(new Date()));
			jl.setForeground(Color.white);
			jl.setToolTipText("点击回到今天日期");
			this.add(jl, BorderLayout.CENTER);
			jl.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
					jl.setForeground(Color.WHITE);
				}

				public void mouseExited(MouseEvent me) {
					jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					jl.setForeground(Color.white);
				}

				public void mousePressed(MouseEvent me) {
					jl.setForeground(Color.WHITE);
					select.setTime(new Date());
					refresh();
					commit();
				}

				public void mouseReleased(MouseEvent me) {
					jl.setForeground(Color.white);
				}
			});
		}
	}
}

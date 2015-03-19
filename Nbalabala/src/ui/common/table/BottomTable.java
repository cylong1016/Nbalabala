package ui.common.table;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ui.UIConfig;

/**
 * 用JPanel为容器装饰表格
 * @author cylong
 * @version Jun 11, 2014 11:55:06 PM
 */
public class BottomTable extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 3966419405700478023L;

	/** 表格每一行的高 */
	private int rowHeight = 25;

	/** 表格模型对象 */
	protected DefaultTableModel tableModel = null;
	protected JTable infoTable = null;
	protected JScrollPane scrollPane = null;
	protected String[] columnNames = null;
	protected Object[][] rowData = null;

	public BottomTable(String[] columnNames, Object[][] rowData) {
		this.columnNames = columnNames;
		this.rowData = rowData;
		this.setLayout(null);
		this.setOpaque(false);
	}

	/**
	 * 初始化表格
	 * @author cylong
	 * @version 2014年12月15日 下午9:43:50
	 */
	public void init() {
		this.tableModel = new DefaultTableModel(rowData, columnNames);
		this.infoTable = new JTable(tableModel) {

			/** serialVersionUID */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			} // 禁止编辑单元格

		};
		this.decorateTable();
		this.initScrollPane();
	}

	/**
	 * 刷新table
	 * @deprecated
	 * @author cylong
	 * @version Jun 12, 2014 12:53:45 PM
	 */
	public void refreshTable() {
		this.remove(scrollPane);
		this.init();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(UIConfig.TABLE_BORDER_COLOR);
		g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
		g.drawLine(0, 0, 0, getHeight());
	}

	/**
	 * 装饰Table
	 * @author cylong
	 * @version Jun 12, 2014 2:47:48 AM
	 */
	protected void decorateTable() {
		infoTable.setFont(UIConfig.TABLE_FONT);	// 设置表格内容字体
		infoTable.setForeground(UIConfig.TABLE_FORE_COLOR);	// 表格前景色
		infoTable.setSelectionForeground(UIConfig.TABLE_SELECTIONFORE);	// 选择文本的前景色
		infoTable.setBorder(null);
		infoTable.setRowHeight(rowHeight);	// 每一行的高
		infoTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		//	infoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		infoTable.setIntercellSpacing(new Dimension(0, 0));
		// 将表格设置为透明，表格同样包括表格本身和其中的内容项 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		// 内容项的设置是通过设置渲染器的透明来实现
		infoTable.setOpaque(false);
		infoTable.setGridColor(UIConfig.TABLE_BORDER_COLOR);	// 表格边框的颜色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setOpaque(false); // 将渲染器设置为透明
		render.setHorizontalAlignment(JLabel.CENTER);	// 设置表格中内容居中显示
		// 将这个渲染器设置到table里。
		// 这个设置在没有另外专门对column设置的情况下有效
		// 若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		// 因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		infoTable.setDefaultRenderer(Object.class, render);
		// 设置显示范围
		Dimension viewSize = new Dimension();
		viewSize.width = infoTable.getColumnModel().getTotalColumnWidth();
		viewSize.height = 10 * infoTable.getRowHeight();
		infoTable.setPreferredScrollableViewportSize(viewSize);
		// 设置头部透明
		// 头部实际上也是一个JTABLE，只有一行而已。
		JTableHeader header = infoTable.getTableHeader();// 获取头部
		header.setPreferredSize(new Dimension(30, 26));
		//	header.setOpaque(false);// 设置头部为透明
		header.setBackground(UIConfig.TABLE_HEADER_BACK_COLOR);	// 设置头部背景色
		header.getTable().setOpaque(false);// 设置头部里面的表格透明
		// 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		// 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		header.setDefaultRenderer(new HeaderCellRenderer());
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel)headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel)headerRenderer).setOpaque(false);
		}
		// 设定最后一行的显示IP的宽度
		TableColumn firstColumn = infoTable.getColumnModel().getColumn(infoTable.getColumnCount() - 1);
		firstColumn.setPreferredWidth(100);
		// 设定倒数第二行的显示姓名的宽度
		TableColumn secondColumn = infoTable.getColumnModel().getColumn(infoTable.getColumnCount() - 2);
		secondColumn.setPreferredWidth(50);
		
	}

	/**
	 * 头部渲染器
	 * @author cylong
	 * @version 2014年12月15日 下午9:39:29
	 */
	class HeaderCellRenderer extends DefaultTableCellRenderer {

		protected static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel label = new JLabel() {

				protected static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
					// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
					Graphics2D g2d = (Graphics2D)g;
					g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
					g2d.drawLine(0, 0, this.getWidth(), 0);
					g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
					g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);
					// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
					super.paintComponent(g);
				}
			};
			label.setText(value != null ? value.toString() : "unknown");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(UIConfig.TABLE_FONT);
			label.setForeground(UIConfig.TABLE_HEADER_FORE_COLOR);	// 表头前景色
			return label;
		}

	}

	/**
	 * 这里将JScrollPane设置为透明的。
	 * JScrollPane包括好几个部分，一个是本身，另一个是中间的viewport，还有头部的headerview，
	 * 左边的rowview，右边和下面的滚动条。
	 * 对于一个默认添加jtable的JScrollPane，它至少包括JScrollPane自己的边缘、头部的标题和中间的JTABLE
	 * 若只将JScrollPane设置为透明，则只是边缘透明，中间的viewport（也就是容纳表格的地方）和头部依然不透明
	 * 因此需要将它们都设置为透明，但是需要注意的是，头部要先手动添加jtable的头部，然后再取出头部，再设置为透明 否则，会报空指针错误
	 * @author cylong
	 * @version Jun 12, 2014 12:07:09 AM
	 */
	protected void initScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setSize(getWidth(), getHeight()); // 和panel一样大
		scrollPane.setViewportView(infoTable);	// 装载表格
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getViewport().setOpaque(false);	// 将中间的viewport设置为透明
		scrollPane.setOpaque(false);	// 将JScrollPane设置为透明
		scrollPane.setColumnHeaderView(infoTable.getTableHeader());	// 设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);	// 再取出头部，并设置为透明
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				JScrollBar onlineFriendsBar = scrollPane.getVerticalScrollBar();
				if (!((onlineFriendsBar.getValue() == onlineFriendsBar.getMinimum() && e.getWheelRotation() <= 0) || (onlineFriendsBar.getValue() == onlineFriendsBar.getMaximum() && e.getWheelRotation() >= 0))) {
					if (onlineFriendsBar.getValue() + onlineFriendsBar.getUnitIncrement() * e.getUnitsToScroll() * 2 >= onlineFriendsBar.getMaximum()) {
						onlineFriendsBar.setValue(onlineFriendsBar.getMaximum());
					} else if (onlineFriendsBar.getValue() + onlineFriendsBar.getUnitIncrement() * e.getUnitsToScroll()
								* 2 <= onlineFriendsBar.getMinimum()) {
						onlineFriendsBar.setValue(onlineFriendsBar.getMinimum());
					} else {
						onlineFriendsBar.setValue(onlineFriendsBar.getValue() + onlineFriendsBar.getUnitIncrement()
													* e.getUnitsToScroll() * 10);
					}
				}
			}
		});

		this.add(scrollPane);
	}

	/**
	 * @version Jun 19, 2014 8:24:21 PM
	 * @return the infoTable
	 */
	public JTable getInfoTable() {
		return this.infoTable;
	}

	/**
	 * @version Jun 19, 2014 8:33:37 PM
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return this.tableModel;
	}

}

package ui.common.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import ui.UIConfig;

/**
 * 装饰表格
 * @author cylong
 * @version 2015年3月19日 下午7:32:53
 */
public class BottomTable extends JTable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3966419405700478023L;

	/** 表格每一行的高 */
	private int rowHeight = 15;

	/** 表头 */
	protected String[] columnNames = null;
	/** 表格数据 */
	protected Object[][] rowData = null;

	public BottomTable(Object[][] rowData, String[] columnNames) {
		super(rowData, columnNames);
		this.columnNames = columnNames;
		this.rowData = rowData;
		this.setAutoCreateRowSorter(true); // 点击表头进行排序
		this.decorateTable();
	}

	/**
	 * @see javax.swing.JTable#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	} // 禁止编辑单元格

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(UIConfig.TABLE_BORDER_COLOR);
		g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
//		g.drawLine(0, 0, 0, getHeight());
	}
	
	/** 设置列宽 */
	public void setWidth(int[]width) {
		TableColumnModel model = this.getColumnModel();
		for (int i=0;i<width.length;i++){
			 model.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	/** 不显示竖线 */
	public void cancelVerticalLines() {
		this.setShowVerticalLines(false);
		JTableHeader header = this.getTableHeader();// 获取头部
		header.setOpaque(false); // 设置头部为透明
		header.setForeground(UIConfig.TABLE_HEADER_FORE_COLOR);	// 设置头部前景色
		header.getTable().setOpaque(false);// 设置头部里面的表格透明
		// 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		// 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		header.setDefaultRenderer(new HeaderCellNoVerticalLinesRenderer());
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel)headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel)headerRenderer).setOpaque(false);
		}
	}
	
	public void setRealOpaque() {
		// 将表格设置为透明，表格同样包括表格本身和其中的内容项 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		// 内容项的设置是通过设置渲染器的透明来实现
		this.setOpaque(false);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setOpaque(false); // 将渲染器设置为透明
		render.setHorizontalAlignment(JLabel.CENTER);	// 设置表格中内容居中显示
		// 将这个渲染器设置到table里。
		// 这个设置在没有另外专门对column设置的情况下有效
		// 若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		// 因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		this.setDefaultRenderer(Object.class, render);
		// 设置显示范围
		Dimension viewSize = new Dimension();
		viewSize.width = this.getColumnModel().getTotalColumnWidth();
		viewSize.height = 10 * this.getRowHeight();
		this.setPreferredScrollableViewportSize(viewSize);
		// 设置头部透明
		// 头部实际上也是一个JTABLE，只有一行而已。
		JTableHeader header = this.getTableHeader();// 获取头部
		// header.setPreferredSize(new Dimension(30, rowHeight));
		header.setOpaque(false); // 设置头部为透明
		header.setForeground(Color.WHITE);	// 设置头部前景色
		header.getTable().setOpaque(false);// 设置头部里面的表格透明
		// 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		// 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		header.setDefaultRenderer(new HeaderCellRenderer());
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel)headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel)headerRenderer).setOpaque(false);
		}
	}

	/**
	 * 装饰Table
	 * @author cylong
	 * @version 2015年3月20日 下午6:32:45
	 */
	public void decorateTable() {
		this.setFont(UIConfig.TABLE_FONT);	// 设置表格内容字体
		this.setForeground(UIConfig.TABLE_FORE_COLOR);	// 表格前景色
		this.setSelectionForeground(UIConfig.TABLE_SELECTIONFORE);	// 选择文本的前景色
		this.setSelectionBackground(UIConfig.TABLE_SELECTIONBACK);
		this.setBorder(null);
		this.setRowHeight(rowHeight);	// 每一行的高
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setIntercellSpacing(new Dimension(0, 0));
		// 将表格设置为透明，表格同样包括表格本身和其中的内容项 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		// 内容项的设置是通过设置渲染器的透明来实现
		this.setOpaque(false);
		this.setGridColor(UIConfig.TABLE_BORDER_COLOR);	// 表格边框的颜色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setOpaque(false); // 将渲染器设置为透明
		render.setHorizontalAlignment(JLabel.CENTER);	// 设置表格中内容居中显示
		// 将这个渲染器设置到table里。
		// 这个设置在没有另外专门对column设置的情况下有效
		// 若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		// 因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		this.setDefaultRenderer(Object.class, render);
		// 设置显示范围
		Dimension viewSize = new Dimension();
		viewSize.width = this.getColumnModel().getTotalColumnWidth();
		viewSize.height = 10 * this.getRowHeight();
		this.setPreferredScrollableViewportSize(viewSize);
		// 设置头部透明
		// 头部实际上也是一个JTABLE，只有一行而已。
		JTableHeader header = this.getTableHeader();// 获取头部
		// header.setPreferredSize(new Dimension(30, rowHeight));
		// header.setOpaque(false); // 设置头部为透明
		header.setBackground(UIConfig.TABLE_HEADER_BACK_COLOR);	// 设置头部背景色
		header.setForeground(UIConfig.TABLE_HEADER_FORE_COLOR);	// 设置头部前景色
		// header.getTable().setOpaque(false);// 设置头部里面的表格透明
		// 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		// 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		header.setDefaultRenderer(new HeaderCellRenderer());
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel)headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel)headerRenderer).setOpaque(false);
		}

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
	 * 没有内竖线的头部渲染器
	 * @author issacDing
	 * @version 2014年12月15日 下午9:39:29
	 */
	class HeaderCellNoVerticalLinesRenderer extends DefaultTableCellRenderer {

		protected static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel label = new JLabel() {

				protected static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
					// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
					Graphics2D g2d = (Graphics2D)g;
					g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
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

}

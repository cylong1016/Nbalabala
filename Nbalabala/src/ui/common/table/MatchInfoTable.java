package ui.common.table;

import java.awt.AlphaComposite;
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

import ui.UIConfig;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月29日  下午4:47:46
 */
public class MatchInfoTable extends JTable{
	
	/** 表格每一行的高 */
	private static final int ROW_Height = 30;
	
	public static final int COLUMN_LENGTH = 10;

	/** 表头 */
	protected static final String[] columnNames 
		= new String[] { "球队", "1", "2", "3", "4", "加时一", "加时二", "加时三", "总分", "" };
	/** 表格数据 */
	protected Object[][] rowData = null;

	public MatchInfoTable(Object[][] rowData) {
		super(rowData, columnNames);
		this.rowData = rowData;
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
	}
	
	public void decorateTable() {
		this.setFont(UIConfig.TABLE_FONT);	// 设置表格内容字体
		this.setForeground(UIConfig.TABLE_FORE_COLOR);	// 表格前景色
		this.setSelectionForeground(UIConfig.TABLE_SELECTIONFORE);	// 选择文本的前景色
		this.setSelectionBackground(UIConfig.TABLE_SELECTIONBACK);
		this.setBorder(null);
		this.setShowGrid(false);
		this.setRowHeight(ROW_Height);	// 每一行的高
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setIntercellSpacing(new Dimension(0, 0));
		// 将表格设置为透明，表格同样包括表格本身和其中的内容项 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		// 内容项的设置是通过设置渲染器的透明来实现
		this.setOpaque(false);
		
		this.setDefaultRenderer(Object.class, new CellRenderer());
		// 设置显示范围
		Dimension viewSize = new Dimension();
		viewSize.width = this.getColumnModel().getTotalColumnWidth();
		viewSize.height = 10 * this.getRowHeight();
		this.setPreferredScrollableViewportSize(viewSize);
		// 设置头部透明
		// 头部实际上也是一个JTABLE，只有一行而已。
		JTableHeader header = this.getTableHeader();// 获取头部
		// header.setOpaque(false); // 设置头部为透明
		header.setBackground(UIConfig.TABLE_HEADER_BACK_COLOR);	// 设置头部背景色
		header.setForeground(UIConfig.TABLE_HEADER_FORE_COLOR);	// 设置头部前景色
		// header.getTable().setOpaque(false);// 设置头部里面的表格透明
		// 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		// 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		header.setDefaultRenderer(new HeaderCellNoVerticalLinesRenderer());
		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		if (headerRenderer instanceof JLabel) {
			((JLabel)headerRenderer).setHorizontalAlignment(JLabel.CENTER);
			((JLabel)headerRenderer).setOpaque(false);
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
			HeaderRenderLabel label = new HeaderRenderLabel();
			label.setText(value != null ? value.toString() : "unknown");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(UIConfig.TABLE_FONT);
			label.setForeground(UIConfig.TABLE_HEADER_FORE_COLOR);	// 表头前景色
			return label;
		}

	}
	
	class HeaderRenderLabel extends JLabel{
		protected void paintComponent(Graphics g) {
			// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
			g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
			g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);
			// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
			super.paintComponent(g);
		}
	}
	
	class CellRenderLabel extends JLabel{
		int row;
		protected CellRenderLabel(int row){
			this.row = row;
			setHorizontalAlignment(JLabel.CENTER);
		}
		protected void paintComponent(Graphics g) {
			// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
			if (row % 2 != 0)
				g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
			// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
			super.paintComponent(g);
			AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .05f);
			g2d.setComposite(newComposite);
			if ((row % 4 == 1 || row % 4 == 0)) {
				g2d.setColor(Color.red);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			} else {
				g2d.setColor(Color.blue);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}
	}
	
	class CellRenderer extends DefaultTableCellRenderer {

		protected static final long serialVersionUID = 1L;
		
		public CellRenderer() {
			setOpaque(false); // 将渲染器设置为透明
			setHorizontalAlignment(JLabel.CENTER);	// 设置表格中内容居中显示
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			CellRenderLabel label = new CellRenderLabel(row);
			label.setText(value != null ? value.toString() : "");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(UIConfig.TABLE_FONT);
			label.setForeground(UIConfig.TABLE_FORE_COLOR);	// 表头前景色
			label.setBackground(Color.red);
			return label;
		}
	}
}

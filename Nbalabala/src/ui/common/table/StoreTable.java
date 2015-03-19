package ui.common.table;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.JTableHeader;

public class StoreTable extends JTable {

	public StoreTable() {
		super(0, 13);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTableHeader().setUI(new UI());
	}

	// 表头绘制器*********************************************************************
	private class UI extends BasicTableHeaderUI {

		private JTableHeader header;

		public void paint(Graphics g, JComponent c) {
			header = (JTableHeader)c;
			getTableHeader().setPreferredSize(new Dimension(StoreTable.this.getWidth(), 45));// 设置表头大小。横坐标必须足够大，
			//否则会出现绘制不完全以及闪烁现象

			//分类
			JLabel label = getLabel("分类");
			rendererPane.paintComponent(g, label, header, 0, 0, getWidth(0), 45, true);
			//到货日期
			label = getLabel("到货日期");
			rendererPane.paintComponent(g, label, header, getX(1), 0, getWidth(1), 45, true);
			//送货日期
			label = getLabel("送货日期");
			rendererPane.paintComponent(g, label, header, getX(2), 0, getWidth(2), 45, true);
			//收款代理
			label = getLabel("收款代理");
			rendererPane.paintComponent(g, label, header, getX(3), 0, getWidth(3), 45, true);
			//客户**************************************************************************
			label = getLabel("客户");
			rendererPane.paintComponent(g, label, header, getX(4), 0, getWidth(4) + getWidth(5), 30, true);

			label = getLabel("上游");
			rendererPane.paintComponent(g, label, header, getX(4), 30, getWidth(4), 15, true);
			label = getLabel("下游");
			rendererPane.paintComponent(g, label, header, getX(5), 30, getWidth(5), 15, true);
			//报关单号**********************************************************************
			label = getLabel("报关单号");
			rendererPane.paintComponent(g, label, header, getX(6), 0, getX(10) - getX(6), 15, true);

			label = getLabel("上游");
			rendererPane.paintComponent(g, label, header, getX(6), 15, getWidth(6) + getWidth(7), 15, true);
			label = getLabel("下游");
			rendererPane.paintComponent(g, label, header, getX(8), 15, getWidth(8) + getWidth(9), 15, true);

			label = getLabel("进口");
			rendererPane.paintComponent(g, label, header, getX(6), 30, getWidth(6), 15, true);
			label = getLabel("出口");
			rendererPane.paintComponent(g, label, header, getX(7), 30, getWidth(7), 15, true);
			label = getLabel("进口");
			rendererPane.paintComponent(g, label, header, getX(8), 30, getWidth(8), 15, true);
			label = getLabel("出口");
			rendererPane.paintComponent(g, label, header, getX(9), 30, getWidth(9), 15, true);
			//业务员
			label = getLabel("业务员");
			rendererPane.paintComponent(g, label, header, getX(10), 0, getWidth(10), 45, true);
			//操作员
			label = getLabel("操作员");
			rendererPane.paintComponent(g, label, header, getX(11), 0, getWidth(11), 45, true);
			//审核人
			label = getLabel("审核人");
			rendererPane.paintComponent(g, label, header, getX(12), 0, getWidth(12), 45, true);
		}

		// 得到指定列的起始坐标
		private int getX(int column) {
			int x = 0;
			for(int i = 0; i < column; i++)
				x += header.getColumnModel().getColumn(i).getWidth();
			return x;
		}

		//得到指定列的宽度
		private int getWidth(int column) {
			return header.getColumnModel().getColumn(column).getWidth();
		}

		//得到具有指定文本的标签
		private JLabel getLabel(String text) {
			JLabel label = new JLabel(text, JLabel.CENTER);
			label.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return label;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 300);
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().add(new JScrollPane(new StoreTable()));
		frame.setVisible(true);
	}
}
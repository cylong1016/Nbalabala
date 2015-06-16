/**
 * 
 */
package ui.common.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

import ui.UIConfig;
import ui.common.table.BottomTable.InterlacedRenderer;

/**
 *
 * @author Issac Ding
 * @since 2015年6月17日 上午12:28:31
 * @version 1.0
 */
public class AutoWrapRenderer extends DefaultTableCellRenderer{
	

		private static final long serialVersionUID = 1L;
		
		private int row;
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			AutoWrapRenderer.this.row = row;
			JTextArea label = new JTextArea() {
				protected static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
					// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
					Graphics2D g2d = (Graphics2D)g;
					g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
					g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
					if (AutoWrapRenderer.this.row % 2 != 0) {
						g2d.setColor(BottomTable.BG_INTERLACE_COLOR);
						g2d.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
					}
					// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
					super.paintComponent(g);
				}
			};
			label.setText(value != null ? value.toString() : " ");
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setAlignmentY(CENTER_ALIGNMENT);
			label.setFont(UIConfig.TABLE_FONT);
			label.setForeground(Color.black);	
			label.setLineWrap(true);
			label.setWrapStyleWord(true);
			if (row % 2 == 1)
				label.setBackground(BottomTable.BG_INTERLACE_COLOR);
			return label;
		}
		

}

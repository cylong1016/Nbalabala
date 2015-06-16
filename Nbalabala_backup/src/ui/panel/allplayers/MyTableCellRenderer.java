package ui.panel.allplayers;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 表格中添加图片的类
 * 
 * @author lsy
 * @version 2015年3月31日 下午11:12:49
 */
public class MyTableCellRenderer implements TableCellRenderer {
	
	public ArrayList<ImageIcon> icon;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		// 根据特定的单元格设置不同的Renderer,假如你要在第2行第3列显示图标
		if (row<icon.size()) {
			ImageIcon iconOne = icon.get(row);
			JLabel label = new JLabel(iconOne);
			return label;
		}
		return null;
	}
}

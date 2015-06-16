package ui.common.table;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import ui.UIConfig;

/**  
* 创建时间：2014-11-13 
* 项目名称：NewBee
* @author Xiaohan Ding
* @version 1.0   
* @since JDK 1.7 
* 文件名称：TableCellTextAreaRenderer.java  
* 类说明：  用于消息通知列表的换行的渲染器
*/
@SuppressWarnings("serial")
public class TableAutoWrapRenderer extends JTextArea implements TableCellRenderer {
	
	// private int wrapColumnWidth = 0;
	private int row = 0;
	
    public TableAutoWrapRenderer(int width) { 
    	setOpaque(false);
        setLineWrap(true); 
        // wrapColumnWidth = width;
        setFont(UIConfig.TABLE_FONT);
        setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        repaint();
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) { 
    	
        // 计算当下行的最佳高度 
        int maxPreferredHeight = 0; 

        for (int i = 0; i < table.getColumnCount(); i++) { 
            setText("" + table.getValueAt(row, i));
            if (value != null && value.toString().length() > 40) maxPreferredHeight = 80;
            else maxPreferredHeight = 40;
        }
            table.setRowHeight(row, maxPreferredHeight);

        setText(value == null ? "" : value.toString()); 
        TableAutoWrapRenderer.this.row = row;
        return this; 
    } 
    
    public void paintComponent(Graphics g) {
		// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(UIConfig.TABLE_BORDER_COLOR);
		g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
		if (TableAutoWrapRenderer.this.row % 2 != 0) {
			g2d.setColor(BottomTable.BG_INTERLACE_COLOR);
			g2d.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		}
		// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
		super.paintComponent(g);
    }
}
package ui.common.table;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import ui.UIConfig;

/**
 * 自己定义的滚动条，实现透明效果
 * @author cylong
 * @version 2015年3月19日 下午7:46:47
 */
public class BottomScrollPane extends JScrollPane {

	/** serialVersionUID */
	private static final long serialVersionUID = -6211694438630784736L;

	public BottomScrollPane(JPanel panel) {
		this.initScrollPane(panel);
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
	protected void initScrollPane(JPanel panel) {
		this.setSize(UIConfig.TABLE_DIMEN); // ScrollPane的大小，和表格大小无关
		this.setViewportView(panel);	// 装载表格
		// this.setBorder(new EmptyBorder(0, 0, 0, 0));
		// this.getViewport().setOpaque(false);	// 将中间的viewport设置为透明
		// this.setOpaque(false);	// 将JScrollPane设置为透明
		// this.setColumnHeaderView(table.getTableHeader());	// 设置头部（HeaderView部分）
		// this.getColumnHeader().setOpaque(false);	// 再取出头部，并设置为透明
		// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 滚动条
		// 设置滚轮可以滑动滚动条
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				JScrollBar onlineFriendsBar = BottomScrollPane.this.getVerticalScrollBar();
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
	}

}

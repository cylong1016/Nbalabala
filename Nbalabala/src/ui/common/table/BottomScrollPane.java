package ui.common.table;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import ui.UIConfig;

/**
 * 自己定义的ScrollPane，实现透明效果
 * @author cylong
 * @version 2015年3月19日 下午7:46:47
 */
public class BottomScrollPane extends JScrollPane {

	/** serialVersionUID */
	private static final long serialVersionUID = -6211694438630784736L;
	
//	/** 背景图片 */
//	private Image bgImage = new ImageIcon("images/tableBG.png").getImage();
	JTable table;
	
	public BottomScrollPane(JTable table) {
		this.initScrollPane(table);
		this.table = table;
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
	protected void initScrollPane(JTable table) {
		
//		//设置背景图片
//		JViewport view = new JViewport(){
//			/** serialVersionUID */
//			private static final long serialVersionUID = 6019924568637393827L;
//
//			public void paintComponent(Graphics g){
//				super.paintComponent(g);
//				g.drawImage(bgImage, 0, 0, null);
//			}
//		};
//		this.setViewport(view);
		
		this.setSize(UIConfig.TABLE_DIMEN); // ScrollPane的大小，和表格大小无关
		this.setViewportView(table);	// 装载表格
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.getViewport().setOpaque(false);	// 将中间的viewport设置为透明
		this.setOpaque(false);	// 将JScrollPane设置为透明
		this.setColumnHeaderView(table.getTableHeader());	// 设置头部（HeaderView部分）
		this.getColumnHeader().setOpaque(false);	// 再取出头部，并设置为透明
		
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

	public void setHead(){
		this.setColumnHeaderView(table.getTableHeader());	// 设置头部（HeaderView部分）
	}
//	
//	public void cancelBgImage() {
//		bgImage = null;
//	}
}

package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import vo.PlayerProfileVO;

/**
 * 设置球员简要信息的table
 * @author lsy
 * @version 2015年4月11日  下午1:37:21
 */
public class PlayerTable{
	
	private static final String[]COLUMN_NAMES = {"球员头像","英文名", "所属球队", "球衣号码", "位置", "生日" };
	public static BottomScrollPane SCROLL;
	private BottomTable table;
	/** 头像宽度 */
	private static final int PORTRAIT_WIDTH = 70;
	ArrayList<PlayerProfileVO> players;
	Panel panel;
	
	public PlayerTable(ArrayList<PlayerProfileVO> players,Panel panel){
		this.players = players;
		this.panel = panel;
		setTable();
	}
	
	public void setTable(){
		int size = players.size();
		int lth = COLUMN_NAMES.length;
		
		Object [][] rowData = new String[size][lth];
		ArrayList<ImageIcon> iconArr = new ArrayList<ImageIcon>();
		table = new BottomTable(rowData, COLUMN_NAMES);
		for (int i = 0; i < size; i++) {
			PlayerProfileVO ppVO = players.get(i);
			Image protrait = ppVO.getPortrait();
		    int  height =  protrait.getHeight(null)*70/protrait.getWidth(null);//按比例，将高度缩减
		    Image smallImg =protrait.getScaledInstance(PORTRAIT_WIDTH, height, Image.SCALE_SMOOTH);
			ImageIcon ic = new ImageIcon(smallImg);
			iconArr.add(ic);
			rowData[i][1] = ppVO.getName();
			rowData[i][2] = Constants.translateTeamAbbr(ppVO.getTeam());
			rowData[i][3] = ppVO.getNumber();
			rowData[i][4] = ppVO.getPosition();
			rowData[i][5] = ppVO.getBirth();
		}
		MyTableCellRenderer myRenderer = new MyTableCellRenderer();
		myRenderer.icon = iconArr;
//		iconArr.clear();
		table.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		
		try{
			table.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						MainController.toPlayerInfoPanel(panel, 
								players.get(rowI).getName(), (BottomPanel)panel);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		table.setRowHeight(57);
		table.setWidth(new int[]{123, 200, 150, 104, 89, 116});
		table.setForeground(Color.WHITE);
		table.cancelVerticalLines();
		table.setRealOpaque();
		
		SCROLL = new BottomScrollPane(table);
		SCROLL.setBounds(101, 160, 802, 365);
		SCROLL.cancelBgImage();
		panel.add(SCROLL);
	}
}

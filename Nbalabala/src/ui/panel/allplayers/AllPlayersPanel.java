package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.common.textField.MyTextField;
import ui.controller.MainController;
import vo.PlayerDetailVO;
import vo.PlayerProfileVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;

/**
 * 全部球员信息主界面
 * 
 * @author cylong
 * @version 2015年3月19日 上午3:19:47
 */
public class AllPlayersPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2951291212735567656L;

	/** 按钮的横纵坐标 间隔 宽度 高度 */
	private int x = 60, y = 60, inter = 33, width = 33, height = 32;
	/** 所有字母的button */
	LetterButton[] buttonArr = new LetterButton[26];
	char[] letter = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	Color letterbg = new Color(51, 66, 84, 130);
	Color letterColor = new Color(38, 41, 46);
	/** 查询按钮 */
	private ImgButton findButton;
	private String imgURL = UIConfig.IMG_PATH + "players/";
	/** 查询的文本框 */
	private MyTextField field;
	MainController controller;
	PlayerQueryBLService playerInfo = new PlayerQuery();

	/**
	 * @param url
	 *            背景图片的Url
	 */
	public AllPlayersPanel(MainController controller, String url) {
		super(controller, url);
		this.controller = controller;
		setButton();
		addButton();
		addFindButton();
		addTextField();
		setEffect(buttonArr[0]);
		iniSet();
		addListener();
		ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.getPlayerProfileByInitial('A');
		setTable(playerInfoArr);
	}

	String[] columns;
	Object[][] rowData;
	BottomScrollPane scroll;
	ImageIcon icon;
	ArrayList<Image> imgArr = new ArrayList<Image>();
	BottomTable table;
	
	public void setTable(final ArrayList<PlayerProfileVO> players) {
		columns = new String[] {"球员头像","英文名", "所属球队", "球衣号码", "位置", "生日" };
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		for (int i = 0; i < size; i++) {
			PlayerProfileVO ppVO = players.get(i);
			imgArr.add(ppVO.getPortrait());
			rowData[i][1] = ppVO.getName();
			rowData[i][2] = ppVO.getTeam();
			rowData[i][3] = ppVO.getNumber();
			rowData[i][4] = ppVO.getPosition();
			rowData[i][5] = ppVO.getBirth();
		}
		table = new BottomTable(rowData, columns);
		//TODO 将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try{
			table.addMouseListener(new UserMouseAdapter(){
				
				public void mouseDoubleClicked(MouseEvent e){
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						controller.toPlayerInfoPanel(AllPlayersPanel.this, players.get(rowI),AllPlayersPanel.this);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		table.setRowHeight(100);
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 239);
		this.add(scroll);
	}

	/**
	 * 添加查询按钮
	 * 
	 * @author lsy
	 * @version 2015年3月20日 下午6:48:07
	 */
	public void addFindButton() {
		findButton = new ImgButton(imgURL + "search.png", 902, 15, imgURL + "searchOn.png", imgURL
				+ "searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				PlayerDetailVO playerInfoArr = playerInfo.getPlayerDetailByName(field.getText());
				ArrayList<PlayerProfileVO> playerPro= new ArrayList<PlayerProfileVO>();
				playerPro.add(playerInfoArr.getProfile());
				scroll.removeAll();
				setTable(playerPro);
			}
		});
	}

	/**
	 * 将A设置为选中状态
	 * 
	 * @author lsy
	 * @version 2015年3月20日 下午6:48:14
	 */
	public void iniSet() {
		LetterButton.current = (LetterButton) buttonArr[0];
	}

	/**
	 * 设置选中效果
	 * 
	 * @param button
	 * @author lsy
	 * @version 2015年3月20日 下午6:50:11
	 */
	public void setEffect(LetterButton button) {
		button.setOpaque(true);
		button.setBackground(letterbg);
		button.setForeground(Color.white);
	}

	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		for (int i = 0; i < 26; i++) {
			buttonArr[i].addMouseListener(mou1);
		}
	}

	class MouListener1 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == LetterButton.current) {
				return;
			}
			LetterButton.current.back();
			LetterButton.current = (LetterButton) e.getSource();
			char goal = LetterButton.current.letter;
			ArrayList<PlayerProfileVO> vo = playerInfo.getPlayerProfileByInitial(goal);
			scroll.removeAll();
			setTable(vo);
		}
	}

	public void addTextField() {
		field = new MyTextField(754, 17, 135, 30);
		this.add(field);
	}

	public void setButton() {
		for (int i = 0; i < buttonArr.length; i++) {
			buttonArr[i] = new LetterButton(x + i * inter, y, width, height, letter[i] + "");
			buttonArr[i].setForeground(letterColor);
		}
	}

	public void addButton() {
		for (int i = 0; i < buttonArr.length; i++) {
			this.add(buttonArr[i]);
			buttonArr[i].letter = letter[i];
		}
	}

}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		return (JComponent) value;
	}
}

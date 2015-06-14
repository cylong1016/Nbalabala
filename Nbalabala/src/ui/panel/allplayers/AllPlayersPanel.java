package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import po.PlayerProfilePO;
import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.common.textField.MyTextField;
import ui.controller.MainController;
import utility.Constants;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;
import data.playerdata.PlayerImageCache;
import enums.PlayerType;

/**
 * 全部球员信息主界面
 * 
 * @author cylong
 * @version 2015年3月19日 上午3:19:47
 */
public class AllPlayersPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2951291212735567656L;
	
	private PlayerType playerType = PlayerType.ON_SERVICE;

	/** 按钮的横纵坐标 间隔 宽度 高度 */
	private static final int BUTTON_X = 60, BUTTON_Y = 63, INTER = 33, WIDTH = 33, HEIGHT = 37;
	private static final char[] LETTERS = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final String IMG_PATH = UIConfig.IMG_PATH + "players/";
	private static final String SEARCH_BUTTON_OFF = IMG_PATH + "search.png";
	private static final String SEARCH_BUTTON_ON = IMG_PATH + "searchOn.png";
	private static final String SEARCH_BUTTON_CLICK = IMG_PATH + "searchClick.png";
	
	private LetterButton current;
	
	/** 所有首字母的button */
	private LetterButton[] initialButtons = new LetterButton[26];
	/** 查询按钮 */
	private ImgButton queryButton;
	/** 查询的文本框 */
	private MyTextField queryTextField;
	
	private PlayerQueryBLService playerInfo = new PlayerQuery();
	
	private TabButton onServiceTab;
	private TabButton offServiceTab;
	private TabButton allTab;
	

	public static BottomScrollPane SCROLL;
	private BottomTable table;
	/** 头像宽度 */
	private static final int PORTRAIT_WIDTH = 70;
	ArrayList<PlayerProfilePO> players;
	Object [][] rowData;
	int size,lth;
	
	/**
	 * @param url 背景图片的Url
	 */
	public AllPlayersPanel(String url) {
		super(url);
		setButton();
		addButton();
		addTabs();
		addFindButton();
		addTextField();
		iniSet();		//一开始默认A选中
		addListener();
		players = playerInfo.getPlayerProfileByInitial('A', playerType);
		addTable();
		setTable();
	}
	
	public void refresh(){
		setTable();
		AllPlayersPanel.this.repaint();
	}
	
	public void addTable() {
		size = players.size();
		lth = Constants.allPlayerHeaders.length;
		rowData = new String[size][lth];
		table = new BottomTable(rowData, Constants.allPlayerHeaders);
		try {
			table.addMouseListener(new UserMouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(players.get(rowI).getName(), AllPlayersPanel.this);
					}
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SCROLL = new BottomScrollPane(table);
		SCROLL.setBounds(61, 117, 888, 465);
		this.add(SCROLL);
	}
	
	public void setTable() {
		size = players.size();
		lth = Constants.allPlayerHeaders.length;
		rowData = new String[size][lth];
		ArrayList<ImageIcon> iconArr = new ArrayList<ImageIcon>();
		table.setModel(new DefaultTableModel(rowData,Constants.allPlayerHeaders));
		table.setRowHeight(57);
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] { 150, 200, 150, 150, 89, 132 });
		table.setForeground(MyFont.BLACK_GRAY);
		
		for (int i = 0; i < size; i++) {
			PlayerProfilePO ppVO = players.get(i);
			Image protrait = PlayerImageCache.getPortraitByName(ppVO.getName());
			int height = protrait.getHeight(null) * 70 / protrait.getWidth(null);// 按比例，将高度缩减
			Image smallImg = protrait.getScaledInstance(PORTRAIT_WIDTH, height, Image.SCALE_SMOOTH);
			ImageIcon ic = new ImageIcon(smallImg);
			iconArr.add(ic);
			table.setValueAt(ppVO.getShortName(), i, 1);
			table.setValueAt(ppVO.getFromYear(), i, 2);
			table.setValueAt(ppVO.getToYear(), i, 3);
			table.setValueAt(ppVO.getPosition(), i, 4);
			table.setValueAt(Constants.translateDate(ppVO.getBirthDate()), i, 5);
			
		}
		MyTableCellRenderer myRenderer = new MyTableCellRenderer();
		myRenderer.icon = iconArr;
		// iconArr.clear();
		table.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
	}
	
	/**
	 * 添加查询按钮
	 * 
	 * @author lsy
	 * @version 2015年3月20日 下午6:48:07
	 */
	public void addFindButton() {
		queryButton = new ImgButton(SEARCH_BUTTON_OFF, 902, 10, SEARCH_BUTTON_ON, SEARCH_BUTTON_CLICK);
		this.add(queryButton);
		queryButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				players= playerInfo.searchPlayers(queryTextField.getText());
				setTable();
				AllPlayersPanel.this.repaint();
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
		current = initialButtons[0];
		setEffect(current);
//		LetterButton.current = initialButtons[0];
//		setEffect(LetterButton.current);	
	}

	/**
	 * 设置选中效果
	 * 
	 * @param button
	 * @author lsy
	 * @version 2015年3月20日 下午6:50:11
	 */
	public void setEffect(LetterButton button) {
		button.setCurrent();
	}

	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		for (int i = 0; i < 26; i++) {
			initialButtons[i].addMouseListener(mou1);
		}
	}

	class MouListener1 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == current) {
				return;
			}
			current.back();
			current = (LetterButton) e.getSource();
//			LetterButton.current.back();
//			LetterButton.current = (LetterButton) e.getSource();
//			char made = LetterButton.current.letter;
			char made = current.letter;
		    players = playerInfo.getPlayerProfileByInitial(made, playerType);
		    setTable();
		}
		
	}

	public void addTextField() {
		queryTextField = new MyTextField(754, 10, 135, 30);
		this.add(queryTextField);
	}

	public void setButton() {
		for (int i = 0; i < initialButtons.length; i++) {
			initialButtons[i] = new LetterButton(BUTTON_X + i * INTER, BUTTON_Y, WIDTH, HEIGHT, LETTERS[i] + "");
			initialButtons[i].setForeground(Color.WHITE);
		}
	}

	public void addButton() {
		for (int i = 0; i < initialButtons.length; i++) {
			this.add(initialButtons[i]);
			initialButtons[i].letter = LETTERS[i];
		}
	}
	
	private void addTabs() {
		onServiceTab = new TabButton(Constants.playerType[0], 
				Images.SERVICE_TAB_ON, Images.SERVICE_TAB_ON);
		onServiceTab.setOn();
		offServiceTab = new TabButton(Constants.playerType[1], 
				Images.SERVICE_TAB_ON, Images.SERVICE_TAB_ON);
		allTab = new TabButton(Constants.playerType[2], 
				Images.SERVICE_TAB_ON, Images.SERVICE_TAB_ON);
		onServiceTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerType = PlayerType.ON_SERVICE;
				onServiceTab.setOn();
				offServiceTab.setOff();
				allTab.setOff();
				players = playerInfo.getPlayerProfileByInitial(current.letter, playerType);
				refresh();
			}
		});
		offServiceTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerType = PlayerType.OFF_SERVICE;
				onServiceTab.setOff();
				offServiceTab.setOn();
				allTab.setOff();
				players = playerInfo.getPlayerProfileByInitial(current.letter, playerType);
				refresh();
			}
		});
		allTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerType = PlayerType.ALL;
				onServiceTab.setOff();
				offServiceTab.setOff();
				allTab.setOn();
				players = playerInfo.getPlayerProfileByInitial(current.letter, playerType);
				refresh();
			}
		});
		onServiceTab.setLocation(25,0);
		add(onServiceTab);
		offServiceTab.setLocation(215,0);
		add(offServiceTab);
		allTab.setLocation(405,0);
		add(allTab);
		
	}
	

}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		return (JComponent) value;
	}
}

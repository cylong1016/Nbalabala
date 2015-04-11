package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.common.textField.MyTextField;
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
	private static final int BUTTON_X = 60, BUTTON_Y = 55, INTER = 33, WIDTH = 33, HEIGHT = 37;
	private static final char[] LETTERS = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final String IMG_PATH = UIConfig.IMG_PATH + "players/";
	private static final String SEARCH_BUTTON_OFF = IMG_PATH + "search.png";
	private static final String SEARCH_BUTTON_ON = IMG_PATH + "searchOn.png";
	private static final String SEARCH_BUTTON_CLICK = IMG_PATH + "searchClick.png";
	
	/** 所有首字母的button */
	private LetterButton[] initialButtons = new LetterButton[26];
	/** 查询按钮 */
	private ImgButton queryButton;
	/** 查询的文本框 */
	private MyTextField queryTextField;
	
	private PlayerQueryBLService playerInfo = new PlayerQuery();
	/** 球员信息界面 */
	PlayerTable playerTable;
	
	/**
	 * @param url 背景图片的Url
	 */
	public AllPlayersPanel(String url) {
		super(url);
		setButton();
		addButton();
		addFindButton();
		addTextField();
		iniSet();		//一开始默认A选中
		addListener();
		ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.getPlayerProfileByInitial('A');
		playerTable  = new PlayerTable(playerInfoArr,this);
	}
	
	public void refresh(){
		ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.getPlayerProfileByInitial('A');
		this.remove(PlayerTable.SCROLL); 
		playerTable = new PlayerTable(playerInfoArr,this);
		AllPlayersPanel.this.repaint();
	}
	
	/**
	 * 添加查询按钮
	 * 
	 * @author lsy
	 * @version 2015年3月20日 下午6:48:07
	 */
	public void addFindButton() {
		queryButton = new ImgButton(SEARCH_BUTTON_OFF, 902, 15, SEARCH_BUTTON_ON, SEARCH_BUTTON_CLICK);
		this.add(queryButton);
		queryButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.searchPlayers(queryTextField.getText());
				AllPlayersPanel.this.remove(PlayerTable.SCROLL);
				playerTable = new PlayerTable(playerInfoArr,AllPlayersPanel.this);
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
		LetterButton.current = (LetterButton) initialButtons[0];
		setEffect(initialButtons[0]);	
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
		button.setBackground(LetterButton.LETTER_BG);
		button.setForeground(Color.white);
	}

	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		for (int i = 0; i < 26; i++) {
			initialButtons[i].addMouseListener(mou1);
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
			AllPlayersPanel.this.remove(PlayerTable.SCROLL);
			playerTable  = new PlayerTable(vo,AllPlayersPanel.this);
		}
		
	}

	public void addTextField() {
		queryTextField = new MyTextField(754, 17, 135, 30);
		this.add(queryTextField);
	}

	public void setButton() {
		for (int i = 0; i < initialButtons.length; i++) {
			initialButtons[i] = new LetterButton(BUTTON_X + i * INTER, BUTTON_Y, WIDTH, HEIGHT, LETTERS[i] + "");
			initialButtons[i].setForeground(LetterButton.LETTER_COLOR);
		}
	}

	public void addButton() {
		for (int i = 0; i < initialButtons.length; i++) {
			this.add(initialButtons[i]);
			initialButtons[i].letter = LETTERS[i];
		}
	}

}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		return (JComponent) value;
	}
}

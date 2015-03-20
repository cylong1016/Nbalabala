package ui.panel.allplayers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.common.textField.MyTextField;
import ui.controller.MainController;
import vo.PlayerProfileVO;
import bl.playerbl.PlayerQuery;
import blservice.PlayerQueryBLService;

/**
 * 全部球员信息主界面
 * @author cylong
 * @version 2015年3月19日 上午3:19:47
 */
public class AllPlayersPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2951291212735567656L;

	/** 按钮的横纵坐标 间隔 宽度 高度 */
	private int x = 60,y=60,inter=33,width=33,height=32;
	/** 所有字母的button */
	LetterButton [] buttonArr = new LetterButton[26];
	char[] letter = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
			'Q','R','S','T','U','V','W','X','Y','Z'};
	
	Color letterbg = new Color(51,66,84,130);
	Color letterColor= new Color(38,41,46);
	/** 查询按钮 */
	private ImgButton findButton;
	private String imgURL = UIConfig.IMG_PATH+"players/";
	/** 查询的文本框 */
	private MyTextField field;
	
	PlayerQueryBLService playerInfo = new PlayerQuery();
	
	/**
	 * @param url 背景图片的Url
	 */
	public AllPlayersPanel(MainController controller,String url) {
		super(controller, url);
		setButton();
		addButton();
		addFindButton();
		addTextField();
		setEffect(buttonArr[0]);
		iniSet();
		addListener();
		ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.getPlayerProfileByInitial('A');
		//TODO 将A开头的球员信息显示出来
	}
	
	/**
	 * 添加查询按钮
	 * @author lsy
	 * @version 2015年3月20日  下午6:48:07
	 */
	public void addFindButton(){
		findButton = new ImgButton(imgURL+"search.png",902,15,imgURL+"searchOn.png",imgURL+"searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				ArrayList<PlayerProfileVO> playerInfoArr = playerInfo.getPlayerProfileByInitial('A');
				//TODO 将查询到的球员信息显示出来
			}
		});
	}
	
	/**
	 * 将A设置为选中状态
	 * @author lsy
	 * @version 2015年3月20日  下午6:48:14
	 */
	public void iniSet() {
		LetterButton.current = (LetterButton)buttonArr[0];
	}
	
	/**
	 * 设置选中效果
	 * @param button
	 * @author lsy
	 * @version 2015年3月20日  下午6:50:11
	 */
	public void setEffect(LetterButton button) {
			button.setOpaque(true);
			button.setBackground(letterbg);
			button.setForeground(Color.white);
	}
	
	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		for(int i = 0; i < 26; i++) {
			buttonArr[i].addMouseListener(mou1);
		}
	}

	class MouListener1 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == LetterButton.current) {
				return;
			}
			LetterButton.current.back();
			LetterButton.current = (LetterButton)e.getSource();
		}
	}
	
	public void addTextField(){
		field = new MyTextField(754,17,135,30);
		this.add(field);
	}
	
	public void setButton(){
		for(int i = 0;i<buttonArr.length;i++){
			buttonArr[i] = new LetterButton(x+i*inter,y,width,height,letter[i]+"");
			buttonArr[i].setForeground(letterColor);
		}
	}
	
	public void addButton(){
		for(int i = 0;i<buttonArr.length;i++){
			this.add(buttonArr[i]);
			buttonArr[i].letter = letter[i];
		}
	}

}

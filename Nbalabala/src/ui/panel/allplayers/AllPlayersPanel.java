package ui.panel.allplayers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.common.textField.MyTextField;
import ui.controller.MainController;

/**
 * 全部球员信息主界面
 * @author cylong
 * @version 2015年3月19日 上午3:19:47
 */
public class AllPlayersPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2951291212735567656L;

	/** 按钮的横纵坐标 间隔 宽度 高度 */
	private int x = 60,y=60,inter=33,width=50,height=27;
	/** 所有字母的button */
	LetterButton [] buttonArr = new LetterButton[26];
	String [] letterArr = new String[]{"A","B","C","D","E","F","G","H","I","J","K",
			"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	Color letterbg = new Color(51,66,84,130);
	Color letterColor= new Color(38,41,46);
	/** 查询按钮 */
	private ImgButton findButton;
	private String imgURL = UIConfig.IMG_PATH+"players/";
	/** 查询的文本框 */
	private MyTextField field;
	
	/**
	 * @param url 背景图片的Url
	 */
	public AllPlayersPanel(MainController controller,String url) {
		super(controller, url);
		setButton();
		addButton();
		addFindButton();
	}
	
	public void addFindButton(){
		findButton = new ImgButton(imgURL+"search.png",902,15,imgURL+"searchOn.png",imgURL+"searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
//				ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
				//TODO changeTable
			}
		});
	}
	
	public void setButton(){
		for(int i = 0;i<buttonArr.length;i++){
			buttonArr[i] = new LetterButton(x+i*inter,y,width,height,letterArr[i]);
			buttonArr[i].setForeground(letterColor);
		}
	}
	
	public void addButton(){
		for(int i = 0;i<buttonArr.length;i++){
			this.add(buttonArr[i]);
		}
	}

}

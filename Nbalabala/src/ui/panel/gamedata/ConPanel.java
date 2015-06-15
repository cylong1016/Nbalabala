package ui.panel.gamedata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import ui.Images;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.MyLabel;
import ui.common.label.Rec;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;

/**
 * 对比panel
 * 
 * @author lsy
 * @version 2015年5月17日 下午1:10:29
 */
public class ConPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 3446121355783155791L;
	private MyLabel[] sort,result1,result2;
	private Rec[] rec1, rec2;
	private double[] lth1,lth2;
	private DecimalFormat df = new DecimalFormat(".0");
	
	public ConPanel(MatchDetailVO vo) {
		super(Images.GAME_CON);
		this.setBounds(22, 292, 952, 309);
		lth1 = vo.getHomeFiveArgs();
		lth2 = vo.getRoadFiveArgs();
		addLabel();
		addRec();
		this.updateUI();
	}

	public ConPanel(double[] lth1,double[] lth2){
		super(Images.GAME_CON);
		this.lth1 = lth1;
		this.lth2 = lth2;
		this.setBounds(UIConfig.RELA_X-4, 250, 948, 349);
		addLabel();
		addRec();
//		addBack();
		this.updateUI();
		this.repaint();
	}
	
	private ImgButton back;
	public void addBack() {
		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 70, 150, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.toLiveInPanel();
			}

		});
	}
	
	private int lb_x = 414, lb_y = 60, lb_x2 = 522, lb_inter = 50, lb_width = 105, lb_height = 40;

	public void addLabel() {
		sort = new MyLabel[5];
		for (int i = 0; i < 5; i++) {
			sort[i] = new MyLabel(lb_x, lb_y + i * lb_inter, lb_width, lb_height, Constants.GAME_SORT[i]);
			this.add(sort[i]);
		}

	}

	public void addRec() {
		rec1 = new Rec[5];
		rec2 = new Rec[5];
		result1 = new MyLabel[5];
		result2 = new MyLabel[5];
		for (int i = 0; i < 3; i++) {
			lth1[i] = Double.parseDouble(df.format(lth1[i]*100));
			rec1[i] = new Rec((int) (lb_x - 4 * lth1[i]), lb_y + i * lb_inter, lth1[i]);
			result1[i] = new MyLabel((int)(lb_x - 4 * lth1[i] - 40),lb_y + i * lb_inter,40,40,lth1[i]+"%");
			this.add(rec1[i]);
			this.add(result1[i]);
		}
		for (int i = 0; i < 3; i++) {
			lth2[i] = Double.parseDouble(df.format(lth2[i]*100));
			rec2[i] = new Rec(lb_x2, lb_y + i * lb_inter, lth2[i]);
			this.add(rec2[i]);
			result2[i] = new MyLabel((int)(lb_x2+4*lth2[i]),lb_y + i * lb_inter,40,40,lth2[i]+"%");
			this.add(result2[i]);
		}
		
		for(int i = 3; i < 5; i++){
			double temp1 = lth1[i];
			double temp2 = lth2[i];
			if(lth1[i] > 79.9) {
				lth1[i] = lth1[i] / 2;
				lth2[i] = lth2[i] / 2;
			}
			rec1[i] = new Rec((int) (lb_x - 4 * lth1[i]), lb_y + i * lb_inter, lth1[i]);
			result1[i] = new MyLabel((int)(lb_x - 4 * lth1[i] - 40),lb_y + i * lb_inter,40,40,(int)temp1+"");
			this.add(rec1[i]);
			this.add(result1[i]);
			rec2[i] = new Rec(lb_x2, lb_y + i * lb_inter, lth2[i]);
			this.add(rec2[i]);
			result2[i] = new MyLabel((int)(lb_x2+4*lth2[i]),lb_y + i * lb_inter,40,40,(int)temp2+"");
			this.add(result2[i]);
		}
		
		for(int i = 0 ;i < 5 ;i++){
			setColor(rec1[i],rec2[i]);
		}
	}
	
	public void setColor(Rec lb1, Rec lb2){
		if(lb1.getLength() > lb2.getLength()) {
			lb1.setBackground(UIConfig.BLUE_TEXT_COLOR);
			lb2.setBackground(Color.gray);
		}else {
			lb2.setBackground(UIConfig.BLUE_TEXT_COLOR);
			lb1.setBackground(Color.gray);
		}
	}

}

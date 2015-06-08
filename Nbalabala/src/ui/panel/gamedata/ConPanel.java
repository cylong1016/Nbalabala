package ui.panel.gamedata;

import java.awt.Color;
import java.text.DecimalFormat;

import ui.Images;
import ui.common.label.MyLabel;
import ui.common.label.Rec;
import ui.common.panel.BottomPanel;
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
	private MatchDetailVO vo;
	
	private DecimalFormat df = new DecimalFormat(".0");
	
	public ConPanel(MatchDetailVO vo) {
		super(Images.GAME_CON);
		this.vo = vo;
		this.setBounds(22, 292, 952, 309);
		addLabel();
		addRec();
	}

	private int lb_x = 416, lb_y = 20, lb_x2 = 536, lb_inter = 50, lb_width = 105, lb_height = 40;

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
		double[] lth1 = vo.getHomeFiveArgs();
		double[] lth2 = vo.getRoadFiveArgs();
		for (int i = 0; i < 3; i++) {
			lth1[i] = Double.parseDouble(df.format(lth1[i]));
			rec1[i] = new Rec((int) (lb_x - 4 * lth1[i]), lb_y + i * lb_inter, lth1[i]);
			result1[i] = new MyLabel((int)(lb_x - 4 * lth1[i] - 40),lb_y + i * lb_inter,40,40,lth1[i]+"");
			this.add(rec1[i]);
			this.add(result1[i]);
		}
		for (int i = 0; i < 3; i++) {
			lth2[i] = Double.parseDouble(df.format(lth2[i]));
			rec2[i] = new Rec(lb_x2, lb_y + i * lb_inter, lth2[i]);
			this.add(rec2[i]);
			result2[i] = new MyLabel((int)(lb_x2+4*lth2[i]),lb_y + i * lb_inter,40,40,lth2[i]+"");
			this.add(result2[i]);
		}
		
		for(int i = 3; i < 5; i++){
			rec1[i] = new Rec((int) (lb_x - 4 * lth1[i]), lb_y + i * lb_inter, lth1[i]);
			result1[i] = new MyLabel((int)(lb_x - 4 * lth1[i] - 40),lb_y + i * lb_inter,40,40,(int)lth1[i]+"");
			this.add(rec1[i]);
			this.add(result1[i]);
		}
		for (int i = 3; i < 5; i++) {
			rec2[i] = new Rec(lb_x2, lb_y + i * lb_inter, lth2[i]);
			this.add(rec2[i]);
			result2[i] = new MyLabel((int)(lb_x2+4*lth2[i]),lb_y + i * lb_inter,40,40,(int)lth2[i]+"");
			this.add(result2[i]);
		}
		
		
		for(int i = 0 ;i < 5 ;i++){
			setColor(rec1[i],rec2[i]);
		}
	}
	
	public void setColor(Rec lb1, Rec lb2){
		if(lb1.getLength() > lb2.getLength()) {
			lb2.setBackground(Color.gray);
		}else {
			lb1.setBackground(Color.gray);
		}
	}

}

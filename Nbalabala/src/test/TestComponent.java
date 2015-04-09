package test;

import javax.swing.JFrame;

import data.playerdata.PlayerImageCache;
import ui.common.label.HotSeasonPlayerLabel;
import vo.HotSeasonPlayerVO;
import enums.HotSeasonPlayerProperty;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月9日  下午5:53:18
 */
public class TestComponent {
	
	public static void main(String[]args){
//		JFrame frame = new JFrame();
//		frame.setLayout(null);
//		HotSeasonPlayerVO vo = new HotSeasonPlayerVO(1, "Alexey Shved", "CHA", "G-F", 12.1219);
//		HotSeasonPlayerLabel label = new HotSeasonPlayerLabel(vo, HotSeasonPlayerProperty.ASSIST_AVG);
//		frame.getContentPane().add(label);
//		frame.setVisible(true);
		
		new PlayerImageCache().loadPortrait();
		JFrame frame = new JFrame();
		frame.setLayout(null);
		HotSeasonPlayerVO vo = new HotSeasonPlayerVO(2, "Chris Douglas-Roberts", "CHA", "G-F", 12.1219);
		HotSeasonPlayerLabel label = new HotSeasonPlayerLabel(vo, HotSeasonPlayerProperty.THREE_POINT_PERCENT);
		frame.getContentPane().add(label);
		frame.setVisible(true);
	}

}

package ui.common.label;

import javax.swing.JLabel;

/**
 * 热点赛季球员和热点赛季球队的label父类
 * @author Issac Ding
 * @version 2015年4月9日  下午4:54:42
 */
public class HotSeasonLabel extends JLabel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -8366400269479677395L;

	public HotSeasonLabel(int top) {
		setOpaque(false);
		setLayout(null);
		switch (top) {
		case 1:
			setBounds(95, 120, 402, 307);
			break;
		case 2:
			setBounds(95, 438, 195, 125);
			break;
		case 3:
			setBounds(303, 438, 195, 125);
			break;
		case 4:
			setBounds(510, 438, 195, 125);
			break;
		default:
			setBounds(716, 438, 195, 125);
			break;
		}
	}
	
}

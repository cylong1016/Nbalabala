package ui.panel.analyse.button;

import ui.common.button.TextButton;
import enums.CareerData;

/**
 * 全部赛季选择条件按钮
 * @author lsy
 * @version 2015年6月16日  上午10:46:07
 */
public class SeasonSelectButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -1625714034916406638L;

	/** 代表当前选中的按钮 */
	public static SeasonSelectButton current;
	/** 按钮上的字 */
	private String text;
	/** 枚举类型 */
	private CareerData careerData;
	
	public SeasonSelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}

	public CareerData getInferenceData() {
		return careerData;
	}
	
	public void setInferenceData(CareerData inferenceData) {
		this.careerData = inferenceData;
	}
	
	public String getText(){
		return text;
	}

}

package ui.panel.analyse.button;

import ui.common.button.TextButton;
import enums.InferenceData;

/**
 * 球员对比界面的按钮
 * @author lsy
 * @version 2015年6月16日  下午9:04:48
 */
public class CompareButton extends TextButton{

	
	/** serialVersionUID */
	private static final long serialVersionUID = -7229571467685610601L;

	/** 代表当前选中的按钮 */
	public static CompareButton current;
	/** 按钮上的字 */
	private String text;
	/** 枚举类型 */
	private InferenceData inferenceData;
	
	public CompareButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public InferenceData getInferenceData() {
		return inferenceData;
	}
	
	public void setInferenceData(InferenceData inferenceData) {
		this.inferenceData = inferenceData;
	}

}

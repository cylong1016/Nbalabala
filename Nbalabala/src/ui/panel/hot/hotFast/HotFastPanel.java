package ui.panel.hot.hotFast;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.label.HotFastestPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotFastestPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotFastestPlayerProperty;

/**
 * 进步最快球员界面
 * @author lsy
 * @version 2015年4月11日  下午4:04:49
 */
public class HotFastPanel extends HotThreeFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 2077718178589844072L;

	ArrayList<HotFastestPlayerVO> fastVO;
	private static final HotFastestPlayerProperty[] HOT_FAST_ARRAY = HotFastestPlayerProperty.values();
	HotBLService hot = new HotQuery();
	HotFastestPlayerLabel label[] = new HotFastestPlayerLabel[5];
	
	public HotFastPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
	}
	
	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:11
	 */
	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].fast = HOT_FAST_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						HotFastPanel.this.remove(label[i]);
					}
					addLabel();
				}

			});
		}
	}

	/**
	 * 添加每个球员显示的label
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:19
	 */
	public void addLabel() {
		fastVO = hot.getHotFastestPlayers(ThreeButton.current.fast);
		for (int j = 0; j < 5; j++) {
			label[j] = new HotFastestPlayerLabel(fastVO.get(j));
			this.add(label[j]);
		}
		this.repaint();
	}

}

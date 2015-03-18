package utility;

public class Sleep {

	/**
	 * 封装一下Thread.sleep
	 * @param time 毫秒
	 * @author cylong
	 * @version 2015年3月18日 下午12:01:41
	 */
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

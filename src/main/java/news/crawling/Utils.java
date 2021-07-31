package news.crawling;

public class Utils {
	public static String numberling(String aid) {
		int temp = Integer.parseInt(aid);
		temp++;
		aid = String.format("%010d", temp);
		return aid;
	}
}

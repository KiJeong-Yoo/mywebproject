package vote.action;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {
	public static int parseInt(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
}

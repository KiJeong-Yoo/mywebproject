package vote.vo;

public class PollListVo {
	private int num;
	private String question;
	private String sdate;
	private String edate;
	private int type;
	private int active;
	
	public PollListVo(String question2, int type2, String sdate2, String edate2) {
		this.question = question2;
		this.type = type2;
		this.sdate = sdate2;
		this.edate = edate2;
	}
	
	public PollListVo() {
		// TODO Auto-generated constructor stub
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	
}

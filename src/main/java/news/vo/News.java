package news.vo;

import java.sql.Date;

public class News {
	private int id;
	private String address;
	private String title;
	private String content;
	private Date date;
	private String aid;
	private String img;
	private int reacount;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public int getReacount() {
		return reacount;
	}

	public void setReacount(int reacount) {
		this.reacount = reacount;
	}

	public News() {
	}
	
	public News(String address, String title, String content, Date date, String aid, String img) {
		this.address = address;
		this.title = title;
		this.content = content;
		this.date = date;
		this.aid = aid;
		this.img = img;
	}
	
	public News(String address, String title, String content, Date date, String aid, String img, int readcount) {
		this.address = address;
		this.title = title;
		this.content = content;
		this.date = date;
		this.aid = aid;
		this.img = img;
		this.reacount = readcount;
	}

	
	
}

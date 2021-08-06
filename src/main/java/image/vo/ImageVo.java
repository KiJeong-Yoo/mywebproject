package image.vo;

import java.sql.Date;

public class ImageVo {
	int idx;
	String title;
	String content;
	String writeId;
	Date writeDay;
	int readcount;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getWriteId() {
		return writeId;
	}
	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}
	public Date getWriteDay() {
		return writeDay;
	}
	public void setWriteDay(Date writeDay) {
		this.writeDay = writeDay;
	}
	
	
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public ImageVo() {
		// TODO Auto-generated constructor stub
	}
}

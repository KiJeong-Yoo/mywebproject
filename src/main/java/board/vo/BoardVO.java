package board.vo;

import java.util.Date;

public class BoardVO {
	int boardid; // 게시판 구분 번호
	int idx; // 게시글 번호
	String title; // 게시글 제목
	String content; // 게시글 내용
	int readcount; // 게시글 조회수
	int groupid; // 게시글과 답글을 묶어줄 번호
	int depth; // 댓글 순서
	int reOrder; // 댓글 갯수
	int isdel; // 삭제 여부
	String writeId; // 작성자 아이디
	String writeName; // 작성자 닉네임
	Date writeDay; // 작성일
	
	public BoardVO() {}

	public BoardVO(String title, String content, String writeId, String writeName) {
		this.title = title;
		this.content = content;
		this.writeId = writeId;
		this.writeName = writeName;
	}
	
	public BoardVO(int boardid, String title, String content, String writeId, String writeName) {
		this.boardid = boardid;
		this.title = title;
		this.content = content;
		this.writeId = writeId;
		this.writeName = writeName;
	}
	
	public BoardVO(int boardid, int idx, String title, String content, int readcount, int groupid, int depth,
			int reOrder, int isdel, String writeId, String writeName, Date writeDay) {
		this.boardid = boardid;
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.groupid = groupid;
		this.depth = depth;
		this.reOrder = reOrder;
		this.isdel = isdel;
		this.writeId = writeId;
		this.writeName = writeName;
		this.writeDay = writeDay;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

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

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getReOrder() {
		return reOrder;
	}

	public void setReOrder(int reOrder) {
		this.reOrder = reOrder;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}

	public String getWriteName() {
		return writeName;
	}

	public void setWriteName(String writeName) {
		this.writeName = writeName;
	}

	public Date getWriteDay() {
		return writeDay;
	}

	public void setWriteDay(Date writeDay) {
		this.writeDay = writeDay;
	}

	@Override
	public String toString() {
		return "BoardVO [boardid=" + boardid + ", idx=" + idx + ", title=" + title + ", content=" + content
				+ ", readcount=" + readcount + ", groupid=" + groupid + ", depth=" + depth + ", reOrder=" + reOrder
				+ ", isdel=" + isdel + ", writeId=" + writeId + ", writeName=" + writeName + ", writeDay=" + writeDay
				+ "]";
	}	
}

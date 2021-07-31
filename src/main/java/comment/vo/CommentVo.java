package comment.vo;

import java.sql.Date;

public class CommentVo {
	private int commentidx; // 댓글 idx
	private int pidx; // 부모 참조
	private String writeid; // 댓글 작성자
	private Date writedate; // 댓글작성일지
	private String content; // 댓글 내용
	private int groupid;
	private int depth;
	private int reOrder;
	private int isdel;
	
	
	public CommentVo() {}

	public CommentVo(String c_content, int parseInt, String cwriteid) {
		this.content = c_content;
		this.pidx = parseInt;
		this.writeid = cwriteid;
	}

	public CommentVo(int parseInt, String content2, int parseInt2, int parseInt3, int parseInt4, String writeid2) {
		this.content = content2;
		this.pidx = parseInt;
		this.groupid = parseInt2;
		this.depth = parseInt3;
		this.reOrder = parseInt4;
		this.writeid = writeid2;
	}

	public CommentVo(int parseInt, int parseInt2, String content2, int parseInt3, int i, int j, String writeid2) {
		this.commentidx = parseInt;
		this.pidx = parseInt2;
		this.content = content2;
		this.groupid = parseInt3;
		this.depth = i;
		this.reOrder = j;
		this.writeid = writeid2;
	}

	public int getCommentidx() {
		return commentidx;
	}

	public void setCommentidx(int commentidx) {
		this.commentidx = commentidx;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public String getWriteid() {
		return writeid;
	}

	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

//	@Override
//	public String toString() {
//		return "CommentVo [commentidx=" + commentidx + ", pidx=" + pidx + ", writeid=" + writeid + ", writedate="
//				+ writedate + ", content=" + content + ", groupid=" + groupid + ", depth=" + depth + ", reOrder="
//				+ reOrder + ", isdel=" + isdel + "]";
//	}

	

}

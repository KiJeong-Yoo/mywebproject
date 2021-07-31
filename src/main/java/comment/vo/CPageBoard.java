package comment.vo;

import java.util.Collections;
import java.util.List;


public class CPageBoard {
	private List<CommentVo> list;
	private int pidx;
	private int requestPage;
	private int totalCount;
	private int totalPage;
	private int firstRow;	
	private int endRow;
	private int beginPage;
	private int endPage;
	
	public CPageBoard() {
		this(Collections.<CommentVo> emptyList(), 0, 0, 0, 0, 0, 0, 0);
	}

	public CPageBoard(List<CommentVo> list, int requestPage, int totalCount, int totalPage, int startRow, int endRow,int beginPage,int endPage) {
		this.list = list;
		this.requestPage = requestPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.firstRow = startRow;
		this.endRow = endRow;
		this.beginPage = beginPage;
		this.endPage = endPage;
	}
	
	public CPageBoard(List<CommentVo> list, int pidx, int requestPage, int totalCount, int totalPage, int startRow, int endRow,int beginPage,int endPage) {
		this.list = list;
		this.pidx = pidx;
		this.requestPage = requestPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.firstRow = startRow;
		this.endRow = endRow;
		this.beginPage = beginPage;
		this.endPage = endPage;
	}
	
	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public List<CommentVo> getList() {
		return list;
	}

	public void setList(List<CommentVo> list) {
		this.list = list;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isHasBoard() {
		return !list.isEmpty();
	}
}

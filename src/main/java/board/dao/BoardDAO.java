package board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import board.vo.BoardVO;
import board.vo.PageBoard;
import debate.inter.DebateInter;
import free.inter.FreeInter;

@Repository
public class BoardDAO implements DebateInter, FreeInter {
	@Autowired
	DriverManagerDataSource ds;
	
	public BoardDAO() {
		
	}
	
	public int insert(BoardVO board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;
		try {
//			sql="insert into board values(?, board_idx_seq.nextval, ?, ?, 0, board_groupid_seq.nextval, 0, 0, 0, ?, ?, sysdate)";
			sql="insert into board values(?, (select nvl(max(idx), 0) + 1 from board), ?, ?, 0, (select nvl(max(groupid), 0) + 1 from board), 0, 0, 0, ?, ?, sysdate)";
//			sql="insert into board values(?, (select nvl(max(idx), 0) + 1 from board), ?, ?, 0, b_groupid_seq.nextval, 0, 0, 0, ?, ?, sysdate)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, board.getBoardid());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWriteId()); //id
			pstmt.setString(5, board.getWriteName()); //name
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("sql 입력 성공");
			}else {
				System.out.println("sql 입력 실패");
			}

			
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	public PageBoard list(int requestPage, int boardid) {
		
		PageBoard pageboard = null; 
				
		int totalPage = 0; // 전체 페이지 = 전체 글 수 / 페이지당 표시 글 수   
		int beginPage = 0; // 시작 페이지 - 요청 페이지 기준으로 표시될 페이지 시작 번호
		int endPage = 0;   // 끝 페이지 - 요청 페이지 기준으로 표시될 페이지 마지막 번호
		int firstRow = 0;   // 시작 글번호 - 요청 페이지 기준
		int endRow = 0;     // 끝 글번호 - 요청 페이지 기준
		int articleCount = 0; // 전체 글 수
		int countPerPage = 10; // 페이지 당 표시될 글의 수
		
		List<BoardVO> list = new ArrayList<BoardVO>(); // 페이지에 대한 10개의 글 리스트
		
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//1. 전체 게시물 수 구하기
		sql = "select count(*) from board where boardid=?";
		pstmt = ds.getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardid);
		rs = pstmt.executeQuery();
		
		if(rs.next())
			articleCount = rs.getInt(1); 
		else 
			articleCount = 0;
		
		//2. 전체 페이지 수
		totalPage = articleCount / countPerPage; // 소수 제외
		if(articleCount % countPerPage > 0) // 나머지 글의 수 파악 후 페이지 추가
			totalPage++;


		//3. 요청한 페이지에 대한 시작 글번호, 끝 글번호
		firstRow = (requestPage - 1) * countPerPage + 1;
		endRow = firstRow + countPerPage - 1;


		//4.시작 페이지 번호, 끝 페이지 번호
		// 5페이지 기준 if문들 조건 순서 중요
		beginPage = ((requestPage - 1) / 5) * 5 + 1;
		endPage = beginPage + 4;
		
		if(beginPage < 1)
			beginPage = 1;
		
		if(endPage > totalPage) 
			endPage = totalPage;		
		
		sql = "select idx, title, content, readcount, groupid, depth, re_order, isdel, write_id, write_name, write_day from (select rownum rnum, idx, title, content, readcount, groupid, depth, re_order, isdel, write_id, write_name, write_day from (select * from board a where boardid=? order by a.groupid desc, a.re_order asc) where rownum <= ?) where rnum >= ?"; 
		
		pstmt = ds.getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardid);
		pstmt.setInt(2, endRow); 
		pstmt.setInt(3, firstRow);
		rs = pstmt.executeQuery();
				
		while(rs.next()) {
			BoardVO board=new BoardVO();
			board.setIdx(rs.getInt("idx"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setReadcount(rs.getInt("readcount"));
			board.setGroupid(rs.getInt("groupid"));
			board.setDepth(rs.getInt("depth"));
			board.setReOrder(rs.getInt("re_order"));
			board.setIsdel(rs.getInt("isdel"));
			board.setWriteId(rs.getString("write_id"));
			board.setWriteName(rs.getString("write_name"));
			board.setWriteDay(rs.getDate("write_day"));
			list.add(board);
		}
		
		pageboard = new PageBoard(list, requestPage, articleCount, totalPage, firstRow, endRow, beginPage, endPage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pageboard;
	}

	public BoardVO select(int idx, int boardid) {
		BoardVO board = null;
		String sql = "select * from board where idx=? and boardid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, boardid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setBoardid(rs.getInt("boardid"));
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setGroupid(rs.getInt("groupid"));
				board.setDepth(rs.getInt("depth"));
				board.setReOrder(rs.getInt("re_order"));
				board.setIsdel(rs.getInt("isdel"));
				board.setWriteId(rs.getString("write_id"));
				board.setWriteName(rs.getString("write_name"));
				board.setWriteDay(rs.getDate("write_day"));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	public int delete(int idx, int boardid) {
		int result = 0;
		int commentResult = commentDelete(idx);
		
		if(commentResult == 1) {			
			String sql = "delete from board where idx=? and boardid=?";
			PreparedStatement pstmt = null;
			
			try{
				pstmt = ds.getConnection().prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.setInt(2, boardid);
				result=pstmt.executeUpdate();
			} catch(Exception e) { 
				e.printStackTrace();
			}
		} else {
			
		}
		return result;
	}
	
	private int commentDelete(int idx) {
		int result = 0;
		
		String sql = "delete from comment_board where pidx=?";
		PreparedStatement pstmt = null;
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
			System.out.println("댓글 없음.");
			return 1;
		}

		return result;
		
	}

	public int update(int idx, String title, String content) {
		int result = 0;
		String sql = "update board set title=?,content=? where idx=?";
		PreparedStatement pstmt=null;
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
			return result;
	}
	
	public int replyInsert(BoardVO board) {
		// 부모글 존재 여부 확인
		if(!parentCheck(board.getIdx())) {
			System.out.println("부모글 확인 실패");
			return 0;
		}
		
		// 같은 그룹 다른 댓글에 대해 depth 1 증가
		System.out.println("replyinsert groupid : " + board.getGroupid());
		reply_before_update(board.getGroupid(), board.getReOrder()-1);
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;
		try {
			sql = "insert into board values(?, (select nvl(max(idx), 0) + 1 from board), ?, ?, 0, ?, ?, ?, 0, ?, ?, sysdate)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, board.getBoardid());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getGroupid());
			System.out.println("replyinsert groupid2 : " + board.getGroupid());
			pstmt.setInt(5, board.getDepth());
			pstmt.setInt(6, board.getReOrder());
			pstmt.setString(7, board.getWriteId()); //id
			pstmt.setString(8, board.getWriteName()); //name
			
			result = pstmt.executeUpdate();
			
//			if(result > 0)
//				System.out.println("sql 댓글 입력 성공");
//			else
//				System.out.println("sql 댓글 입력 실패");
			
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	// 부모 글 존재 확인 함수
	public boolean parentCheck(int idx) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select * from board where idx=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//리플의 depth 증가 함수
	public void reply_before_update(int groupid, int reOrder) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update board set re_order=re_order+1 where groupid=? and re_order>?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, groupid);
			pstmt.setInt(2, reOrder);
			result = pstmt.executeUpdate();
			if(result < 1)
				System.out.println("reply 업데이트 실패");
			else
				System.out.println("reply 전 업데이트 했음.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int readcountUpdate(int idx) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update board set readcount=readcount+1 where idx=?";
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}

	public PageBoard searchlist(String field, String search, int requestPage, int boardid) {
		PageBoard pageboard = null; 
		
		int totalPage = 0; // 전체 페이지 = 전체 글 수 / 페이지당 표시 글 수   
		int beginPage = 0; // 시작 페이지 - 요청 페이지 기준으로 표시될 페이지 시작 번호
		int endPage = 0;   // 끝 페이지 - 요청 페이지 기준으로 표시될 페이지 마지막 번호
		int firstRow = 0;   // 시작 글번호 - 요청 페이지 기준
		int endRow = 0;     // 끝 글번호 - 요청 페이지 기준
		int articleCount = 0; // 전체 글 수
		int countPerPage = 10; // 페이지 당 표시될 글의 수
		
		List<BoardVO> list = new ArrayList<BoardVO>(); // 페이지에 대한 10개의 글 리스트
		
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//1. 전체 게시물 수 구하기
		sql = "select count(*) from board where " + field + " like" + " '%" + search + "%' and boardid=?";
		pstmt = ds.getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardid);
		rs = pstmt.executeQuery();
		
		if(rs.next())
			articleCount = rs.getInt(1); 
		else 
			articleCount = 0;
		
		//2. 전체 페이지 수
		totalPage = articleCount / countPerPage; // 소수 제외
		if(articleCount % countPerPage > 0) // 나머지 글의 수 파악 후 페이지 추가
			totalPage++;

		//3. 요청한 페이지에 대한 시작 글번호, 끝 글번호
		firstRow = (requestPage - 1) * countPerPage + 1;
		endRow = firstRow + countPerPage - 1;

		//4.시작 페이지 번호, 끝 페이지 번호
		// 다섯 페이지 기준 if문들 조건 순서 중요
		beginPage = ((requestPage - 1) / 5) * 5 + 1;
		endPage = beginPage + 4;
		
		if(beginPage < 1)
			beginPage = 1;
		
		if(endPage > totalPage) 
			endPage = totalPage;		
		
		sql = "select idx, title, content, readcount, groupid, depth, re_order, isdel, write_id, write_name, write_day from (select rownum rnum, idx, title, content, readcount, groupid, depth, re_order, isdel, write_id, write_name, write_day from(select * from board a where " + field + " like" + " '%" + search + "%' and boardid=? order by a.groupid desc, a.re_order asc) where rownum <= ?) where rnum >= ?"; 
		
		pstmt = ds.getConnection().prepareStatement(sql);
		pstmt.setInt(1, boardid);
		pstmt.setInt(2, endRow); 
		pstmt.setInt(3, firstRow);
		rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			BoardVO board=new BoardVO();
			board.setIdx(rs.getInt("idx"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setReadcount(rs.getInt("readcount"));
			board.setGroupid(rs.getInt("groupid"));
			board.setDepth(rs.getInt("depth"));
			board.setReOrder(rs.getInt("re_order"));
			board.setIsdel(rs.getInt("isdel"));
			board.setWriteId(rs.getString("write_id"));
			board.setWriteName(rs.getString("write_name"));
			board.setWriteDay(rs.getDate("write_day"));
			list.add(board);
		}
		
		pageboard = new PageBoard(list, requestPage, articleCount, totalPage, firstRow, endRow, beginPage, endPage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pageboard;
	}

	
	
}

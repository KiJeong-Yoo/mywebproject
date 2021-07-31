package comment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import comment.vo.CPageBoard;
import comment.vo.CommentVo;


@Repository
public class CommentDao implements CommentInter {
	
	@Autowired
	DriverManagerDataSource ds;
	
	public CPageBoard list(int requestPage, int pidx) {
		
		CPageBoard CPageBoard = null; 
				
		int totalPage = 0; // 전체 페이지 = 전체 글 수 / 페이지당 표시 글 수   
		int beginPage = 0; // 시작 페이지 - 요청 페이지 기준으로 표시될 페이지 시작 번호
		int endPage = 0;   // 끝 페이지 - 요청 페이지 기준으로 표시될 페이지 마지막 번호
		int firstRow = 0;   // 시작 글번호 - 요청 페이지 기준
		int endRow = 0;     // 끝 글번호 - 요청 페이지 기준
		int articleCount = 0; // 전체 글 수
		int countPerPage = 10; // 페이지 당 표시될 글의 수
		
		List<CommentVo> list = new ArrayList<CommentVo>(); // 페이지에 대한 10개의 글 리스트

		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//1. 전체 게시물 수 구하기
		sql = "select count(*) from cboard where pidx=?";
		pstmt = ds.getConnection().prepareStatement(sql);
		pstmt.setInt(1, pidx);
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
		
		sql = "select idx, pidx, content, groupid, depth, re_order, isdel, write_id, write_day from (select rownum rnum, idx, pidx, content, groupid, depth, re_order, isdel, write_id, write_day from (select * from cboard a where pidx=? order by a.groupid asc, a.re_order asc) where rownum <= ?) where rnum >= ?"; 
		
		pstmt = ds.getConnection().prepareStatement(sql);		
		pstmt.setInt(1, pidx); 
		pstmt.setInt(2, endRow); 
		pstmt.setInt(3, firstRow);
		rs = pstmt.executeQuery();
				
		while(rs.next()) {
			CommentVo vo=new CommentVo();		
			vo.setCommentidx(rs.getInt("idx"));
			vo.setPidx(rs.getInt("pidx"));
			vo.setContent(rs.getString("content"));
			vo.setGroupid(rs.getInt("groupid"));
			vo.setDepth(rs.getInt("depth"));
			vo.setReOrder(rs.getInt("re_order"));
			vo.setIsdel(rs.getInt("isdel"));
			vo.setWriteid(rs.getString("write_id"));
			vo.setWritedate(rs.getDate("write_day"));
			list.add(vo);
		}
		
		CPageBoard = new CPageBoard(list, requestPage, articleCount, totalPage, firstRow, endRow, beginPage, endPage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return CPageBoard;
	}
	
	public int insert(CommentVo comment) { // 댓글 입력
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;

		try {
			sql="insert into cboard values(cboard_idx_seq.nextval, ?, ?, cboard_groupid_seq.nextval, 0, 0, 0, ?, sysdate)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, comment.getPidx());
			pstmt.setString(2, comment.getContent());
			pstmt.setString(3, comment.getWriteid());			
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
	
	public int delete(int idx) { // 댓글 삭제
		int result = 0;
		String sql = "delete from cboard where idx=?";
		PreparedStatement pstmt = null;
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			result=pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(int idx, String content) { // 댓글 수정
		int result = 0;
		String sql = "update cboard set content=? where idx=?";
		PreparedStatement pstmt=null;
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
			return result;
	}
	
	public int replyInsert(CommentVo comment) {
		// 부모글 존재 여부 확인
		System.out.println(comment.getCommentidx());
		if(!parentCheck(comment.getCommentidx())) {
			System.out.println("부모글 확인 실패");
			return 0;
		}
		
		// 같은 그룹 다른 댓글에 대해 depth 1 증가
		reply_before_update(comment.getGroupid(), comment.getReOrder()-1);
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;
		try {
			sql = "insert into cboard values(cboard_idx_seq.nextval, ?, ?, ?, ?, ?, 0, ?, sysdate)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, comment.getPidx());
			pstmt.setString(2, comment.getContent());
			pstmt.setInt(3, comment.getGroupid());
			pstmt.setInt(4, comment.getDepth());
			pstmt.setInt(5, comment.getReOrder());
			pstmt.setString(6, comment.getWriteid()); //id
			
			result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("sql 댓글 입력 성공");
			else
				System.out.println("sql 댓글 입력 실패");
			
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
		String sql = "select * from cboard where idx=?";
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
		String sql = "update cboard set re_order=re_order+1 where groupid=? and re_order>?";
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

	public List<CommentVo> clist(int pidx) {
		List<CommentVo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select * from cboard where pidx=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, pidx);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setCommentidx(rs.getInt("idx"));
				vo.setPidx(rs.getInt("pidx"));
				vo.setContent(rs.getString("content"));
				vo.setGroupid(rs.getInt("groupid"));
				vo.setDepth(rs.getInt("depth"));
				vo.setReOrder(rs.getInt("re_order"));
				vo.setIsdel(rs.getInt("isdel"));
				vo.setWriteid(rs.getString("write_id"));
				vo.setWritedate(rs.getDate("write_day"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delete(int groupid, int reorder) {
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		if(groupid == 0) {
			sql = "delete from cboard where groupid=?";
			try {
				pstmt = ds.getConnection().prepareStatement(sql);
				pstmt.setInt(1, groupid);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("delete fail");
				e.printStackTrace();
			}
		} else {
			sql = "delete from cboard where groupid=? and re_order=?";
			try {
				pstmt = ds.getConnection().prepareStatement(sql);
				pstmt.setInt(1, groupid);
				pstmt.setInt(2, reorder);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("delete fail");
				e.printStackTrace();
			}
		}
		return result;
	}

}

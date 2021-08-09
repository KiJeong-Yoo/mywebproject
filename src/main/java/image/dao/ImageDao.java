package image.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import image.vo.ImageVo;
import image.vo.PageBoard;

@Repository
public class ImageDao implements ImageInter {
	
	@Autowired
	DriverManagerDataSource ds;

	// 입력
	public int insert(String title, String writeId, String img) { 
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into photoboard(idx, title, content, write_id, write_day) values((select nvl(max(idx), 0) + 1 from photoboard), ?, ?, ?, sysdate)";
		
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, img);
			pstmt.setString(3, writeId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	// 조회수
	public int readcountUpdate(int idx) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update photoboard set readcount=readcount+1 where idx=?";
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(String title, String img, int idx) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update photoboard set title=?, content=? where idx=?";
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, img);
			pstmt.setInt(3, idx);
			result = pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(int idx) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from photoboard where idx=?";
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ImageVo select(int idx) {
		ImageVo image = new ImageVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from photoboard where idx=?";
		
		try{
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				image.setIdx(rs.getInt("idx"));
				image.setTitle(rs.getString("title"));
				image.setContent(rs.getString("content"));
				image.setWriteId(rs.getString("write_id"));
				image.setWriteDay(rs.getDate("write_day"));
				image.setReadcount(rs.getInt("readcount"));				
			}
			
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return image;
	}
	
	public PageBoard list(int requestPage) {
		
		PageBoard pageboard = null; 
				
		int totalPage = 0; // 전체 페이지 = 전체 글 수 / 페이지당 표시 글 수   
		int beginPage = 0; // 시작 페이지 - 요청 페이지 기준으로 표시될 페이지 시작 번호
		int endPage = 0;   // 끝 페이지 - 요청 페이지 기준으로 표시될 페이지 마지막 번호
		int firstRow = 0;   // 시작 글번호 - 요청 페이지 기준
		int endRow = 0;     // 끝 글번호 - 요청 페이지 기준
		int articleCount = 0; // 전체 글 수
		int countPerPage = 5; // 페이지 당 표시될 글의 수
		
		List<ImageVo> list = new ArrayList<ImageVo>(); // 페이지에 대한 10개의 글 리스트
		
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//1. 전체 게시물 수 구하기
		sql = "select count(*) from photoboard";
		pstmt = ds.getConnection().prepareStatement(sql);
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
		
		sql = "select * from photoboard order by idx desc"; 
		
		pstmt = ds.getConnection().prepareStatement(sql);
		rs = pstmt.executeQuery();
				
		while(rs.next()) {
			ImageVo image = new ImageVo();
			image.setIdx(rs.getInt("idx"));
			image.setTitle(rs.getString("title"));
			image.setContent(rs.getString("content"));
			image.setWriteId(rs.getString("write_id"));
			image.setWriteDay(rs.getDate("write_day"));
			image.setReadcount(rs.getInt("readcount"));
			list.add(image);
		}
		
		pageboard = new PageBoard(list, requestPage, articleCount, totalPage, firstRow, endRow, beginPage, endPage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pageboard;
	}
	
	public PageBoard searchlist(int requestPage, String title) {
		
		PageBoard pageboard = null; 
				
		int totalPage = 0; // 전체 페이지 = 전체 글 수 / 페이지당 표시 글 수   
		int beginPage = 0; // 시작 페이지 - 요청 페이지 기준으로 표시될 페이지 시작 번호
		int endPage = 0;   // 끝 페이지 - 요청 페이지 기준으로 표시될 페이지 마지막 번호
		int firstRow = 0;   // 시작 글번호 - 요청 페이지 기준
		int endRow = 0;     // 끝 글번호 - 요청 페이지 기준
		int articleCount = 0; // 전체 글 수
		int countPerPage = 5; // 페이지 당 표시될 글의 수
		
		List<ImageVo> list = new ArrayList<ImageVo>(); // 페이지에 대한 10개의 글 리스트
		
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		//1. 전체 게시물 수 구하기
		sql = "select count(*) from photoboard where title like " + "'%" + title + "%'";
		pstmt = ds.getConnection().prepareStatement(sql);
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
		
		sql = "select * from photoboard where title like "+ "'%" + title + "%'" + " order by idx desc"; 
		
		pstmt = ds.getConnection().prepareStatement(sql);
		rs = pstmt.executeQuery();
				
		while(rs.next()) {
			ImageVo image = new ImageVo();
			image.setIdx(rs.getInt("idx"));
			image.setTitle(rs.getString("title"));
			image.setContent(rs.getString("content"));
			image.setWriteId(rs.getString("write_id"));
			image.setWriteDay(rs.getDate("write_day"));
			image.setReadcount(rs.getInt("readcount"));
			list.add(image);
		}
		
		pageboard = new PageBoard(list, requestPage, articleCount, totalPage, firstRow, endRow, beginPage, endPage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pageboard;
	}
}

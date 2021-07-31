package news.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import news.service.NewsInter;
import news.vo.News;

@Repository
public class NewsDAO implements NewsInter {
	@Autowired
	DriverManagerDataSource ds;

	public NewsDAO() {
		
	}
	
	// 데이터베이스에 뉴스 기사 저장
	public int newsInsert(News news) {		
		String sql = "";
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			sql = "insert into news values(news_id_seq.nextval, ?, ?, ?, ?, ?, ?, 0)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, news.getAddress());
			pstmt.setString(2, news.getTitle());
			pstmt.setString(3, news.getContent());
			pstmt.setDate(4, news.getDate());
			pstmt.setString(5, news.getAid());
			pstmt.setString(6, news.getImg());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 번호 확인 후 데이터베이스의 마지막 다음 번호부터 기사를 가져옴
	public String aid() {				
		String sql = "";
		String aid = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select max(aid) from news";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {
				aid = rs.getString("max(aid)");
				return aid;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aid;
	}
	
	// 뉴스기사 메인페이지 갔을 시 뉴스 기사 출력
	public List<News> print() {
		List<News> list = new ArrayList<>();
		
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from news";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News vo = new News();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 뉴스 제목 눌렀을 때 내용 나오는 함수
	public News newsChoice(String aid) {
		String sql = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		News vo = new News();
		try{
			sql = "select * from news where aid=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, aid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	// 검색한 뉴스 기사 출력
	public List<News> newsSearch(String search) {
		List<News> list = new ArrayList<>();
		String sql = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			sql = "select * from news where title like " +"'%" + search + "%' or content like " +"'%" + search + "%'";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				News vo = new News();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 더보기 누르기 전에 보여줄 약간의 기사 목록 (추천 뉴스)
	public List<News> recommendRandom() {
		List<News> list = new ArrayList<>();

		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from (select * from news order by dbms_random.value) where rownum <= 10";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News vo = new News();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// main 화면에 보여줄 뉴스기사
	public List<News> mainRandom() {
		List<News> list = new ArrayList<>();
		
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from (select * from news order by dbms_random.value) where rownum <= 5";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News vo = new News();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 조회수 높은 기사 10개 가져오는 함수
	public List<News> topcount() {
		List<News> list = new ArrayList<>();

		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql = "select * from news where rownum <= 10 order by readcount desc";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News vo = new News();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("newsdate"));
				vo.setAid(rs.getString("aid"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 뉴스 기사 눌렀을 때 조회수 증가 함수 aid로 구분하려함
	public int readcountUpdate(String aid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = null;

		try{
			sql = "update news set readcount=readcount+1 where aid=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, aid);
			result = pstmt.executeUpdate();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return result;
	}

}

// newsmain 화면에서는 뉴스가 전체를 보여주는게 아님 그래서 가져올때 일정량만 가져옴, 더보기를 눌렀을 때 다 가져오게 함


package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import member.vo.MemberVo;

@Repository
public class MemberDao implements MemberInter {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Autowired
	DriverManagerDataSource ds;
	
	public MemberDao() {
		
	}
	
	// 로그인 처리
	public String login(String id, String pw) {
		String sql = "select * from login where id=? and password=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println("db 없음");
				return "x";
			} else {				
				//id 전달 후 xx님 환영합니다. 같은 문구 활용?
				return rs.getString("id");				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "x";
	}
	
	public MemberVo infoSelect(String id) { // id를 통한 정보 찾기 함수
		
		MemberVo vo = new MemberVo();
		String sql = "select * from login where id=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);		
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println("db 없음");
			} else {
				vo.setName(rs.getString("name"));
				vo.setSsn(rs.getString("ssn"));
				vo.setHp(rs.getString("hp"));
				vo.setEmail(rs.getString("email"));
				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	// 아이디 중복 확인
	public int checkId(String id) {
		String sql = "";
		int result = 0;
		try {
			sql = "select id from login where id=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = 1; // 아이디 존재
			else 
				result = 0;
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원가입
	public int join(MemberVo loginvo) {
		System.out.println(loginvo.getId());
		int result = checkId(loginvo.getId());
		
		System.out.println("checkid result : " + result);
		String sql = "";
		
		try {
				if(result == 0) {
					System.out.println("if test");
					sql = "insert into login values(?,?,?,?,?,?)";
					pstmt = ds.getConnection().prepareStatement(sql);
					pstmt.setString(1, loginvo.getId());
					pstmt.setString(2, loginvo.getPw());
					pstmt.setString(3, loginvo.getName());
					pstmt.setString(4, loginvo.getSsn());
					pstmt.setString(5, loginvo.getHp());
					pstmt.setString(6, loginvo.getEmail());
					pstmt.executeUpdate();
					return result;
				} else {
					System.out.println("id 존재함");
					return result;
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 아이디 찾기
	public MemberVo findInfo(String name, String ssn, String email) {
		MemberVo loginvo = new MemberVo();
		String sql = "select id from login where name=? and ssn=? and email=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, ssn);
			pstmt.setString(3, email);
			pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {			
				loginvo.setId(rs.getString("id"));
			} else {
				System.out.println("해당 아이디 없음");
			}
			System.out.println(rs.getString("id"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return loginvo;
	}
	
	
	// 개인정보 수정
	public int infoChange(String id, String name, String ssn, String hp, String email, String pw) {
		int pwCheck = pwCheck(id, pw);
		int result = 0;
		if(pwCheck == 1) {
			String sql = "update login set name=?, ssn=?, hp=?, email=? where id=?";
			try {
				pstmt = ds.getConnection().prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, ssn);
				pstmt.setString(3, hp);
				pstmt.setString(4, email);
				pstmt.setString(5, id);
				result = pstmt.executeUpdate();
				
				System.out.println(result);			
				if(result < 0)
					System.out.println("데이터 입력 실패");
				return result;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		return result;
	}

	private int pwCheck(String id, String pw) {
		String sql = "select * from login where id=? and password=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println("db 없음");
				return 0;
			} else 
				return 1;
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int memberDelete(String deleteId, String deletePw) {
		int result = 0;
		String sql = "delete from login where id=? and password=?";
		try {
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, deleteId);
			pstmt.setString(2, deletePw);
			
			result = pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}


package vote.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import vote.vo.PollItemVo;
import vote.vo.PollListVo;

// tblPollList table
@Repository
public class PollMgr implements PollInter {
	
	@Autowired
	DriverManagerDataSource ds;
	
	public PollMgr() {}
	
	//설문과 Item이 동시에 저장이 되게끔...
	public boolean insertPoll(PollListVo plVo, PollItemVo piVo) {
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			sql = "insert into tblPollList(num, question, sdate, edate, wdate, type) values(list_num_seq.nextval, ?, ?, ?, sysdate, ?)";
			pstmt = ds.getConnection().prepareStatement(sql);
			pstmt.setString(1, plVo.getQuestion());
			pstmt.setString(2, plVo.getSdate());
			pstmt.setString(3, plVo.getEdate());
			pstmt.setInt(4, plVo.getType());
			int cnt = pstmt.executeUpdate();

			
			if(cnt == 1) {
				sql = "insert into tblPollItem values(?,?,?,0)";
				pstmt = ds.getConnection().prepareStatement(sql);
				int listNum = getMaxNum();
				String item[] = piVo.getItem();
				int j = 0;
				for(int i = 0; i< item.length; i++) {
					if(item[i] == null || item[i].trim().equals("")) {
						break;
					}
					
					pstmt.setInt(1, listNum);
					pstmt.setInt(2, i);
					pstmt.setString(3, item[i]);
					j = pstmt.executeUpdate();
				}
				if(j == 1)
					flag = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//Max Num(자동증가) : JSP페이지에서 num값 요청이 안될 경우 가장 최신의 설문지 값
	//가장 최신의 투표 설문 리스트가 나타나도록
	public int getMaxNum() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int maxNum = 0;
		
		try {
			sql = "select max(num) from tblPollList";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				maxNum = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		return maxNum;
	}
	//Poll Read: 하나의 설문 가져오기 
	public PollListVo getPollRead(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		PollListVo plvo = new PollListVo();
		
		try {
			sql = "select num, question, type, active from tblPollList where num=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			
			if(num == 0)
				num = getMaxNum();
			
			//최신에 등록된 설문지가 대표설문지로 화면에 뜬다.
			//num이 가장 높은 설문지
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				plvo.setNum(rs.getInt(1));
				plvo.setQuestion(rs.getString(2));
				plvo.setType(rs.getInt(3));
				plvo.setActive(rs.getInt(4));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return plvo;
	}
	//Poll Item List: 설문 하나에 연결된 아이템 
	public Vector<String> getItem(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<String> vlist = new Vector<>();
		
		try {
			sql = "select item from tblPollItem where listnum=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			
			if(num == 0)
				num = getMaxNum();
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next())
				vlist.addElement(rs.getString(1));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}
	
	//Poll Update: 투표버튼을 누르면 투표가 실행
	//"update tblPollItem set count = count +1 where listnum=? and itemnum=?";
	//itemNum[] 정수변환, for 문
	//투표하는 창, 문항체크시, ?itemnum0&itemnum2&itemnum3&itemnum0 이런식으로 정보가 넘어감
	public boolean updatePoll(int listNum, String itemNum[]) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		
		try {
			sql = "update tblPollItem set count=count+1 where listnum=? and itemnum=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			
			if(listNum == 0)
				listNum = getMaxNum();
			
			for(int i = 0; i < itemNum.length; i++) {
				pstmt.setInt(1, listNum);
				pstmt.setInt(2, Integer.parseInt(itemNum[i]));
				
				if(pstmt.executeUpdate() == 1)
					flag = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt = ds.getConnection().prepareStatement(sql);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	public Vector<PollListVo> getAllList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PollListVo> vlist = new Vector<>();
		
		try {
			sql = "select * from tblPollList order by num desc";
			pstmt = ds.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PollListVo plvo = new PollListVo();
				plvo.setNum(rs.getInt("num"));
				plvo.setQuestion(rs.getString("question"));
				plvo.setSdate(rs.getString("sdate"));
				plvo.setEdate(rs.getString("edate"));
				vlist.addElement(plvo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}
	
	public int sumCount(int listNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int sum = 0;
		
		try {
			sql = "select sum(count) from tblPollItem where listnum=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			
			if(listNum == 0)
				listNum = getMaxNum();
			
			pstmt.setInt(1,listNum);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				sum = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public int getMaxcount(int listNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int maxCnt = 0;
		
		try {
			sql = "select max(count) from tblPollItem where listnum=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			
			if(listNum == 0)
				listNum = getMaxNum();
			
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				maxCnt = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return maxCnt;
	}
	
	public Vector<PollItemVo> getView(int listNum){//2개의 값이(빈즈에 넣어서 사용할 수 밖에 없음) 동시에 리턴을 하려면 빈즈를 사용할 수 밖에 없다. 1개면 상관없는데...		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PollItemVo> vlist= new Vector<PollItemVo>();
		try {
			
			sql = "select item, count from tblPollItem where listNum=?";
			pstmt = ds.getConnection().prepareStatement(sql);
			/* if(listNum==0) listNum=getMaxNum(); */
			pstmt.setInt(1, listNum==0?getMaxNum():listNum);//if 로직을 3항연산자로 리턴(ㅇㅇ?는 0이면 getMaxNum, 0이 아니면 listNum이다.
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PollItemVo piBean = new PollItemVo();
				//배열을 만들어야 한다.
				//빈즈를 배열로 만들었어서!
				String item[]=new String[1];
				item[0]=rs.getString("item");
				piBean.setItem(item);
				piBean.setCount(rs.getInt("count"));
				vlist.addElement(piBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}
}

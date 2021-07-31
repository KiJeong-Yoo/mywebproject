package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dao.MemberInter;
import member.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	MemberInter login;
	
	public String login(String id, String pw) {
		return login.login(id, pw);
	}
	
	public MemberVo infoSelect(String id) {
		return login.infoSelect(id);
	}
	
	public int join(MemberVo loginvo) {
		return login.join(loginvo);
	}
		
	public MemberVo findInfo(String name, String ssn, String email) {
		return login.findInfo(name, ssn, email);
	}
		
	public int infoChange(String id, String name, String ssn, String hp, String email, String pw) {
		return login.infoChange(id, name, ssn, hp, email, pw);
	}

	public int memberDelete(String deleteId, String deletePw) {
		return login.memberDelete(deleteId, deletePw);
	}
}

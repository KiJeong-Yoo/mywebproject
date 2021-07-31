package member.dao;

import member.vo.MemberVo;

public interface MemberInter {
	public String login(String id, String pw);
	public MemberVo infoSelect(String id);
	public int join(MemberVo loginvo);
	public MemberVo findInfo(String name, String ssn, String email);
	public int infoChange(String id, String name, String ssn, String hp, String email, String pw);
	public int memberDelete(String deleteId, String deletePw);
}

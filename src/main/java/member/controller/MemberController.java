package member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.service.MemberService;
import member.vo.MemberVo;

@Controller
@RequestMapping("/login/")
public class MemberController {
	HttpSession session;
	@Autowired
	MemberService memberService;
	String info = "";
	
	@RequestMapping("loginMain")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("section", "/login/loginMain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("login.do")
	public ModelAndView loginProc(String id, String pw, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		info = memberService.login(id, pw);
		
		if(info.equals("x")) {
			mv.addObject("loginfail", info);
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		
		session = req.getSession();
		session.setAttribute("login", info);		
		mv.addObject("section", "/main/section.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		session.invalidate();
		mv.addObject("section", "/login/loginMain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("loginFind")
	public ModelAndView find() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("section", "/login/loginFind.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("find.do")
	public ModelAndView findProc(String findName, String findSsn, String findEmail) {
		ModelAndView mv = new ModelAndView();
		MemberVo member = memberService.findInfo(findName, findSsn, findEmail);
		
		if(!member.getId().equals(null)) {
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		else {
			mv.addObject("find", "fail");
			mv.addObject("section", "/login/loginFind.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}			
		return mv;
	}
	
	@RequestMapping("loginJoin")
	public ModelAndView join() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("section", "/login/loginJoin.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("join.do")
	public ModelAndView joinProc(String joinId, String joinPw, String joinName, String joinSsn, String joinHp, String joinEmail) {
		ModelAndView mv = new ModelAndView();
		MemberVo member = new MemberVo(joinId, joinPw, joinName, joinSsn, joinHp, joinEmail);
		int joinResult = memberService.join(member);
		
		if(joinResult == 0) { //  반대로 해놨음
			System.out.println(member.toString());
			mv.addObject("join", joinResult);
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		} else {
			mv.addObject("join", joinResult);
			mv.addObject("section", "/login/loginJoin.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	@RequestMapping("info")
	public ModelAndView info() {
		ModelAndView mv = new ModelAndView();
		MemberVo member = memberService.infoSelect(info);
		mv.addObject("loginvo", member);
		mv.addObject("section", "/login/myInfoChange.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("info.do")
	public ModelAndView infoProc(String infoId2, String infoName, String infoSsn, String infoHp, String infoEmail, String infoPw) {
		ModelAndView mv = new ModelAndView();
		int infoResult = memberService.infoChange(infoId2, infoName, infoSsn, infoHp, infoEmail, infoPw);
		
		if(infoResult == 1) {
			mv.addObject("changecheck", infoResult);
			mv.addObject("section", "/main/section.jsp");
			mv.setViewName("/WEB-INF/index.jsp");			
		} else {
			mv.addObject("changecheck", infoResult);
			mv.addObject("section", "/main/section.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	@RequestMapping("memberdelete")
	public ModelAndView memberDelete() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("section", "/login/memberDelete.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	
	@RequestMapping("memberdelete.do")
	public ModelAndView memberDeleteProc(String deleteId, String deletePw) {
		ModelAndView mv = new ModelAndView();
		int deleteResult = memberService.memberDelete(deleteId, deletePw);
		
		if(deleteResult == 1) {
			//mv.addObject("msg", "회원 탈퇴에 성공하셨습니다.");
			session.invalidate();
			mv.addObject("section", "/main/section.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		} else {
			mv.addObject("msg", "회원 탈퇴에 실패하셨습니다.");
			mv.addObject("section", "/login/memberDelete.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
}

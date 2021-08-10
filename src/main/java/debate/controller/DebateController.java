package debate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.vo.BoardVO;
import board.vo.PageBoard;
import comment.service.CommentService;
import comment.vo.CPageBoard;
import comment.vo.CommentVo;
import debate.service.DebateService;

@Controller
@RequestMapping("/debateboard/")
public class DebateController {
	@Autowired
	DebateService debateService;
	@Autowired
	CommentService commentService;
	
	int boardid = 1;
	int request_Page = 1;
	
	@RequestMapping("debatemain")
	public ModelAndView main(String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		if(requestPage != null && !requestPage.equals(""))
			request_Page = Integer.parseInt(requestPage);
		
		
		PageBoard pageboard = debateService.list(request_Page, boardid);
		
		mv.addObject("pageboard", pageboard);
		mv.addObject("requestPage", request_Page);
		mv.addObject("section", "/debateboard/debatemain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("read")
	public ModelAndView read(String idx, String requestPage) {
		ModelAndView mv = new ModelAndView();
		request_Page = Integer.parseInt(requestPage);
		BoardVO board = null;
		
		CPageBoard commentlist = null;
		List<CommentVo> list = new ArrayList<>();
		int pidx = Integer.parseInt(idx);
		
		int result = debateService.readcountUpdate(pidx);
		if(result == 1) {
			board = debateService.select(pidx, boardid);
			list = commentService.clist(pidx);
			commentlist = commentService.list(request_Page, pidx); // 댓글
		}		

		mv.addObject("board", board);
		mv.addObject("comlist", list);
		mv.addObject("cboard", commentlist);
		mv.addObject("requestPage", request_Page);
		mv.addObject("section", "/debateboard/read.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView update(HttpServletRequest req, String requestPage, String idx, String writeid) {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("login") == null) {
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		} else {
			if(session.getAttribute("login").equals(writeid)) {
				
				BoardVO board = debateService.select(Integer.parseInt(idx), boardid);
				mv.addObject("board", board);
				mv.addObject("requestPage", requestPage);
				mv.addObject("section", "/debateboard/update.jsp");
				mv.setViewName("/WEB-INF/index.jsp");
				
			} else {
				BoardVO board = debateService.select(Integer.parseInt(idx), boardid);
				mv.addObject("board", board);
				mv.addObject("requestPage", requestPage);
				mv.addObject("id", "ux");
				mv.addObject("section", "/debateboard/read.jsp");
				mv.setViewName("/WEB-INF/index.jsp");
			}
		}
		return mv;
	}
	
	@RequestMapping("update.do")
	public ModelAndView updateProc(String title, String content, String idx, String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		int uresult = debateService.update(Integer.parseInt(idx), title, content);
		if(uresult == 1) {
			request_Page = 1;
				
   			if(requestPage != null && !requestPage.equals(""))
   				request_Page = Integer.parseInt(requestPage);
   			
   			PageBoard pageboard = debateService.list(request_Page, boardid);
   			mv.addObject("pageboard", pageboard);
   			mv.addObject("requestPage", request_Page);
   			mv.addObject("section", "/debateboard/debatemain.jsp");
   			mv.setViewName("/WEB-INF/index.jsp");
		}		
		return mv;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(HttpServletRequest req, String idx, String requestPage, String writeid) {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("login") == null) {
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		} else {
			
			if(session.getAttribute("login").equals(writeid)) {
				int dresult = debateService.delete(Integer.parseInt(idx), boardid);
				
				if(dresult == 1) {
					request_Page = 1;
					
					if(requestPage != null && !requestPage.equals(""))
						request_Page = Integer.parseInt(requestPage);
					
					PageBoard pageboard = debateService.list(request_Page, boardid);
					mv.addObject("pageboard", pageboard);
					mv.addObject("requestPage", request_Page);
		   			mv.addObject("section", "/debateboard/debatemain.jsp");
		   			mv.setViewName("/WEB-INF/index.jsp");
		   			return mv;
				}
			} else {
				BoardVO board = debateService.select(Integer.parseInt(idx), boardid);
				mv.addObject("board", board);
				mv.addObject("requestPage", requestPage);
				mv.addObject("id", "dx");
	   			mv.addObject("section", "/debateboard/read.jsp");
	   			mv.setViewName("/WEB-INF/index.jsp");
	   			return mv;
			}
		}
		return mv;
	}
	
	@RequestMapping("reply")
	public ModelAndView reply(HttpServletRequest req, String title, String content, String idx, String groupid, String depth, String reOrder) {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("login") == null) {
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		} else {
			BoardVO board = new BoardVO();
	   		board.setBoardid(boardid);
	   		board.setIdx(Integer.parseInt(idx));
	   		board.setGroupid(Integer.parseInt(groupid));
	   		board.setDepth(Integer.parseInt(depth));
	   		board.setReOrder(Integer.parseInt(reOrder));
	   		board.setTitle(title);
	   		board.setContent(content);
	   		
	   		mv.addObject("board", board);
	   		mv.addObject("requestPage", request_Page);
	   		mv.addObject("section","/debateboard/replyForm.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	@RequestMapping("reply.do")
	public ModelAndView replyProc(String requestPage, String writeName, String title, String content, String parent_idx, String groupid, String depth, String reOrder) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = new BoardVO();
		
   		board.setBoardid(boardid);
   		board.setIdx(Integer.parseInt(parent_idx));
   		board.setGroupid(Integer.parseInt(groupid));
   		board.setDepth(Integer.parseInt(depth) + 1);
   		board.setReOrder(Integer.parseInt(reOrder) + 1);
   		board.setTitle(title);
   		board.setContent(content);
   		board.setWriteId(writeName);
		board.setWriteName(writeName);
		
   		if(debateService.replyInsert(board) == 1) {
   			System.out.println("댓글 성공");
   			request_Page = 1;

   			if(requestPage != null && !requestPage.equals(""))
   				request_Page = Integer.parseInt(requestPage);
   			
   			PageBoard pageboard = debateService.list(request_Page, boardid);
   			mv.addObject("pageboard", pageboard);
   			mv.addObject("requestPage", request_Page);
   			mv.addObject("section", "/debateboard/debatemain.jsp");
   			mv.setViewName("/WEB-INF/index.jsp");
   		}  		
   		return mv;
	}
	
	@RequestMapping("searchList")
	public ModelAndView search(String field, String search, String boardid, String requestPage) {
		ModelAndView mv = new ModelAndView();
		
		request_Page = 1;

		if(requestPage != null && !requestPage.equals(""))
				request_Page = Integer.parseInt(requestPage);
		
		PageBoard pageboard = debateService.searchlist(field, search, request_Page, Integer.parseInt(boardid));
		mv.addObject("pageboard", pageboard);
		mv.addObject("requestPage", request_Page);
		mv.addObject("section", "/debateboard/debatemain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("insert")
	public ModelAndView insert(HttpServletRequest req) {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("login") == null) {
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		}
		else {
			mv.addObject("section", "/debateboard/writeForm.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		}
	}
	
	@RequestMapping("insert.do")
	public ModelAndView insertProc(String title, String content, String writeName) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = new BoardVO(boardid, title, content, writeName, writeName);
		int iresult = debateService.insert(board);
		
		if(iresult == 1) {
			PageBoard pageboard = debateService.list(1, boardid);
			mv.addObject("pageboard", pageboard);
			mv.addObject("requestPage", 1);
			mv.addObject("section", "/debateboard/debatemain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		} else {
			mv.addObject("section", "/WEB-INF/debateboard/writeForm.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
}

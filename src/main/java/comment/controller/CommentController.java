package comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.vo.BoardVO;
import comment.service.CommentService;
import comment.vo.CPageBoard;
import comment.vo.CommentVo;
import debate.service.DebateService;
import free.service.FreeService;

//댓글 관련
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService; // 댓글
	@Autowired
	FreeService freeService; // 자유 게시판
	@Autowired
	DebateService debateService; // 토론 게시판
	
	int request_Page = 1;	
	
	// 자유게시판 댓글 입력
	@RequestMapping("/freecomment")
	public ModelAndView commentInsert(HttpServletRequest req, String c_content, String pidx, String cwriteid, String crequestPage) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		request_Page = Integer.parseInt(crequestPage);
		BoardVO board = null;
		
		if(session.getAttribute("login") == null) {
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		}

		CommentVo comment = new CommentVo(c_content, Integer.parseInt(pidx), cwriteid);
		
		int cresult = commentService.insert(comment);
		
		board = freeService.select(Integer.parseInt(pidx), 2);		
				
		if(cresult == 1) {
			CPageBoard cpb = commentService.list(1, Integer.parseInt(pidx));
			mv.addObject("board", board);
			mv.addObject("cboard", cpb);
			mv.addObject("requestPage", Integer.parseInt(crequestPage));
			mv.addObject("section", "/freeboard/read.jsp");			
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	//토론 게시판 댓글 입력
	@RequestMapping("/debatecomment")
	public ModelAndView dcommentInsert(HttpServletRequest req, 
									   @RequestParam(value = "c_content") String c_content, 
									   @RequestParam(value = "pidx") String pidx, 
									   @RequestParam(value = "cwriteid") String cwriteid, 
									   @RequestParam(value = "crequestPage") String crequestPage) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		request_Page = Integer.parseInt(crequestPage);
		BoardVO board = null;

		if(session.getAttribute("login") == null) {
			mv.addObject("section", "/login/loginMain.jsp");
			mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		}	
		
		CommentVo comment = new CommentVo(c_content, Integer.parseInt(pidx), cwriteid);
		
		int cresult = commentService.insert(comment);
		
		board = debateService.select(Integer.parseInt(pidx), 1);		
				
		if(cresult == 1) {
			CPageBoard cpb = commentService.list(1, Integer.parseInt(pidx));
			mv.addObject("board", board);
			mv.addObject("cboard", cpb);
			mv.addObject("requestPage", Integer.parseInt(crequestPage));
			mv.addObject("section", "/debateboard/read.jsp");			
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	//댓글에 댓글
	@RequestMapping("/creply")
	public ModelAndView creply(HttpServletRequest req,String requestPage, String commentreply, String commentidx, String pidx, String groupid, String depth, String reOrder, String writeid, String boardid) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		BoardVO board = null;
		
		// 로그인 여부 확인
		if(session.getAttribute("login") == null) {
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
		}
				
		CommentVo comment = new CommentVo(Integer.parseInt(commentidx), Integer.parseInt(pidx), commentreply, Integer.parseInt(groupid), (Integer.parseInt(depth) + 1), (Integer.parseInt(reOrder) + 1), writeid);
		
		int boardnumber = Integer.parseInt(boardid);
		
		int rresult = commentService.replyInsert(comment);

		if(rresult == 1) {
   			
   			if(boardnumber == 1) {
   				board = debateService.select(Integer.parseInt(pidx), boardnumber);
   				mv.addObject("section", "/debateboard/read.jsp");
   			} else {
   				board = freeService.select(Integer.parseInt(pidx), boardnumber);  
   				mv.addObject("section", "/freeboard/read.jsp");
   			}
   			
   			CPageBoard cpb = commentService.list(1, Integer.parseInt(pidx));

   			mv.addObject("board", board);
   			mv.addObject("cboard", cpb);
   			mv.addObject("requestPage", 1);		
			mv.setViewName("/WEB-INF/index.jsp");
			
		} 
		return mv;
	}
	
	// 댓글 삭제
	@RequestMapping("/cdelete")
	public ModelAndView delete(HttpServletRequest req, String requestPage, String writeid, String groupid, String pidx, String reorder, String boardid) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		BoardVO board = null;
		
		int boardnumber = Integer.parseInt(boardid);
		
		if(session.getAttribute("login") == null) {
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
			
		} else {
			
			if(session.getAttribute("login").equals(writeid)) {
				int dresult = commentService.delete(Integer.parseInt(groupid), Integer.parseInt(reorder));
				if(dresult == 1)
					System.out.println("delete success");
				
				CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
				
				if(boardnumber == 1) {
					board = debateService.select(Integer.parseInt(pidx), boardnumber);
					mv.addObject("section", "/debateboard/read.jsp");
				} else {
					board = freeService.select(Integer.parseInt(pidx), boardnumber);  
					mv.addObject("section", "/freeboard/read.jsp");
				}
				
				mv.addObject("board", board);
				mv.addObject("cboard", cpb);
				mv.addObject("requestPage", Integer.parseInt(requestPage));		
				mv.setViewName("/WEB-INF/index.jsp");				
			} else {
				
				CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
				
				if(boardnumber == 1) {
					board = debateService.select(Integer.parseInt(pidx), boardnumber);
					mv.addObject("section", "/debateboard/read.jsp");
				} else {
					board = freeService.select(Integer.parseInt(pidx), boardnumber);  
					mv.addObject("section", "/freeboard/read.jsp");
				}
				
				mv.addObject("id", "dx");
				mv.addObject("cboard", cpb);
				mv.addObject("requestPage", Integer.parseInt(requestPage));		
				mv.setViewName("/WEB-INF/index.jsp");				
			}
		}
			
		return mv;
	}
	
	@RequestMapping("/cupdate.do")
	public ModelAndView update(HttpServletRequest req, String requestPage, String groupid, String pidx, String reorder, String boardid, String writeid, String commentidx, String commentreply1) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		
		BoardVO board = null;
		int boardnumber = Integer.parseInt(boardid);
		
		if(session.getAttribute("login") == null) {
			
			mv.addObject("section","/login/loginMain.jsp");
	   		mv.setViewName("/WEB-INF/index.jsp");
			return mv;
			
		} else {
			
			if(session.getAttribute("login").equals(writeid)) {
				
				int result = commentService.update(Integer.parseInt(commentidx), commentreply1);
				
				if(result == 1)
					System.out.println("update success");
				
				CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
				
				if(boardnumber == 1) {
					board = debateService.select(Integer.parseInt(pidx), boardnumber);
					mv.addObject("section", "/debateboard/read.jsp");
				} else {
					board = freeService.select(Integer.parseInt(pidx), boardnumber);  
					mv.addObject("section", "/freeboard/read.jsp");
				}
				
				mv.addObject("board", board);
				mv.addObject("cboard", cpb);
				mv.addObject("requestPage", Integer.parseInt(requestPage));		
				mv.setViewName("/WEB-INF/index.jsp");
				
			} else {
				
				CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
				
				if(boardnumber == 1) {
					board = debateService.select(Integer.parseInt(pidx), boardnumber);
					mv.addObject("section", "/debateboard/read.jsp");
				} else {
					board = freeService.select(Integer.parseInt(pidx), boardnumber);  
					mv.addObject("section", "/freeboard/read.jsp");
				}
				
				mv.addObject("id", "ux");
				mv.addObject("cboard", cpb);
				mv.addObject("requestPage", Integer.parseInt(requestPage));		
				mv.setViewName("/WEB-INF/index.jsp");				
			}
		}
			
		return mv;
	}
	
	//댓글 출력
	@RequestMapping("/clist")
	public ModelAndView list(String requestPage, String pidx, String boardid) {
	     ModelAndView mv = new ModelAndView();
	     BoardVO board = null;
	     
	     int request_Page = 1;
	     int boardnumber = Integer.parseInt(boardid);
	     
	     // 요청 페이지 확인, 없을 경우 1 페이지로
	     if(requestPage != null && !requestPage.equals("")) 
	        request_Page = Integer.parseInt(requestPage);
	     
	     CPageBoard pageboard = commentService.list(request_Page, Integer.parseInt(pidx));
	     
	     if(boardnumber == 1) {
	        board = debateService.select(Integer.parseInt(pidx), boardnumber);
	        mv.addObject("section", "/debateboard/read.jsp");
	     } else {
	        board = freeService.select(Integer.parseInt(pidx), boardnumber);  
	        mv.addObject("section", "/freeboard/read.jsp");
	     }
	     
	     mv.addObject("board", board);
	     mv.addObject("cboard", pageboard);
	     mv.addObject("requestPage", request_Page);
	     mv.setViewName("/WEB-INF/index.jsp");
	     return mv;
	  }
}

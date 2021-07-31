package comment.controller;

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
import free.service.FreeService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	FreeService freeService;
	int request_Page = 1;
	String _requestPage = "";	
	
	
	@RequestMapping("/freecomment")
	public ModelAndView commentInsert(HttpServletRequest req, String c_content, String idx, String pidx, String cgroupid, String cdepth, String creOrder, String cwriteid, String crequestPage) {
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
			CPageBoard cpb = commentService.list(Integer.parseInt(crequestPage), Integer.parseInt(pidx));
			mv.addObject("board", board);
			mv.addObject("cboard", cpb);
			mv.addObject("requestPage", Integer.parseInt(crequestPage));
			mv.addObject("section", "/freeboard/read.jsp");			
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	@RequestMapping("clist")
	public ModelAndView list(String requestPage, String pidx) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = null;
		int request_Page = 1;
		
		if(requestPage != null && !requestPage.equals("")) {
			request_Page = Integer.parseInt(requestPage);
		}
		
		CPageBoard pageboard = commentService.list(request_Page, Integer.parseInt(pidx));
		
		board = freeService.select(Integer.parseInt(pidx), 2);
		
		mv.addObject("board", board);
		mv.addObject("cboard", pageboard);
		mv.addObject("requestPage", request_Page);
		mv.addObject("section", "/freeboard/read.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("creply")
	public ModelAndView creply(String requestPage, String commentreply, String commentidx, String pidx, String groupid, String depth, String reOrder, String writeid) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = null;
		
		CommentVo comment = new CommentVo(Integer.parseInt(commentidx), Integer.parseInt(pidx), commentreply, Integer.parseInt(groupid), (Integer.parseInt(depth) + 1), (Integer.parseInt(reOrder) + 1), writeid);
		request_Page = Integer.parseInt(requestPage);
		
		int rresult = commentService.replyInsert(comment);

		if(rresult == 1) {
   			request_Page = 1;
			_requestPage = requestPage;
				
   			if(_requestPage != null && !_requestPage.equals(""))
   				request_Page = Integer.parseInt(_requestPage);
   			
   			board = freeService.select(Integer.parseInt(pidx), 2);
   			
   			CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
   			mv.addObject("board", board);
   			mv.addObject("cboard", cpb);
   			mv.addObject("requestPage", request_Page);
   			mv.addObject("section", "/freeboard/read.jsp");			
			mv.setViewName("/WEB-INF/index.jsp");
			
		} else {
			CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
			board = freeService.select(Integer.parseInt(pidx), 2);
			mv.addObject("board", board);
   			mv.addObject("cboard", cpb);
   			mv.addObject("requestPage", request_Page);
   			mv.addObject("section", "/freeboard/read.jsp");			
			mv.setViewName("/WEB-INF/index.jsp");
		}
		return mv;
	}
	
	@RequestMapping("cdelete")
	public ModelAndView delete(String requestPage, String groupid, String pidx, String reorder) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = null;
		
		int dresult = commentService.delete(Integer.parseInt(groupid), Integer.parseInt(reorder));
		if(dresult == 1)
			System.out.println("delete success");
		
		CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
		
		board = freeService.select(Integer.parseInt(pidx), 2);
		mv.addObject("board", board);
		mv.addObject("cboard", cpb);
		mv.addObject("requestPage", Integer.parseInt(requestPage));
		mv.addObject("section", "/freeboard/read.jsp");			
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("cupdate")
	public ModelAndView update(String requestPage, String groupid, String pidx, String reorder) {
		ModelAndView mv = new ModelAndView();
		BoardVO board = null;
		
		int dresult = commentService.delete(Integer.parseInt(groupid), Integer.parseInt(reorder));
		if(dresult == 1)
			System.out.println("delete success");
		
		CPageBoard cpb = commentService.list(request_Page, Integer.parseInt(pidx));
		
		board = freeService.select(Integer.parseInt(pidx), 2);
		mv.addObject("board", board);
		mv.addObject("cboard", cpb);
		mv.addObject("requestPage", Integer.parseInt(requestPage));
		mv.addObject("section", "/freeboard/read.jsp");			
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
}

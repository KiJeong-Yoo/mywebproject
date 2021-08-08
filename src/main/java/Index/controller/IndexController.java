package Index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.vo.BoardVO;
import board.vo.PageBoard;
import debate.service.DebateService;
import free.service.FreeService;
import news.service.NewsService;
import news.vo.News;
import vote.dao.PollMgr;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	DebateService debateService;
	@Autowired
	FreeService freeService;
	@Autowired
	NewsService newsService;
	
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		int boardid = 1; // 토론 게시판
    	int request_Page = 1;
    	
    	
		PageBoard pageboard = debateService.mainpagelist(request_Page, boardid);
    	mv.addObject("pageboard1", pageboard);
    	mv.addObject("requestPage", request_Page);
    	
    	boardid = 2; // 자유 게시판
    		    	
    	pageboard = freeService.mainpagelist(request_Page, boardid);
    	mv.addObject("pageboard2", pageboard);
    	mv.addObject("requestPage", request_Page);       	
    	
		List<News> list = newsService.mainRandom();
		mv.addObject("news", list);
		mv.addObject("section", "/main/section.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		
		return mv;
	}
	
	

}

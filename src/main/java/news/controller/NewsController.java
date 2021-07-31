package news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import news.service.NewsService;
import news.vo.News;

@Controller
@RequestMapping("/news/")
public class NewsController {
	
	@Autowired
	NewsService newsService;
		
	@RequestMapping("newsmain")
	public ModelAndView main() {
		ModelAndView mv= new ModelAndView();
		
		List<News> recommend_list = newsService.recommendRandom();
		List<News> top_list = newsService.topcount();
		mv.addObject("news", recommend_list);
		mv.addObject("topnews", top_list);
		mv.addObject("section", "/news/newsmain.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("recommend")
	public ModelAndView recommend() {
		ModelAndView mv= new ModelAndView();
		List<News> all_list = newsService.print();
		mv.addObject("news", all_list);
		mv.addObject("section", "/news/newsrecommend.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("search")
	public ModelAndView search(String newsinput) {
		ModelAndView mv= new ModelAndView();
		List<News> searchlist = newsService.newsSearch(newsinput);
		mv.addObject("search", searchlist);
		mv.addObject("section", "/news/newssearch.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
	
	@RequestMapping("newscontent")
	public ModelAndView content(String aid) {
		ModelAndView mv= new ModelAndView();
		newsService.readcountUpdate(aid);
		News news = newsService.newsChoice(aid);
		mv.addObject("news", news);
		mv.addObject("section", "/news/newscontent.jsp");
		mv.setViewName("/WEB-INF/index.jsp");
		return mv;
	}
}

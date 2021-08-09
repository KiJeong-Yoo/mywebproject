package news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import news.vo.News;

@Service
public class NewsService {
	@Autowired
	NewsInter news;

	public List<News> topcount() {
		return news.topcount();
	}

	public List<News> print() {
		return news.print();
	}

	public List<News> newsSearch(String newsinput) {
		return news.newsSearch(newsinput);
	}

	public int readcountUpdate(String aid) {
		return news.readcountUpdate(aid);
		
	}

	public News newsChoice(String aid) {
		return news.newsChoice(aid);
	}

	public List<News> mainRandom() {
		return news.mainRandom();
	}	
	
}

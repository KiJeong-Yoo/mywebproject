package news.service;

import java.util.List;

import news.vo.News;

public interface NewsInter {
	public int newsInsert(News news);
	public List<News> print();
	public News newsChoice(String aid);
	public List<News> newsSearch(String search);
	public List<News> topcount();
	public int readcountUpdate(String aid);
	public List<News> mainRandom();
}

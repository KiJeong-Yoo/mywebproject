package news.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import news.crawling.Crawling;
import news.crawling.Utils;
import news.vo.News;

public class NewsApp {

	public NewsApp() {
		//public static void main(String[] args) {
					
		NewsDAO dao = new NewsDAO();
		Crawling craw = new Crawling();
		
		String aid = dao.aid();

		if(aid == null)
			aid = "0000046180";
		
		System.out.println("main aid : " + aid);
		int count = 0;

		while(true) {
			
			String address = "https://sports.news.naver.com/news?oid=436&aid=" + aid;
			craw.download(address);
			aid = Utils.numberling(aid);

			count++;
			
			if(count == 100) 
				break;

		}
		int size = craw.arr.size();
		
		for(int i=0; i<size; i++) {			
			News vo = new News(craw.arr.get(i).getAddress(), craw.arr.get(i).getTitle(), craw.arr.get(i).getContent(), craw.arr.get(i).getDate(), craw.arr.get(i).getAid(), craw.arr.get(i).getImg());
			int result = dao.newsInsert(vo);
			System.out.println("insert result : " + result);			
		}

	}

}

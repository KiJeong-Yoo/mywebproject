package news.crawling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import news.vo.News;

public class Crawling {
	
	public ArrayList<News> arr = new ArrayList<>();
	
	public void download(String address) {
		StringBuilder html = new StringBuilder();
		
		try {
			URL url = new URL(address);
			
			URLConnection con = url.openConnection();
			
			BufferedReader download = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while(true) {
				String line = download.readLine();
				if(line == null)
					break;
				html.append(line);
			}
			download.close();
			System.out.println("다운로드 종료");
		} catch(Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Document doc = Jsoup.parse(html.toString());
		
		Elements articleTitle = doc.select(".title");
		Element title = null;
		Elements articleContent = doc.select("#newsEndContents");
		Element content = null;
		Elements articleDate = doc.select(".info");
		Element date = null;
		Elements articleImg = doc.select(".end_photo_org > img");
		Element img = null;
		String[] aid = address.split("&aid=");
		try {
			title = articleTitle.get(0);
			content = articleContent.get(0);
			date = articleDate.get(0);
			img = articleImg.get(0);
			
			String[] pdate = date.text().split(" "); // date parser
			String date2 = pdate[1].replace('.', '-').substring(0, 10); // date replace
			Date udate = new Date(sdf.parse(date2).getTime()); // util date
			java.sql.Date sdate = new java.sql.Date(udate.getTime()); // util date -> sql date
			String imgsrc = img.attr("src"); // img src
			
			News news = new News();
			news.setAddress(address);
			news.setTitle(title.text());
			news.setContent(System.lineSeparator() + content.html());			
			news.setDate(sdate);
			news.setAid(aid[1]);
			news.setImg(imgsrc);
			arr.add(news);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

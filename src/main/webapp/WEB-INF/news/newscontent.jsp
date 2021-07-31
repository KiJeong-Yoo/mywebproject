<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
	<div class="newsread_con">
		<div class="news_content_title">
			<span class="news_title">${news.title}</span>
			<span class="news_date">${news.date}</span>
		</div>
		<div class="news_content_img">
			<img src="${news.img}">
		</div>
		<div class="news_content_content">
			<span class="news_content">${news.content}</span>
		</div>
	</div>
</section>
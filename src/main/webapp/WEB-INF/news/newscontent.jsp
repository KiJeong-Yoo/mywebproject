<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
<div class="newscontent_container">
	<div class="newsread_con" style="border-right: 1px solid lightgray;">
		<div class="news_content_title">
			<span class="news_title">${news.title}</span><br>
			<span class="news_date">${news.date}</span>
		</div>
		<div class="news_content_img">
			<img src="${news.img}">
		</div>
		<div class="news_content_content">
			<span class="news_content">${news.content}</span>
		</div>
	</div>
	<div id="news_topcount">
			<div id="topcount_head">
				<h4>조회수 탑 10 뉴스</h4>	
			</div>
			<div class="news_ul">
				<ul class="topcount_ul">
					<c:forEach items="${topnews}" var="topnews">
						<li><a href="/news/newscontent?aid=${topnews.aid}">${topnews.title}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		</div>
</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div class="container-xxl">
		<div class="newscontent_container">
			<div class="newsread_con" style="border-right: 1px solid lightgray; width: 700px;">
				<div class="news_content_title">
					<span class="news_title">${news.title}</span><br>
					<span class="news_date" style="margin: 10px 0;">${news.date}</span>
				</div>
				<div class="news_content_content" style="padding: 20px;">
					<span class="news_content">${news.content}</span>
				</div>
			</div>
			<div id="news_topcount" style="margin-top: 50px;">
				<div id="topcount_head">
					<h3>조회수 탑 10 뉴스</h3>	
				</div>
				<div class="news_ul">
					<ul class="topcount_ul" style="padding: 60px 10px; list-style: none;">
						<c:forEach items="${topnews}" var="topnews">
							<li style="padding: 10px 0;"><a href="/news/newscontent?aid=${topnews.aid}">${topnews.title}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="news_recommend" style="width: 500px; height:200px;">
			<div id="recommend_head">
				<h4>추천 뉴스</h4>		
			</div>
			<div class="news_table">
				<ul class="news_re_ul">
					<c:forEach items="${news}" var="news" begin="0" end="5">
						<li><a class="news_re_img" href="/news/newscontent?aid=${news.aid}"><img src="${news.img}" style="width:100px; height: 50px;"></a>
						<a class="news_re_title" href="/news/newscontent?aid=${news.aid}">${news.title}</a></li>
					</c:forEach>
				</ul>
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
</section>
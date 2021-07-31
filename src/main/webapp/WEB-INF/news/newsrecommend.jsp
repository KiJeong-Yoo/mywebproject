<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="news_recommend">
			<div id="recommend_head">
				<h4>추천 뉴스</h4>		
			</div>
			<div class="news_table">
				<ul>
					<c:forEach items="${news}" var="news">
						<li><a href="/news/newscontent?aid=${news.aid}"><img src="${news.img}"></a>
						<a href="/news/newscontent?aid=${news.aid}">${news.title}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
</section>
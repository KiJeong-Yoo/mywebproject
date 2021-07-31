<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="news_con">
		<div id="news_search">
			<form action="/news/search" method="get" onsubmit="return check()">
				<input type="text" name="newsinput" id="newsinput" placeholder="검색할 내용을 입력하세요.">
				<input type="submit" value="검색">
			</form>		
		</div>
	
		<div id="news_recommend">
			<div id="recommend_head">
				<h4>추천 뉴스</h4>
				<a href="/news/recommend">더보기</a>			
			</div>
			<div class="news_ul">
				<ul class="topcount_ul">
					<c:forEach items="${news}" var="news">
						<li><a href="/news/newscontent?aid=${news.aid}">${news.title}</a></li>
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
			
	</div>
</section>
<script>
	const input = document.getElementById('newsinput');
	
	function check() {
		if(input.value == '') {
			alert("검색할 내용을 적어주세요.");
			return false;
		}
		return true;
	}
</script>

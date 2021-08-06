<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
		<div id="news_search" style="border: 1px solid black;">
			<form action="/news/search" method="get" onsubmit="return check()">
				<input type="text" name="newsinput" id="newsinput" placeholder="검색할 내용을 입력하세요.">
				<input type="submit" value="검색">
			</form>		
		</div>
	<div id="news_con" style="padding: 0 50px; border: 1px solid black; width: 100%; height: 800px; display: flex">
	
		<div id="news_recommend">
			<div id="recommend_head" style="padding: 10px;">
				<span style="font-size: 30px; font-weight:900;">추천 뉴스</span>
				<a href="/news/recommend" style="padding-left: 30px; text-decoration: none; color:black;">더보기</a>			
			</div>
			<div class="news_ul">
				<ul class="topcount_ul" style="list-style: none;">
					<c:forEach items="${news}" var="news">
						<li style="padding: 10px;">
						<a href="/news/newscontent?aid=${news.aid}" style="text-decoration: none; color:black; font-size: 17px; font-weight: 700;">${news.title}</a></li>
					</c:forEach>
				</ul>

			</div>
		</div>
		
		<div id="news_topcount">
			<div id="topcount_head">
				<span style="font-size: 30px; font-weight:900;">조회수 탑 10 뉴스</span>	
			</div>
			<div class="news_ul">
				<ol class="topcount_ul">
					<c:forEach items="${topnews}" var="topnews">
						<li><a href="/news/newscontent?aid=${topnews.aid}">${topnews.title}</a></li>
					</c:forEach>
				</ol>
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

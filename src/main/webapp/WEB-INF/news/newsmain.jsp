<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
<div class="container-md">
		<div class="form-group">
			<div style="margin-left: 15px">
				<span style="font-weight: 700">뉴스 검색</span>
			</div>
			<form action="/news/search" method="get" onsubmit="return check()">
			<div class="form-group search">
				<div class="col-xs-6">
					<input class="form-control" type="text" name="newsinput" id="newsinput" placeholder="검색할 내용을 입력하세요.">
				</div>
				<div class="col-xs-2">
					<input class="form-control"  type="submit" value="검색">
				</div>
			</div>
			</form>		
		</div>
	<div id="news_con" style="padding: 0 20px; width: 100%; height: 800px; display: flex">
	
		<div id="news_recommend">
			<div id="recommend_head" style="padding: 10px;">
				<span style="font-size: 30px; font-weight:900;">추천 뉴스</span>
				<a href="/news/recommend" style="padding-left: 30px; text-decoration: none; color:black;">더보기</a>			
			</div>
			<div class="news_ul">
				<ul class="list-group">
					<c:forEach items="${news}" var="news">
						<li class="list-group-item">
						<a href="/news/newscontent?aid=${news.aid}" style="text-decoration: none; color:black; font-size: 17px; font-weight: 700;">${news.title}</a></li>
					</c:forEach>
				</ul>

			</div>
		</div>
		
		<div id="news_topcount">
			<div id="topcount_head" style="padding: 10px;">
				<span style="font-size: 30px; font-weight:900;">조회수 탑 10 뉴스</span>	
			</div>
			<div class="news_ul">
				<ul class="topcount_ul list-group">
					<c:forEach items="${topnews}" var="topnews">
						<li class="list-group-item"><a href="/news/newscontent?aid=${topnews.aid}" style="text-decoration: none; color:black; font-size: 17px; font-weight: 700;">${topnews.title}</a></li>
					</c:forEach>
				</ul>
			</div>
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

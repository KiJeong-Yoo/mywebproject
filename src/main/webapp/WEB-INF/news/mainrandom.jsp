<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 	<div class="container" style="margin-top: 30px;">
			<div id="recommend_head">
				<span style="font-size: 30px; font-weight: 600;"><a href="/news/newsmain">축구 뉴스</a></span>		
			</div>
			<div style="display: flex;">
			<div class="news_table" style="">
				<table class="table table-border" style="float:left">
					<c:forEach items="${news}" var="news" begin="0" end="4">
						<tr>
							<td><a href="/news/newscontent?aid=${news.aid}"><img class="img-thumbnail" src="${news.img}" style="width:200px; height:100px;"></a></td>
							<td><a href="/news/newscontent?aid=${news.aid}">${news.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="news_table" style="">
				<table class="table table-border">
					<c:forEach items="${news}" var="news" begin="5" end="9">
						<tr>
							<td><a href="/news/newscontent?aid=${news.aid}"><img class="img-thumbnail" src="${news.img}" style="width:200px; height:100px;"></a></td>
							<td><a href="/news/newscontent?aid=${news.aid}">${news.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			</div>
		</div>
	
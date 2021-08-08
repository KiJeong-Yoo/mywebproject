<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="container" style="width: 600px;">
			<div id="recommend_head">
				<span style="font-size: 30px; font-weight: 600;"><a href="/news/newsmain">축구 뉴스</a></span>		
			</div>
			<div class="news_table">
				<table class="table table-border">
					<c:forEach items="${news}" var="news">
						<tr>
							<td><a href="/news/newscontent?aid=${news.aid}">${news.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	
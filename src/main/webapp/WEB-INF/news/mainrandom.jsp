<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="news_recommend">
			<div id="recommend_head">
				<h4><a href="/news/newsmain">축구 뉴스</a></h4>		
			</div>
			<div class="news_table">
				<table>
					<tr>
						<c:forEach items="${news}" var="news">
							<td><a href="/news/newscontent?aid=${news.aid}">${news.title}</a></td>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
</section>
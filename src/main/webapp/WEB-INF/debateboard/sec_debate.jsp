<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div class="container" style="display: flex; justify-content: space-between; margin-top: 50px; padding: 0 20px;">
		<div class="sec_debate_con">
			<h2 class="sec_debate_h">토론 게시판</h2>
			<hr>
			<div class="sec_debate_wrap">
				<table class="sec_debate_table">
					<c:forEach var="board" items="${pageboard1.list}">
						<tr class="tit" onclick="location.href='/debateboard/read?idx=${board.idx}&requestPage=${pageboard1.requestPage}'" style="cursor: pointer;">
							<td style="padding:2px; font-weight: 600;">
							<c:if test="${board.depth>0}">
								<c:forEach begin="1" end="${board.depth}">&nbsp;&nbsp;&nbsp;</c:forEach><img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
							</c:if>
							${board.title}
							</td>
							<td style="padding:2px; font-weight: 600;">${board.writeDay}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="sec_free_con">
			<h2>자유 게시판</h2>
			<hr>
			<table class="sec_free_table">
				<c:forEach var="board" items="${pageboard2.list}">
					<tr onclick="location.href='/freeboard/read?idx=${board.idx}&requestPage=${pageboard2.requestPage}'" style="cursor: pointer;">
						<td style="padding:2px; font-weight: 600;">
						<c:if test="${board.depth>0}">
							<c:forEach begin="1" end="${board.depth}">&nbsp;&nbsp;&nbsp;</c:forEach><img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
						</c:if>
						${board.title}
						</td>
						<td style="padding:2px; font-weight: 600;">${board.writeDay}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
		<div id="recommend_head">
				<h2>축구 뉴스</h2>		
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
	</div>
</section>
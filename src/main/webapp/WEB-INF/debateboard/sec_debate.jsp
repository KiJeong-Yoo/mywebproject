<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 메인 화면에 보여줄 게시판 미리보기 부분 -->
<section>
	<div class="container" style="display: flex; justify-content: space-between; margin-top: 50px; padding: 0 10px; height: 350px;">
		<div class="sec_debate_con" style="margin-left: 100px;">
			<h2 class="sec_debate_h">토론 게시판</h2>
			<hr>
			<div class="sec_debate_wrap">
				<table class="sec_debate_table table table-border">
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
		<div class="sec_free_con" style="margin-right: 100px;">
			<h2>자유 게시판</h2>
			<hr>
			<table class="sec_free_table table table-border">
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
	</div>
</section>
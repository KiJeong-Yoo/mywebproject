<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div class="sec_debate_con">
		<h2 class="sec_debate_h">
			<button class="btn" onclick="location.href='/debateboard/debatemain'"><i class="fa fa-home"></i></button>
			토론 게시판
		</h2>
		<div class="board_list_wrap">
			<table class="border_list">
				<c:forEach var="board" items="${pageboard1.list}">
					<tr class="tit" onclick="location.href='/debateboard/read?idx=${board.idx}&requestPage=${pageboard1.requestPage}'">
						<td>${board.idx}</td>
						<td>
						<c:if test="${board.depth>0}">
							<c:forEach begin="1" end="${board.depth}">&nbsp;&nbsp;&nbsp;</c:forEach><img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
						</c:if>
						${board.title}
						</td>
						<td>${board.writeName}</td>
						<td>${board.writeDay}</td>
						<td>${board.readcount}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</section>
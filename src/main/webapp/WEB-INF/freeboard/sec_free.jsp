<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
<div class="container">
 <h2><button class="btn" onclick="location.href='freeboard/freemain'"><i class="fa fa-home"></i></button>자유 게시판</h2>
<table class="table">
<c:forEach var="board" items="${pageboard2.list}">
<tr onclick="location.href='/freeboard/read?idx=${board.idx}&requestPage=${pageboard2.requestPage}'">
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
</section>
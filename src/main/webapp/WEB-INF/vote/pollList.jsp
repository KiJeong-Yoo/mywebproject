<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
<div class="container-xxl">
	<div>
		<h2>VOTE THIS</h2>
		
		<b style="font-family:d2coding; font-size:15pt;"></b><br/>
		<form action="/vote/pollFormProc" onsubmit="return check()">
				<table class="table" border="1">
					<tr>
						<td colspan="2" width="300">Q : ${plvo.question} 
						<font color="blue">${voteCount}</font></td>
					</tr>
					<tr>
						<td style="text-align:left;" colspan="2">
							<c:forEach var="item" items="${ilist}">
								<c:choose>
									<c:when test="${plvo.type == 1}">
										<input type="checkbox" name="itemnum" value="${status.index+1}">
									</c:when>
									<c:otherwise>
										<input type="radio" name="itemnum" value="${status.index+1}">
									</c:otherwise>
								</c:choose>
								${item}<br/>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td width="150">
							<c:if test="${plvo.active == 1}">
								<input class="btn btn-primary" type="submit" value="투표">
							</c:if>
							<c:if test="${plvo.active != 1}">
								투표종료
							</c:if>
						</td>
						<td width="150">
							<input class="btn btn-primary" type="button" value="결과" 
							onclick="javascript:window.open('/vote/pollview?num=${voteCount}&listnum=${plvo.num}'
							,'pollview','width=650, height=550')" style="float:right;">
						</td>
					</tr>
				</table>
				<input type="hidden" name="num" value="${plvo.num}">
			</form>
	</div>
	<div align="center">
		<br/>		
		<b style="font-family:d2coding; font-size:15pt;">VOTE LIST</b>
		<table>
			<tr>
				<td>
				<table class="table" border="1">
					<tr>
						<th width="50">번호</th>
						<th width="250" align="left">질문</th>
						<th width="200">시작일~종료일</th>
					</tr>			
					<c:forEach var="list" items="${alllist}" varStatus="status">
						<tr align="center">					
							<td>${status.index+1}</td>
							<td align="left"><a href="/vote/pollList?num=${list.num}">${list.question}</a></td>
							<td>${list.sdate}~${list.edate}</td>
						</tr>
					</c:forEach>
				</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<button class="btn btn-primary" onclick="location.href='/vote/pollInsert'">설문작성하기</button>
				</td>
			</tr>
		</table>
	</div>
	</div>
</section>
<script>
var ch = document.getElement

var check = test1.getAttribute("checked")

</script>
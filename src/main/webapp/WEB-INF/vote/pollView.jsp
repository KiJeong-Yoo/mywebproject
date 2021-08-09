<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>JSP Poll</title>
</head>
<body bgcolor="#D5D5D5">
<div align="center"><br/>
<h2>투표 결과</h2>
<table border="1" width="400">
	<tr>
		<td colspan="4">
		<b>Q :${plvo2.question} </b>	
		</td>
	</tr>
	<tr>
		<td colspan="3">
		<b>총 투표자 : ${sumCnt2} </b>	
		</td>
		<td width="40"><b>count(%)</b></td>
	</tr>
	<c:forEach var="vlist" items="${vlist2}" varStatus="status">
	<tr>
		<td width="20" align="center">${status.index+1}</td>
		<td width="120">
				<font color="black"><b>${item2.get(status.index)}</b></font>
			<%-- <c:if test="${maxCnt2 eq count2}">
			</c:if> --%>
		</td>
		    <td style="background-color:black;">
			   <table width="${ratio2[status.index]}">
				<tr>
					<td bgcolor="white" height="15">${ratio2[status.index]}%</td>
				</tr>
			</table>
		</td>
		<td width="40" align="center">${vlist.count}(${ratio2[status.index]}%)</td>
	</tr>	
	</c:forEach> 
</table><br/>
<a href="javascript:window.close()">닫기</a>
</div>
</body>
</html>
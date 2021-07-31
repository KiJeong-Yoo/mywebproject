<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
		int num = MyUtil.parseInt(request, "num");
		PollListVo plBean = mgr.getPollRead(num);
		String question = plBean.getQuestion();
		Vector<PollItemVo> vlist = mgr.getView(num);
		//현재 설문지에서의 총 투표수
		int sumCnt = mgr.sumCount(num);		
		Random r = new Random(); //랜덤객체를 만든다.
		int maxCnt = mgr.getMaxcount(num);		
%> --%>
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
		<b>Q :${plvo.question} </b>	
		</td>
	</tr>
	<tr>
		<td colspan="3">
		<b>총 투표자 : ${sumCnt} </b>	
		</td>
		<td width="40"><b>count(%)</b></td>
	</tr>
	<c:forEach var="vlist" items="${vlist}" varStatus="status">
	
	
<%-- <%
			for(int i=0;i<vlist.size();i++){
				PollItemVo piBean = vlist.get(i);
				String item[] = piBean.getItem();
				int count = piBean.getCount();
				int ratio = (int)(Math.round((double)count/sumCnt*100));
				String rgb = "#"+ Integer.toHexString(r.nextInt(255*255*255));
	%> --%>
	<tr>
		<td width="20" align="center">${status.index+1}</td>
		<td width="120">
			<c:if test="${maxCnt} == ${count}">
				<font color="red"><b>${vlist.item}</b></font>
			</c:if>
			
			<%-- <%if(maxCnt==count){%><font color="red"><b><%}%>
				<%=item[0]%>
			<%if(maxCnt==count){%></b></font><%}%> --%>
		</td>
		    <td style="background-color:black;">
			   <table width="${ratio}">
	<tr>
					<td bgcolor="white" height="15">${ratio}%</td>
				</tr>
			</table>
		</td>
		<td width="40" align="center">${count}(${ratio}%)</td>
	</tr>	
<%-- 	<%}//---for%> --%>
	</c:forEach> 
</table><br/>
<a href="javascript:window.close()">닫기</a>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div align="center" class="container">
		<br />
		<h2>VOTE PROGRAM</h2>
		<!--  <hr width="600" />-->
		<h3>설문지를 작성하는 페이지입니다.<br/> 
		<span style="color: #005766;">"원하시는 질문"</span>과 
		<span style="color: #99004C;">"항목"</span> <br/>
		그리고 "시간"을 선택해주세요</h3>
		<hr width="600" />
	<form name="frm" method="post" action="/vote/pollInsertProc">
		<!-- 투표처리기능 pollInsertProc.jsp-->
			<table border="1" width="500" hight="400" class="table">
				<tr>
					<td style= "background-color:#005766; color: #FFFFFF; font-size:18; text-align: center;"><b>질문</b></td>
					<td colspan="2"><input name="question" size="54"></td>
				</tr>
				<tr>
					<td style="text-align: center;">투표 시작일</td>
					<td colspan="2"><select name="sdateY">
							<option value="2021">2021
							<option value="2022">2022
					</select>년 <select name="sdateM">
						<c:forEach var="i" begin="1" end="12">
							<option value="${i}">${i}							
						</c:forEach>
					</select>월 <select name="sdateD">
						<c:forEach var="i" begin="1" end="31">
							<option value="${i}">${i}							
						</c:forEach>
					</select>일</td>
				</tr>
				<tr>
					<td style="text-align: center;">투표 마감일</td>
					<td colspan=2><select name="edateY">
							<option value="2021">2021
							<option value="2022">2022
					</select>년 <select name="edateM">
						<c:forEach var="i" begin="1" end="12">
							<option value="${i}">${i}							
						</c:forEach>
					</select>월 <select name="edateD">
						<c:forEach var="i" begin="1" end="31">
							<option value="${i}">${i}							
						</c:forEach>
					</select>일</td>
				</tr>
				<tr>
					<td style="text-align: center;">복수투표</td>
					<td colspan=2>
						<input type="radio" name="type" value="1" checked>네 
						<input type="radio" name="type" value="0">아니요
					</td>
				</tr>				
				<tr>
					<td style= "background-color: #99004C; color: #FFFFFF; font-size:18; text-align: center; "rowspan="30" >
					<b>항목</b></td>
				</tr>
			</table>
			<div style="display:flex; width: 500px; margin: 10px; padding: 10px; justify-content: center;">
				<input type="button" value="항목 추가" onclick="addRow()">
				<input type="button" value="항목 삭제" onclick="deleteRow(-1)">
			</div>
			<div style="display:flex; width: 500px; margin: 10px; padding: 10px; justify-content: center;">
				<input type="button" value="작성하기" onclick="send()"> 
				<input type="reset" value="다시쓰기"> 
				<input type="button" value="리스트" onClick="javascript:location.href='/vote/pollList'">
			</div>
			<input type="hidden" name="sdate">
			<input type="hidden" name="edate">
		</form>
	</div>
</section>

<script>
	function send() {
		f = document.frm;
		f.sdate.value = f.sdateY.value + "-"
		+ f.sdateM.value + "-"
		+ f.sdateD.value;
		
		f.edate.value = f.edateY.value + "-"
		+f.edateM.value + "-"
		+f.edateD.value;
		
		f.submit();
	}
	
	let count = 1;
	
	function addRow() {
		  // table element 찾기
		  const table = document.getElementById('table');
		  
		  // 새 행(Row) 추가
		  const newRow = table.insertRow(5);
		  
		  // 새 행(Row)에 Cell 추가
		  const newCell1 = newRow.insertCell(0);
		  const newCell2 = newRow.insertCell(1);
		  
		  var input = document.createElement("input");
		  input.setAttribute("name", "item");
		  input.setAttribute("type", "text");
		  // Cell에 텍스트 추가
		  newCell1.innerText = '*';
		  newCell2.appendChild(input);
		  count++;

	}
	
	function deleteRow(rownum) {
		  // table element 찾기
		  
		  if(!(count < 1)) {
			  const table = document.getElementById('table');		  
			  // 행(Row) 삭제
			  const newRow = table.deleteRow(rownum);			  
			  count--;
		  }
	}
</script>
</html>
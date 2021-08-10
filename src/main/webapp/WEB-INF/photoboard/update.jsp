<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<form action="/photoboard/update.do" method="post" enctype="multipart/form-data" name="myForm" onsubmit="return send()">
		<table class="table">
			<tr>
			  	<td>
			  		<img alt="" src="${image.content}" class="content_img">
			  	</td>
		  	</tr>
			<tr>		
				<td width="300"> 제목 : <input type="text" name="title" value="${image.title}"> </td>
			</tr>
			<tr>
		    	<td> 작성일 : ${image.writeDay} </td>
		  	</tr>
			<tr>
				<td> 조회 수 : ${image.readcount} </td>
			</tr>
			<tr>
				<td> 작성자 : ${image.writeId} </td>
			</tr>
			<tr>
				<td bgcolor="#999999">파 일</td>
				<td width="300"><input type="file" name="upload"></td>
			</tr>
			<tfoot>
				<tr>
					<td>
						<input value="등록하기" type="submit">
				  		<a href="javascript:history.back()">취소</a>
						<input type="hidden" name="idx" value="${image.idx}">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>
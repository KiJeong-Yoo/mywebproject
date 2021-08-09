<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="container">   
	<table class="table">
	  <tr>
	    <td> 제목 : ${image.title } </td>
	  </tr>
	    <tr>
	    <td> 작성일 : ${image.writeDay } </td>
	  </tr>
	    <tr>
	    <td> 조회 수 : ${image.readcount } </td>
	  </tr>
	    <tr>
	    <td> 작성자 : ${image.writeId } </td>
	  </tr>
	  <tr>
	  	<td>
	  		<img alt="" src="${image.content}" class="content_img">
	  	</td>
	  </tr>
	  
	  <tfoot>
	  	<tr>
	  		<td>
	  			<a href="/photoboard/update?idx=${image.idx}&title=${image.title}">수정</a>
	  		</td>
	  		<td>
	  			<a href="/photoboard/delete?idx=${image.idx}&requestPage=1">삭제</a>
	  		</td>
	  	</tr>
	  </tfoot>
	</table>
</div>
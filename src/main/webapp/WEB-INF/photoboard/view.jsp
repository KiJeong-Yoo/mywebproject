<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
	<div class="photoview_con">
		<p>${image.title}</p>
		<span>${image.writeId}</span>
		<span>${image.writeDay}</span>
		<span>${image.readcount}</span>				
		<img alt="" src="${image.content}">
		
		<div>
			<a href="location.href='/photoboard/update'">수정</a>
			<a href="location.href='/photoboard/delete'">삭제</a>
		</div>
	</div>
</section>
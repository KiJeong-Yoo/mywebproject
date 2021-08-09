<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jstl.jsp" %>
<section>
<div class="container-fluid">
	<div class="boardname">
		<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>포토 게시판</h2>
	</div>
	<div class="searchgroup">
		<form action="/photoboard/search">
			<div class="form-group search">
				<div class="col-xs-6">
					<input class="form-control" type="text" name="search" placeholder="검색할 내용을 입력하세요.">
				</div>
				<div class="col-xs-2">
					<input class="form-control" type="submit" value="검색">
				</div>
			</div>
		</form>
		<table class="table">
			<tr>
				<th>제목</th>
				<th>이미지</th>
			</tr>
			<c:forEach var="list" items="${pageboard.list}">
			<tr>
				<td>
					<a href="/photoboard/view?idx=${list.idx}&requestPage=${pageboard.requestPage}">
				${list.title}</a>
				</td>
				<td>
					<a href="/photoboard/view?idx=${list.idx}&requestPage=${pageboard.requestPage}">
					<img class="img-thumbnail"  alt="" src="${list.content}" style="width:200px;height:200px;vertical-align:middle;"></a>
					
				</td>
			</tr>
			</c:forEach>
		</table>
			<ul class="pagination" style="display:flex; margin-top: 20px; text-align: center; font-size: 0; justify-content: center; align-items: center;">	   
			    <c:if test="${pageboard.beginPage > 5}">
			    	<li class="page-item">
			    		<a class="page-link bt" href="/photoboard/photomain?requestPage=${pageboard.beginPage - 5}">이전페이지</a>
			    	</li>
			    </c:if>
			 
			    <c:forEach var="i" begin="${pageboard.beginPage}" end="${pageboard.endPage}">
			    	<li class="page-item">
			    		<a class="page-link num on" href="/photoboard/photomain?requestPage=${i}">${i}</a>
			    	</li>
			    </c:forEach>
			   
			    <c:if test="${pageboard.totalPage ne pageboard.endPage}">
			    	<li class="page-item">
			    		<a class="page-link bt" href="/photoboard/photomain?requestPage=${pageboard.endPage + 1}">다음페이지</a>
			    	</li>
			    </c:if>	    
			 </ul>
			<button class="btn btn-primary" onclick="location.href='/photoboard/write'">글쓰기</button>
			
		</div>
	</div>
</section>
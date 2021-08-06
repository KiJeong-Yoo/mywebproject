<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
	<div class="photomain_con">
		<form action="/photoboard/search">
			<input type="text" name="search">
			<input type="submit" value="검색">
		</form>

			<c:forEach var="list" items="${pageboard.list}">
				<a href="/photoboard/view?idx=${list.idx}&requestPage=${pageboard.requestPage}">${list.title}<img alt="" src="${list.content}" style="width:400px;height:400px;vertical-align:middle;"><%-- <img alt="" src="/photoboard/getImg.do?fileNM=${list.content}" style="width:400px;height:400px;vertical-align:middle;"> --%></a>
			</c:forEach>
			<ul class="pagination">	   
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
			<a href="/photoboard/write?requestPage=${requestPage}">글쓰기</a>

	</div>
</section>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
	<div class="container">
		<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>자유 게시판</h2>
		<!-- 검색리스트 -->
		<form name="form" action="/freeboard/searchList" method="post">
		 	<div class="form-group search">	 
			 	<div class="col-xs-2">	
				 	<select class="form-control"  name=field>
					 	<option value=title>제목</option>
					 	<option value=content>내용</option>
					 	<option value=write_name>작성자</option>
				 	</select>
				 </div> 
				 <div class="col-xs-6">  
				    <input class="form-control" onmouseover="this.focus()" type="text" id="search" name="search" placeholder="찾을 내용을 입력하세요">
				 </div>
				 <div class="col-xs-2">
				    <input type="hidden" name="boardid" id="boardid" value="2">
				    <input class="form-control" type="submit" class="btn btn-default" value="검색">
				</div>	    
		    </div>
		 </form>
		<!-- 검색리스트  -->
		<div class="board_list_wrap">
			<table class="table">		
				<tr class="success">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="board" items="${pageboard.list}">
					<tr class="tit" onclick="location.href='/freeboard/read?idx=${board.idx}&requestPage=${pageboard.requestPage}&boardid=2'">
						<td>${board.idx}</td>
						<td>
							<c:if test="${board.depth > 0}">
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
			<ul class="pagination" style="display:flex; margin-top: 20px; text-align: center; font-size: 0; justify-content: center; align-items: center;">			   
				<c:if test="${pageboard.beginPage > 5}">
					  <li class="page-item"><a class="page-link" href="/freeboard/list?requestPage=${pageboard.beginPage - 5}">이전페이지</a></li>
				</c:if>
					 
				<c:forEach var="i" begin="${pageboard.beginPage}" end="${pageboard.endPage}">
					  <li class="page-item"><a class="page-link" href="/freeboard/list?requestPage=${i}">${i}</a></li>
				</c:forEach>
					   
				<c:if test="${pageboard.totalPage ne pageboard.endPage}">
					 <li class="page-item"><a class="page-link" href="/freeboard/list?requestPage=${pageboard.endPage + 1}">다음페이지</a></li>
				</c:if>			    
				</ul>
				<button class="btn btn-primary" onclick="location.href='/freeboard/insert'">글쓰기</button>
		</div>
	</div>
</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.page-link {
    text-decoration: none;
    color: inherit;
}

.board_list_wrap {
    padding: 50px;
    width: 100%;
}
 

.board_list {   
    border-top: 2px solid green;
    border-collapse: collapse;
}

.board_list > tr {
    border-bottom: 1px solid #ccc;
}

.board_list > th,
.board_list > td {
    padding: 10px;
    font-size: 14px;
}

.board_list > td {
    text-align: center;
}

.tit {
    text-align: left;
}

.tit:hover {
    text-decoration: underline;
}

.pagination {
    margin-top: 20px;
    text-align: center;
    font-size: 0;
}

.page-link {
    display: inline-block;
    margin-left: 10px;
    padding: 5px 10px;
    border-radius: 100px;
    font-size: 12px;
}

.page-link:first-child {
    margin-left: 0;
}

.bt {
    border: 1px solid #eee;
    background: #eee;
}

.num {
    border: 1px solid green;
    font-weight: 600;
    color: green;
}

</style>
<section>
	<div class="container">
		<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>토론 게시판</h2>
		<!-- 검색리스트 -->
		<form name="form" action="/debateboard/searchList" method="post">
		 	<div class="form-group">
		 		<div class="col-xs-2" style="display: inline-block;"> 	
				 	<select class="form-control" name=field>
					 	<option value=title>제목</option>
					 	<option value=content>내용</option>
					 	<option value=write_name>작성자</option>
				 	</select>
			 	</div>
			    <div class="col-xs-6" style="display: inline-block;">
			    	<input class="form-control" onmouseover="this.focus()" type="text" id="search" name="search" placeholder="찾을 내용을 입력하세요" >
			    </div>
			    <div class="col-xs-2" style="display: inline-block;">
			    	<input type="hidden" name="boardid" id="boardid" value="1">
			    	<input type="submit" class="form-control" value="검색" style="width: 100px; height: 35px;">
			    </div>
		    </div>
		 </form>
		<!-- 검색리스트  -->
		<div class="board_list_wrap" style="width: 100%;">
			<table class="border_list" style="border-top: 2px solid green; border-collapse: collapse; padding: 50px; width: 100%;">
				<!-- 페이지번호 표시 -->
				<tr align="right" style="border-bottom: 1px solid #ccc;">
					<td colspan="5">[${pageboard.requestPage}/${pageboard.totalPage}]</td>
				</tr>
			
				<tr class="success" style="font-size: 20px; padding: 10px;">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="board" items="${pageboard.list}">
					<tr class="tit" onclick="location.href='/debateboard/read?idx=${board.idx}&requestPage=${pageboard.requestPage}'">
						<td style="text-align: center; font-size: 18px;">${board.idx}</td>
						<td  style="font-size: 18px;">
							<c:if test="${board.depth>0}">
								<c:forEach begin="1" end="${board.depth}">&nbsp;&nbsp;&nbsp;</c:forEach><img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
							</c:if>
							${board.title}				
						</td>
						<td style="text-align: center; font-size: 18px;">${board.writeName}</td>
						<td style="text-align: center; font-size: 18px;">${board.writeDay}</td>
						<td style="text-align: center; font-size: 18px;">${board.readcount}</td>
					</tr>
				</c:forEach>
			</table>
			<ul class="pagination" style="display:flex; margin-top: 20px; text-align: center; font-size: 0; justify-content: center; align-items: center;">	   
			    <c:if test="${pageboard.beginPage > 5}">
			    	<li class="page-item">
			    		<a style="text-decoration: none; color: inherit; display: inline-block; margin-left: 10px; padding: 5px 10px; border-radius: 100px; font-size: 12px;" class="page-link bt" href="/debateboard/debatemain?requestPage=${pageboard.beginPage - 5}">이전페이지</a>
			    	</li>
			    </c:if>
			 
			    <c:forEach var="i" begin="${pageboard.beginPage}" end="${pageboard.endPage}">
			    	<li class="page-item">
			    		<a class="page-link num on" href="/debateboard/debatemain?requestPage=${i}">${i}</a>
			    	</li>
			    </c:forEach>
			   
			    <c:if test="${pageboard.totalPage ne pageboard.endPage}">
			    	<li class="page-item">
			    		<a class="page-link bt" href="/debateboard/debatemain?requestPage=${pageboard.endPage + 1}">다음페이지</a>
			    	</li>
			    </c:if>	    
			 </ul>	
			<button onclick="location.href='/debateboard/insert'">글쓰기</button>
		</div>
	</div>
</section>
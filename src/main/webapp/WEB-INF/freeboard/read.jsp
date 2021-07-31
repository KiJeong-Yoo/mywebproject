<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<div class="container">
	<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>글읽기</h2>
     
	<div class="form-group">
		<p>글번호[${board.idx}] 제목 : ${board.title}</p>
		<p>작성자 : ${board.writeName } 작성일:${board.writeDay} 조회수:${board.readcount}</p>
	</div>  
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" disabled id="content" name="content" cols="80%" rows="10" placeholder="글을 입력 하세요">${board.content}</textarea>
	</div>
    
    <div class="form-group">
		<button onclick="location.href='/freeboard/freemain?requestPage=${requestPage}'">목록으로</button>
		<button onclick="location.href='/freeboard/reply?idx=${board.idx}&groupid=${board.groupid}&depth=${board.depth}&reOrder=${board.reOrder}&title=${board.title}&requestPage=${requestPage}'">답글쓰기</button>
		<button onclick="location.href='/freeboard/update?idx=${board.idx}&requestPage=${requestPage}'">글 수정</button>
		<button onclick="location.href='/freeboard/delete?idx=${board.idx}&requestPage=${requestPage}'">글 삭제</button>
		
    	<form action="/comment/freecomment" method="post" onsubmit="return check()">
    		<input type="text" name="c_content" id="c_content" style="width: 100px; height: 40px;">
    		<input type="hidden" name="pidx" value="${board.idx}">
    		<input type="hidden" name="cwriteid" value="${login}">
    		<input type="hidden" name="crequestPage" value="${requestPage}">
    		<input type="submit" value="댓글 달기">
		</form>
    </div>
    <!-- comment -->
    <div class="comment_table">
	 	<table class="table">
			<tr class="success">
				<th>작성자</th>
				<th>작성일</th>
				<th>내용</th>
			</tr>
			<c:forEach var="clist" items="${cboard.list}" varStatus="status">
				<tr>
					<td>
						<c:if test="${clist.depth > 0}">
							<c:forEach begin="1" end="${clist.depth}">&nbsp;&nbsp;&nbsp;</c:forEach>
							<img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
						</c:if>
					</td>
					<td>${clist.writeid}</td>
					<td>${clist.writedate}</td>
					<td>${clist.content}</td>
					<td><a id="${status.index}" class="on">답글쓰기</a></td>
				</tr>
				<tr>
					<td id="${status.index}" class="comment_reply_div_${status.index}" style="display: none;">
						<div>
							<form action="/comment/creply" method="post" onsubmit="return check2()">
								<input type="text" name="commentreply">
								<input type="hidden" name="commentidx" value="${clist.commentidx}">
								<input type="hidden" name="pidx" value="${board.idx}">
								<input type="hidden" name="groupid" value="${clist.groupid}">
								<input type="hidden" name="depth" value="${clist.depth}">
								<input type="hidden" name="reOrder" value="${clist.reOrder}">
								<input type="hidden" name="writeid" value="${clist.writeid}">								
								<input type="hidden" name="requestPage" value="${requestPage}">								
								<input type="submit" value="입력">
							</form>
						</div>
					</td>
					<td><button onclick="location.href='/comment/cupdate?idx=${clist.commentidx}&requestPage=${requestPage}&pidx=${board.idx}'">댓글 수정</button></td>
					<td><button onclick="location.href='/comment/cdelete?requestPage=${requestPage}&groupid=${clist.groupid}&pidx=${board.idx}&reorder=${clist.reOrder}'">댓글 삭제</button></td>
				</tr>
			</c:forEach>
			<!-- page list -->
			<tr>
				<td colspan=4 align=center valign="center">
					<ul class="pagination">			   
					    <c:if test="${cboard.beginPage > 5}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.beginPage - 5}&pidx=${board.idx}">이전페이지</a></li>
					    </c:if>
					 
					    <c:forEach var="i" begin="${cboard.beginPage}" end="${cboard.endPage}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${i}&pidx=${board.idx}">${i}</a></li>
					    </c:forEach>
					   
					    <c:if test="${cboard.totalPage ne cboard.endPage}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.endPage + 1}&pidx=${board.idx}">다음페이지</a></li>
					    </c:if>			    
					</ul>
				</td>
			</tr>
			<!-- end page list -->
		</table>
	</div>
</div>

<script>
let content = document.getElementById("c_content");
let creply = document.getElementsByName("commentreply");

function check() {
	if(content.value == '') {
		alert("댓글을 입력해주세요.");
		return false;
	}
	return true;
}

function check2() {
	if(creply.value == '') {
		alert("댓글을 입력해주세요.");
		return false;
	}
	return true;
}

$(function() {
	$('.on').click(function() {
		
		let id = $(this).attr('id');
		
		$('.comment_reply_div_' + id).css('display', '' );		 
	})
});
</script>
<c:if test="${id eq 'x'}">
	<script>
		alert("글쓴이만 수정이 가능 합니다.");
	</script>
</c:if>

<c:if test="${id eq 'xx'}">
	<script>
		alert("글쓴이만 삭제가 가능 합니다.");
	</script>
</c:if>
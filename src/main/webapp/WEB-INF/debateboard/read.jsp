<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jstl.jsp" %>
<div class="container-fluid">
	<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>글읽기</h2>
   <div class="form-group">
     <table class="table">
     	<tr>
     		<td colspan="2">
     			글번호[${board.idx}] 
     		</td>
     		<td colspan="2">
     			작성자 : ${board.writeName } 
     		</td>
     	</tr>
     	<tr>
     		<td colspan="4">
     			제목 : ${board.title}
     		</td>
     	</tr>
     	<tr>
     		<td colspan="2">
     			작성일:${board.writeDay} 
     		</td>
     		<td colspan="2">
     			조회수:${board.readcount}
     		</td>
     	</tr>
     </table>
   </div>  
    
		
		
 	
    <div class="form-group">
      <span class="label">내용</span>
      <textarea class="form-control" disabled id="content" name="content" cols="80%" rows="10" placeholder="글을 입력 하세요">
      ${board.content}
      </textarea>
    </div>
    
    <div class="form-group form-btn-group">
		<button class="btn btn-light" onclick="location.href='/debateboard/debatemain?requestPage=${requestPage}'">목록으로</button>
		<button class="btn btn-light" onclick="location.href='/debateboard/reply?idx=${board.idx}&groupid=${board.groupid}&depth=${board.depth}&reOrder=${board.reOrder}&title=${board.title}&requestPage=${requestPage}'">답글쓰기</button>
		<button class="btn btn-light" onclick="location.href='/debateboard/update?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 수정</button>
		<button class="btn btn-light" onclick="location.href='/debateboard/delete?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 삭제</button>
    </div>
 	<div class="form-group form-btn-group" style="float: left; margin: 15px; display:flex;">
 		<form action="/comment/debatecomment" method="post" onsubmit="return check()">
 			<div class="col-xs-6">
    			<input class="form-control" type="text" name="c_content" id="c_content" placeholder="댓글을 입력해주세요.">
    		</div>
    		<input type="hidden" name="pidx" value="${board.idx}">
    		<input type="hidden" name="cwriteid" value="${login}">
    		<input type="hidden" name="crequestPage" value="${requestPage}">
    		<div class="col-xs-2">
    			<input class="form-control" type="submit" value="댓글 달기">
    		</div>
		</form>
	</div>   
	<!-- comment -->
<c:if test="${fn:length(cboard.list) > 0 }">
    <div class="comment_table container">
	 	<table class="table">
			<tr class="success">
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="clist" items="${cboard.list}" varStatus="status">
				<tr>
					<c:if test="${clist.depth > 0}">
						<td>
							<c:forEach begin="1" end="${clist.depth}">&nbsp;&nbsp;&nbsp;</c:forEach>
							<img style="width:42px;height:15px" src="/img/reply_icon.gif"/>
						</td>
					</c:if>
					<td>${clist.content}</td>
					<td>${clist.writeid}</td>
					<td>${clist.writedate}</td>
					<td><a id="${status.index}" class="on">답글쓰기</a></td>
				</tr>
				<tr>
					<td id="${status.index}" class="comment_reply_div_${status.index}" style="display: none;">
						<div class="form-group">
							<form action="/comment/creply" method="post" onsubmit="return check2()">
								<div class="col-xs-6">
									<input class="form-control" type="text" name="commentreply">
								</div>
								<div class="col-xs-2">						
									<input class="form-control" type="submit" value="입력">
								</div>
								<input type="hidden" name="commentidx" value="${clist.commentidx}">
								<input type="hidden" name="boardid" value="1">
								<input type="hidden" name="pidx" value="${board.idx}">
								<input type="hidden" name="groupid" value="${clist.groupid}">
								<input type="hidden" name="depth" value="${clist.depth}">
								<input type="hidden" name="reOrder" value="${clist.reOrder}">
								<input type="hidden" name="writeid" value="${clist.writeid}">								
								<input type="hidden" name="requestPage" value="${requestPage}">	
							</form>
						</div>
					</td>
					<td>
						<button class="btn btn-light"  onclick="location.href='/comment/cupdate?idx=${clist.commentidx}&requestPage=${requestPage}&pidx=${board.idx}&boardid=1'">댓글 수정</button>
					</td>
					<td>
						<button class="btn btn-light"  onclick="location.href='/comment/cdelete?requestPage=${requestPage}&groupid=${clist.groupid}&pidx=${board.idx}&reorder=${clist.reOrder}&boardid=1'">댓글 삭제</button>
					</td>
				</tr>
			</c:forEach>
			<!-- page list -->
			<tr>
				<td colspan=4 align=center valign="center">
					<ul class="pagination">			   
					    <c:if test="${cboard.beginPage > 5}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.beginPage - 5}&pidx=${board.idx}&boardid=1">이전페이지</a></li>
					    </c:if>
					 
					    <c:forEach var="i" begin="${cboard.beginPage}" end="${cboard.endPage}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${i}&pidx=${board.idx}&boardid=1">${i}</a></li>
					    </c:forEach>
					   
					    <c:if test="${cboard.totalPage ne cboard.endPage}">
					    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.endPage + 1}&pidx=${board.idx}&boardid=1">다음페이지</a></li>
					    </c:if>			    
					</ul>
				</td>
			</tr>
			<!-- end page list -->
		</table>
	</div>
</c:if>
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
<c:if test="${id eq 'not equals'}">
	<script>
		alert("글쓴이만 수정이 가능 합니다.");
	</script>
</c:if>

<c:if test="${id eq 'not equals'}">
	<script>
		alert("글쓴이만 삭제가 가능 합니다.");
	</script>
</c:if>

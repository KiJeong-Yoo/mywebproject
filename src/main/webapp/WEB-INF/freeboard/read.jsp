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
		<button class="btn btn-light" onclick="location.href='/freeboard/freemain?requestPage=${requestPage}'">목록으로</button>
		<button class="btn btn-light" onclick="location.href='/freeboard/reply?idx=${board.idx}&groupid=${board.groupid}&depth=${board.depth}&reOrder=${board.reOrder}&title=${board.title}&requestPage=${requestPage}'">답글쓰기</button>
		<button class="btn btn-light" onclick="location.href='/freeboard/update?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 수정</button>
		<button class="btn btn-light" onclick="location.href='/freeboard/delete?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 삭제</button>
    </div>
    
    <!-- 댓글 -->
    <div class="form-group form-btn-group" style="float: left; margin: 15px; display:flex;">
 		<form action="/comment/freecomment" method="post" onsubmit="return check()">
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
	    <div class="comment_table h-100">
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
									${clist.content}
							</td>
						</c:if>
						<c:if test="${clist.depth == 0}">	
							<td>${clist.content}</td>
						</c:if>
						<td>${clist.writeid}</td>
						<td>${clist.writedate}</td>
					</tr>
					<tr>
						<td id="${status.index}" class="comment_reply_div_${status.index}" style="display: none;">
							<div class="form-group">
								<form action="/comment/creply" method="post" onsubmit="return check2()">
									<div class="col-xs-6">
										<input class="form-control"  type="text" name="commentreply">
									</div>
									<div class="col-xs-2">
										<input class="form-control"  type="submit" value="입력">
									</div>
									<input type="hidden" name="commentidx" value="${clist.commentidx}">
									<input type="hidden" name="boardid" value="2">
									<input type="hidden" name="pidx" value="${board.idx}">
									<input type="hidden" name="groupid" value="${clist.groupid}">
									<input type="hidden" name="depth" value="${clist.depth}">
									<input type="hidden" name="reOrder" value="${clist.reOrder}">
									<input type="hidden" name="writeid" value="${clist.writeid}">								
									<input type="hidden" name="requestPage" value="${requestPage}">								
								</form>
							</div>
						</td>
						<td><a id="${status.index}" class="on">답글쓰기</a></td>
						<!-- 댓글 수정 -->
						<td>					
							<a class="on2"  id="re_${status.index}">댓글 수정</a>
						</td>
						<td id="${status.index}" class="comment_reply_div_re_${status.index}" style="display: none;">
							<div class="form-group">
								<form action="/comment/cupdate.do" method="post" onsubmit="return check3()">
									<div class="col-xs-6">
										<input class="form-control" type="text" name="commentreply1">
									</div>
									<div class="col-xs-2">						
										<input class="form-control" type="submit" value="입력">
									</div>
									<input type="hidden" name="commentidx" value="${clist.commentidx}">
									<input type="hidden" name="boardid" value="2">
									<input type="hidden" name="pidx" value="${board.idx}">
									<input type="hidden" name="groupid" value="${clist.groupid}">
									<input type="hidden" name="depth" value="${clist.depth}">
									<input type="hidden" name="reOrder" value="${clist.reOrder}">
									<input type="hidden" name="writeid" value="${clist.writeid}">								
									<input type="hidden" name="requestPage" value="${requestPage}">	
								</form>
							</div>
						</td>
						<td><button class="btn btn-light"  onclick="location.href='/comment/cdelete?requestPage=${requestPage}&groupid=${clist.groupid}&pidx=${board.idx}&reorder=${clist.reOrder}&boardid=2&writeid=${clist.writeid}'">댓글 삭제</button></td>
					</tr>
				</c:forEach>
				<!-- page list -->
				<tr>
					<td colspan=4 align=center valign="center">
						<ul class="pagination" style="display:flex; margin-top: 20px; text-align: center; font-size: 0; justify-content: center; align-items: center;">			   
						    <c:if test="${cboard.beginPage > 5}">
						    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.beginPage - 5}&pidx=${board.idx}&boardid=2">이전페이지</a></li>
						    </c:if>
						 
						    <c:forEach var="i" begin="${cboard.beginPage}" end="${cboard.endPage}">
						    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${i}&pidx=${board.idx}&boardid=2">${i}</a></li>
						    </c:forEach>
						   
						    <c:if test="${cboard.totalPage ne cboard.endPage}">
						    	<li class="page-item"><a class="page-link" href="/comment/clist?requestPage=${cboard.endPage + 1}&pidx=${board.idx}&boardid=2">다음페이지</a></li>
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
let creply2 = document.getElementsByName("commentreply1");

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

function check3() {
	if(creply2.value == '') {
		alert("댓글을 입력해주세요.");
		return false;
	}
	return true;
}

let onCount = 0;
// 댓글 입력 클릭 시 display 변경
$(function() {
	$('.on').click(function() {
		
		let id = $(this).attr('id');
		
		if(onCount == 0) {
			$('.comment_reply_div_' + id).css('display', '');
			onCount = 1;
		} else {
			$('.comment_reply_div_' + id).css('display', 'none');
			onCount = 0;
		}
	})
});


let onCount2 = 0;
//대댓글 입력 클릭 시 display 변경
$(function() {
	$('.on2').click(function() {
		
		let id = $(this).attr('id');
		if(onCount2 == 0) {
			$('.comment_reply_div_' + id).css('display', '' );		 
			onCount2 = 1;
		} else {
			$('.comment_reply_div_' + id).css('display', 'none' );
			onCount2 = 0;
		}
	})
});
</script>

<c:if test="${id eq 'ux'}">
	<script>
		alert("글쓴이만 수정이 가능 합니다.");
	</script>
</c:if>

<c:if test="${id eq 'dx'}">
	<script>
		alert("글쓴이만 삭제가 가능 합니다.");
	</script>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>글읽기</h2>
     
    <div class="form-group">
		<p>글번호[${board.idx}] 제목 : ${board.title}</p>
		<p>작성자 : ${board.writeName } 작성일:${board.writeDay} 조회수:${board.readcount}</p>
 	</div>  
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" disabled id="content" name="content" cols="80%" rows="10" placeholder="글을 입력 하세요">
      ${board.content}
      </textarea>
    </div>
    
    <div class="form-group">
		<button onclick="location.href='/debateboard/debatemain?requestPage=${requestPage}'">목록으로</button>
		<button onclick="location.href='/debateboard/reply?idx=${board.idx}&groupid=${board.groupid}&depth=${board.depth}&reOrder=${board.reOrder}&title=${board.title}&requestPage=${requestPage}'">답글쓰기</button>
		<button onclick="location.href='/debateboard/update?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 수정</button>
		<button onclick="location.href='/debateboard/delete?idx=${board.idx}&requestPage=${requestPage}&writeid=${board.writeName}'">글 삭제</button>
    </div>
	<div class="comment_con">
		<table>
		    <c:if test="${requestScope.commentList != null}">
		    	<c:forEach var="comment" items="${requestScope.commentList}">
				    <tr>
				    <!-- id, date -->
				    	<td width="150">
				   			<div>
				   				${comment.comment_id}<br>
				   				<font size="2" color="lightgray">${comment.comment_date}</font>
				   			</div>
				   		</td>
				   		<!-- content -->
				   		<td width="550">
				   			<div class="comment_text_wrapper">
				   				${comment.comment_content}
				   			</div>
				   		</td>
				   		<!-- button -->
				   		<td width="100">
				   			<div id="comment_btn" style="text-align:center;">
				   				<a href="#">답변</a><br>
				   				<c:if test="${comment.comment_id == ${id}">
				   					<a href="#">수정</a><br>
				   					<a href="#">삭제</a>
				   				</c:if>
				   			</div>	
				   		</td>
				   	</tr>
			    </c:forEach>
			</c:if>
			<!-- 로그인 여부 -->
			<c:if test="${id != null}">
				<tr bgcolor="#F5F5F5">
					<form id="writeCommentForm">
						<input type="hidden" name="comment_board" value="${board.idx}">
						<input type="hidden" name="comment_id" value="${id}">
						<!-- id -->
						<td width="150">
							<div>
								${id}
							</div>
						</td>
						<!-- content -->
						<td width="550">
							<div>
								<textarea name="comment_content" rows="4" cols="70"></textarea>
							</div>
						</td>
						<!-- comment btn -->
						<td width="100">
							<div id="comment_btn" style="text-align:center;">
								<p><a href="#" onclick="writeCmt()">댓글등록</a></p>
							</div>
						</td>
					</form>
				</tr>
			</c:if>
		</table>
	</div>
</div>
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

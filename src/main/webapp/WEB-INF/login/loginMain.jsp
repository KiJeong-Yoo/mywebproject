<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${loginfail eq 'x'}">
	<script>
		alert("아이디 혹은 비밀번호가 틀렸습니다.");
	</script>
</c:if>

<c:if test="${join == 0}">
	<script>
		alert("회원가입이 완료되었습니다.");
	</script>
</c:if>

<section>
	<div class="container">       
	       <div id="log_con" style="margin: 0;">
	           <div id="h_con">
	               <span style="font-size: 30px; font-weight: 400;">풋살을 즐기며 소통하자</span><br>
	               <span class="logo_h1" style="font-size: 30px; font-weight: 400;">풋볼데이</span>
	           </div>
	           <div id="form_con">
	               <form action="/login/login.do" method="post" onsubmit="return check()">
	                   <table>
	                       <tr class="login_tr">
	                           <td class="login_td"><input type="text" name="id" id="id" placeholder="아이디" value="test1" style="width: 250px; height: 30px;"><br/></td>
	                       </tr>
	                       <tr class="login_tr">
	                           <td class="login_td"><input type="password" name="pw" id="pw" placeholder="비밀번호" value="1111111111"style="width: 250px; height: 30px;"><br/></td>
	                       </tr>
	                       <tr class="login_tr">
	                           <td class="login_td"><input type="submit" value="로그인" class="submit_btn" style="width: 250px; height: 30px;"></td>
	                       </tr>
	                   </table>
	               </form>
	               <hr>
	               <a class="join_a" href="/login/loginJoin">회원가입</a> <small>또는</small> <a class="join_a" href="/login/loginFind">아이디 찾기</a>
	           </div>
	        </div>
	   </div>
</section>

<script>
	var id = document.getElementById('id');
	var pw = document.getElementById('pw');
	
	function check() {
		if(id.value == '') {
			alert("아이디를 입력하세요");
			return false;
		}
		
		if(pw.value == '') {
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
</script>

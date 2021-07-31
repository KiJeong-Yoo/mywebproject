<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="container">
	<div id="header_con">
		<header>
			<div id="logo_img">
	            <a href="/index"><img src="/img/logo3.jpg" width="150px"></a>
	        </div>
	        <div id="header_login">					
				<c:if test="${not empty login && login ne 'error'}">
					<span>${login}님 반갑습니다</span>
					<a href="/login/info">개인정보수정</a>
					<a href="/login/logout">로그아웃</a>
				</c:if>
			    <c:if test="${login eq 'error'}">
					<script>
						 alert("아이디나 비밀번호가 틀렸거나 존재하지 않습니다.");
					</script>
				</c:if>
				<c:if test="${empty login || login eq 'error'}">
					<a href="/login/loginMain">로그인</a><small>또는</small><a href="/login/loginJoin">회원가입</a>
				</c:if>
	        </div>
		</header>
	</div>
</div>
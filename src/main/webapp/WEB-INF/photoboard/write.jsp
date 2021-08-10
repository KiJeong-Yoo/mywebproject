<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div align="center">
	<br>
		<div align="center">
			이미지 게시판
		</div>
		<form action="/photoboard/write.do" method="post" enctype="multipart/form-data" name="myForm" onsubmit="return send()">
		<input type="hidden" name="writeId" value="${login}">
			<table width="400" align="center" class="table">
				<tr>
					<td bgcolor="#999999">제 목</td>
					<td width="300"><input type="text" name="title"> </td>
				</tr>
				<tr>
					<td bgcolor="#999999">파 일</td>
					<td width="300"><input type="file" name="upload"> </td>
				</tr>
			</table>
			<br>
			<div align="center">
				<input value="등록하기" type="submit">
				<input value="다시입력" type="reset">
				<input value="작성취소" type="button" onclick="javascript:location.href='/photoboard/photomain'">
			</div>
		</form>
	</div>
</section>
<script>
	function isValidEmail(email) {
	    var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
	    if (email.search(format) != -1)
	        return true; //올바른 포맷 형식
	    return false;
	}
	 
	String.prototype.trim = function() {
	        var TRIM_PATTERN = /(^\s*)|(\s*$)/g;
	        return this.replace(TRIM_PATTERN, "");
	}
	 
	function send() {
	    f = document.myForm;
	    
	    str = f.subject.value;
	    str = str.trim();
	    if(!str) {
	        alert("제목을 입력하세요 !!!");
	        f.subject.focus();
	        return false;
	    }
	    f.subject.value = str;
	 
	    str = f.upload.value;
	    if(!str) {
	        alert("이미지 파일을 선택 하세요 !!!");
	        f.upload.focus();
	        return false;
	    }
	    
		return true;
	}
</script>

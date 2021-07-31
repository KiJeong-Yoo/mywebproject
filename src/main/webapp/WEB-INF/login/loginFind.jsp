<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${find eq 'fail'}">
	<script>
		alert("존재하지 않는 아이디 입니다.");
	</script>
</c:if>
<section>
    <div id="container"> 
        <div id="find_con">
            <div id="find_title_con">
                <span>아이디 찾기</span>
            </div>
            <div id="find_form">
                <hr>            
                <form action="/login/find.do" method="post" onsubmit="return check()">
                    <table id="find_tbl">
                        <tr class="find_tr">
                            <td class="find_td">이름</td>
                            <td class="find_td"><input class="findinput" type="text" name="findName" id="findName" placeholder="필수입력"></td>
                        </tr>
                        <tr class="find_tr">
                            <td class="find_td">주민등록번호</td>
                            <td class="find_td"><input class="findinput" type="text" name="findSsn" id="findSsn" placeholder="필수입력"></td>
                        </tr>
                        <tr class="find_tr">
                            <td class="find_td">이메일</td>
                            <td class="find_td"><input class="findinput" type="text" name="findEmail" id="findEmail" placeholder="필수입력"></td>
                        </tr>
                        <tr class="find_tr">
                            <td class="find_td" colspan="2">
                                <input class="findbtn" type="submit" value="확인">
                                <input class="findbtn" type="button" value="취소" onclick="history.back()">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</section>
<script>
var id = document.getElementById('findName');
var ssn = document.getElementById('findSsn');
var email = document.getElementById('findEmail');
function check() {
	if(id.value == '') {
		alert("이름을 입력해 주세요");
		return false;
	}
	
	if(ssn.value == '') {
		alert("주민등록번호를 입력해 주세요");
		return false;
	}
	
	if(email.value == '') {
		alert("이메일을 입력해 주세요");
		return false;
	}
		
	return true;
}
</script>
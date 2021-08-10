<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
session.getAttribute("login");
session.getAttribute("loginvo");
%>
<section>
	<div id="info_container">
        <div id="info_con">
            <div id="title_con">
                <span class="span_color">수정할 개인정보를 입력해주세요</span>
                <p><a href="/login/memberdelete">회원 탈퇴</a>
            </div>
            
            <div id="info_form">
                <form action="/login/info.do" method="post" onsubmit="return check()">
                    <table id="info_tbl">
                        <tr class="info_tr">
                            <td class="info_td">아이디</td>
                            <td class="info_td"><input class="infotext" type="text" name="infoId" id="infoId" value="${login}" disabled>
                            <input class="infotext" type="hidden" name="infoId2" id="infoId2" value="${login}"></td>
                            <hr>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">이름</td>
                            <td class="info_td"><input class="infotext"  type="text" name="infoName" id="infoName" value="${loginvo.name }"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">주민등록번호</td>
                            <td class="info_td"><input class="infotext"  type="text" name="infoSsn" id="infoSsn" value="${loginvo.ssn }"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">전화번호</td>
                            <td class="info_td"><input class="infotext"  type="text" name="infoHp" id="infoHp" value="${loginvo.hp }"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">이메일</td>
                            <td class="info_td"><input class="infotext"  type="email" name="infoEmail" id="infoEmail" value="${loginvo.email }"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">비밀번호</td>
                            <td class="info_td"><input class="infotext"  type="password" name="infoPw" id="infoPw" placeholder="필수입력"></td>
                        </tr>

                        <tr class="info_tr">
                            <td class="info_td">비밀번호 확인</td>
                            <td class="info_td"><input class="infotext"  type="password" name="infoPw2" id="infoPw2" placeholder="필수입력"></td>
                        </tr>              
                        <tr class="info_tr">
                            <td class="info_td" colspan="2">
                                <input class="infobtn" type="submit" value="확인">
                                <input class="infobtn"  type="button" value="취소" onclick="location.href='/index'">
                            </td>
                        </tr>
                    </table>
                    <hr>
                </form>
            </div>
        </div>
    </div>
</section>

<script>
	var pw = document.getElementById('infoPw');
    var pw2 = document.getElementById('infoPw2');
    const name = document.getElementById('infoName');
    const ssn = document.getElementById('infoSsn');
    const hp = document.getElementById('infoHp');
    const email = document.getElementById('infoEmail');
	function check() {	    
	    if(name.value == '') {
	    	alert("이름 입력해주세요.");
	    	return false;
	    }
	    if(ssn.value == '') {
	    	alert("주민등록번호를 입력해주세요.");
	    	return false;
	    }
	    if(hp.value == '') {
	    	alert("전화번호를 입력해주세요.");
	    	return false;
	    }
	    if(email.value == '') {
	    	alert("이메일을 입력해주세요.");
	    	return false;
	    }
	    
	    if(pw.value != '' && pw2.value != '')  {
		    if(pw.value != pw2.value) {
		    alert("비밀번호가 다릅니다.");
		    return false;
		    }	
	    } else {
	    	alert("비밀번호를 입력해주세요.");
	    	return false;
	    }
	    
	    return true;
	}
	
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${join lt 1}">
	<script>
		alert("회원가입에 실패하였습니다. 이미 존재하는 id 입니다.");
	</script>
</c:if>
<section>
<div id="join_container">
        <div id="join_con">
            <div id="title_con">
                <span>풋살을 즐기며 소통하자</span><br>
                <span class="logo_h1">풋볼데이</span>
            </div>
           
            <div id="join_form">
                <form action="/login/join.do" method="post" onsubmit="return check()">
                    <table id="join_tbl">
                        <tr class="join_tr">
                            <td class="join_td">아이디</td>
                            <td class="join_td"><input class="inputtext" type="text" name="joinId" id="joinId" placeholder="필수입력 5~15자"></td>
                            <hr>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">비밀번호</td>
                            <td class="join_td"><input class="inputtext"  type="password" name="joinPw" id="joinPw" placeholder="필수입력 10~20자"></td>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">비밀번호 확인</td>
                            <td class="join_td"><input class="inputtext"  type="password" name="joinPw2" id="joinPw2" placeholder="필수입력 10~20자"></td>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">이름</td>
                            <td class="join_td"><input class="inputtext"  type="text" name="joinName" id="joinName" placeholder="필수입력"></td>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">주민등록번호</td>
                            <td class="join_td"><input class="inputtext"  type="text" name="joinSsn" id="joinSsn" placeholder="필수입력"></td>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">전화번호</td>
                            <td class="join_td"><input class="inputtext"  type="text" name="joinHp" id="joinHp" placeholder="필수입력"></td>
                        </tr>
                        <tr class="join_tr">
                            <td class="join_td">이메일</td>
                            <td class="join_td"><input class="inputtext"  type="email" name="joinEmail" id="joinEmail" placeholder="필수입력"></td>                      
                        </tr>
                        
                        <tr class="join_tr">
                            <td class="join_td" colspan="2">
                                <input class="inputbtn"  type="submit" value="확인"> <!-- 버튼을 눌러서 전달했을 때 디비에서 아이디 존재 여부 확인 필요함 -->
                                <input class="inputbtn"  type="button" value="취소" onclick="location.href='/index'">
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
	
    var id = document.getElementById('joinId');
    var pw = document.getElementById('joinPw');
    var pw2 = document.getElementById('joinPw2');
    var name = document.getElementById('joinName');
    var ssn = document.getElementById('joinSsn');
    var hp = document.getElementById('joinHp');
    var email = document.getElementById('joinEmail');
      
    function check() {

        if(id.value == '') {
            alert("아이디를 입력해주세요");
            return false;
        }
        
        if(pw.value == '') {
            alert("비밀번호를 입력해주세요");
            return false;
        }
        
        if(name.value == '') {
            alert("이름을 입력해주세요");
            return false;
        }
        
        if(ssn.value == '') {
            alert("주민등록번호를 입력해주세요");
            return false;
        }
        
       if(hp.value == '') {
            alert("전화번호를 입력해주세요");
            return false;
        }
        
        if(email.value == '') {
            alert("이메일을 입력해주세요");
            return false;
        }
        
        if(pw.value != pw2.value) {
            alert("비밀번호가 다릅니다.");
            return false;
        } 
        
        if(!(id.value.length >= 5 && id.value.length <= 15)) {
        	alert("아이디의 길이는 5~15 이내로 가능합니다.");
        	return false;
        }
        
        if(!(pw.value.length >= 10 && pw.value.length <= 20)) {
        	alert("비밀번호의 길이는 10~20 이내로 가능합니다.");
        	return false;
        }
        
        return true;       
    }
    
    $(function() {
    	$('.emailbtn').click(function() {    	
    		
    		$('.codetext').css('display', '');		 
    	})
    });
    
</script>
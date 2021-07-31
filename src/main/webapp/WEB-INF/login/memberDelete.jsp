<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
	<div class="info_container">
		 <div id="info_con">
            <div id="title_con">
                <span class="span_color">회원 탈퇴ㅜㅜ</span>
            </div>
            <div id="info_form">
                <form action="/login/memberdelete.do" method="post" onsubmit="return check()">
                    <table id="info_tbl">
                        <tr class="info_tr">
                            <td class="info_td">아이디</td>
                            <td class="info_td">
	                            <input class="infotext" type="text" name="deleteId" id="deleteId">
                            </td>
                            <hr>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">이름</td>
                            <td class="info_td"><input class="infotext"  type="text" name="deleteName" id="deleteName"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">주민등록번호</td>
                            <td class="info_td"><input class="infotext"  type="text" name="deleteSsn" id="deleteSsn"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">전화번호</td>
                            <td class="info_td"><input class="infotext"  type="text" name="deleteHp" id="deleteHp"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">이메일</td>
                            <td class="info_td"><input class="infotext"  type="email" name="deleteEmail" id="deleteEmail"></td>
                        </tr>
                        <tr class="info_tr">
                            <td class="info_td">비밀번호</td>
                            <td class="info_td"><input class="infotext"  type="password" name="deletePw" id="deletefoPw" placeholder="필수입력"></td>
                        </tr>

                        <tr class="info_tr">
                            <td class="info_td">비밀번호 확인</td>
                            <td class="info_td"><input class="infotext"  type="password" name="deletePw2" id="deletePw2" placeholder="필수입력"></td>
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
	if(${msg} != null) {
		alert(${msg});			
	}

	var id = document.getElementById('deleteId');
	var pw = document.getElementById('deletePw');
    var pw2 = document.getElementById('deletePw2');
    const name = document.getElementById('deleteName');
    const ssn = document.getElementById('deleteSsn');
    const hp = document.getElementById('deletep');
    const email = document.getElementById('deleteEmail');
	function check() {
		if(id.value == '') {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
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
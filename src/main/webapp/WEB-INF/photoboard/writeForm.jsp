<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	function dataCheck(){
		if(document.getElementById("title").value==""){
			alert("제목을 입력해 주세요")
			return;
		}
		if(document.getElementById("content").value==""){
			alert("내용을 입력해 주세요")
			return;
		}
		document.form.submit();
		
	}
</script>

<div class="container">
  <h2><button class="btn" onclick="location.href='/index'"><i class="fa fa-home"></i></button>글작성</h2>
  <form name=form action="/photoboard/insert.do" method="post" enctype="multipart/form-data"> 
    
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력 하세요">
    </div>
    
    <div class="form-group">
      <label for="writeName">작성자</label>
      <input type="text" class="form-control" id="writeId" name="writeId" value="${login}" readonly />
    </div>
    
    <div class="form-group">
      <input type="file" name="upload" value="파일첨부" class="board_view_input" />
    </div>
    
    <button type="button" onclick=dataCheck() class="btn btn-default">입력</button>
  </form>
</div>
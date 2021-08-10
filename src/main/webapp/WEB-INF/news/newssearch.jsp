<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div class="container-fluid">
		<div class="form-group h-100">
			<div style="margin-left: 15px">
				<span style="font-weight: 700; font-size: 30px;">뉴스 검색</span>
			</div>
			
			<form action="/news/search" method="get" onsubmit="return check()">
				<div class="form-group search">
					<div class="col-xs-6">
						<input class="form-control" type="text" name="newsinput" id="newsinput" placeholder="검색할 내용을 입력하세요.">
					</div>
					<div class="col-xs-2">
						<input class="form-control"  type="submit" value="검색">
					</div>
				</div>
			</form>		
		</div>
		<div class="con">
			<table class="table table-border">
				<c:forEach items="${search}" var="search">
					<tr>
						<td><a href="/news/newscontent?aid=${search.aid}">${search.title}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</section>
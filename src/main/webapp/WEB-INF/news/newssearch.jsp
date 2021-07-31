<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="newssearch_con">
		<table>
			<tr>
				<c:forEach items="${search}" var="search">
					<td><a href="/news/newscontent?aid=${search.aid}">${search.title}</a></td><br>
				</c:forEach>
			</tr>
		</table>
	</div>
</section>
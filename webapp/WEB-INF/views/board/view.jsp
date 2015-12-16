<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.content, newLineChar, '<br/>') }
							</div>
						</td>
					</tr>
					<tr>
							<td class="label">파일</td>
							<td>
								<img src="${pageContext.request.contextPath }/upload-files/${vo.fileName}" width=150px>
							</td> 
						</tr>
				</table>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<form action="${pageContext.request.contextPath}/board/writeform" method="post">
							<input type="hidden" name="group_no" value="${vo.group_no }">
							<input type="hidden" name="order_no" value="${vo.order_no }">
							<input type="hidden" name="depth" value="${vo.depth }">
							<input type="submit" value="답글">
						</form>
					</c:if>
					<a href="${pageContext.request.contextPath}/board/">글목록</a>
					<c:if test="${vo.member_no==authUser.no }">
						<a href="${pageContext.request.contextPath}/board/modifyform?no=${vo.no }">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<c:if test="${param.result=='fail' }">
	<script>
		alert('제목을 입력하세요.');
	</script>
</c:if>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath}/board/write"  enctype="multipart/form-data">
		 		<!--<form class="board-form" method="post" action="${pageContext.request.contextPath}/board/write">  -->
		 			<input type="hidden" name="member_no" value="${authUser.no }">
					<input type = "hidden" name = "group_no" value="${vo.group_no }">
					<input type = "hidden" name = "order_no" value="${vo.order_no }">
					<input type = "hidden" name = "depth" value="${vo.depth }">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label">파일</td>
							<td>
								<input type="file" name="file"  >
							</td> 
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
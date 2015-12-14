<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="message" cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
					<c:forEach var="vo"  items="${list }"  varStatus="status">
					<li>
						<table>
							<tr>
								<td>[ ${list.size()-status.index } ]</td>
								<td>${vo.name }</td>
								<td>${vo.reg_date }</td>
								<td><a href="${pageContext.request.contextPath}/guestbook/deleteform?no=${vo.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
									${fn:replace(vo.message, newLineChar, '<br/>') }					
								</td>
							</tr>
						</table>
						<br>
					</li>
					</c:forEach>
				</ul>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
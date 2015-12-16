<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.title-td {
	text-align:left;
}	

#searchType{
	width : 100px;
	height: 27px;
}
</style>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/" method="get">
					<select id="searchType" name="searchType">
						<option value="title">제목+내용</option>
						<option value="user">글쓴이</option>
					</select>
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					
					<c:forEach var="vo" items="${data.list }" varStatus="status">
					<tr>
						<td>${data.startIndex - status.index }</td>
						<td class="title-td"><a href="${pageContext.request.contextPath}/board/view?no=${vo.no }">
							<c:if test="${vo.depth>0 }">
								<c:forEach begin="1" end="${vo.depth }">
									&nbsp;&nbsp;
								</c:forEach>
								<img src="${pageContext.request.contextPath}/assets/images/ico-reply.gif">
							</c:if>
							${vo.title }
						</a></td>
						<td>${vo.member_name }</td>
						<td>${vo.view_cnt }</td>
						<td>${vo.reg_date }</td>
						<td>
							<c:if test="${vo.member_no==authUser.no }">
								<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no }" class="del">삭제</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				<c:set var="prev" value="1"/>
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="${pageContext.request.contextPath}/board/?pg=${data.prevPage }&kwd=${data.kwd }&searchType=${data.searchType }">◀ 이전</a></li>
						<c:forEach begin="0" end="${data.blockSize-1}" varStatus="status">
							<c:if test="${data.startPageNo+status.index <= data.totalPageSize }">
								<li><a href="${pageContext.request.contextPath}/board/?pg=${data.startPageNo+status.index }&kwd=${data.kwd }&searchType=${data.searchType }">${data.startPageNo+status.index }</a></li>
							</c:if>						
						</c:forEach>
						<li class="pg-next"><a href="${pageContext.request.contextPath}/board/?pg=${data.nextPage }&kwd=${data.kwd }&searchType=${data.searchType }">다음 ▶</a></li>
					</ul>	
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath}/board/writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>
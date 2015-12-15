<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	var msg = "<c:out value='${msg}'/>";
	var url = "<c:out value='${url}'/>";
	alert(msg);
	location.href=url;	
</script>
<title>mysite</title>
</head>
<body>
</body>
</html>
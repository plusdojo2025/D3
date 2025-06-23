<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/結果</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">

</head>
<body>
	<p>
		<c:out value="${Result.message}" />
	</p>
	<a href="${Result.backTo}">顧客一覧へ</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/QRコード</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/QRCode.css' />">


</head>
<body>
<p id="clock">time</p>
	<header>
		<a href="<c:url value='/StoreBusinessServlet' />">
<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
</a>
	</header>
	
	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/StoreBusinessServlet">業務</a><br>
		<a href="${pageContext.request.contextPath}/StoreStaffServlet">事務</a><br>
		
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
		
	</div>
	<!-- ?storeId=${store_id} -->
	
<main>
<div class="QR">
	<a href="${pageContext.request.contextPath}/MenuAccessServlet?store=${store}">
		 <img src="data:image/png;base64,${qrBase64}" alt="QRコード" />
	</a>
</div>
</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>
	

	<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>
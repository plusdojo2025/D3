<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/QRコード</title>
<link rel="stylesheet" href="css/QRCode.css">

</head>
<body>
	<header>
		<a href="<c:url value='/CustomerHomeServlet' />">
		<img src="/D3/img/BARLOOP.png" alt="BARLOOP" class="icon" width="250">
		</a>
	</header>
	
	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/StoreBusinessServlet">業務</a><br>
		<a href="${pageContext.request.contextPath}/StoreStaffServlet">事務</a><br>
		<a href="${pageContext.request.contextPath}/QRCodeServlet">QRコード</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
		
	</div>
	<p id="clock">time</p>
	<!-- ?storeId=${store_id} -->
	
<main>
	<a href="${pageContext.request.contextPath}/MenuListServlet?store_id=${store_id}">
		<img src="${pageContext.request.contextPath}/qr/${fileName}" alt="QR" />
	</a>
</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>
	

	<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>
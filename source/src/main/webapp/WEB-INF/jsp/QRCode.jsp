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
	<!-- >画像リンク -->
	<a href="/D3/Servlet">
	<img src="/D3/img/BARLOOP_6.png" alt="BARLOOP" class="icon" width="250">
	</a>
	</header>
	<div class="menu">
	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
	<a href="${pageContext.request.contextPath}/HomeServlet">ホーム</a><br>
	<a href="${pageContext.request.contextPath}/MenuServlet">メニュー</a><br>
	<a href="${pageContext.request.contextPath}/QRCodeServlet">QRコード</a>
	<p>${store_id}</p>
	</div>
	</div>
	<!-- ?storeId=${store_id} -->
	
<main>
	<a href="${pageContext.request.contextPath}/MenuListServlet?store_id=${store_id}">
		<img src="${pageContext.request.contextPath}/qr/${fileName}" alt="QR" />
	</a>
</main>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/ホーム</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/CustomerHome.css' />">
</head>
<body>
<header>

<a href="<c:url value='/CustomerHomeServlet' />">
<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
</a>
</header>
<!-- ヘッダー -->
<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/CustomerHomeServlet">ホーム</a><br>
		<a href="${pageContext.request.contextPath}/MenuListServlet">メニュー</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
	</div>

<main>
<h1>${nickname} さん、ようこそ！</h1>

<!-- お知らせ・イベント情報 -->
<h2>📢お知らせ・イベント</h2>
<c:if test="${not empty eventList}">
	<c:forEach var="event" items="${eventList}">
		<p>
			<strong>イベント名：</strong> ${event.event_name}<br>
			<strong>日程：</strong> ${event.event_date}<br>
			<strong>内容：</strong> ${event.event_remark}
		</p>
	</c:forEach>
</c:if>
<c:if test="${empty eventList}">
	<p>現在お知らせはありません。</p>
</c:if>

<!-- キープボトル情報 -->
<h2>🍶キープボトル情報</h2>
<c:if test="${not empty bottleList}">
	<c:forEach var="bottle" items="${bottleList}">
		<form>
			ボトル名：<input type="text" value="${bottle.commodity.commodity_name}" readonly><br>
			期限：<input type="text" value="${bottle.bottle_limit}" readonly><br>
			残量：<input type="text" value="${bottle.bottle_remaining}ml" readonly><br>
		</form>
	</c:forEach>
</c:if>
<c:if test="${empty bottleList}">
	<p>キープ中のボトルはありません。</p>
</c:if>
</main>
<!-- フッター -->
<footer>
		<div class="copyright">
			<p>&copy;おかゆ</p>
		</div>
	</footer>
<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>

	<script src="${pageContext.request.contextPath}/js/StoreBusiness.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>

</body>
</html>
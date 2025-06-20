<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/ホーム</title>
</head>
<body>
<header>

<a href="<c:url value='/CustomerHomeServlet' />">
<img src="/D3/img/BARLOOP.png" alt="BARLOOP" class="icon" width="250">
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


<h1>${nickname} さん、ようこそ！</h1>
<div style="text-align: right;">
    <a href="<c:url value='/CustomerHomeServlet' />">☰ </a>
    <a href="<c:url value='/QRCodeServlet' />">メニュー </a>
</div>

<!-- お知らせ・イベント情報 -->
<h2>📢お知らせ・イベント</h2>
<c:if test="${not empty talkList}">
	<c:forEach var="talk" items="${talkList}">
		<p>
			<strong>タイトル：</strong> ${fn:escapeXml(talk.title)}<br>
			<strong>本文：</strong> ${fn:escapeXml(talk.body)}
		</p>
	</c:forEach>
</c:if>
<c:if test="${empty talkList}">
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

<!-- フッター -->
<div id="footer">
	<p>&copy; plusDOJO(SE plus). All rights reserved.</p>
</div>

</body>
</html>
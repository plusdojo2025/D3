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
			ボトル名：<input type="text" value="${fn:escapeXml(bottle.name)}" readonly><br>
			ニックネーム：<input type="text" value="${fn:escapeXml(bottle.nickname)}" readonly><br>
			キープ日：<input type="text" value="${bottle.keepDate}" readonly><br>
			残量：<input type="text" value="${bottle.remain}ml" readonly><br>
			備考：<input type="text" value="${fn:escapeXml(bottle.note)}" readonly><br><br>
		</form>
	</c:forEach>
</c:if>
<c:if test="${empty bottleList}">
	<p>キープ中のボトルはありません。</p>
</c:if>

<!-- フッター -->
	<footer>
		<div class="copyright">
			<p>&copy;おかゆ</p>
		</div>
	</footer>

</body>
</html>

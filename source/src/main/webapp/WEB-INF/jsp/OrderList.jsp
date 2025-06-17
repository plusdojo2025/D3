<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	<!-- >画像リンク -->
	</header>
	
	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
	<a href="webapp/HomeServlet">ホーム</a><br>
	<a href="webapp/MenuServlet">メニュー</a><br>
	<a href="webapp/QRcodeServlet">QRコード</a>
	</div>
	
<main>
<p>ご注文リスト</p>
	<form action="${pageContext.request.contextPath}/OrderListServlet" method="POST">
	<div id="orderList">
		<c:forEach var="order" items="${orderList}">
		<div class="order" onclick="">
			<input type="hidden" name="commodityId" value="${order.commodity.commodity_id}">
			<input name="commodityName" value="${order.commodity.commodity_name}" readonly>
			<input name="commodityPrice" value="${order.commodity.commodity_price}" readonly>
			<input name="commodityQuantity" value="${order.quantity}" readonly>
		</div>
		</c:forEach>
		<input type="submit" name="submit" value="注文確定する">
	</div>
	<input type="submit" name="menu" value="メニューに戻る">
	<input type="submit" name="call" value="店員呼び出し">
	<input type="submit" name="accounting" value="会計">
	</form>
</main>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>
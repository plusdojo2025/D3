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
	
	<div id="panel">
	<a href="webapp/HomeServlet">ホーム</a>
	<a href="webapp/MenuServlet">メニュー</a>
	<a href="webapp/QRcodeServlet">QRコード</a>
	</div>
<main>
<p>ご注文リスト</p>
	<form action="${pageContext.request.contextPath}/OrderListServlet" method="POST">
	<div id="orderList">
		<c:forEach var="order" items="${orderList}">
		<div class="order" onclick="">
			<input type="hidden" name="commodityId" value="${order.commodity_id}">
			<input name="commodityName" value="${order.commodity_id.commodity_name}" readonly>
			<input name="commodityPrice" value="${order.commodity.id.commodity_price}" readonly>
			<input name="commodityQuantity" value="${order.order_quantity}" readonly>
		</div>
		</c:forEach>
		<input type="submit" name="submit" value="注文確定する">
	</div>
	<input type="submit" name="menu" value="メニューに戻る">
	<input type="submit" name="call" value="店員呼び出し">
	<input type="submit" name="accounting" value="会計">
	</form>
</main>
	<!-- スクリプト -->
</body>
</html>
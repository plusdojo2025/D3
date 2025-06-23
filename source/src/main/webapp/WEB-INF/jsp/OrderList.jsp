<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/ご注文</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/OrderList.css' />">
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
		<a href="${pageContext.request.contextPath}/CustomerHomeServlet">ホーム</a><br>
		<a href="${pageContext.request.contextPath}/MenuListServlet">メニュー</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
	</div>
	
	<!-- onclick="RedirectQR(${store_id})" -->
	<main>
		<h2>ご注文リスト</h2>
		<form id="orderForm" action="<%= request.getContextPath() %>/OrderListServlet" method="POST">
			<div id="orderList">
				<c:forEach var="order" items="${orderList}">
					<div class="order" onclick="">
						<input type="hidden" name="customerId" value="${order.customer.customer_id}">
						<input type="hidden" name="commodityId" value="${order.commodity.commodity_id}"> 
						<input value="${order.commodity.commodity_name}" readonly class="inputMenu"> 
						￥<input value="${order.commodity.commodity_price}" readonly class="inputMenu">
						×<input name="commodityQuantity" value="${order.order_quantity}" readonly class="inputMenu">
							
					</div>
				</c:forEach>
				<button type="button" onclick="submitOrder()"class="cool-submit">注文確定する</button>
			</div>
			<div class="subButton">
			<input type="submit" name="menu" value="メニューに戻る" onclick="RedirectMenu(1)" >
			<input type="submit" name="call" value="店員呼び出し" onclick="TODO">
			</div>
		</form>
	</main>
	
<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>
	
	
	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/OrderList.js"></script>
</body>
</html>
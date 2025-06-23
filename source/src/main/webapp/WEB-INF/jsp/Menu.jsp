<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.Cart" %>
<%@ page import="dto.Commodity" %>
<%@ page import="dao.CartDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/メニュー</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/Menu.css' />">
</head>



<body>
<header>

<a href="<c:url value='/CustomerHomeServlet' />">
<img src=<c:url value='/D3/img/BARLOOP.png' /> alt="BARLOOP" class="icon" width="250">
</a>
</header>

<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/CustomerHomeServlet">ホーム</a><br>
		<a href="${pageContext.request.contextPath}/MenuListServlet">メニュー</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
	</div>


	<main>
		<h1>メニュー</h1>
		<form method="GET" action="/D3/MenuListServlet">
			<input type="submit" value="cocktail" name="category"> <input
				type="submit" value="whisky" name="category"> <input
				type="submit" value="beer" name="category"> <input
				type="submit" value="food" name="category">
		</form>


		<div>
			<table>
				<c:forEach var="menu" items="${menuList}" varStatus="status">

					<c:if test="${status.index % 3 == 0}">
					</c:if>

					<div class="commodity"
						onclick="popupCommodity(${menu.commodity_id}, '${menu.commodity_image}', '${menu.commodity_name}', ${menu.commodity_price})">
						<img src="/D3/img/${menu.commodity_image}" alt="メニュー" width="150"
							height="150"> ${menu.commodity_name}
						￥${menu.commodity_price}
					</div>

					<c:if test="${(status.index + 1) % 3 == 0}">
					</c:if>
				</c:forEach>
			</table>
		</div>

		<!-- ポップアップ用 -->
		<c:if test="${commodity}">
		</c:if>
		<div id="popupCommodity">
			<input type="hidden" id="commodityId" value="" readonly><br>
			<p id="commodityImage"></p><br>
			<p id="commodityName"></p><br>
			<p id="commodityPrice"></p>
			<select id="quantity">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
			</select> <input type=submit value="追加" onclick="addCart()"> <input
				type=button value="キャンセル" onclick="closePopup()">
		</div>

		<form id="orderForm" method="POST" action="<%= request.getContextPath() %>/OrderSubmitServlet">
			<div id="cartInputs"></div>

			<button id="submitOrderButton" type="button" onclick="submitOrder()">注文する</button>
		</form>

		<div>
			<form method="GET" action="/D3/MenuListServlet">
				<c:forEach var="page" items="${pagenumber}" varStatus="status">
					<c:if test="${(status.index) <5}">
						<input type=submit value="${page}" name="number">
					</c:if>
				</c:forEach>
			</form>
		</div>
	</main>

	<footer>
		<div class="copyright">
			<p>&copy;おかゆ</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/MenuList.js"></script>
</body>
</html>
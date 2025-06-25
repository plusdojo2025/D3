<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.Cart"%>
<%@ page import="dto.Commodity"%>
<%@ page import="dao.CartDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/メニュー</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/MenuList.css' />">
</head>



<body data-visitor="${isVisitor ? 'true' : 'false'}"
	data-login="${isLogin ? 'true' : 'false'}">
	
	<div id="overlay" style="display: none"></div>
	<header>

		<a href="<c:url value='/CustomerHomeServlet' />"> <img
			src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon"
			width="250">
		</a>
	</header>

	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/CustomerHomeServlet">ホーム</a><br>
		<a href="${pageContext.request.contextPath}/MenuListServlet">メニュー</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
	</div>

	<div id="loginPopup">
		<div id="loginChoice">
			<button type="button" onclick="showLoginForm()">ログイン</button>
			<button type="submit" onclick="postAndRedirectMenuAccess()">ゲスト</button>
		</div>

		<div id="loginForm">
			<button type="button" onclick="backLoginChoice()">X</button>
			<form action="<%=request.getContextPath()%>/MenuAccessServlet" method="POST">
				<input type="text" name="email" placeholder="メールアドレス" required><br>
				<input type="password" name="password" placeholder="パスワード" required><br>
				<button type="submit">送信</button>
			</form>
		</div>
	</div>


	<main>
		<h1>メニュー</h1>
		<form id="categorys" method="GET" action="<%=request.getContextPath()%>/MenuListServlet">
			<input type="submit" value="cocktail" name="category">
			<input type="submit" value="whisky" name="category">
			<input type="submit" value="beer" name="category">
			<input type="submit" value="food" name="category">
		</form>


		<div class="commority-container">

			<c:forEach var="menu" items="${menuList}" varStatus="status">

				<c:if test="${status.index % 3 == 0}">
				</c:if>

				<div class="commodity"
					onclick="popupCommodity(${menu.commodity_id}, '${menu.commodity_image}', '${menu.commodity_name}', ${menu.commodity_price})">
					<img src="<c:url value='/img/${menu.commodity_image}' />" alt="メニュー" width="150"
						height="150"> <br>${menu.commodity_name}<br>
					￥${menu.commodity_price}
				</div>

				<c:if test="${(status.index + 1) % 3 == 0}">
				</c:if>
			</c:forEach>

		</div>

		<!-- ポップアップ用 -->
		<c:if test="${commodity}">
		</c:if>
		<div id="popupCommodity">
			<input type="hidden" id="commodityId" value="" readonly><br>
			<img id="commodityImage" src="" alt="商品画像" width="200">
			<br>
			<p id="commodityName"></p>
			<br>
			<p id="commodityPrice"></p>

			<div class="visitor">
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
				</select> <input type=submit value="追加" name="add" onclick="addCart()">
			</div>
			<input type=button value="キャンセル" name="cancel" onclick="closePopup()">
		</div>

		<div class="visitor">
			<form id="orderForm" method="POST"
				action="<%=request.getContextPath()%>/OrderSubmitServlet">
				<div id="cartInputs"></div>

				<button id="submitOrderButton" type="button" onclick="submitOrder()">注文する</button>
			</form>
		</div>

		<div class="Pagination">
			<form method="GET" action="<%=request.getContextPath()%>/MenuListServlet">
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
	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>

	<script src="${pageContext.request.contextPath}/js/StoreBusiness.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>

</body>
</html>
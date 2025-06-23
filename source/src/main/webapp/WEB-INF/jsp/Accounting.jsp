<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>BARLOOP/会計確認</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 40px;
}

table {
	border-collapse: collapse;
	width: 60%;
}

th, td {
	border: 1px solid #aaa;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #ddd;
}

.total {
	font-weight: bold;
	font-size: 1.2em;
	color: #333;
}

/* ポップアップスタイル */
#popup {
	display: none;
	position: fixed;
	top: 20%;
	left: 30%;
	width: 40%;
	background: #fff;
	border: 2px solid #333;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
	padding: 20px;
	z-index: 9999;
}

#popupContent {
	margin-bottom: 10px;
}

#popup button {
	padding: 5px 10px;
}

#overlay {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.3);
	z-index: 9998;
}

/* エラーメッセージスタイル */
#errorMessage {
	color: red;
	margin-top: 5px;
}
</style>
</head>

<body>

	<p id="clock">time</p>
	<header>
		<a href="<c:url value='/StoreBusinessServlet' />"> 
	<img src=<c:url value='/D3/img/BARLOOP.png' /> alt="BARLOOP" class="icon" width="250">
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


	<main data-context="${pageContext.request.contextPath}">
		<h2>会計内容の確認</h2>

		<form action="<%=request.getContextPath()%>/AccountingServlet"
			method="POST">
			<table>
				<tr>
					<th>商品名</th>
					<th>価格（円）</th>
					<th>数量</th>
				</tr>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td>${order.commodity.commodity_name}</td>
						<td>${order.commodity.commodity_price}</td>
						<td>${order.order_quantity}</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="total">合計</td>
					<td class="total">${total}</td>
					<td></td>
				</tr>
			</table>
		</form>

		<br>
		<hr>
		<br>

		<!-- 支払い方法フォーム -->
		<form id="paymentForm" action="<%= request.getContextPath() %>/AccountingServlet" method="POST">
			<p>支払い方法を選択：</p>
			<input type="hidden" name="visitId" value="${visitId}">
			<label><input type="radio" name="payment_method" value="現金">現金</label><br>
			<label><input type="radio" name="payment_method" value="paypay"> paypay</label><br>

			<!-- エラーメッセージ表示場所 -->
			<div id="errorMessage"></div>

			<button type="submit">会計</button>
		</form>

		<!-- ポップアップとオーバーレイ -->
		<div id="overlay"></div>
		<div id="popup">
			<div id="popupContent">読み込み中...</div>
			<button onclick="closePopup()">閉じる</button>
		</div>
	</main>

	<footer>
		<div id="footer">
			<p>&copy; おかゆ</p>
		</div>
	</footer>

	<!-- 外部JSファイル読み込み -->
	<script src="js/Accounting.js"></script>

</body>
</html>

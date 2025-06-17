<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>BARLOOP/業務画面</title>
<link rel="stylesheet" href="css/StoreBusiness.css">

<%-- 仮でcss記入してます --%>
<style>
.delete-btn {
	color: red;
	cursor: pointer;
}
</style>
<%-- 仮でcss記入してます --%>

</head>


<body>

	<header>
		<h1>業務画面</h1>
	</header>

	<%-- 編集前です --%>
	<button id="">O</button>
	<button id="">X</button>
	<div id="">
		<a href="/D3/StoreBusiness">業務画面</a><br> <a href="/D3/StoreStaff">事務画面</a><br>
		<a href="/D3/">ログアウト</a>
	</div>


	<main>

		<div>
			<h2>連絡事項</h2>

			<ul>
				<li>連絡１（変数）</li>
				<li>連絡２（変数）</li>
			</ul>
		</div>

		<%-- 仮でcss記入してます --%>
		<h2>注文</h2>
		<div
			style="height: 100px; overflow-y: scroll; border: 1px solid #ccc;">
			<div id="orderList">
				<c:if test="${empty orderData}">
					<p>注文なし</p>
				</c:if>

				<c:forEach var="order" items="${orderData}">
					<div class="order">
						${order.customer.customer_name}
						${order.commodity.commodity_name}
						${order.order_quantity}
						<button class="deleteBtn" onclick="removeItem(this)">X</button>
					</div>
				</c:forEach>
			</div>
		</div>

		<%-- 仮でcss記入してます --%>
		<h2>来店者一覧</h2>
		<div style="height: 100px; overflow-y: scroll; border: 1px solid #ccc;">
			<div id="visitors">
			<c:if test="${empty visitors}">
			<p>来店者なし</p>
			</c:if>
			
			<c:forEach var="visitor" items="${visitors}">
			<div class="visitor">
			${visitor.customer.customer_name}
			${visitor.commodity.commodity_name}
			${visitor.topic.topic_name}
			</div>
			</c:forEach>
			</div>
		</div>
	</main>

	<footer>
		<div class="copyright">
			<p>&copy;おかゆ</p>
		</div>
	</footer>




	<script src="${pageContext.request.contextPath}/js/StoreBusiness.js"></script>
</body>

</html>
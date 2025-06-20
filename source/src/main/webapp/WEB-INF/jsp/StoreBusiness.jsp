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

	<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/StoreBusinessServlet">業務</a><br>
		<a href="${pageContext.request.contextPath}/StoreStaffServlet">事務</a><br>
		
	</div>
	<p id="clock">time</p>


	<main>

		<div>
			<h2>連絡事項</h2>
			<div id="visitors">
				<c:if test="${empty visitors}">
					<p>来店者なし</p>
				</c:if>
				
				<c:forEach var="memo" items="${memoList}">
				<div class="memo">
				${memo.store_remark}
				</div>
				</c:forEach>
			</div>
		</div>

		<%-- 仮でcss記入してます --%>
		<h2>注文</h2>
		<label> <input type="checkbox" id="showComplete">
			完了も表示
		</label>
		<div id="orderList"></div>

		<%-- 仮でcss記入してます --%>
		<h2>来店者一覧</h2>
		<div
			style="height: 100px; overflow-y: scroll; border: 1px solid #ccc;">
			<div id="visitors">
				<c:if test="${empty visitors}">
					<p>来店者なし</p>
				</c:if>

				<c:forEach var="visitor" items="${visitors}">
					<div class="visitor">
						<button class="toggleBtn">▽</button>
						<a
							href="${pageContext.request.contextPath}/CustomerListServlet?customerId=${visitor.customer.customer_id}">
							${visitor.customer.customer_name}<br>
						</a>
						<div class="viditorDetails" style="display: none;">
							${visitor.commodity.commodity_name} ${visitor.topic.topic_name}
							<button class="payment" onclick="postAndRedirectPayment(${visitor.customer.customer_id})">会計
							</button>
						</div>
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
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>

</html>
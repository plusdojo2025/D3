<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>BARLOOP/注文履歴</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/OrderHistory.css' />">

</head>

<meta charset="UTF-8">
<title>BARLOOP/注文履歴</title>
<link rel="stylesheet" href="css/OrderHistory.css">


<style>
/* 見出しの色反転：ウイスキー、カクテル、ビールなど */
.accordion-toggle {
	width: 650px;
	margin: 20px auto 0 auto; /* 上マージン＋中央寄せ */
	display: block;
	cursor: pointer;
	background-color: rgb(227, 208, 149);
	color: rgb(14, 33, 72);
	padding: 10px;
	font-weight: bold;
	border: 1px solid rgb(14, 33, 72);
}
/* アコーディオンの中身 */
.accordion-content {
	display: none;
	margin-bottom: 20px;
}

/* テーブル全体 */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: center;
}

th {
	background-color: rgb(227, 208, 149);
	color: rgb(14, 33, 72);
}

/* 追加：商品データ行（td）にも反転色を適用 */
.categoryRankingTable td {
	background-color: rgb(14, 33, 72);
	color: rgb(227, 208, 149);
}
</style>
</head>

<body>
<p id="clock">time</p>
<header>
  <a href="<c:url value='/StoreStaffServlet' />">
<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
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

	<main>

	<form action="OrderHistoryServlet" method="get" class="date-search-form">
  <label for="targetDate">表示日付：</label>
  <input type="date" id="targetDate" name="targetDate" value="${today}">
  <input type="submit" value="表示" class="cool-submit">
</form>

		<!-- カテゴリランキング -->
		<div class="categoryRanking">
			<h2>カテゴリ</h2>

			<!-- ウイスキー -->
			<div class="accordion-section">
				<h3 class="accordion-toggle">WHISKY</h3>
				<div class="accordion-content">
					<table class="categoryRankingTable">
						<tr>
							<th>商品名</th>
							<th>個数</th>
							<th>日付</th>
						</tr>
						<c:forEach var="list2" items="${list2}" varStatus="status">
							<tr>
								<td>${list2.commodity_name}</td>
								<td>${list2Sum[status.index]}</td>
								<td>${today}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<!-- カクテル -->
			<div class="accordion-section">
				<h3 class="accordion-toggle">COCKTAIL</h3>
				<div class="accordion-content">
					<table class="categoryRankingTable">
						<tr>
							<th>商品名</th>
							<th>個数</th>
							<th>日付</th>
						</tr>
						<c:forEach var="list3" items="${list3}" varStatus="status">
							<tr>
								<td>${list3.commodity_name}</td>
								<td>${list3Sum[status.index]}</td>
								<td>${today}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<!-- ビール -->
			<div class="accordion-section">
				<h3 class="accordion-toggle">BEER</h3>
				<div class="accordion-content">
					<table class="categoryRankingTable">
						<tr>
							<th>商品名</th>
							<th>個数</th>
							<th>日付</th>
						</tr>
						<c:forEach var="list4" items="${list4}" varStatus="status">
							<tr>
								<td>${list4.commodity_name}</td>
								<td>${list4Sum[status.index]}</td>
								<td>${today}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<!-- 注文ランキング -->
		<div class="orderRanking">
			<h2>ランキング</h2>

			<!-- フード -->
			<div class="accordion-section">
				<h3 class="accordion-toggle">FOOD</h3>
				<div class="accordion-content">
					<table class="categoryRankingTable">

						<tr>
							<th>順位</th>
							<th>品目</th>
							<th>個数</th>
							<th>日付</th>
						</tr>
						<tr>
							<td>1</td>
							<td>${best3food[0].commodity_name}</td>
							<td>${best3foodSum[0]}</td>
							<td>${today}</td>
						</tr>
						<tr>
							<td>2</td>
							<td>${best3food[1].commodity_name}</td>
							<td>${best3foodSum[1]}</td>
							<td>${today}</td>
						</tr>
						<tr>
							<td>3</td>
							<td>${best3food[2].commodity_name}</td>
							<td>${best3foodSum[2]}</td>
							<td>${today}</td>
						</tr>
					</table>
				</div>
			</div>

			<!-- ドリンク -->
			<div class="accordion-section">
				<h3 class="accordion-toggle">DRINK</h3>
				<div class="accordion-content">
					<table class="categoryRankingTable">

						<tr>
							<th>順位</th>
							<th>品目</th>
							<th>個数</th>
							<th>日付</th>
						</tr>
						<tr>
							<td>1</td>
							<td>${best3drink[0].commodity_name}</td>
							<td>${best3drinkSum[0]}</td>
							<td>${today}</td>
						</tr>
						<tr>
							<td>2</td>
							<td>${best3drink[1].commodity_name}</td>
							<td>${best3drinkSum[1]}</td>
							<td>${today}</td>
						</tr>
						<tr>
							<td>3</td>
							<td>${best3drink[2].commodity_name}</td>
							<td>${best3drinkSum[2]}</td>
							<td>${today}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

	</main>

	<footer>
		<div class="copyright">
			<p>&copy; おかゆ</p>
		</div>
	</footer>

	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/OrderHistory.js"></script>

</body>
</html>

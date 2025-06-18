<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>BARLOOP/注文履歴</title>
<link rel="stylesheet" href="css/OrderHistory.css">
</head>


<body>


<p id="clock">time</p>

<a href="/D3/StoreStaffServlet">
<img src="/D3/img/BARLOOP.png" alt="BARLOOP" width="200" height="100">
</a>

<header>
</header>



<main>
	<div class="categoryRanking">
	<h2>カテゴリランキング</h2>
		<table class="categoryRankingTable">
				
			<tr>
				<th>ウイスキー</th>
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
			
			
			<tr>
				<th>カクテル</th>
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
			
			<tr>
				<th>ビール</th>
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
	
	<div class="orderRanking">
		<h2>注文ランキング</h2>

		<table class="orderRankingTable">
			<tr>
				<th>フード</th>
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
			
			<tr>
				<th>ドリンク</th>
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
	
	
</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>

<script src="${pageContext.request.contextPath}/js/common.js"></script>

</body>



</html>
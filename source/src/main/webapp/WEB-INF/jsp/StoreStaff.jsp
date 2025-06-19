<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/店舗事務</title>
</head>


<body>
<header>
<h2>店舗事務画面</h2>
</header>

<main>
<div>
イベント
<form method="POST" action="/D3/StoreStaffServlet">
	<input type="text" name="event_name" placeholder="イベント名">
	<input type="text" name="event_remark" placeholder="備考">
	<input type="date" name="event_date" >
	<input type="submit" name="event">
</form>
</div>

<div>
業務連絡
<form method="POST" action="/D3/StoreStaffServlet">
	<input type="text" name="store_remark" placeholder="連絡内容">
	<input type="date" name="store_date" id="store_date" >
	<input type="submit" name="info">
</form>
<br><br><br><br>
</div>



<div>

<h2>顧客リスト</h2>
<form method="POST" action="/D3/CustomerListServlet">
<input type="text" name="customer_name" placeholder="ニックネーム"><br>

<input type="submit" name="submit" value="検索"><br>
</form>
<br>
</div>

<div>
<p>今日来店したお客様</p>
<ul>
<c:forEach var="vis" items="${visitor}">
<li>${vis.customer.customer_name }</li>
</c:forEach>
<li>ニックネーム（変数）</li>
</ul>

</div>
	
<div>
<a href="/D3/OrderHistoryServlet">
<button>注文履歴</button>
</a>
</div>
	
</main>

<footer>
<div id="footer">
<p>&copy;おかゆ</p>
</div>
</footer>

</body>

</html>
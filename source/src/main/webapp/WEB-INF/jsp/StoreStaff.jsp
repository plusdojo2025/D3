
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/事務画面</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/StoreStaff.css' />">
</head>


<body>

<p id="clock">time</p>
<header>
<img src="/D3/img/BARLOOP.png" alt="BARLOOP" class="icon" width="250">


<h2>店舗事務画面</h2>
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
<div>
イベント
<form method="POST" action="<c:url value='/StoreStaffServlet' />">
	<input type="text" name="event_name" placeholder="イベント名">
	<input type="text" name="event_remark" placeholder="備考">
	<input type="date" name="event_date" >
	<input type="submit" name="event">
</form>
</div>

<div>
業務連絡
<form method="POST" action="<c:url value='/StoreStaffServlet' />">
	<input type="text" name="store_remark" placeholder="連絡内容">
	<input type="date" name="store_date" >
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
<li>ニックネーム（変数）</li>
</c:forEach>

<li>ニックネーム（変数）</li>
</ul>
<br><br><br><br><br>

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
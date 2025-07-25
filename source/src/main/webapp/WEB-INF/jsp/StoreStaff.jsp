
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
<style>


}
</style>

<body>

<p id="clock">time</p>
<header>
  <a href="<c:url value='/StoreBusinessServlet' />">
<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
</a>
</header>
<h1>事務画面</h1>

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
<h2>イベント</h2>
<form id="eventForm" method="POST" action="<c:url value='/StoreStaffServlet' />">
	<input type="text" name="event_name" placeholder="イベント名">
	<input type="text" name="event_remark" placeholder="イベント内容">
	<input type="date" name="event_date" >
	<input type="submit" name="event">
</form>
</div>

<div>
<h2>業務連絡</h2>
<form id="infoForm" method="POST" action="<c:url value='/StoreStaffServlet' />">
	<input type="text" name="store_remark" placeholder="連絡内容">
	<input type="date" name="store_date" >
	<input type="submit" name="info">
</form>

</div>



<div>


<h2>顧客リスト</h2>
<form method="POST" action="<c:url value='/CustomerListServlet' />">
<input type="text" name="customer_name" placeholder="ニックネーム"><br>

<input type="submit" name="submit" value="検索"><br>
</form>
<br>
</div>

<h2 class="accordion-toggle">今日来店したお客様 ▼</h2>
<div class="accordion-content" >
        <c:forEach var="vis" items="${visitor}" varStatus="status">
    <li>
      <form id="customerForm_${status.index}" method="POST" action="${pageContext.request.contextPath}/CustomerListServlet" style="display: none;">
        <input type="hidden" name="customer_name" value="${vis.customer.customer_name}" />
      </form>

      <a href="#" onclick="document.getElementById('customerForm_${status.index}').submit(); return false;">
        ${vis.customer.customer_name}
      </a>
    </li>
  </c:forEach>
    </ul>
</div>


	
<div>
<a href="<c:url value='/OrderHistoryServlet'/>">
<button name="acc">注文履歴</button>
</a>
</div>
	
</main>

<footer>
<div id="footer">
<p>&copy;おかゆ</p>
</div>
</footer>

	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/StoreStaff.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	
</body>


</html>
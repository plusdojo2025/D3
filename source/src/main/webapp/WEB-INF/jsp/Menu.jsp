<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/メニュー</title>
</head>


<body>
<header>
	<!-- >画像リンク -->
</header>

<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
	<a href="/D3/CustomerHomeServlet">ホーム</a><br>
	<a href="/D3/MenuListServlet">メニュー</a><br>
	<a href="/D3/QRCodeServlet">QRコード</a>
</div>


<main>
<h1>メニュー</h1>
<ul>
<li>カクテル</li>
<li>ウイスキー</li>
<li>ビール</li>
<li>フード</li>
</ul>


<div>
<table>
	
	
	<c:forEach var="menu" items="${menuList}" varStatus="status">
	<c:if test="${status.index % 3 == 0}">
      <tr>
    </c:if>
		<td>
		<table>
			
			<tr>
				<td><img src="${menu.commodity_image}" alt="メニュー"></td>
			</tr>
			<tr>
				<td>${menu.commodity_name}</td>
			</tr>
			<tr>
				<td>￥${menu.commodity_price}</td>
			</tr>
			
		</table>
		
		</td>
		<c:if test="${(status.index + 1) % 3 == 0}">
            </tr>
        </c:if>
		</c:forEach>
	
	


	
</table>
</div>

<form method="POST" action="/D3/OrderSubmitServlet">
<input type=submit value="注文する">
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






<div>
<br><br><br><br><br>
<form>
<table>
			
<tr>
<td><img src="変数" alt="変数未定義"></td>
<tr>
<td>ジントニック(変数)</td>
</tr>
<tr>
<td>￥金額(変数)</td>
</tr>

			
</table>
<select name="options">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>   
    <option value="6">6</option>
    <option value="7">7</option>
    <option value="8">8</option>
    <option value="9">9</option>
</select>
<input type=submit value="追加">
<input type=button value="キャンセル">

</form>
</div>

</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>

</body>
</html>
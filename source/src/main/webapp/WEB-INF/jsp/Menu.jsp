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
<form method="GET" action="/D3/MenuListServlet">
<input type="submit" value="cocktail" name="category">
<input type="submit" value="whisky" name="category">
<input type="submit" value="beer" name="category">
<input type="submit" value="food" name="category">
</form>


<div>
<table>
	
	
	<c:forEach var="menu" items="${menuList}" varStatus="status">
	<c:if test="${status.index % 3 == 0}">
      <tr>
    </c:if>
		<td>
		<table>
			
			<tr>
				<td><img src="/D3/img/${menu.commodity_image}" alt="メニュー" width="150" height="150"></td>
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







<br><br><br><br><br>
	
<form>
<div>
<p><img src="画像変数" alt="メニュー"></p>
<p>商品名(変数)</p>
<p>￥金額(変数)</p>
</div>

			

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


</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>

</body>
</html>
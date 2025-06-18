<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.Cart" %>
<%@ page import="dto.Commodity" %>
<%@ page import="dao.CartDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page session="true" %>
<%
    List<Cart> cart = (List<Cart>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<Cart>();
        session.setAttribute("cart", cart);
    }

    String action = request.getParameter("action");
    String product = request.getParameter("product");

    if ("add".equals(action) && product != null) {
    	int id=Integer.parseInt(request.getParameter("id"));
    	String name=request.getParameter("name");
    	int price=Integer.parseInt(request.getParameter("price"));
    	int category=Integer.parseInt(request.getParameter("category"));
    	String image=request.getParameter("image");
    	
    	int num=Integer.parseInt(request.getParameter("num"));
    	CartDAO dao = new CartDAO();
    	Commodity data=dao.getCommodityById(id);
    	cart.add(new Cart(data,num));
    	session.setAttribute("cart", cart);
    }
%>
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

<form method="post" action="<%= request.getContextPath() %>/OrderSubmitServlet">
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


<form method="get" action="#">

<p><img src="/D3/img/1.jpg" alt="メニュー"  width="150" height="150"></p>
<input type="text" value="フライドポテト(変数)" name="name" readonly><br>
<input type="text" value="￥770(変数)" name="price1" readonly><br>


<input type="hidden" value="3" name="id" readonly>
<input type="hidden" value="1gpg" name="image" readonly>
<input type="hidden" value="770" name="price" readonly>
<input type="hidden" value="1" name="category" readonly>
<input type="hidden" name="action" value="add"><br>
			

<select name="num">
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
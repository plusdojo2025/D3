<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<form>
	<input type="text" name="event" placeholder="イベント名">
	<input type="text" name="event" placeholder="備考">
	<input type="date" name="event" >
	<input type="submit" name="event">
</form>
</div>

<div>
業務連絡
<form>
	<input type="text" name="info" placeholder="連絡内容">
	<input type="date" name="info" >
	<input type="submit" name="info">
</form>
<br><br><br><br>
</div>



<div>
<a href="/D3/CustomerListServlet">
<button>顧客リスト</button>
</a>
<form method="POST" action="/D3/CustomerListServlet">
<input type="text" name="customer_name" placeholder="ニックネーム"><br>
<input type="text" name="customer_name" placeholder="いつもの"><br>

<input type="submit" name="submit" value="検索"><br>
</form>
<br>
</div>

<div>
<p>今日来店したお客様</p>
<ul>
<li>ニックネーム（変数）</li>
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
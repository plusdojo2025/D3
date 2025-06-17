<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗事務画面</title>
</head>
<body>
 <h2>顧客検索</h2>
<hr>
<form method="POST" action="/D3/CustomerListServlet">
ユーザーネーム<input type="text" name="customer_name"><br>
<input type="submit" name="submit" value="検索"><br>
</form>
  <!-- メイン（ここまで） -->
  <!-- フッター（ここから） -->
  <div id="footer">
    <p>&copy;おかゆ</p>
  </div>
  <!-- フッター（ここまで） -->
</body>
</html>
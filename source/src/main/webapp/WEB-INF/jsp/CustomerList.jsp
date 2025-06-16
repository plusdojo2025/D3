<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客一覧</title>
</head>
<body>
	<!-- ヘッダー（ここから） -->
	<h1>顧客一覧</h1>
	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->
	<p class="customer_data">
		<c:forEach var="e" items="${customerList}">
			<form method="POST" action="/D3/UpdateDeleteCustomerServlet">
				<input type="hidden" name="customer_id" value="${e.customer_id}"><br>
				ユーザーネーム<input type="text" name="customer_name" value="${e.customer_name}"><br>
				誕生日<input type="text" name="customer_birthday" value="${e.customer_birthday}"><br>
				メールアドレス<input type="text" name="customer_email" value="${e.customer_email}"><br>
				<!-- いつもの<input type="text" name="" value="${e.}"><br>
				内容<input type="text" name="talk_remark" value="${e.talk_remark}"> <br>
				会話タグ<input type="text" name="topic_name" value="${e.topic_name}"><br>
				ボトル情報<input type="button" name="" value="${e.}"><br>
				注文履歴<input type="button" name="" value="${e.}"><br>
				最終来店履歴<input type="button" name="" value="${e.}"><br>
				<input type="submit" name="submit" value="更新">
				<input type="submit" name="submit" value="削除"> <br>-->
			</form>
		</c:forEach>
		<c:if test="${empty customerList}">
			<p>指定された条件に一致するデータはありません。</p>
		</c:if>
	<p>
		<!-- メイン（ここまで） -->
		<!-- フッター（ここから） -->
	<div id="footer">
		<p>&copy;Copyright plusDOJO(SE plus). All rights reserved.</p>
	</div>
	<!-- フッター（ここまで） -->
	<!-- JavaScript（ここから） -->
	<!-- JavaScript（ここまで） -->
</body>
</html>
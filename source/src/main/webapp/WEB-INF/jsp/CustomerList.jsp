<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客一覧</title>
</head>
<body>
<h1>顧客一覧</h1>

<c:forEach var="e" items="${customerList}">
    <form method="POST" action="/D3/UpdateDeleteCustomerServlet">
        <input type="hidden" name="customer_id" value="${e.customer_id}">
        ユーザーネーム: <input type="text" name="customer_name" value="${e.customer_name}"><br>
        誕生日: <input type="text" name="customer_birthday" value="${e.customer_birthday}"><br>
        メールアドレス: <input type="text" name="customer_email" value="${e.customer_email}"><br>
        いつもの: <input type="text" name="every" value="${every}"><br>
        
        <p>会話内容:</p>
    <c:forEach var="t" items="${talkList}">
        <c:if test="${t.customer_id == e.customer_id}">
            ・<strong>タグID:</strong> ${t.topic_id}<br>
            ・<strong>内容:</strong> ${t.talk_remark}<br><br>
        </c:if>
    </c:forEach>
        
           <!-- ここに注文履歴といつもの商品を表示 -->
          <p>注文履歴：</p>
        <c:forEach var="order" items="${orderHistoryMap[e.customer_id]}">
            ・${order.commodity.commodity_name} （注文日: <fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd" />）<br>
        </c:forEach>
        <c:if test="${empty orderHistoryMap[e.customer_id]}">
            注文履歴はありません。<br>
        </c:if>
        
        <p>キープボトル情報：</p>
        <c:forEach var="kb" items="${keepBottoleMap[e.customer_id]}">
            <input type="hidden" name="bottole_id" value="${kb.bottole_id}" />
            商品名: ${kb.commodity.commodity_name}<br>
            残量: <input type="number" name="bottole_remaining_${kb.bottole_id}" value="${kb.bottole_remaining}" min="0" /><br>
            期限: <fmt:formatDate value="${kb.bottole_rimit}" pattern="yyyy-MM-dd" /><br>

            <button type="submit" name="action" value="update_bottle_${kb.bottole_id}">更新</button>
            <button type="submit" name="action" value="delete_bottle_${kb.bottole_id}" onclick="return confirm('削除してよろしいですか？');">削除</button><br><br>
        </c:forEach>

        <input type="submit" name="submit" value="顧客情報更新">
        <input type="submit" name="submit" value="顧客情報削除"><br><br>
    </form>
</c:forEach>

<c:if test="${empty customerList}">
    <p>指定された条件に一致するデータはありません。</p>
</c:if>

<div id="footer">
    <p>&copy; おかゆ</p>
</div>
</body>
</html>

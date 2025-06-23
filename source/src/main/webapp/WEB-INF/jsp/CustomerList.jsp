<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/顧客一覧</title>
</head>
<body>
<p id="clock">time</p>
<header>
<a href="<c:url value='/StoreBusinessServlet' />">
<img src="/D3/img/BARLOOP.png" alt="BARLOOP" class="icon" width="250">
</a>
</header>

<button id="openSiteMap">O</button>
	<button id="closeSiteMap">X</button>
	<div id="siteMapPanel">
		<a href="${pageContext.request.contextPath}/StoreBusinessServlet">業務</a><br>
		<a href="${pageContext.request.contextPath}/StoreStaffServlet">事務</a><br>
		<a href="${pageContext.request.contextPath}/QRCodeServlet">QRコード</a><br>
		<a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a><br>
		
	</div>
	<p id="clock">time</p>

<main>
	<h1>顧客一覧</h1>

	<c:forEach var="e" items="${customerList}" varStatus="status">

	<form method="POST" action="/D3/UpdateDeleteCustomerServlet">
		<input type="hidden" name="customer_id" value="${e.customer_id}">
		ユーザーネーム: <input type="text" name="customer_name" value="${e.customer_name}"><br>
		誕生日: <input type="text"name="customer_birthday" value="${e.customer_birthday}"><br>
		メールアドレス: <input type="text" name="customer_email" value="${e.customer_email}"><br>
		いつもの: <input type="text" name="every" value="${every[status.index]}"><br>
		
	

	<p>会話内容:</p>
	<c:forEach var="t" items="${talkMap[e.customer_id]}">
    ・<strong>会話タグ:</strong>
    
    <select name="talk_topic_id_${t.topic_id}">
        <c:forEach var="tag" items="${topicTagList}">
            <option value="${tag.topic_id}" <c:if test="${tag.topic_id == t.topic_id}">selected</c:if>>
                ${tag.topic_name}
            </option>
        </c:forEach>
    </select>
    
    <br>
    ・<strong>内容:</strong>
    <input type="text" name="talk_remark_${t.topic_id}" value="${t.talk_remark}">
    <br>
    <button type="submit" name="action" value="update_talk_${t.topic_id}">会話更新</button>
    <button type="submit" name="action" value="delete_talk_${t.topic_id}" onclick="return confirm('削除しますか？');">会話削除</button>
    <br><br>
	</c:forEach>


	<p>会話新規登録:</p>
	話題タグ:
	<select name="new_topic_id">
		<c:forEach var="tag" items="${topicTagList}">
			<option value="${tag.topic_id}">${tag.topic_name}</option>
		</c:forEach>
	</select>
	<br> 
	内容:<input type="text" name="new_talk_remark">
	<br>
	<button type="submit" name="action" value="insert_talk">新規登録</button>

	<p>キープボトル情報：</p>
	<c:set var="customerId" value="${e.customer_id}" />
	<c:forEach var="kb" items="${keepBottleList}">
		<c:if test="${kb.customer.customer_id == customerId}">
      商品名: ${kb.commodity.commodity_name}<br>
      残量: <input type="number" name="bottle_remaining_${kb.bottle_id}" value="${kb.bottle_remaining}" min="0"><br>
      期限: <fmt:formatDate value="${kb.bottle_limit}" pattern="yyyy-MM-dd" /><br>
			<button type="submit" name="action" value="update_bottle_${kb.bottle_id}">更新</button>
			<button type="submit" name="action" value="delete_bottle_${kb.bottle_id}" onclick="return confirm('削除しますか？');">削除</button>
			<br>
			<br>
			
		</c:if>
	</c:forEach>
	
	<p>キープボトル新規登録:</p>
    <select name="new_commodity_id">
        <c:forEach var="item" items="${commodityList}">
            <option value="${item.commodity_id}">${item.commodity_name}</option>
        </c:forEach>
    </select>
    <input type="number" name="new_bottle_remaining" placeholder="残量">
    <input type="date" name="new_bottle_limit" placeholder="期限">
    <button type="submit" name="action" value="insert_bottle">ボトル登録</button>

	<input type="submit" name="submit" value="顧客情報更新">
	<input type="submit" name="submit" value="顧客情報削除">
	<br>
	<br>
	</form>
	</c:forEach>

	<c:if test="${empty customerList}">
		<p>指定された条件に一致するデータはありません。</p>
	</c:if>
</main>

<footer>
	<div id="footer">
		<p>&copy; おかゆ</p>
	</div>
</footer>
</body>
</html>

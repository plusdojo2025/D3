<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BARLOOP/顧客一覧</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/CustomerList.css' />">
</head>
<body>
<p id="clock">time</p>
<header>
<a href="<c:url value='/StoreBusinessServlet' />">
<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
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


<main>
	<h1>顧客一覧</h1>

	<c:forEach var="e" items="${customerList}" varStatus="status">
		<form method="POST" action="${pageContext.request.contextPath}/UpdateDeleteCustomerServlet">
			<div class="customer-row">
				<input type="hidden" name="customer_id" value="${e.customer_id}">
				
				<!-- 基本情報 -->
				<div class="customer-info">
					<p><strong>ユーザーネーム:<br></strong> <input type="text" name="customer_name" value="${e.customer_name}"></p>
					<p><strong>誕生日:<br></strong> <input type="text" name="customer_birthday" value="${e.customer_birthday}"placeholder="1990-01-01"></p>
					<p><strong>メールアドレス:<br></strong> <input type="text" name="customer_email" value="${e.customer_email}"></p>
					<p><input type="hidden" name="customer_password" value="${e.customer_password}"></p>
					<p><strong>いつもの:<br></strong> <input type="text" name="every" value="${every[status.index]}"readonly id="itumo"></p>
					<input type="submit" name="submit" value="顧客情報更新" class="cool-submit-small">
					<input type="submit" name="submit" value="顧客情報削除" class="cool-submit-small">
				
				</div>
				
				

				<!-- 会話 -->
				<div class="talk-section">
					<p><strong>会話内容:</strong></p>
<c:forEach var="t" items="${talkMap[e.customer_id]}">
    <p>
        <select disabled>
            <c:forEach var="tag" items="${topicTagList}">
                <option value="${tag.topic_id}" <c:if test="${tag.topic_id == t.topic_id}">selected</c:if>>
                    ${tag.topic_name}
                </option>
            </c:forEach>
        </select>
        <input type="hidden" name="talk_topic_id_${t.topic_id}" value="${t.topic_id}">
        
        <textarea name="talk_remark_${t.topic_id}" rows="3" cols="40">${t.talk_remark}</textarea><br>
        
        <button type="submit" name="action" value="update_talk_${t.topic_id}" class="cool-submit-mini">更新</button>
        <button type="submit" name="action" value="delete_talk_${t.topic_id}" class="cool-submit-mini">削除</button>
    </p>
</c:forEach>

					<p><strong>会話新規登録:</strong></p>
					<p>
						<select name="new_topic_id">
							<c:forEach var="tag" items="${topicTagList}">
								<option value="${tag.topic_id}">${tag.topic_name}</option>
							</c:forEach>
						</select>
						<textarea name="new_talk_remark" placeholder="会話内容" rows="3" cols="40"></textarea><br>
						<button type="submit" name="action" value="insert_talk" class="cool-submit-mini">登録</button>
					</p>
				</div>

				<!-- キープボトル -->
				<div class="bottle-section">
					<p><strong>キープボトル情報:</strong></p>
					<c:forEach var="kb" items="${keepBottleList}">
						<c:if test="${kb.customer.customer_id == e.customer_id}">
							<p class="keep">
								商品名: <strong>${kb.commodity.commodity_name}　</strong><br>
								
								期限: <strong><fmt:formatDate value="${kb.bottle_limit}" pattern="yyyy-MM-dd" /></strong>
								残量: <input type="number" class="bold-input" name="bottle_remaining_${kb.bottle_id}" value="${kb.bottle_remaining}" min=0 style="width: 70px;">ml<br>
								<button type="submit" name="action" value="update_bottle_${kb.bottle_id}" class="cool-submit-mini">更新</button>
								<button type="submit" name="action" value="delete_bottle_${kb.bottle_id}" class="cool-submit-mini">削除</button>
							</p>
						</c:if>
					</c:forEach>

					<p><strong>キープボトル新規登録:</strong></p>
					<p>
						<select name="new_commodity_id">
							<c:forEach var="item" items="${commodityList}">
								<option value="${item.commodity_id}">${item.commodity_name}</option>
							</c:forEach>
						</select>
						<input type="number" name="new_bottle_remaining" placeholder="残量[ml]" min=0>
						<input type="date" name="new_bottle_limit" placeholder="期限">
						<button type="submit" name="action" value="insert_bottle" class="cool-submit-mini">ボトル登録</button>
					</p>
				</div>

				<!-- ボタン -->
				
			</div>
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
<script src="${pageContext.request.contextPath}/js/CustomerList.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>BARLOOP/会計確認</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/Accounting.css' />">
</head>

<body data-context="${pageContext.request.contextPath}">

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
        <h2>会計内容の確認</h2>

        <form action="<%=request.getContextPath()%>/AccountingServlet" method="POST">
            <table>
                <tr>
                    <th>商品名</th>
                    <th>価格（円）</th>
                    <th>数量</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.commodity.commodity_name}</td>
                        <td>${order.commodity.commodity_price}</td>
                        <td>${order.order_quantity}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td class="total">合計</td>
                    <td class="total">${total}</td>
                    <td></td>
                </tr>
            </table>
        </form>

        <br>
        <hr>
        <br>

        <!-- 支払い方法フォーム -->
        <form id="paymentForm" action="<%= request.getContextPath() %>/AccountingServlet" method="POST" onsubmit="event.preventDefault(); submitPayment();">
            <p>支払い方法を選択：</p>
            <input type="hidden" name="visitId" value="${visitId}">
            <label><input type="radio" name="payment_method" value="現金">現金</label><br>
            <label><input type="radio" name="payment_method" value="paypay"> paypay</label><br>

            <!-- エラーメッセージ表示場所 -->
            <div id="errorMessage"></div>

            <button type="submit">会計</button>
        </form>

        <!-- ポップアップとオーバーレイ -->
        <div id="overlay"></div>
        <div id="popup">
            <div id="popupContent">読み込み中...</div>
            <button onclick="closePopup()" style="margin-top: 15px; padding: 10px 20px;">閉じる</button>
        </div>
    </main>

    <footer>
        <div id="footer">
            <p>&copy; おかゆ</p>
        </div>
    </footer>

    <!-- 外部JSファイル読み込み -->
    <script src="${pageContext.request.contextPath}/js/Accounting.js"></script>

</body>
</html>

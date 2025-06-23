<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BARLOOP/会計確認</title>
    <link rel="stylesheet" href="<c:url value='/css/common.css' />">
    <link rel="stylesheet" href="<c:url value='/css/Accounting.css' />">
</head>
<body data-context="${pageContext.request.contextPath}">

<header>
    <a href="<c:url value='/StoreBusinessServlet' />">
        <img src="/D3/img/BARLOOP.png" alt="BARLOOP" class="icon" width="250">
    </a>
</header>

<main>
    <p>${nickname} 様</p>
    <h2>会計内容の確認</h2>

    <c:if test="${not empty orderDetails}">
        <table>
            <tr>
                <th>商品名</th>
                <th>価格（円）</th>
                <th>数量</th>
            </tr>
            <c:forEach var="item" items="${orderDetails}">
                <tr>
                    <td>${item.commodity_name}</td>
                    <td>${item.commodity_price}</td>
                    <td>${item.order_quantity}</td>
                </tr>
            </c:forEach>
            <tr>
                <td class="total">合計</td>
                <td class="total">${total} 円</td>
                <td></td>
            </tr>
        </table>

        <br><hr><br>

        <form id="paymentForm">
            <p>支払い方法を選択：</p>
            <label><input type="radio" name="payment_method" value="現金"> 現金</label><br>
            <label><input type="radio" name="payment_method" value="paypay"> PayPay</label><br>

            <div id="errorMessage" style="color:red;"></div>

            <c:forEach var="item" items="${orderDetails}">
                <input type="hidden" name="commodity_id" value="${item.commodity_id}" />
                <input type="hidden" name="order_quantity" value="${item.order_quantity}" />
            </c:forEach>
            <input type="hidden" name="total" value="${total}" />

            <br>
            <!-- 普通のsubmitではなくボタンでJSのsubmitPayment()を呼び出す -->
            <input type="button" value="会計を確定する" onclick="submitPayment()">
        </form>
    </c:if>

    <c:if test="${empty orderDetails}">
        <p>注文情報がありません。</p>
    </c:if>

    <!-- 既存の会計内容表示・フォームのコード -->

    <!-- ここにポップアップ用のHTMLを追加 -->
    <div id="overlay" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:#00000080; z-index:999;"></div>

    <div id="popup" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%);
        background:#fff; padding:20px; border-radius:8px; z-index:1000; max-width:400px;">
        <div id="popupContent"></div>
        <button onclick="closePopup()">閉じる</button>
    </div>
</main>


<footer>
    <div class="copyright">
        <p>&copy; おかゆ</p>
    </div>
</footer>

<script src="<c:url value='/js/Accounting.js' />"></script>
</body>
</html>

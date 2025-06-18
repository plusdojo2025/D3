<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>会計確認</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        table {
            border-collapse: collapse;
            width: 60%;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #ddd;
        }
        .total {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }

        /* ポップアップスタイル */
        #popup {
            display: none;
            position: fixed;
            top: 20%;
            left: 30%;
            width: 40%;
            background: #fff;
            border: 2px solid #333;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
            padding: 20px;
            z-index: 9999;
        }

        #popupContent {
            margin-bottom: 10px;
        }

        #popup button {
            padding: 5px 10px;
        }

        #overlay {
            display: none;
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: rgba(0,0,0,0.3);
            z-index: 9998;
        }
    </style>
</head>
<body>

<h2>会計内容の確認</h2>

<%
    String[] commodity_name = (String[]) request.getAttribute("commodity_name");
    String[] commodity_price = (String[]) request.getAttribute("commodity_price");
    String[] order_quantity = (String[]) request.getAttribute("order_quantity");
    Integer total = (Integer) request.getAttribute("total");
%>

<% if (commodity_name != null && commodity_price != null && order_quantity != null && commodity_name.length > 0) { %>
    <table>
        <tr>
            <th>商品名</th>
            <th>価格（円）</th>
            <th>数量</th>
        </tr>
        <% for (int i = 0; i < commodity_name.length; i++) { %>
            <tr>
                <td><%= commodity_name[i] %></td>
                <td><%= commodity_price[i] %></td>
                <td><%= order_quantity[i] %></td>
            </tr>
        <% } %>
        <tr>
            <td class="total">合計</td>
            <td class="total"><%= total %> 円</td>
            <td></td>
        </tr>
    </table>

    <br><hr><br>

    <!-- 支払い方法フォーム -->
    <form id="paymentForm">
        <p>支払い方法を選択：</p>
        <label><input type="radio" name="payment_method" value="現金" required> 現金</label><br>
        <label><input type="radio" name="payment_method" value="paypay"> paypay</label><br><br>

        <% for (int i = 0; i < commodity_name.length; i++) { %>
            <input type="hidden" name="commodity_name" value="<%= commodity_name[i] %>">
            <input type="hidden" name="commodity_price" value="<%= commodity_price[i] %>">
            <input type="hidden" name="order_quantity" value="<%= order_quantity[i] %>">
        <% } %>
        <input type="hidden" name="total" value="<%= total %>">

        <input type="button" value="会計を確定する" onclick="submitPayment()">
    </form>
<% } else { %>
    <p>注文情報がありません。</p>
<% } %>

<!-- ポップアップとオーバーレイ -->
<div id="overlay"></div>
<div id="popup">
    <div id="popupContent">読み込み中...</div>
    <button onclick="closePopup()">閉じる</button>
</div>

<script>
function submitPayment() {
    const form = document.getElementById('paymentForm');
    const formData = new FormData(form);

    fetch('<%= request.getContextPath() %>/PaymentComplete', {
        method: 'POST',
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('popupContent').innerHTML = html;
        document.getElementById('popup').style.display = 'block';
        document.getElementById('overlay').style.display = 'block';
    })
    .catch(err => {
        alert("エラーが発生しました：" + err);
    });
}

function closePopup() {
    document.getElementById('popup').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function closePopup() {
    document.getElementById('popup').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';

    // 🔽 閉じた後にページ遷移する
    window.location.href = '<%= request.getContextPath() %>/menu.jsp';
}
</script>

</body>
</html>

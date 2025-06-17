<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        h2 {
            margin-bottom: 20px;
        }
        .total {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }
    </style>
</head>
<body>

<h2>会計内容の確認</h2>

<%
    String[] commodity_name = (String[]) request.getAttribute("commodity_name");
    String[] commodity_price = (String[]) request.getAttribute("commodity_price");
    
    Integer total = (Integer) request.getAttribute("total");
%>

<% if (commodity_name != null && commodity_price != null && commodity_name.length > 0) { %>
    <table>
        <tr>
            <th>商品名</th>
            <th>価格（円）</th>
        </tr>
        <% for (int i = 0; i < commodity_name.length; i++) { %>
            <tr>
                <td><%= commodity_name[i] %></td>
                <td><%= commodity_price[i] %></td>
            </tr>
        <% } %>
        <tr>
            <td class="total">合計</td>
            <td class="total"><%= total %> 円</td>
        </tr>
    </table>
<% } else { %>
    <p>注文情報がありません。</p>
<% } %>

</body>
</html>

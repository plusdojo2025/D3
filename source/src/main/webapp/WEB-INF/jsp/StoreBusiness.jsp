<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>BARLOOP/業務画面</title>
<link rel="stylesheet" href="css/StoreBusiness.css">

<%-- 仮でcss記入してます --%>
  <style>
    .delete-btn { color: red; cursor: pointer; }
  </style>
<%-- 仮でcss記入してます --%>

</head>


<body>

<header>
	<h1>業務画面</h1>
</header>

<%-- 編集前です --%>
<button id="">O</button>
<button id="">X</button>
<div id="">
	<a href="/D3/StoreBusiness">業務画面</a><br>
	<a href="/D3/StoreStaff">事務画面</a><br>
	<a href="/D3/">ログアウト</a>
</div>


<main>

<div>
<h2>連絡事項</h2>

<ul>
<li>連絡１（変数）</li>
<li>連絡２（変数）</li>
</ul>
</div>

<%-- 仮でcss記入してます --%>
<h2>注文</h2>
<div style="height: 100px; overflow-y: scroll; border: 1px solid #ccc;">
<ul>
<li>ユーザー１：ビール（変数）<span class="delete-btn" onclick="removeItem(this)">×</span></li>
<li>ユーザー２：ハイボール（変数）<span class="delete-btn" onclick="removeItem(this)">×</span></li>
</ul>
</div>

<%-- 仮でcss記入してます --%>
<h2>来店者一覧</h2>
<div style="height: 100px; overflow-y: scroll; border: 1px solid #ccc;">
<div>
<table>
	<tr>
	<th>ニックネーム１（変数）</th>
	</tr>
	<tr>
	<td>いつもの</td>
	<td>ビール（変数）</td>
	</tr>
	<tr>
	<td>会話タグ</td>
	<td>スポーツ（変数）</td>
	</tr>	
</table>
<button>会計</button>
</div>

<div>
<table>
	<tr>
	<th>ニックネーム２（変数）</th>
	</tr>
	<tr>
	<td>いつもの</td>
	<td>枝豆（変数）</td>
	</tr>
	<tr>
	<td>会話タグ</td>
	<td>政治（変数）</td>
	</tr>	
</table>
<button>会計</button>
</div>

</div>




	
</main>

<footer>
<div class="copyright">
<p>&copy;おかゆ</p>
</div>
</footer>




</body>

<script>
  function removeItem(element) {
    element.parentElement.remove(); // <li>を削除
  }
</script>
</html>
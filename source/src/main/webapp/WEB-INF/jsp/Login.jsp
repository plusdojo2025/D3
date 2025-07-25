<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>BARLOOP/ログイン</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/login.css' />">


<style>
.overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100vw;
	height: 100vh;
	background: rgba(0, 0, 0, 0.5);
	display: none; /* 初期は非表示 */
	z-index: 999;
}

.popup {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: white;
	padding: 20px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
	z-index: 1000;
	display: none;
}
</style>

</head>
<body>

<header>

<img src="<c:url value='/img/BARLOOP.png' />" alt="BARLOOP" class="icon" width="250">
</header>

<main>
	<h1>ログイン画面</h1>

	<%-- エラーメッセージ表示 --%>
	<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	if (errorMsg != null) {
	%>
	<p style="color: red;"><%=errorMsg%></p>
	<%
	}
	%>



	<%-- 店舗ログインフォーム --%>
	<div id="storeLogin" onclick="popup('store')">
		<h2 class="h2Button">店舗ログイン</h2>
	</div>
	<div id="storeLoginOverlay" class="overlay"></div>

	<div id="storeLoginForm" class="popup">
		<h2>店舗ログイン</h2>
		<button id="closeStoreLogin" type="button">X</button>
		<form action="<%=request.getContextPath()%>/LoginServlet"
			method="post" onsubmit="return validateLoginForm('store')">
			<input type="hidden" id="store_userType" name="userType"
				value="store" /> ID: <input type="text" id="store_email"
				name="email"><br /> パスワード: <input type="password"
				id="store_password" name="password"><br /> <input
				type="submit" value="ログイン">
		</form>
	</div>



	<%-- 顧客ログインフォーム --%>
	<div id="customerLogin" onclick="popup('customer')">
		<h2 class="h2Button">顧客ログイン</h2>
	</div>
	<div id="customerLoginOverlay" class="overlay"></div>

	<div id="customerLoginForm" class="popup">
		<h2>顧客ログイン</h2>
		<button id="closeCustomerLogin" type="button">X</button>
		<form action="<%=request.getContextPath()%>/LoginServlet"
			method="post" onsubmit="return validateLoginForm('customer')">
			<input type="hidden" id="customer_userType" name="userType"
				value="customer" /> メールアドレス: <input type="text" id="customer_email"
				name="email"><br /> パスワード: <input type="password"
				id="customer_password" name="password"><br /> <input
				type="submit" value="ログイン">
		</form>
	</div>



	<%-- 新規登録フォーム --%>
	<div id="regist" onclick="popup('regist')">
		<h2 class="h2Button">新規登録</h2>
	</div>
	<div id="registOverlay" class="overlay"></div>

	<div id="registForm" class="popup">
		<h2>新規登録</h2>
		<button id="closeRegist" type="button">X</button>
		<form action="<%=request.getContextPath()%>/LoginServlet"
			method="post" onsubmit="return validateRegisterForm()">
			<input type="hidden" id="register_userType" name="userType"
				value="register" /> ニックネーム: <input type="text" id="register_name"
				name="name"><br /> メールアドレス: <input type="text"
				id="register_email" name="email"><br /> パスワード: <input
				type="password" id="register_password" name="password"><br />
			生年月日: <input type="date" id="register_birthday" name="birthday"><br />
			<input type="submit" value="登録">
		</form>
	</div>

	<%-- ゲストログイン --%>
	
	<div id="guest">
		<button onclick="guestLogin()">ゲストでログイン</button>
	</div>
	</main>
	
	<footer>
		<div class="copyright">
			<p>&copy;おかゆ</p>
		</div>
	</footer>

	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>

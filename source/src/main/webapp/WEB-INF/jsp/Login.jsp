<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン画面</title>
    <script>
        function validateLoginForm(formType) {
            var email = document.getElementById(formType + "_email").value.trim();
            var password = document.getElementById(formType + "_password").value.trim();

            if (!email || !password) {
                alert("ログインIDとパスワードを入力してください。");
                return false;
            }

            // userType hidden input に値を設定（念のため）
            document.getElementById(formType + "_userType").value = formType;
            return true;
        }

        function validateRegisterForm() {
            var name = document.getElementById("register_name").value.trim();
            var email = document.getElementById("register_email").value.trim();
            var password = document.getElementById("register_password").value.trim();
            var birthday = document.getElementById("register_birthday").value.trim();

            if (!name || !email || !password || !birthday) {
                alert("すべての登録情報を入力してください。");
                return false;
            }

            document.getElementById("register_userType").value = "register";
            return true;
        }

        function guestLogin() {
            if (confirm("20歳以上ですか？")) {
                window.location.href = "/D3/CustomerHomeServlet"; // ゲストの遷移先
            }
        }
    </script>
</head>
<body>
    <h1>ログイン画面</h1>

    <%-- エラーメッセージ表示 --%>
    <%
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (errorMsg != null) {
    %>
        <p style="color:red;"><%= errorMsg %></p>
    <%
        }
    %>

    <%-- 店舗ログインフォーム --%>
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post" onsubmit="return validateLoginForm('store')">
        <h2>店舗ログイン</h2>
        <input type="hidden" id="store_userType" name="userType" value="store" />
        メールアドレス: <input type="text" id="store_email" name="email"><br />
        パスワード: <input type="password" id="store_password" name="password"><br />
        <input type="submit" value="ログイン">
    </form>

    <%-- 顧客ログインフォーム --%>
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post" onsubmit="return validateLoginForm('customer')">
        <h2>顧客ログイン</h2>
        <input type="hidden" id="customer_userType" name="userType" value="customer" />
        メールアドレス: <input type="text" id="customer_email" name="email"><br />
        パスワード: <input type="password" id="customer_password" name="password"><br />
        <input type="submit" value="ログイン">
    </form>

    <%-- 新規登録フォーム --%>
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post" onsubmit="return validateRegisterForm()">
        <h2>新規登録</h2>
        <input type="hidden" id="register_userType" name="userType" value="register" />
        ニックネーム: <input type="text" id="register_name" name="name"><br />
        メールアドレス: <input type="text" id="register_email" name="email"><br />
        パスワード: <input type="password" id="register_password" name="password"><br />
        生年月日: <input type="date" id="register_birthday" name="birthday"><br />
        <input type="submit" value="登録">
    </form>

    <%-- ゲストログイン --%>
    <h2>ゲストログイン</h2>
    <button onclick="guestLogin()">ゲストでログイン</button>
</body>
</html>

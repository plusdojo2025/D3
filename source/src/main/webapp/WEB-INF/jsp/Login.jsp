<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン画面</title>
    <script>
        function validateLoginForm(formType) {
            var email = document.getElementById(formType + "_email").value.trim();
            var password = document.getElementById(formType + "_password").value.trim();
            var errors = [];

            if (!email) errors.push("ログインIDを入力してください。");
            if (!password) errors.push("パスワードを入力してください。");

            if (errors.length > 0) {
                alert(errors.join("\n"));
                return false;
            }

            // 簡易バリデーション後はsubmit
            return true;
        }

        function validateRegisterForm() {
            var name = document.getElementById("customer_name").value.trim();
            var email = document.getElementById("customer_email").value.trim();
            var password = document.getElementById("customer_password").value.trim();
            var birthday = document.getElementById("customer_birthday").value.trim();
            var errors = [];

            if (!name) errors.push("ニックネームを入力してください。");
            if (!email) errors.push("メールアドレスを入力してください。");
            else if (!email.includes("@")) errors.push("無効なメールアドレスです。");

            if (!password) errors.push("パスワードを入力してください。");
            if (!birthday) errors.push("生年月日を入力してください。");
            else {
                var birthDate = new Date(birthday);
                var today = new Date();
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) age--;

                if (age < 20) errors.push("未成年は登録できません");
            }

            if (errors.length > 0) {
                alert(errors.join("\n"));
                return false;
            }

            return true;
        }

        function guestLogin() {
            if (confirm("20歳以上ですか？")) {
                window.location.href = "customerHome.jsp"; // 遷移先を適宜変更
            }
        }
    </script>
</head>
<body>
    <h1>ログイン画面</h1>

    <!-- ログインフォーム（storeユーザー用） -->
    <form action="StoreLoginServlet" method="post" onsubmit="return validateLoginForm('store')">
        <h2>店舗事務 ログイン</h2>
        メールアドレス: <input type="text" id="store_email" name="email" /><br />
        パスワード: <input type="password" id="store_password" name="password" /><br />
        <button type="submit" name="login">ログイン</button>
    </form>

    <!-- ログインフォーム（customerユーザー用） -->
    <form action="CustomerLoginServlet" method="post" onsubmit="return validateLoginForm('customer')">
        <h2>顧客 ログイン</h2>
        メールアドレス: <input type="text" id="customer_email" name="email" /><br />
        パスワード: <input type="password" id="customer_password" name="password" /><br />
        <button type="submit" name="login_2">ログイン</button>
    </form>

    <!-- 新規登録フォーム -->
    <form action="RegisterServlet" method="post" onsubmit="return validateRegisterForm()">
        <h2>新規登録</h2>
        ニックネーム: <input type="text" id="customer_name" name="name" /><br />
        メールアドレス: <input type="text" id="customer_email" name="email" /><br />
        パスワード: <input type="password" id="customer_password" name="password" /><br />
        生年月日: <input type="date" id="customer_birthday" name="birthday" /><br />
        <button type="submit" name="register">登録</button>
    </form>

    <!-- ゲストログイン -->
    <h2>ゲストログイン</h2>
    <button onclick="guestLogin()">ゲストでログイン</button>
</body>
</html>

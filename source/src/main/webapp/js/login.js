
// ポップアップ共通
function popup(type) {
	if (type == "store") {
		storeLoginPopup.classList.add("open");
		storeLoginPopup.style.display = "block";
		storeLoginOverlay.style.display = "block";
	}
	else if (type == "customer") {
		customerLoginPopup.classList.add("open");
		customerLoginPopup.style.display = "block";
		customerLoginOverlay.style.display = "block";
	}
	else if (type = "regist") {
		registPopup.classList.add("open");
		registPopup.style.display = "block";
		registOverlay.style.display = "block";
	}
	else { }
}



// 店舗ログイン用
const storeLoginPopup = document.getElementById("storeLoginForm");
const storeLoginOverlay = document.getElementById("storeLoginOverlay");
const storeLoginClose = document.getElementById("closeStoreLogin");

storeLoginOverlay.addEventListener("click", (event) => {
	if (!storeLoginPopup.contains(event.target)) {
		storeLoginPopup.style.display = "none";
		storeLoginOverlay.style.display = "none";
	}
});

storeLoginClose.addEventListener("click", () => {
	storeLoginPopup.style.display = "none";
	storeLoginOverlay.style.display = "none";
});



// 顧客ログイン用
const customerLoginPopup = document.getElementById("customerLoginForm");
const customerLoginOverlay = document.getElementById("customerLoginOverlay");
const customerLoginClose = document.getElementById("closeCustomerLogin");

customerLoginOverlay.addEventListener("click", (event) => {
	if (!customerLoginPopup.contains(event.target)) {
		customerLoginPopup.style.display = "none";
		customerLoginOverlay.style.display = "none";
	}
});

customerLoginClose.addEventListener("click", () => {
	customerLoginPopup.style.display = "none";
	customerLoginOverlay.style.display = "none";
});



// 新規登録用
const registPopup = document.getElementById("registForm");
const registOverlay = document.getElementById("registOverlay");
const registClose = document.getElementById("closeRegist");

registOverlay.addEventListener("click", (event) => {
	if (!registPopup.contains(event.target)) {
		registPopup.style.display = "none";
		registOverlay.style.display = "none";
	}
});

registClose.addEventListener("click", () => {
	registPopup.style.display = "none";
	registOverlay.style.display = "none";
});



// ログインチェック
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

// 登録チェック
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



// ゲストログイン
function guestLogin() {
	if (!confirm("20歳以上ですか？")) {
		return;
	}

	const form = document.createElement("form");
	form.method = "POST";
	form.action = `${contextPath}/LoginServlet`;

	const input = document.createElement("input");
	input.type = "hidden";
	input.name = "userType";
	input.value = "guest";

	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
}
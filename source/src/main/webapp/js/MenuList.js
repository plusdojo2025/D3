const popup = document.getElementById("popupCommodity");
const btn = document.getElementById("submitOrderButton");

popup.style.display = "none";

const isVisitor = document.body.dataset.visitor === "true";
const isLogin = document.body.dataset.login === "true";



window.addEventListener("DOMContentLoaded", () => {
	if (0 < sessionStorage.length) {
		btn.style.display = "block"
	}
	else {
		btn.style.display = "none"
	}



	const loginElement = document.getElementById("loginPopup");
	const overlay = document.getElementById("overlay");
	const loginForm = document.getElementById("loginForm");
	loginForm.style.display = "none";

	if (!isVisitor) {
		loginElement.style.display = "none";
		overlay.style.display = "none";
		return;
	}

	if (!isLogin) {
		loginElement.style.display = "block";
		overlay.style.display = "block";
	}
	else {
		loginElement.style.display = "none";
		overlay.style.display = "none";
	}
});



function popupCommodity(id, image, name, price) {
	popup.classList.add("open");
	document.getElementById("commodityId").value = id;

	const cImg = document.getElementById("commodityImage");
	const cName = document.getElementById("commodityName");
	const cPrice = document.getElementById("commodityPrice");

	cImg.innerHTML = "";
	cName.innerHTML = "";
	cPrice.innerHTML = "";

	cImg.src = `${contextPath}/img/${image}`;
	cName.textContent = `${name}`;
	cPrice.textContent = `${price}`;

	popup.style.display = "block";

	if (!isVisitor) {
		const visitorElements = document.getElementsByClassName("visitor");
		for (let i = 0; i < visitorElements.length; i++) {
			visitorElements[i].style.display = "none";
		}
		return;
	}

	var nowQuantity = window.sessionStorage.getItem([id]);
	if (nowQuantity != null) {
		nowQuantity = parseInt(nowQuantity);
		document.getElementById("quantity").value = nowQuantity;
	}
	else {
		document.getElementById("quantity").value = 0;
	}
}

function addCart() {
	const id = document.getElementById("commodityId").value;
	var quantity = document.getElementById("quantity").value;
	quantity = parseInt(quantity);

	window.sessionStorage.setItem([id], [quantity]);

	if (quantity < 1) {
		sessionStorage.removeItem([id]);
	}

	closePopup();
}

function closePopup() {
	popup.style.display = "none";

	if (0 < sessionStorage.length) {
		btn.style.display = "block";
	}
	else {
		btn.style.display = "none";
	}
}

function submitOrder() {
	if (sessionStorage.length < 1)
		return;

	const cartInputs = document.getElementById("cartInputs");
	cartInputs.innerHTML = "";

	for (let i = 0; i < sessionStorage.length; i++) {
		const key = sessionStorage.key(i);
		const value = sessionStorage.getItem(key);

		if (0 < parseInt(value)) {
			const input = document.createElement("input");
			input.type = "hidden";
			input.name = "cart_" + key;
			input.value = value;
			cartInputs.appendChild(input);
		}
	}

	document.getElementById("orderForm").submit();
}



function showLoginForm() {
	document.getElementById("overlay").style.display = "block";
	document.getElementById("loginChoice").style.display = "none";
	document.getElementById("loginForm").style.display = "block";
}
function backLoginChoice() {
	document.getElementById("loginChoice").style.display = "block";
	document.getElementById("loginForm").style.display = "none";
}

function postAndRedirectMenuAccess() {
	const form = document.createElement("form");
	form.method = "POST";
	form.action = `${contextPath}/MenuAccessServlet`;

	const input = document.createElement("input");
	input.type = "hidden";
	input.name = "userType";
	input.value = "guest";

	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
}
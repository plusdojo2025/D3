const popup = document.getElementById("popupCommodity");
const btn = document.getElementById("submitOrderButton");

popup.style.display = "none";
const isGuest = document.body.dataset.guest === "true";


function popupCommodity(id, image, name, price) {
	popup.classList.add("open");
	document.getElementById("commodityId").value = id;

	const cImg = document.getElementById("commodityImage");
	const cName = document.getElementById("commodityName");
	const cPrice = document.getElementById("commodityPrice");

	cImg.innerHTML = "";
	cName.innerHTML = "";
	cPrice.innerHTML = "";

	cImg.textContent = `${image}`;
	cName.textContent = `${name}`;
	cPrice.textContent = `${price}`;

	popup.style.display = "block";

	if (isGuest) {
		const guestElements = document.getElementsByClassName("guestLogin");
		for (let i = 0; i < guestElements.length; i++) {
			guestElements[i].style.display = "none";
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

window.addEventListener("DOMContentLoaded", () => {
	if (0 < sessionStorage.length) {
		btn.style.display = "block"
	}
	else {
		btn.style.display = "none"
	}
});